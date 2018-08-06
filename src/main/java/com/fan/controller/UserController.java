package com.fan.controller;

import com.fan.po.PhotoInfo;
import com.fan.po.User;
import com.fan.service.PhotoInfoService;
import com.fan.service.UserService;
import com.fan.vo.PhotoInfoVo;
import com.fan.vo.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/8/6 11:14
 * @UpdateUser: hf
 * @UpdateDate: 2018/8/6 11:14
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@RestController
@RequestMapping("vRabbit/user")
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("updateUser")
    public void updateUser(String nickname, Long userId) {
        User user = userService.selectUserByUserId(userId);
        user.setNickname(nickname);
        userService.updateUser(user);
    }

    @PostMapping("getUserPhotos")
    public ResponseResult getUserPhoto(Long userId) {
        List<PhotoInfoVo> list = userService.getUserPhoto(userId);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setData(list);
        return responseResult;

    }

    @PostMapping("delUserPhotoById")
    public ResponseResult delUserPhotoById(Long userId, Long photoId) {
        userService.delUserPhotoById(userId, photoId);
        return new ResponseResult();
    }

}
