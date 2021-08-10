package com.venvo.springboot.filter;

import com.venvo.springboot.config.WxConfig;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import weixin.popular.bean.user.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author venvo
 * @date 2021-08-10 13:49
 * @description
 * @modified By
 * @version: jdk1.8
 */
@WebFilter(filterName = "WxAuthFilter", urlPatterns = "/profile/*")
public class WxAuthFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(WxAuthFilter.class);
    @Autowired
    WxConfig wxConf;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("WxAuthFilter Init...");

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        // 判断用户 登录状态 检查session 里有没有 User对象
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // 框架 封装
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            //如果没有
            // 获取 用户的 openid 跳转登录 ，去数据库比对
            String uri = request.getRequestURI();
            logger.info("uri"+uri);
            String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wxConf.getAppID() +
                    "&redirect_uri=http://venvo.nat300.top/auth?uri="
                    + uri + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
            response.sendRedirect(url);
        } else {
            logger.info("user:" + ToStringBuilder.reflectionToString(user));
        }
        //如果没有 写数据库


        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        logger.info("WxAuthFilter destroy...");

    }
}
