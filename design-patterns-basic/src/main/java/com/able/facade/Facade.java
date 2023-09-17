package com.able.facade;

public class Facade {
    private Cpu cpu;
    private Ssd ssd;
    private Ddr ddr;

    public Facade() {
        cpu = new Cpu();
        ssd = new Ssd();
        ddr = new Ddr();
    }

    public void openCPU() {
        cpu.open();
    }

    public void openDDR() {
        ddr.open();
    }

    public void openSSD() {
        ssd.open();
    }

    public void closeCPU() {
        cpu.close();
    }

    public void closeDDR() {
        ddr.close();
    }

    public void closeSSD() {
        ssd.close();
    }
}
