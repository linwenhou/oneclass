package com.venvo.springbootmvcoaproject.service;

import com.venvo.springbootmvcoaproject.common.RespStat;
import com.venvo.springbootmvcoaproject.dto.AccountDTO;
import com.venvo.springbootmvcoaproject.entity.Account;

import java.util.List;
import java.util.Map;

/**
 * @author venvo
 * @date 2021-07-14 17:17
 * @description
 * @modified By
 * @version: jdk1.8
 */
public interface AccountService {
    RespStat validataAccount(AccountDTO accountDTO);

    Map findAll(int pageNum, int size);
}
