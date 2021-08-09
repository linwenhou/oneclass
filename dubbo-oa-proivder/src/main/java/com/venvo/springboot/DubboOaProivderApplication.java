package com.venvo.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.venvo.springboot.mapper")
public class DubboOaProivderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboOaProivderApplication.class, args);
    }

}
