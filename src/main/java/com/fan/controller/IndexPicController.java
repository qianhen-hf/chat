package com.fan.controller;

import com.fan.po.IndexPic;
import com.fan.service.IndexPicService;
import com.fan.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/10/30 9:24
 * @UpdateUser: hf
 * @UpdateDate: 2018/10/30 9:24
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@RestController
@Api(value = "首页图片", tags = {"首页图片"})
@RequestMapping("vRabbit/index")
public class IndexPicController {
    @Autowired
    IndexPicService indexPicService;


    @ApiOperation(value = "查询所有首页图片")
    @PostMapping("getIndexPics")
    public ResponseResult getIndexPics() {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setData(indexPicService.getIndexPics());
        return responseResult;
    }

    @ApiOperation(value = "查询所有在线繁忙的主播")
    @PostMapping("getIndexCustomers")
    public ResponseResult getIndexCustomers() {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setData(indexPicService.getIndexCustomers());
        return responseResult;

    }
}
