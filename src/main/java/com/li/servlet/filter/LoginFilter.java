package com.li.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter("/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");


        String[] urls = {"index.html", "login.html", "register.html", "css", "js", "user"};

        String url = request.getRequestURL().toString();

        for (String u : urls) {
            if (url.contains(u)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        Object user = request.getSession().getAttribute("user");

        if( user != null ) {
            filterChain.doFilter(request, response);
        } else {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/login.html");
        }
    }

    public void destroy() {

    }
}
