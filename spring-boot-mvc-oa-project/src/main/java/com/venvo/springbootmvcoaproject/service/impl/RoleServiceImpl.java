package com.venvo.springbootmvcoaproject.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.venvo.springbootmvcoaproject.common.RespStat;
import com.venvo.springbootmvcoaproject.entity.Role;
import com.venvo.springbootmvcoaproject.mapper.RoleExample;
import com.venvo.springbootmvcoaproject.mapper.RoleMapper;
import com.venvo.springbootmvcoaproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author venvo
 * @date 2021-07-21 16:51
 * @description
 * @modified By
 * @version: jdk1.8
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public PageInfo<Role> findRoleByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        RoleExample roleExample = new RoleExample();
        final List<Role> roles = roleMapper.selectByExample(roleExample);

        return new PageInfo<Role>(roles);
    }

    @Override
    public Role findById(int id) {
        return roleMapper.findByRoleId(id);
    }

    @Override
    public RespStat add(int[] permissions, int id) {
        roleMapper.add(permissions, id);
        return new RespStat(200, "success", null);
    }
}
