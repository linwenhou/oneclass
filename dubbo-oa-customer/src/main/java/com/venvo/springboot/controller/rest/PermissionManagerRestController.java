package com.venvo.springboot.controller.rest;

import com.venvo.springboot.common.RespStat;
import com.venvo.springboot.entity.Permission;
import com.venvo.springboot.service.IPermissionService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

/**
 * @author venvo
 * @date 2021-07-22 08:50
 * @description
 * @modified By
 * @version: jdk1.8
 */
@RestController
@RequestMapping("/api/v1/manager/permission")
public class PermissionManagerRestController {

    @Reference(version = "1.0")
    IPermissionService permissionService;


    @RequestMapping("/deleteById")
    @ResponseBody
    public RespStat deletedById(@RequestParam("id")
                                        Integer id) {
        RespStat result = permissionService.deletedById(id);
        return result;

    }

    @RequestMapping("/add")
    public RespStat add(@RequestBody Permission permission) {
        int i;
        if (permission.getId() != null) {
            i = permissionService.update(permission);
            return new RespStat(200, "update success", i);
        } else {
            i = permissionService.add(permission);
            return new RespStat(200, "add success", i);
        }
    }
}
