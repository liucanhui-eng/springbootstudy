package com.controller;

import com.model.pro.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("MyController")
public class MyController {
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("getUserById")
    public String getUserById(String id){
        System.out.println(id);
        String res=null;
        try {
            int _id = Integer.parseInt(id);
            User user = userService.getUserById(_id);
            res=user.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return res;
    }
}
