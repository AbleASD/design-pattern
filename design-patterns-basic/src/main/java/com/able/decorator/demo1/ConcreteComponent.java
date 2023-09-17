package com.able.decorator.demo1;

/**
 * 具体构建
 */
public class ConcreteComponent implements Component {

    @Override
    public void method() {
        System.out.println("被装饰类实现的方法");
    }
    
}
