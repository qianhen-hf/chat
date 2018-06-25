package com.fan.controller;

import com.fan.Exception.VRabbitException;
import com.fan.Exception.VRabbitUserErrors;
import com.fan.config.OssConfig;
import com.fan.config.PrefixConfig;
import com.fan.jwt.JwtHelper;
import com.fan.po.User;
import com.fan.service.SendMsgService;
import com.fan.service.UserService;
import com.fan.service.redis.RedisOperator;
import com.fan.util.MD5;
import com.fan.vo.ResponseResult;
import com.fan.vo.UserVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

@Slf4j
@RestController
@RequestMapping("vRabbit")
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    PrefixConfig prefixConfig;
    @Autowired
    RedisOperator redisOperator;
    @Autowired
    SendMsgService sendMsgService;

    @PostMapping("login")
    public ResponseResult Login(String userName, String msgCode) {
        ResponseResult responseResult = new ResponseResult(false);
        User user = userService.selectUserName(userName);
        String redisMsgCode = redisOperator.get(prefixConfig.getUserCodePrefix().concat(userName));
        log.info("redisMsgCode:{}", redisMsgCode);
        if (user == null) {
            throw new VRabbitException(VRabbitUserErrors.USER_ERROR);
        }
        if (!msgCode.equals(redisMsgCode)) {
            throw new VRabbitException(VRabbitUserErrors.USER_MSG_CODE_ERROR);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        responseResult.setCode(ResponseResult.SUCCESS);
        String uid = user.getUserId().toString();
        String userSerializable = null;
        try {
            userSerializable = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new VRabbitException(VRabbitUserErrors.OBJECT_MAPPER_ERROR);
        }
        redisOperator.set(prefixConfig.getUserIdPrefix().concat(uid), userSerializable, prefixConfig.getUserOverTime());
        String token = JwtHelper.createJWT(uid, "", "", "",
                Integer.MAX_VALUE, MD5.Md5Encryption(uid));
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        userVo.setToken(token);
        responseResult.setData(userVo);
        responseResult.setCode(ResponseResult.SUCCESS);
        return responseResult;
    }

    @RequestMapping("/overTime")
    public ResponseResult overTime() {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(303);
        responseResult.setMsg("登录超时");
        return responseResult;
    }

    @RequestMapping("/tokenIsNull")
    public ResponseResult tokenIsNull() {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(101);
        responseResult.setMsg("token为空");
        return responseResult;
    }

    @RequestMapping("/tokenValidate")
    public ResponseResult tokenValidate() {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(202);
        responseResult.setMsg("token验证失败");
        return responseResult;
    }


    @PostMapping("sendMsgCode")
    public ResponseResult sendMsgCode(String userName) {
        String msgCode = String.valueOf(new Random().nextInt(899999) + 100000);
        redisOperator.set(prefixConfig.getUserCodePrefix().concat(userName), msgCode);
        sendMsgService.sendMessage(userName, "SMS_137790152", "{\"code\":\"" + msgCode + "\"}");
        return new ResponseResult(true);
    }


}

