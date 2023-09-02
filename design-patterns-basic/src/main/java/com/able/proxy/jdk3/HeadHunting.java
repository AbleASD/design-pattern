package com.able.proxy.jdk3;

import java.lang.reflect.Method;

public class HeadHunting implements MyInvocationHandler {

    private Person target;

    public Object getInstance(Person target) {
        this.target = target;
        Class<? extends Person> clazz = target.getClass();
        // TODO
        return MyProxy.newProxyInstance(new MyClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("I'm headHunding, find a job for you!");
        Object returnValue = method.invoke(target, args);
        return returnValue;
    }
    
}
