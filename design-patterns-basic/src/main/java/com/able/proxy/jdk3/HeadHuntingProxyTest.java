package com.able.proxy.jdk3;

public class HeadHuntingProxyTest {
    public static void main(String[] args) {
        Person obj = (Person) new HeadHunting().getInstance(new TianBoGuang());
        System.out.println(obj.getClass());
        obj.findJob();
    }
}
