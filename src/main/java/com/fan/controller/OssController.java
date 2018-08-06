package com.fan.controller;

import com.fan.service.external.OssService;
import com.fan.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/6/11 10:39
 * @UpdateUser: hf
 * @UpdateDate: 2018/6/11 10:39
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Api(value = "oss", tags = {"oss(图片上传)接口"})
@RestController
@RequestMapping("oss")
public class OssController {
    @Autowired
    OssService ossService;

//    @ApiIgnore
//    @RequestMapping("getOssCertificate1")
//    public ResponseResult getOssCertificate1() {
//        ResponseResult responseResult = new ResponseResult(true);
//        responseResult.setData(ossService.getOssCertificate());
//        return responseResult;
//    }

    @ApiIgnore
    @PostMapping("getOssCertificate1")
    public Map<String, String> getOssCertificate1() {
        return ossService.getOssCertificate();
    }

    @ApiOperation(value = "获取oss参数")
    @PostMapping("getOssCertificate")
    public Map<String, String> getOssCertificate() {
        return ossService.getOssCertificate();
    }
}
