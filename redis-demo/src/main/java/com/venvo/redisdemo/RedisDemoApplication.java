package com.venvo.redisdemo;

import com.venvo.redisdemo.demo.TestRedis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RedisDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RedisDemoApplication.class, args);
        final TestRedis bean = run.getBean(TestRedis.class);
        bean.addString();
    }

}
