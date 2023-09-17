package com.able.visitor;

public class ElementC implements Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
