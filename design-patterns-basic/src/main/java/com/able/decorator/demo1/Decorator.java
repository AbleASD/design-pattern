package com.able.decorator.demo1;

/**
 * 抽象装饰器，一般为抽象类
 */
public abstract class Decorator implements Component {
    
    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void method() {
        this.component.method();
    }
}
