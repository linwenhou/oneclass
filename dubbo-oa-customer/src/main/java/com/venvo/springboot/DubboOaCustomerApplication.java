package com.venvo.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ServletComponentScan(basePackages = "com.venvo.springboot.listener")
public class DubboOaCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboOaCustomerApplication.class, args);
    }

}
