package com.able.visitor;

public class ConcreteVisitor implements Visitor{

    @Override
    public void visit(ElementA elementA) {
        System.out.println("visit " + elementA);
    }

    @Override
    public void visit(ElementB elementB) {
        System.out.println("visit " + elementB);
    }

    @Override
    public void visit(ElementC elementC) {
        System.out.println("visit " + elementC);
    }
    
}
