package com.jhua.Service;

import com.jhua.dao.UserMapper;
import com.jhua.pojo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUserByName(String username) {
        User user = userMapper.queryUserByName(username);
        System.out.println(user);
        return user;
    }
}
