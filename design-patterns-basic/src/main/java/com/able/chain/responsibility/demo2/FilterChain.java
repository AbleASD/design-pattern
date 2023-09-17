package com.able.chain.responsibility.demo2;

import java.util.List;

import com.able.chain.responsibility.demo2.domain.Request;
import com.able.chain.responsibility.demo2.domain.Response;

public class FilterChain implements Filter {
    private List<Filter> filters;
    private int index = 0;

    public FilterChain addFilter(Filter filter) {
        filters.add(filter);
        return this;
    }

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        if (index == filters.size()) return;

        Filter f = filters.get(index);
        index++;
        f.doFilter(request, response, filterChain);

    }
    
}
