package com.able.facade;

/**
 * 硬盘
 */
public class Ssd implements Computer {

    @Override
    public void open() {
        System.out.println("start ssd");
    }

    @Override
    public void close() {
        System.out.println("close ssd");
    }
    
}
