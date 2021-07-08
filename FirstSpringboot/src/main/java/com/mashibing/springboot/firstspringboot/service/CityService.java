package com.mashibing.springboot.firstspringboot.service;

import com.mashibing.springboot.firstspringboot.domain.City;

import java.util.List;

/**
 * @author venvo
 * @date 2021-07-05 15:47
 * @description
 * @modified By
 * @version: jdk1.8
 */
public interface CityService {
    String addAll(Integer id, String name);

    List<City> findAll();

    String deleteById(Integer id);
}
