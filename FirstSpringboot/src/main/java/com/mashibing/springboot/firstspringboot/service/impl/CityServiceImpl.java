package com.mashibing.springboot.firstspringboot.service.impl;

import com.mashibing.springboot.firstspringboot.dao.CityRepository;
import com.mashibing.springboot.firstspringboot.domain.City;
import com.mashibing.springboot.firstspringboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author venvo
 * @date 2021-07-06 12:31
 * @description
 * @modified By
 * @version: jdk1.8
 */
@Service
public class CityServiceImpl implements CityService {

    Map map = new ConcurrentHashMap<String, City>();

    @Autowired
    CityRepository cityRepository;

    private City findCityByid(Integer id) {
        if (id == null) {
            return null;
        }
        return (City) map.get(id);
    }


    @Override
    public String addAll(Integer id, String name) {
        final City cityByid = findCityByid(id);
        if (cityByid != null) {
            return "数据已存在";
        } else {
            City city = new City();
            city.setId(id);
            city.setName(name);
            map.put(id, city);
            return "数据保存成功";
        }
    }

//    @Override
//    public List<City> findAll() {
//        return (List<City>) map.values().stream().collect(Collectors.toList());
//
//    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();

    }

    @Override
    public String deleteById(Integer id) {
        if (id == null) {
            return null;
        }
        map.remove(id);
        return null;
    }

    @Override
    public City findOne(Integer id) {
        return cityRepository.getOne(id);
    }
}
