package com.mashibing.springboot.firstspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author venvo
 * @date 2021-07-05 14:27
 * @description
 * @modified By
 * @version: jdk1.8
 */
@Controller
@RequestMapping("/user")
public class MainController {

    @RequestMapping("/list")
    public String getName(ModelMap model) {
        model.addAttribute("name", "00000");
        return "list";
    }
}
