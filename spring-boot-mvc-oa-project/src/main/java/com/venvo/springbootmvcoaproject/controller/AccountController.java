package com.venvo.springbootmvcoaproject.controller;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.venvo.springbootmvcoaproject.common.RespStat;
import com.venvo.springbootmvcoaproject.dto.AccountDTO;
import com.venvo.springbootmvcoaproject.entity.Account;
import com.venvo.springbootmvcoaproject.entity.Config;
import com.venvo.springbootmvcoaproject.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
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
    @Value("${file.upload.path}")
    private String uploadPath;


    @Autowired
    Config config;

    @Autowired
    AccountService accountService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("config", config);
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
        System.out.println("respStat.getData()=" + respStat.getData());
        if (respStat.getMsg().equals("success")) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("account", respStat.getData());
        }
        return respStat;

    }

    @RequestMapping("/list")
    public String list(ModelMap map, @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, @RequestParam(value = "size", required = false, defaultValue = "2") int size) {

//        List<Account> list = accountService.findAll(pageNum, size);
        PageInfo<Account> maps = accountService.findAll(pageNum, size);
        System.out.println("PageInfo<Account>" + maps.toString());
        map.addAttribute("page", maps);
//        map.addAttribute("accountList", maps.get("list"));
//        map.addAttribute("pages",maps.get("pages"));

        return "account/list";
    }

    @RequestMapping("/logOut")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.removeAttribute("account");
        return "account/login";
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public RespStat deleteById(@RequestParam("id") Integer id) {
        RespStat respStat = accountService.deleteById(id);

        return respStat;
    }


    @RequestMapping("/registerss")
    @ResponseBody
    public RespStat registerss(HttpServletRequest request, Account account) {
        System.out.println("registerss" + account);
        int size = accountService.registerss(account);
        return new RespStat(300, "保存成功！", size);
    }

    @RequestMapping("/register")
    public String register() {


        return "account/register";
    }


    @RequestMapping("/isExistLoginName")
    @ResponseBody
    public RespStat isExistLoginName(Model modelMap, @RequestParam("loginName") String loginName) {
        if (StringUtil.isEmpty(loginName)) {
            return new RespStat(222, "用户名不能为空", false);
        }
        RespStat respStat = accountService.isExistLoginName(loginName);


        return respStat;
    }

    /**
     * 修改密码
     */
    @RequestMapping("/updatePass")
    public String updatePass() {
        return "/account/updatePass";
    }

    @RequestMapping("/update")
    public RespStat update(HttpServletRequest request, String oldPass, String newPass) {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String password = account.getPassword();
        if (!password.equals(oldPass)) {
            return new RespStat(1020, "原密码输入有误", null);
        }
        account.setPassword(newPass);
        int i = accountService.update(account);
        if (i == 1) {
            session.setAttribute("account", account);
        }
        return new RespStat(1022, "密码修改成功", i);
    }

    @RequestMapping("/profile")
    public String profile() {
        return "/account/profile";
    }
//
//    @RequestMapping("/fileUploadController")
//    public String fileUpload(MultipartFile filename, String password) {
//        System.out.println("password:" + password);
//        System.out.println("file:" + filename.getOriginalFilename());
//        try {
//
//            File path = new File(ResourceUtils.getURL("classpath:").getPath());
//            File upload = new File(path.getAbsolutePath(), "static/upload/");
//
//            System.out.println("upload:" + upload);
//
//
//            filename.transferTo(new File(upload + "/" + filename.getOriginalFilename()));
//
//
//        } catch (IllegalStateException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return "profile";
//    }


    @RequestMapping("/fileUploadController")
    public String fileUpload(HttpServletRequest request, MultipartFile filename, String password) {
        System.out.println("password:" + password);
        System.out.println("file:" + filename.getOriginalFilename());
        try {


            HttpSession session = request.getSession();
            filename.transferTo(new File(uploadPath + filename.getOriginalFilename()));
            String url = filename.getOriginalFilename();
            Account account = (Account) session.getAttribute("account");
            account.setUrl(url);
            accountService.update(account);

        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "/account/profile";
    }


    @RequestMapping("/permission")
    public String permisssion(Model map, @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, @RequestParam(value = "size", required = false, defaultValue = "2") int size) {

        PageInfo<Account> maps = accountService.getPermisssion(pageNum, size);
//        PageInfo<Account> maps = accountService.findAll(pageNum, size);
        System.out.println("PageInfo<Account>" + maps.toString());
        map.addAttribute("page", maps);
        return "";
    }

    @RequestMapping("/error")
    public String error() {

        return "/error";
    }

}
