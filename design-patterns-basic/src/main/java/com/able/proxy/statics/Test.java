package com.able.proxy.statics;

import com.able.proxy.common.UserDao;

public class Test {
    public static void main(String[] args) {
        UserDaoProxy proxy = new UserDaoProxy(new UserDao());
        proxy.save();
    }
}
