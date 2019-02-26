package com.service;

import com.model.pro.User;

import java.util.List;

public interface UserService {
        User getUserById(int id)throws Exception;
        void recruitment(List<User> users)throws Exception;
        List<User> northernExpedition(User record)throws Exception;
}
