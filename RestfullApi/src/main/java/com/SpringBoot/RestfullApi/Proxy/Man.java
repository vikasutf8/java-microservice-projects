package com.SpringBoot.RestfullApi.Proxy;

public class Man implements Person {

    @Override
    public String sayHello(String str) {
        return "hello from"+str;
    }
}
