package com.venvo.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.venvo.springboot.common.RespStat;
import com.venvo.springboot.entity.Permission;
import com.venvo.springboot.mapper.PermissionExample;
import com.venvo.springboot.mapper.PermissionMapper;
import com.venvo.springboot.service.IPermissionService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author venvo
 * @date 2021-07-20 15:33
 * @description
 * @modified By
 * @version: jdk1.8
 */
@Component
@Service(version = "1.0", timeout = 10000, interfaceClass = IPermissionService.class)
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public PageInfo<Permission> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Permission> permissions = permissionMapper.selectByExample(new PermissionExample());
        return new PageInfo<>(permissions);
    }

    @Override
    @Transactional
    public RespStat deletedById(Integer id) {
        if (id == null) {
            return new RespStat(1021, "id不能为空", null);
        }
        int i = permissionMapper.deleteByPrimaryKey(id);
        return new RespStat(200, "sucess", i);
    }

    @Override
    public Permission findById(int id) {
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        return permission;
    }

    @Override
    @Transactional
    public int update(Permission permission) {
        final int i = permissionMapper.updateByPrimaryKey(permission);
        return i;
    }

    @Override
    @Transactional
    public int add(Permission permission) {
        System.out.println("permission.toString()" + permission.toString());
        final int insert = permissionMapper.insert(permission);
        return insert;
    }

    @Override
    public List<Permission> findAll() {
         PermissionExample permissionExample = new PermissionExample();
        return permissionMapper.selectByExample(permissionExample);
    }
}
