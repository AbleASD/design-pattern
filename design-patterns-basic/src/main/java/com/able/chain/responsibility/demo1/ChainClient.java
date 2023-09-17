package com.able.chain.responsibility.demo1;

public class ChainClient {
    public static void main(String[] args) {
        AbstarctHandler concreteHandlerA = new ConcreteHandlerA();
        AbstarctHandler concreteHandlerB = new ConcreteHandlerB();
        AbstarctHandler concreteHandlerZ = new ConcreteHandlerZ();

        concreteHandlerA.setNextHandler(concreteHandlerB);
        concreteHandlerB.setNextHandler(concreteHandlerZ);

        concreteHandlerA.handleRequest("Z");
    }
}
