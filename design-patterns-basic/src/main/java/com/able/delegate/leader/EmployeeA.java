package com.able.delegate.leader;

public class EmployeeA implements IEmployee {

    @Override
    public void doSomething(String command) {
        System.out.println("员工A, 开始处理" + command + "工作");
    }

}
