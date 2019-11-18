package com.lzj.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OAuthController {

    @GetMapping("/callback")
    public String Callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state){
        
        return "index";
    }
}
