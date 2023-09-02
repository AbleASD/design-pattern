package com.able.proxy.cglib;

import com.able.proxy.common.UserDao;

public class Test {
    public static void main(String[] args) {
        UserDao target = new UserDao();

        UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();

        proxy.save();
    }
}
