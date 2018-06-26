package com.fan.service;

import com.fan.mapper.UserLoginMapper;
import com.fan.po.User;
import com.fan.po.UserLogin;
import com.fan.vo.UserLoginVo;
import com.fan.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/6/25 16:58
 * @UpdateUser: hf
 * @UpdateDate: 2018/6/25 16:58
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Service
@Slf4j
public class LoginService {
    @Autowired
    UserLoginMapper userLoginMapper;


    public void userLogin(Integer code, User user, UserLoginVo userLoginVo) {
        UserLogin userLogin = new UserLogin();
        BeanUtils.copyProperties(user, userLogin);
        userLogin.setCode(code);
        userLoginMapper.insertSelective(userLogin);
    }
}
