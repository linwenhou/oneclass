package com.venvo.springbootmvcoaproject.mapper;

import com.venvo.springbootmvcoaproject.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AccountDAO继承基类
 */
@Repository
public interface AccountMapper  extends MyBatisBaseDao<Account, Integer, AccountExample>{
    List<Account> getRight();
}