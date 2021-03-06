package com.fan.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fan.Exception.VRabbitException;
import com.fan.Exception.VRabbitUserErrors;
import com.fan.config.PrefixConfig;
import com.fan.jwt.JwtHelper;
import com.fan.po.User;
import com.fan.requestVo.RegisterUser;
import com.fan.requestVo.UserLoginVo;
import com.fan.service.LoginService;
import com.fan.service.SendMsgService;
import com.fan.service.UserService;
import com.fan.service.redis.RedisOperator;
import com.fan.util.MD5;
import com.fan.video.CheckSumBuilder;
import com.fan.video.HttpClientUtil;
import com.fan.vo.ResponseResult;
import com.fan.vo.UserVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.spring.web.json.Json;

import java.util.Date;
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
@Api(value = "用户登录", tags = {"用户登录接口"})
@RestController
@RequestMapping("vRabbit")
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    LoginService loginService;
    @Autowired
    PrefixConfig prefixConfig;
    @Autowired
    RedisOperator redisOperator;
    @Autowired
    SendMsgService sendMsgService;

    @ApiOperation(value = "登录")
    @PostMapping("login")
    public ResponseResult Login(UserLoginVo userLoginVo) {
        ResponseResult responseResult = new ResponseResult(true);
        User user = userService.selectUserName(userLoginVo.getUserName());
        String redisMsgCode = redisOperator.get(prefixConfig.getUserCodePrefix().concat(userLoginVo.getUserName()));
        log.info("redisMsgCode:{}", redisMsgCode);
        if (StringUtils.isBlank(redisMsgCode)) {
            throw new VRabbitException(VRabbitUserErrors.USER_MSG_CODE_NOT_EXIST);
        }
        if (user == null) {
            user = new User();
            user.setUserName(userLoginVo.getUserName());
            user.setNickName("");
            userService.insertUser(user);
        }
        if (userLoginVo.getMsgCode().intValue() != Integer.valueOf(redisMsgCode)) {
            throw new VRabbitException(VRabbitUserErrors.USER_MSG_CODE_ERROR);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        responseResult.setCode(ResponseResult.SUCCESS);
        String uid = user.getUserId().toString();
        if (StringUtils.isBlank(user.getWyToken())) {
            String s = HttpClientUtil.httpClient(uid);
            JSONObject jsonObject = JSON.parseObject(s);
            if (jsonObject.get("code").toString().equals("200")) {
                User userUp = new User();
                userUp.setUserId(user.getUserId());
                JSONObject info = (JSONObject) jsonObject.get("info");
                Object token = info.get("token");
                userUp.setWyToken(token.toString());
                userService.updateUser(userUp);
                user = userService.selectUserName(userLoginVo.getUserName());
            }
        }
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
        loginService.userLogin(user, userLoginVo);
        return responseResult;
    }


    @ApiIgnore()
    @GetMapping("/overTime")
    public ResponseResult overTime() {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(303);
        responseResult.setMsg("登录超时");
        return responseResult;
    }


    @ApiIgnore
    @GetMapping("/tokenIsNull")
    public ResponseResult tokenIsNull() {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(101);
        responseResult.setMsg("token为空");
        return responseResult;
    }


    @ApiIgnore
    @GetMapping("/tokenValidate")
    public ResponseResult tokenValidate() {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(202);
        responseResult.setMsg("token验证失败");
        return responseResult;
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户登陆名", required = true, dataType = "String")
    })

    @ApiOperation(value = "发送验证码")
    @PostMapping("sendMsgCode")
    public ResponseResult sendMsgCode(String userName) {
        String msgCode = String.valueOf(new Random().nextInt(899999) + 100000);
        redisOperator.set(prefixConfig.getUserCodePrefix().concat(userName), msgCode, 1800);
        sendMsgService.sendMessage(userName, "SMS_137790152", "{\"code\":\"" + msgCode + "\"}");
        return new ResponseResult(true);
    }

    @ApiOperation(value = "注册")
    @PostMapping("register")
    public ResponseResult register(RegisterUser requestUser) {
        User user = new User();
        user.setUserName(requestUser.getUsername());
        user.setNickName(requestUser.getNickname());
        userService.insertUser(user);
        return new ResponseResult(true);
    }

    @ApiOperation(value = "云信注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户/主播ID", required = true, dataType = "java.lang.Long")
    })
    @PostMapping("yunXinRegister")
    public ResponseResult yunXinRegister(Long userId) {
        ResponseResult responseResult = new ResponseResult();
        String s = HttpClientUtil.httpClient(userId.toString());
        JSONObject jsonObject = JSON.parseObject(s);
        if (jsonObject.get("code").toString().equals("200")) {
            User user = new User();
            user.setUserId(userId);
            JSONObject info = (JSONObject) jsonObject.get("info");
            Object token = info.get("token");
            user.setWyToken(token.toString());
            userService.updateUser(user);
        }
        responseResult.setData(s);
        return responseResult;
    }


//    public static void main(String[] args) {
//        String s = "{\"code\":200,\"info\":{\"token\":\"83ad8090541a3048e3b10f89f0018434\",\"accid\":\"888888\",\"name\":\"\"}}";
//        JSONObject jsonObject = JSONObject.parseObject(s);
//        if (jsonObject.get("code").toString().equals("200")) {
//            JSONObject info = (JSONObject) jsonObject.get("info");
//            Object token = info.get("token");
//            System.out.println(token);
//        }
//    }
}

