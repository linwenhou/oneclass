package com.venvo.springbootmvcoaproject.controller;

import com.github.pagehelper.PageInfo;
import com.venvo.springbootmvcoaproject.entity.Permission;
import com.venvo.springbootmvcoaproject.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author venvo
 * @date 2021-07-20 15:12
 * @description
 * @modified By
 * @version: jdk1.8
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    PermissionService permissionService;

    @RequestMapping("/permissionList")
    public String permissionList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, Model model) {

        PageInfo<Permission> page = permissionService.findByPage(pageNum, pageSize);
        model.addAttribute("page", page);
        return "manager/permissionList";
    }


//    @RequestMapping("/permissionAdd")
//    public String permissionAdd(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, Model model) {
//
//        PageInfo<Permission> page = permissionService.findByPage(pageNum, pageSize);
//        model.addAttribute("page", page);
//        return "manager/permissionAdd";
//    }


    @RequestMapping("/permissionModify")
    public String permissionModify(@RequestParam("id") int id, Model model) {

        Permission p = permissionService.findById(id);
        model.addAttribute("p", p);
        return "manager/permissionModify";
    }

    @RequestMapping("/permissionAdd")
    public String permissionAdd() {

//        Permission p = permissionService.findById(id);
//        model.addAttribute("p", p);
        return "manager/permissionModify";
    }





}
