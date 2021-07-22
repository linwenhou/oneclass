package com.venvo.springbootmvcoaproject.controller;

import com.github.pagehelper.PageInfo;
import com.venvo.springbootmvcoaproject.entity.Permission;
import com.venvo.springbootmvcoaproject.entity.Role;
import com.venvo.springbootmvcoaproject.service.PermissionService;
import com.venvo.springbootmvcoaproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    RoleService roleService;

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
        return "manager/permissionModify";
    }

    @RequestMapping("/roleList")
    public String roleList(Model model, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {

        PageInfo<Role> role = roleService.findRoleByPage(pageNum, pageSize);
        model.addAttribute("page", role);
        return "manager/roleList";
    }


    @RequestMapping("/rolePermission/{id}")
    public String roleList(HttpServletRequest request, Model model, @PathVariable("id") int id) {

        Role role = roleService.findById(id);
        model.addAttribute("role", role);
        List<Permission> pList = permissionService.findAll();
        model.addAttribute("pList", pList);
        List<Permission> permissionList = role.getPermissions();
        List list = new ArrayList();
        permissionList.forEach(item -> {
            list.add(item.getId());
        });
        model.addAttribute("roleP", list);
//        model.addAttribute("role", role);
        Object attribute = request.getSession().getAttribute("account");
//        System.out.println("attribute:" + ToStringBuilder.reflectionToString(attribute));

//        PageInfo<Role> role = roleService.findRoleByPage();
//        model.addAttribute("page", role);
        return "manager/rolePermission";
    }


}
