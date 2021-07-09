package com.mashibing.springboot.firstspringboot.domain;


import javax.persistence.*;


/**
 * @author venvo
 * @date 2021-07-05 15:45
 * @description
 * @modified By
 * @version: jdk1.8
 */
@Table(name = "city")
@Entity
public class City {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
