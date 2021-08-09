package com.venvo.springboot.controller.rest;

import com.venvo.springboot.common.RespStat;
import com.venvo.springboot.service.IRoleService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author venvo
 * @date 2021-07-22 08:56
 * @description
 * @modified By
 * @version: jdk1.8
 */
@RestController
@RequestMapping("/api/v1/manager/role")
public class RoleManagerRestController {
    @Reference(version = "1.0")
    IRoleService roleService;

    @RequestMapping("/Permission/add")
    public RespStat add(int[] permissions, int id) {
        RespStat respStat = roleService.add(permissions, id);
        return respStat;
    }
}
