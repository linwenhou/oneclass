package com.venvo.springbootmvcoaproject.service;

import com.github.pagehelper.PageInfo;
import com.venvo.springbootmvcoaproject.common.RespStat;
import com.venvo.springbootmvcoaproject.entity.Role;

/**
 * @author venvo
 * @date 2021-07-21 16:50
 * @description
 * @modified By
 * @version: jdk1.8
 */
public interface RoleService {
    PageInfo<Role> findRoleByPage(int pageNum, int pageSize);

    Role findById(int id);

    RespStat add(int[] permissions, int id);
}
