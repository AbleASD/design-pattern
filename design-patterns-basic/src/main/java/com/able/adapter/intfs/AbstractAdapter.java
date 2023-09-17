package com.able.adapter.intfs;

public abstract class AbstractAdapter implements Target {

    @Override
    public void targetMethod1() {
        System.out.println("默认实现1");
        
    }

    @Override
    public abstract void targetMethod2();

    @Override
    public void targetMethod3() {
        System.out.println("默认实现3");
    }
    
}
