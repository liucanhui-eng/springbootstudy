package com.model.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("MyController")
public class MyController {
    @ResponseBody
    @RequestMapping("getUserById")
    public String getUserById(String id){
        System.out.println(id);
        return id;
    }
}
