package com.venvo.springbootmvcfour.controller;
import com.venvo.springbootmvcfour.entity.Account;
import com.venvo.springbootmvcfour.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author venvo
 * @date 2021-07-05 14:27
 * @description
 * @modified By
 * @version: jdk1.8
 */
@Controller
@RequestMapping("/account")
public class MainController {


    @Autowired
    AccountService accountService;


    @RequestMapping("/list")
    @ResponseBody
    public List<Account> list(@RequestParam("pageNum") Integer pageNum,@RequestParam("size") Integer size) {
        List<Account> list = accountService.findAll(pageNum,size);
        return list;
    }


}
