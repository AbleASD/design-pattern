package com.able.proxy.jdk2;

public class Test {
    public static void main(String[] args) throws Exception {
        Manager manager = (Manager) Proxy.newProxyInstance();
        manager.doSomething();
    }
}
