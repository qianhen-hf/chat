package com.fan.controller;

import com.fan.service.ConsumeInfoService;
import com.fan.service.redis.RedisOperator;
import com.fan.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/10/9 15:42
 * @UpdateUser: hf
 * @UpdateDate: 2018/10/9 15:42
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

@Api(value = "消费", tags = {"用户/主播 消费接口"})
@RestController
@RequestMapping("vRabbit/consume")
public class ConsumeInfoController {
    @Autowired
    ConsumeInfoService consumeInfoService;
    @Autowired
    RedisOperator redisOperator;


    @ApiOperation(value = "打赏主播,刷礼物")
    @PostMapping("reward")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id(登录后传入token)", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "anchorId", value = "主播id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "presentId", value = "礼物id", required = true, dataType = "Long")
    })
    public ResponseResult reward(Long userId, Long anchorId, Long presentId) {
        ResponseResult responseResult = new ResponseResult();
        if (redisOperator.lock(userId.toString(), String.valueOf(System.currentTimeMillis()))) {
            boolean reward = consumeInfoService.reward(userId, anchorId, presentId);
            if (!reward) {
                responseResult.setCode(0);
                responseResult.setMsg("余额不足");
            }
        } else {
            responseResult.setCode(0);
            responseResult.setMsg("系统繁忙,稍后再试");
        }
        return responseResult;
    }


    @ApiOperation(value = "用户查询刷的礼物")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id(登录后传入token)", required = true, dataType = "Long"),
    })
    @PostMapping("selectConsumeInfoByUserId")
    public ResponseResult selectConsumeInfoByUserId(Long userId) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setData(consumeInfoService.selectConsumeInfoByUserId(userId));
        return responseResult;
    }


    @ApiOperation(value = "主播查询刷的礼物")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "anchorId", value = "主播id", required = true, dataType = "Long"),
    })
    @PostMapping("selectConsumeInfoByAnchorId")
    public ResponseResult selectConsumeInfoByAnchorId(Long anchorId) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setData(consumeInfoService.selectConsumeInfoByAnchorId(anchorId));
        return responseResult;
    }
}
