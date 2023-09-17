package com.able.visitor;

import java.util.ArrayList;
import java.util.List;

public class ObjectStructure {
    private List<Element> elements = new ArrayList<>();
    
    public ObjectStructure() {
        elements.add(new ElementA());
        elements.add(new ElementB());
        elements.add(new ElementC());
    }

    public void accept(Visitor visitor) {
        for (Element element : elements) {
            element.accept(visitor);
        }
    }
}
