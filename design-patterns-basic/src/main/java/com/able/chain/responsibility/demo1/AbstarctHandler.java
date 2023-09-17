package com.able.chain.responsibility.demo1;

public abstract class AbstarctHandler {
    private AbstarctHandler nextHandler;

    public abstract void handleRequest(String condition);

    public AbstarctHandler getNextHandler() {
        return this.nextHandler;
    }

    public void setNextHandler(AbstarctHandler nexHandler) {
        this.nextHandler = nexHandler;
    }

}
