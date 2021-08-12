package com.venvo.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.venvo.springboot.common.RespStat;
import com.venvo.springboot.entity.Role;
import com.venvo.springboot.mapper.RoleExample;
import com.venvo.springboot.mapper.RoleMapper;
import com.venvo.springboot.service.IRoleService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author venvo
 * @date 2021-07-21 16:51
 * @description
 * @modified By
 * @version: jdk1.8
 */
@Component
@Service(version = "1.0", timeout = 10000, interfaceClass = IRoleService.class)
public class RoleServiceImpl implements IRoleService {

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
