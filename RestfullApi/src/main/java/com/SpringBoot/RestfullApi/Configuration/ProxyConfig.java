package com.SpringBoot.RestfullApi.Configuration;

import com.SpringBoot.RestfullApi.Proxy.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


public class ProxyConfig implements InvocationHandler {

    private final Person targe;

    public ProxyConfig(Person targe) {
        this.targe = targe;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("[JDK Proxy] Before method: " + method.getName());
        Object result = method.invoke(targe, args);
        System.out.println("[JDK Proxy] After method: " + method.getName());
        return result;
    }

    public static class CglibInterceptor implements MethodInterceptor {
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println("[CGLIB Proxy] Before method: " + method.getName());
            Object result = proxy.invokeSuper(obj, args);
            System.out.println("[CGLIB Proxy] After method: " + method.getName());
            return result;
        }
    }
}
