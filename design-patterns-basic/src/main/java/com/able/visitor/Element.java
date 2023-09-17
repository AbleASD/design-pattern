package com.able.visitor;

public interface Element {
    void accept(Visitor visitor);
}
