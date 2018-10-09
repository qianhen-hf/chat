package com.fan.controller;

import com.fan.service.GiftInfoService;
import com.fan.vo.ResponseResult;
import io.swagger.annotations.Api;
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
 * @CreateDate: 2018/10/9 16:43
 * @UpdateUser: hf
 * @UpdateDate: 2018/10/9 16:43
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Api(value = "礼物", tags = {"礼物接口"})
@RestController
@RequestMapping("vRabbit/gift")
public class GiftController {
    @Autowired
    GiftInfoService giftInfoService;

    @ApiOperation(value = "查询所有礼物")
    @PostMapping("getAllGift")
    public ResponseResult getAllGift() {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setData(giftInfoService.getAllGift());
        return responseResult;
    }

}
