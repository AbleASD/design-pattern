package com.able.adapter.intfs;

public class Adapter extends AbstractAdapter {

    @Override
    public void targetMethod2() {
        System.out.println("子类必须实现");
    }

    @Override
    public void targetMethod3() {
        System.out.println("子类重写");
    }
    
}
