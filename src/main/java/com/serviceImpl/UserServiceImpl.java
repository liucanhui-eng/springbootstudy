package com.serviceImpl;

import com.model.dao.UserMapper;
import com.model.pro.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserById(int id)throws Exception {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public void recruitment(List<User> users) throws Exception{
        try {
            users.forEach(user ->{
                userMapper.insert(user);
            });
        } catch (Exception e) {
            throw new Exception(e.getMessage()+"募兵出现异常");
        }

        /*if (users!=null){
            for (User user: users) {
                System.out.println(user+"==============插入数据");
                userMapper.insert(user);
            }
        }*/

        System.out.println("添加成功");
    }

    @Override
    public List<User> northernExpedition(User record) throws Exception {
        List<User> list=null;
        try {
            list=userMapper.selectByExample(record);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage()+"出兵异常");
        }
        return list;
    }


}
