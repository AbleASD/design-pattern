package com.able.delegate.leader;

import java.util.HashMap;
import java.util.Map;

public class Leader {
    private Map<String, IEmployee> register = new HashMap<String, IEmployee>();

    public Leader() {
        register.put("加密", new EmployeeA());
        register.put("登录", new EmployeeB());
    }

    void doSomething(String command) {
        register.get(command).doSomething(command);
    }
}
