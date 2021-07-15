package com.venvo.springbootmvcoaproject.controller;

import com.venvo.springbootmvcoaproject.common.RespStat;
import com.venvo.springbootmvcoaproject.dto.AccountDTO;
import com.venvo.springbootmvcoaproject.entity.Account;
import com.venvo.springbootmvcoaproject.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author venvo
 * @date 2021-07-14 16:32
 * @description
 * @modified By
 * @version: jdk1.8
 */
@RequestMapping("/account")
@Controller
public class AccountController {
    @Autowired
    AccountService accountService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "account/login";
    }

    @RequestMapping("/validataAccount")
    @ResponseBody
    public RespStat validataAccount(@Valid AccountDTO accountDTO, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            final List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError error : allErrors) {
                return new RespStat(400, "error", error.getDefaultMessage());
            }
        }

        RespStat respStat = accountService.validataAccount(accountDTO);
        if (respStat.getMsg().equals("success")) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("account", respStat.getData());
        }
        return respStat;

    }

    @RequestMapping("/list")
    public String list(ModelMap map, @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, @RequestParam(value = "size", required = false, defaultValue = "2") int size) {

//        List<Account> list = accountService.findAll(pageNum, size);
       Map maps= accountService.findAll(pageNum, size);
        map.addAttribute("accountList", maps.get("list"));
        map.addAttribute("pages",maps.get("pages"));

        return "account/list";
    }

    @RequestMapping("/logOut")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.removeAttribute("account");
        return "account/login";
    }

}
