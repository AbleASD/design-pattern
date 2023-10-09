package com.able.ioc.beans;

import com.able.ioc.annotation.MyComponent;

@MyComponent
public class UserDao {
    public void findUserByName(String userName) {
        System.out.println("find a user named " + userName);
    }
}
