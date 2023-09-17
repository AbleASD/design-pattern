package com.able.facade;

/**
 * 内存
 */
public class Ddr implements Computer {

    @Override
    public void open() {
        System.out.println("start ddr");
    }

    @Override
    public void close() {
        System.out.println("close ddr");
    }
    
}
