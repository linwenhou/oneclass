package com.venvo.redisdemo.demo;

import java.io.Serializable;

/**
 * @author venvo
 * @date 2021-08-25 16:47
 * @description
 * @modified By
 * @version: jdk1.8
 */
public class Person implements Serializable {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
