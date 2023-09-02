package com.able.proxy.jdk3;

public class TianBoGuang implements Person {

    @Override
    public void eat() {
        System.out.println("Meat and vegetables!");
    }

    @Override
    public void think() {
        System.out.println("Little Master Yilin");
    }

    @Override
    public void buy() {
        System.out.println("wine!");
    }

    @Override
    public void findJob() {
        System.out.println("bodyguard, 20 - 30k/month");
    }
    
}
