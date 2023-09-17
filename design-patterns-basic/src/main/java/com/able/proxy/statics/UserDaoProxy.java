package com.able.proxy.statics;

import com.able.proxy.common.IUserDao;
import com.able.proxy.common.UserDao;

public class UserDaoProxy implements IUserDao {

    private UserDao userDao;

    public UserDaoProxy() {
        this.userDao = new UserDao();
    }

    @Override
    public void save() {
        System.out.println("start");
        userDao.save();
        System.out.println("end");
    }
    
}
