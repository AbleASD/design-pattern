package com.able.adapter.clazz;

import com.able.adapter.common.Adaptee;
import com.able.adapter.common.Target;

public class ClassAdapter extends Adaptee implements Target  {

    @Override
    public void request() {
        specialRequest();
    }
    
}
