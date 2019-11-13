package com.lzj.community.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    /**
     *  GetMapping注解就相当于@RequestMapping(method = RequestMethod.GET) 的缩写
     */

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name")String name, Model model){

        model.addAttribute("name", name);
        return "hello"; //回自动在templates目录下寻找hello.html
    }
}
