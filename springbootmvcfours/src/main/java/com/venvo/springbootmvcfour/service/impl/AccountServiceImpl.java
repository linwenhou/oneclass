package com.venvo.springbootmvcfour.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.venvo.springbootmvcfour.dao.AccountMapper;
import com.venvo.springbootmvcfour.entity.Account;
import com.venvo.springbootmvcfour.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author venvo
 * @date 2021-07-11 18:07
 * @description
 * @modified By
 * @version: jdk1.8
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;

    @Override
    public List<Account> findAll(int pageNum, int size) {
        Page<Account> pageHelper = PageHelper.startPage(pageNum,size);
        List<Account> list = accountMapper.findAll();
        return list;
    }
}
