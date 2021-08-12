package com.venvo.springboot.mapper;

import com.venvo.springboot.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * RoleMapper继承基类
 */
@Repository
public interface RoleMapper extends MyBatisBaseDao<Role, Integer, RoleExample> {
    Role findByRoleId(int id);

    void add(int[] permissions, int id);
}