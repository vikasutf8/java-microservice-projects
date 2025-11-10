package com.SpringBoot.RestfullApi;

import com.SpringBoot.RestfullApi.Configuration.ProxyConfig;
import com.SpringBoot.RestfullApi.Proxy.Man;
import com.SpringBoot.RestfullApi.Proxy.Person;
import com.SpringBoot.RestfullApi.Proxy.Woman;
import net.sf.cglib.proxy.Enhancer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Proxy;

@SpringBootTest
public class ProxyHandlerTest {
    @Test
    void testDynamicProxy() {
        Person man = new Man();
//        Interface-based proxy
        Person proxyPerson = (Person) Proxy.newProxyInstance(
                Person.class.getClassLoader(),
                new Class[]{Person.class},
                new ProxyConfig(man)
        );

        proxyPerson.sayHello("MAN");
    }
//    Class-based proxy (subclass)
    @Test
    void testCglibProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Woman.class);
        enhancer.setCallback(new ProxyConfig.CglibInterceptor());

        Woman proxyWoman = (Woman) enhancer.create();
        proxyWoman.sayHello();
    }
}
