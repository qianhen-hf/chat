package com.fan.controller;

import com.fan.po.User;
import com.fan.service.UserService;
import com.fan.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/7/26 10:05
 * @UpdateUser: hf
 * @UpdateDate: 2018/7/26 10:05
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Api(value = "音视频", tags = {"音视频接口"})
@RestController
@Slf4j
@RequestMapping("video")
public class VideoController {
    @Autowired
    UserService userService;

//    @ApiOperation(value = "视频登录")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId", value = "用户id(登录后传入token)", required = true, dataType = "String")
//    })
//    @PostMapping("create")
//    public String create(String userId) {
//        return HttpClientUtil.httpClient(userId);
//    }

    @ApiOperation(value = "视频扣费")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id(登录后传入token)", required = true, dataType = "java.lang.Long")
    })
    @PostMapping("deductionFee")
    public ResponseResult deductionFee(Long userId) {
        ResponseResult responseResult = new ResponseResult();
        User user = userService.selectUserByUserId(userId);
        User userUp = new User();
        userUp.setUserId(userId);
        userUp.setAmount(user.getAmount() - 200);
        userService.updateUser(userUp);
        responseResult.setData(userUp.getAmount());
        return responseResult;
    }
}
