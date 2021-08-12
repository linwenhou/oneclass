package com.venvo.springboot.controller.rest;//package com.venvo.springbootmvcoaproject.controller.rest;
//
//import com.venvo.springbootmvcoaproject.common.RespStat;
//import com.venvo.springbootmvcoaproject.entity.Permission;
//import com.venvo.springbootmvcoaproject.service.PermissionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import javax.xml.ws.Action;
//
///**
// * @author venvo
// * @date 2021-07-20 15:12
// * @description
// * @modified By
// * @version: jdk1.8
// */
//@RestController
//@RequestMapping("/api/v1/manager/permission")
//public class ManagerRestController {
//    @Autowired
//    PermissionService permissionService;
//
//
//    @RequestMapping("/deleteById")
//    @ResponseBody
//    public RespStat deletedById(@RequestParam("id")
//                                        Integer id) {
//        RespStat result = permissionService.deletedById(id);
//        return result;
//
//    }
//
//    @RequestMapping("/add")
//    public RespStat add(@RequestBody Permission permission) {
//        int i;
//        if (permission.getId() != null) {
//            i = permissionService.update(permission);
//            return new RespStat(200, "update success", i);
//        } else {
//            i = permissionService.add(permission);
//            return new RespStat(200, "add success", i);
//        }
//    }
//}
