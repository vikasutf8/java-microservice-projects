package com.SpringBoot.RestfullApi.Configuration;

import com.SpringBoot.RestfullApi.Proxy.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

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
}
