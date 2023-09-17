package com.able.visitor;

public class Test {
    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();
        ConcreteVisitor visitor = new ConcreteVisitor();
        objectStructure.accept(visitor);
        
    }
}
