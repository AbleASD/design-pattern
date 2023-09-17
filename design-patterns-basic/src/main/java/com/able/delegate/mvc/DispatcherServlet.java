package com.able.delegate.mvc;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.able.delegate.mvc.controllers.MemberAction;

/**
 * 模拟SpringMVC的核心类DispatcherServlet来进行说明委派模式的作用
 */
public class DispatcherServlet {
    private List<Handler> handlerMapping = new ArrayList<>();

    public DispatcherServlet() {
        Class<?> memberActionClass = MemberAction.class;
        try {
            handlerMapping.add(new Handler()
                    .setController(memberActionClass.getDeclaredConstructor().newInstance())
                    .setMethod(memberActionClass.getMethod("getMemberById", new Class[] { String.class }))
                    .setUrl("/web/getMemberById.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void doService(HttpServletRequest request, HttpServletResponse response) {
        doDispatch(request, response);
    }

    private void doDispatch(HttpServletRequest request, HttpServletResponse response) {
        String url = request.getRequestURI();

        Handler handler = null;
        for (Handler h: handlerMapping) {
            if (url.equals(h.getUrl())) {
                handler = h;
                break;
            }
        }

        Object object = null;
        try {
            object = handler.getMethod().invoke(handler.getController(), request.getParameter("mid"));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
