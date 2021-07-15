package com.venvo.springbootmvcoaproject.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.venvo.springbootmvcoaproject.entity.Account;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.stereotype.Component;

/**
 * @author venvo
 * @date 2021-07-14 19:38
 * @description
 * @modified By
 * @version: jdk1.8
 */
@Component
@WebFilter(urlPatterns = "/*")
public class AccountFilter implements Filter {

    private final String[] IGNORE_RESOURCE ={"/index","/account/validataAccount","/css/","/js/","/account/login","/images"};


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Account account = (Account) request.getSession().getAttribute("account");
        String uri = request.getRequestURI();

        // 当前访问的URI是不是 在Ignore列表里
        System.out.println("uri:" + uri);
        boolean pass = canPassIgnore(uri);
        if (pass) {
            // 在的话 放行
            filterChain.doFilter(request, response);
            return;
        }


        if (account == null) {
            response.sendRedirect("/account/login");
            return;
        }

        String servletPath = request.getServletPath();


        System.out.println("account===" + account);

        System.out.println("doFilter filterChain");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean canPassIgnore(String uri) {
        for (String url : IGNORE_RESOURCE) {

            final boolean b = uri.startsWith(url);
            if (b) {
                return true;
            }
        }
        return false;
    }
}
