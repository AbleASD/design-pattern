package com.able.adapter.object;

import com.able.adapter.common.Adaptee;
import com.able.adapter.common.Target;

public class ObjectAdapter implements Target {
    private Adaptee adaptee = new Adaptee();

    @Override
    public void request() {
        adaptee.specialRequest();
    }
    
}
