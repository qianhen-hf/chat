package com.fan.service;

import com.fan.mapper.UserMapper;
import com.fan.po.User;
import com.fan.po.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public User selectUserByUserId(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public User selectUserName(String userName) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(userName);
        List<User> users = userMapper.selectByExample(userExample);
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

}
