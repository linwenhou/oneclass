package com.venvo.springboot.controller;

import com.venvo.springboot.config.WxConfig;
import com.venvo.springboot.filter.WxAuthFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import weixin.popular.api.SnsAPI;
import weixin.popular.bean.sns.SnsToken;
import weixin.popular.bean.user.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author venvo
 * @date 2021-08-10 14:04
 * @description
 * @modified By
 * @version: jdk1.8
 */
@Controller
@RequestMapping("/auth")
public class WxAuthController {
    private static final Logger logger = LoggerFactory.getLogger(WxAuthFilter.class);
    @Autowired
    WxConfig wxConfig;

    @RequestMapping("")
    public String list(@RequestParam Map<String, String> param, HttpServletRequest request) {
        //Code
        String code = param.get("code");

        //获取User对象
        SnsToken stoken = SnsAPI.oauth2AccessToken(wxConfig.getAppID(), wxConfig.getAppsecret(), code);
        User user = SnsAPI.userinfo(stoken.getAccess_token(), wxConfig.getAppID(), "zh_CN");
        logger.info("user:" + user);
        // User写入Session
        logger.info("headimgurl:" + user.getHeadimgurl());

        request.getSession().setAttribute("user", user);

        logger.info("param.get(\"uri\");" + param.get("uri"));
        // 访问受限的那个URI
        return "redirect:" + param.get("uri");
    }

}
