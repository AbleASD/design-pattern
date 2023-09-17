package com.able.facade;

public class Test {
    public static void main(String[] args) {
        Facade facade = new Facade();
        
        facade.openCPU();
        facade.openDDR();
        facade.closeSSD();

        facade.closeCPU();
        facade.closeDDR();
        facade.closeSSD();
    }
}
