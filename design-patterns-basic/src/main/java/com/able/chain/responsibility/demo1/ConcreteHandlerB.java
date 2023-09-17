package com.able.chain.responsibility.demo1;

public class ConcreteHandlerB extends AbstarctHandler {
    @Override
    public void handleRequest(String condition) {
        if (condition.equals("A")) {
            System.out.println("ConcreteHandlerB 处理");
        } else {
            System.out.println("ConcreteHandlerB 不处理，交由其他 Handler 处理");
            super.getNextHandler().handleRequest(condition);
        }
    }
}
