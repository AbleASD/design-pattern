package com.able.proxy.statics;


public class Test {
    public static void main(String[] args) {
        UserDaoProxy proxy = new UserDaoProxy();
        proxy.save();
    }
}
