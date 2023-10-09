package com.able.aop.log;

public class UserServiceImpl implements UserService {
    LoggingAspect loggingAspect;

    public UserServiceImpl(LoggingAspect loggingAspect) {
        this.loggingAspect = loggingAspect;
    }

    @MyAnnotation("hahaha")
    public void login(String userName, String password) {
        System.out.println("login: " + userName + " " + password);
        int i = 1 / 0;
    }
}
