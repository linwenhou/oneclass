package com.venvo.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author venvo
 * @date 2021-08-10 14:17
 * @description
 * @modified By
 * @version: jdk1.8
 */
@Controller
@RequestMapping("/profile")
public class WxProfileController {
    @RequestMapping("/my")
    public String my(Model model) {
        return "profile/my";
    }
}
