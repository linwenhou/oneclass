package com.mashibing.springboot.firstspringboot.dao;

import com.mashibing.springboot.firstspringboot.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author venvo
 * @date 2021-07-09 09:55
 * @description
 * @modified By
 * @version: jdk1.8
 */
public interface CityRepository extends JpaRepository<City, Integer> {
}
