package com.venvo.springbootmvcoaproject.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.venvo.springbootmvcoaproject.common.RespStat;
import com.venvo.springbootmvcoaproject.dto.AccountDTO;
import com.venvo.springbootmvcoaproject.entity.Account;
import com.venvo.springbootmvcoaproject.mapper.AccountExample;
import com.venvo.springbootmvcoaproject.mapper.AccountMapper;
import com.venvo.springbootmvcoaproject.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.peer.LightweightPeer;
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
    public PageInfo<Account> findAll(int pageNum, int size) {
        PageHelper.startPage(pageNum, size);
        AccountExample accountExample = new AccountExample();
        List<Account> list = accountMapper.selectByExample(accountExample);
        return new PageInfo<>(list, 2);
    }

    @Override
    @Transactional
    public RespStat deleteById(Integer id) {
        int i = accountMapper.deleteByPrimaryKey(id);
        return new RespStat(200, "删除成功！！", i);
    }

    @Override
    public RespStat isExistLoginName(String loginName) {
        boolean isExist = false;
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andLoginNameEqualTo(loginName);
        final List<Account> list = accountMapper.selectByExample(accountExample);
        if (list.size() >= 1) {
            return new RespStat(300, "已存在", true);
        }
        return new RespStat(300, "", false);
    }

    @Override
    @Transactional
    public int registerss(Account account) {
        return accountMapper.insert(account);
    }

    @Override
    @Transactional
    public int update(Account account) {
        final int i = accountMapper.updateByPrimaryKey(account);
        return i;
    }
}
