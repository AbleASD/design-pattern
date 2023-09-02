package com.able.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ProxyFactory implements MethodInterceptor{

    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
        System.out.println("Cglib start----------------");
        System.out.println("obj = " + arg0.getClass());
        System.out.println("method = " + arg1);
        System.out.println("object[] = " + arg2);
        System.out.println("proxy = " + arg3);
        
        Object returnValue = arg1.invoke(target, arg2);
        System.out.println("Cglib end------------------");
        return returnValue;
    }
    
}
