package com.venvo.springbootmvcfour.service;

import com.venvo.springbootmvcfour.entity.Account;

import java.util.List;

/**
 * @author venvo
 * @date 2021-07-11 18:06
 * @description
 * @modified By
 * @version: jdk1.8
 */
public interface AccountService {
    List<Account> findAll(int pageNum,int size);
}
