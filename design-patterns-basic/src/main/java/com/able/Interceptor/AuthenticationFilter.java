package com.able.Interceptor;

/**
 * 身份验证过滤器
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void execute(String request) {
        System.out.println("Authentication request: " + request);
    }
    
}
