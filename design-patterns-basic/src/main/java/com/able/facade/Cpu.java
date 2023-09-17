package com.able.facade;

public class Cpu implements Computer {

    @Override
    public void open() {
        System.out.println("start cpu");
    }

    @Override
    public void close() {
        System.out.println("close cpu");
    }
    
}
