package com.able.delegate.mvc;

import java.lang.reflect.Method;

public class Handler {
    private Object controller;
    private Method method;
    private String url;
    public Object getController() {
        return controller;
    }
    public Handler setController(Object controller) {
        this.controller = controller;
        return this;
    }
    public Method getMethod() {
        return method;
    }
    public Handler setMethod(Method method) {
        this.method = method;
        return this;
    }
    public String getUrl() {
        return url;
    }
    public Handler setUrl(String url) {
        this.url = url;
        return this;
    }

    
}
