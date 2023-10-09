package com.able.aop;

import com.able.aop.log.LoggingAspect;
import com.able.aop.log.UserService;
import com.able.aop.log.UserServiceImpl;

public class Test {
    public static void main(String[] args) {
        UserService user = new UserServiceImpl(new LoggingAspect());
        user.login("admin", "admin");
    }
}
