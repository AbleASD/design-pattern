package com.able.ioc;

import java.io.IOException;

import com.able.ioc.beans.UserService;

public class Test {

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        MyApplicationContext context = new MyApplicationContext();

        UserService userService = (UserService) context.getBean("userService");
        UserService userService2 = (UserService) context.getBean("userService");
        System.out.println(userService);
        System.out.println(userService2);

        userService.findUserByName("张三");
    }
}
