package com.venvo.springbootmvcoaproject.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.venvo.springbootmvcoaproject.common.RespStat;
import com.venvo.springbootmvcoaproject.dto.AccountDTO;
import com.venvo.springbootmvcoaproject.entity.Account;
import com.venvo.springbootmvcoaproject.mapper.AccountExample;
import com.venvo.springbootmvcoaproject.mapper.AccountMapper;
import com.venvo.springbootmvcoaproject.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author venvo
 * @date 2021-07-14 17:18
 * @description
 * @modified By
 * @version: jdk1.8
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;

    @Override
    public RespStat validataAccount(AccountDTO accountDTO) {

        AccountExample accountExample = new AccountExample();
        AccountExample.Criteria criteria = accountExample.createCriteria();
        criteria.andLoginNameEqualTo(accountDTO.getLoginName());
        criteria.andPasswordEqualTo(accountDTO.getPassword());
        List<Account> accounts = accountMapper.selectByExample(accountExample);

        return accounts.size() == 1 ? new RespStat(200, "success", accounts.get(0)) : new RespStat(200, "fail", null);
    }

    @Override
    public Map findAll(int pageNum, int size) {
        Page<Account> objects = PageHelper.startPage(pageNum, size);
        final int pages = objects.getPages();
        List<Account> list = accountMapper.selectByExample(new AccountExample());
        Map result = new HashMap();
        result.put("list", list);
        result.put("pages", pages);
        return result;
    }
}
