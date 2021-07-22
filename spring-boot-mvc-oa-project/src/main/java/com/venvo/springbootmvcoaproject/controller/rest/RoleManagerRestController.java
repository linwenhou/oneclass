package com.venvo.springbootmvcoaproject.controller.rest;

import com.venvo.springbootmvcoaproject.common.RespStat;
import com.venvo.springbootmvcoaproject.service.RoleService;
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
    @Autowired
    RoleService roleService;

    @RequestMapping("/Permission/add")
    public RespStat add(int[] permissions, int id) {
        RespStat respStat = roleService.add(permissions, id);
        return respStat;
    }
}
