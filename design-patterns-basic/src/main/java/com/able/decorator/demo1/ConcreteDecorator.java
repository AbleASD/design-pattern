package com.able.decorator.demo1;

public class ConcreteDecorator extends Decorator {
    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void method() {
        System.out.println("before decotator");
        super.method();
        System.out.println("after decotator");
    }
}
