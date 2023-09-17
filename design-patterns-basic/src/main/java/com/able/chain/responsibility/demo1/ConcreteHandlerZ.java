package com.able.chain.responsibility.demo1;

public class ConcreteHandlerZ extends AbstarctHandler {

    @Override
    public void handleRequest(String condition) {
        System.out.println("ConcreteHandlerZ 处理");
    }
    
}
