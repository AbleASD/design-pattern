package com.able.chain.responsibility.demo2;

import com.able.chain.responsibility.demo2.domain.Request;
import com.able.chain.responsibility.demo2.domain.Response;

public interface Filter {
    void doFilter(Request request, Response response, FilterChain filterChain);
}
