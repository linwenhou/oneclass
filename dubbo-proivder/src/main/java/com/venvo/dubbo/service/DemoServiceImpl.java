package com.venvo.dubbo.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @author venvo
 * @date 2021-08-07 17:47
 * @description
 * @modified By
 * @version: jdk1.8
 */
@Component
@Service(version = "1.0", timeout = 10000, interfaceClass = DemoService.class)
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        System.out.println(name);
        return "hi" + name;
    }
}
