package com.able.delegate.leader;

public class EmployeeB implements IEmployee {

    @Override
    public void doSomething(String command) {
        System.out.println("员工B, 开始处理" + command + "工作");
    }

}
