package com.able.adapter.intfs;

public class InterfaceAdapterTest {
    public static void main(String[] args) {
        Target adapter = new Adapter();

        adapter.targetMethod1();
        adapter.targetMethod2();
        adapter.targetMethod3();
    }
}
