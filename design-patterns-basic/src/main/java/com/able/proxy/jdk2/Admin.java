package com.able.proxy.jdk2;

public class Admin implements Manager {

    @Override
    public void doSomething() {
        System.out.println("Admin do something.");
    }
    
}
