package com.fan.service;

import com.fan.mapper.UserMapper;
import com.fan.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/5/17 11:04
 * @UpdateUser: hf
 * @UpdateDate: 2018/5/17 11:04
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;


    public void insertUser(User user) {
        userMapper.insertSelective(user);
    }

    public User selectUser(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

}
