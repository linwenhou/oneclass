package com.venvo.springboot;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ServletComponentScan(basePackages = {"com.venvo.springboot.listener", "com.venvo.springboot.filter"})
@EnableSwagger2Doc
public class OaCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OaCustomerApplication.class, args);
    }

}
