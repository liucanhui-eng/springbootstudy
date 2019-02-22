package com.controller;

import com.model.pro.User;
import com.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("MyController")
public class MyController {
    private final static Logger logger = LoggerFactory.getLogger(MyController.class);

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("getUserById")
    public String getUserById(String id)throws Exception{
        logger.info("根据id查询用户");
        System.out.println(id);
        User user = null;
        try {
            user = userService.getUserById(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            throw new Exception("根据id查询用户出错 "+e.getMessage());
        }
        return user.toString();
    }

    @ResponseBody
    @RequestMapping("delUserById")
    public String delUserById(String id)throws Exception{
        logger.info("根据id删除用户");
        System.out.println(id);
        return "删除成功";
    }
}
