package com.venvo.dubbo.controller;

import com.venvo.dubbo.service.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author venvo
 * @date 2021-08-07 17:59
 * @description
 * @modified By
 * @version: jdk1.8
 */
@RestController
@Controller
public class DemoCustomerController {

    @Reference(version = "1.0")
    DemoService demoService;

    @RequestMapping("/hello")
    public String sayHello() {
        return demoService.sayHello("lisi");
    }
}
