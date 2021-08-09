package com.venvo.springboot.service;


import com.github.pagehelper.PageInfo;
import com.venvo.springboot.common.RespStat;
import com.venvo.springboot.dto.AccountDTO;
import com.venvo.springboot.entity.Account;

/**
 * @author venvo
 * @date 2021-07-14 17:17
 * @description
 * @modified By
 * @version: jdk1.8
 */
public interface IAccountService {
    RespStat validataAccount(AccountDTO accountDTO);

    PageInfo<Account> findAll(int pageNum, int size);

    RespStat deleteById(Integer id);

    RespStat isExistLoginName(String loginName);

    int registerss(Account account);

    int update(Account account);

    PageInfo<Account> getPermisssion(int pageNum, int size);
}
