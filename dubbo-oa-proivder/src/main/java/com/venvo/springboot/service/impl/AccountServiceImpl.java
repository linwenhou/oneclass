package com.venvo.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.venvo.springboot.common.RespStat;
import com.venvo.springboot.dto.AccountDTO;
import com.venvo.springboot.entity.Account;
import com.venvo.springboot.mapper.AccountExample;
import com.venvo.springboot.mapper.AccountMapper;
import com.venvo.springboot.service.IAccountService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author venvo
 * @date 2021-07-14 17:18
 * @description
 * @modified By
 * @version: jdk1.8
 */
@Component
@Service(version = "1.0", timeout = 10000, interfaceClass = IAccountService.class)
public class AccountServiceImpl implements IAccountService {

    @Autowired
    AccountMapper accountMapper;

    @Override
    public RespStat validataAccount(AccountDTO accountDTO) {

        AccountExample accountExample = new AccountExample();
        AccountExample.Criteria criteria = accountExample.createCriteria();
        criteria.andLoginNameEqualTo(accountDTO.getLoginName());
        criteria.andPasswordEqualTo(accountDTO.getPassword());
//        List<Account> accounts = accountMapper.selectByExample(accountExample);
        Account account = accountMapper.selectByLoginNameAndPassword(accountDTO.getLoginName(),accountDTO.getPassword());

        return new RespStat(200, "success", account);
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

    @Override
    public PageInfo<Account> getPermisssion(int pageNum, int size) {
        PageHelper.startPage(pageNum, size);

        List<Account> list = accountMapper.getRight();
        PageInfo<Account> accountPageInfo = new PageInfo<>(list);
        return accountPageInfo;
    }
}
