package com.fan.controller;

import com.fan.po.User;
import com.fan.service.UserService;
import com.fan.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/5/17 10:53
 * @UpdateUser: hf
 * @UpdateDate: 2018/5/17 10:53
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

@RestController
@RequestMapping("vRabbit")
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping("vRabbit")
    public UserVo Login() {
        User user = new User();
        user.setUserId(1l);
        user.setUserName("huangfan");
        userService.insertUser(user);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }
}

