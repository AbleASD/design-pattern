package com.able.proxy.jdk2;

public class AdminProxy implements Manager{

    private Admin admin;

    public AdminProxy(Admin admin) {
        super();
        this.admin = admin;
    }

    @Override
    public void doSomething() {
        System.out.println("Log:admin操作开始");
        admin.doSomething();
        System.out.println("Log:admin操作结束");
    }
    
}
