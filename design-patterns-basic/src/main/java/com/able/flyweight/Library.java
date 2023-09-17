package com.able.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂
 */
public class Library {
    private Map<String, Book> bookPools = new HashMap<>();
    private static Library factory = new Library();
    public static final Library getInstance() {
        return Library.factory;
    }

    private Library(){};

    public Library readResolve() {
        return Library.factory;
    }

    public Book libToBorrow(String bootName){
        Book order = null;
        if (bookPools.containsKey(bootName)) {
            order = bookPools.get(bootName);
        } else {
            order = new ConcreteBook(bootName);
            bookPools.put(bootName, order);
        }
        return order;
    }

    public int getAllBookSize() {
        return bookPools.size();
    }

}
