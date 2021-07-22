package com.venvo.springbootmvcoaproject.service;

import com.github.pagehelper.PageInfo;
import com.venvo.springbootmvcoaproject.common.RespStat;
import com.venvo.springbootmvcoaproject.entity.Permission;
import com.venvo.springbootmvcoaproject.entity.Role;

import java.util.List;

/**
 * @author venvo
 * @date 2021-07-20 15:33
 * @description
 * @modified By
 * @version: jdk1.8
 */
public interface PermissionService {
    PageInfo<Permission> findByPage(int pageNum, int pageSize);

    RespStat deletedById(Integer id);

    Permission findById(int id);

    int update(Permission permission);

    int add(Permission permission);

    List<Permission> findAll();
}
