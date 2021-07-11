package com.venvo.springbootmvcfour.dao;

import com.venvo.springbootmvcfour.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author venvo
 * @date 2021-07-11 18:08
 * @description
 * @modified By
 * @version: jdk1.8
 */
@Mapper
public interface AccountMapper {
//    @Select("select * from account")
    List<Account> findAll();
}
