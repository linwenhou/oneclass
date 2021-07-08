package com.mashibing.springboot.firstspringboot.controller;

import com.mashibing.springboot.firstspringboot.domain.City;
import com.mashibing.springboot.firstspringboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author venvo
 * @date 2021-07-05 14:27
 * @description
 * @modified By
 * @version: jdk1.8
 */
@Controller
@RequestMapping("/user")
public class MainController {


    @Autowired
    CityService cityService;

//    @RequestMapping("/list")
//    public String getName(ModelMap model) {
//        model.addAttribute("name", "00000");
//        return "list";
//    }

    /**
     * @param model
     * @return
     * @RequestParam("id") Integer id,@RequestParam("name") String name
     * <p>
     * id,name
     */

    @RequestMapping("/list")
    public String list(ModelMap model) {
        List<City> list = cityService.findAll();
        model.addAttribute("list", list);
        return "list";
    }


    @RequestMapping("/add")
    public String list(ModelMap model, @RequestParam("id") Integer id, @RequestParam("name") String name) {
        String s = cityService.addAll(id, name);
        model.addAttribute("result", s);
        return "add";
    }

    @RequestMapping("/addPage")
    public String addPage() {
        return "add";
    }

    @RequestMapping("/deleteById")
    public String delete(ModelMap model,@RequestParam("id") Integer id) {

        String s=cityService.deleteById(id);
        model.addAttribute("result", s);

        return "list";
    }
}
