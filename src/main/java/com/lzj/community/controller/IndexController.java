package com.lzj.community.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    /**
     *  GetMapping注解就相当于@RequestMapping(method = RequestMethod.GET) 的缩写
     */

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
