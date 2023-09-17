package com.able.visitor;

public class ElementB implements Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
