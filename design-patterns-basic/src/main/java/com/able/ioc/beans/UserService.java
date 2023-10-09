package com.able.ioc.beans;

import com.able.ioc.annotation.MyAutowired;
import com.able.ioc.annotation.MyComponent;

@MyComponent
public class UserService {
    @MyAutowired
    private UserDao userDao;

    public void findUserByName(String userName) {
        userDao.findUserByName(userName);
    }
}
