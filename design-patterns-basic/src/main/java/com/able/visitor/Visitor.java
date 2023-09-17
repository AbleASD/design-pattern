package com.able.visitor;

public interface Visitor {
    void visit(ElementA elementA);
    void visit(ElementB elementB);
    void visit(ElementC elementC);
}
