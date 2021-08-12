package com.venvo.springboot.service;

import com.github.pagehelper.PageInfo;
import com.venvo.springboot.common.RespStat;
import com.venvo.springboot.entity.Role;


/**
 * @author venvo
 * @date 2021-07-21 16:50
 * @description
 * @modified By
 * @version: jdk1.8
 */
public interface IRoleService {
    PageInfo<Role> findRoleByPage(int pageNum, int pageSize);

    Role findById(int id);

    RespStat add(int[] permissions, int id);
}
