package com.able.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new DynamicProxyHandler(target));
    }

    private class DynamicProxyHandler implements InvocationHandler {
        private Object target;

        public DynamicProxyHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            System.out.println("start proxy----------------------");
            System.out.println("method = " + method);
            System.out.println("args = " + args);
            Object returnObject = method.invoke(target, args);
            System.out.println("end proxy------------------------");
            return returnObject;
        }

    }
}
