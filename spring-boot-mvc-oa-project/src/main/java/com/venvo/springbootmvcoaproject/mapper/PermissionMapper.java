package com.venvo.springbootmvcoaproject.mapper;

import com.venvo.springbootmvcoaproject.entity.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * PermissionMapper继承基类
 */
@Repository
public interface PermissionMapper extends MyBatisBaseDao<Permission, Integer, PermissionExample> {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into permission(uri,name,c,r,u,d) values(#{uri},#{name},#{c},#{r},#{u},#{d})")
    int save(Permission permission);
}