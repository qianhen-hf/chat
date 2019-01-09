package com.fan.controller;

import com.fan.mapper.ConsumeInfoDao;
import com.fan.po.User;
import com.fan.service.CustomerService;
import com.fan.service.UserService;
import com.fan.vo.PhotoInfoVo;
import com.fan.vo.ResponseResult;
import com.fan.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
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
@Api(value = "user", tags = {"user接口"})
@RestController
@RequestMapping("vRabbit/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CustomerService customerService;
    @Autowired
    ConsumeInfoDao consumeInfoDao;

    @ApiOperation(value = "更新用户昵称")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id(登录后传入token)", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "nickname", value = "用户昵称", required = true, dataType = "String")
    })
    @PostMapping("updateUser")
    public ResponseResult updateUser(String nickname, Long userId) {
        User user = userService.selectUserByUserId(userId);
        User userUpdate = new User();
        userUpdate.setUserId(user.getUserId());
        userUpdate.setNickName(nickname);
        userService.updateUser(userUpdate);
        return new ResponseResult(true);
    }

    @ApiOperation(value = "获取用户所有照片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id(登录后传入token)", required = true, dataType = "Long")
    })
    @PostMapping("getUserPhotos")
    public ResponseResult getUserPhoto(Long userId) {
        List<PhotoInfoVo> list = userService.getUserPhoto(userId);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setData(list);
        return responseResult;
    }


    @ApiOperation(value = "删除用户照片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id(登录后传入token)", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "photoId", value = "照片id", required = true, dataType = "Long")
    })
    @PostMapping("delUserPhotoById")
    public ResponseResult delUserPhotoById(Long userId, Long photoId) {
        userService.delUserPhotoById(userId, photoId);
        return new ResponseResult();
    }


    @ApiOperation(value = "查看用户当前余额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id(登录后传入token)", required = true, dataType = "Long"),
    })
    @PostMapping("getUserBalance")
    public ResponseResult getUserBalance(Long userId) {
        ResponseResult responseResult = new ResponseResult();
        User user = userService.selectUserByUserId(userId);
        responseResult.setData(user.getAmount());
        return responseResult;
    }


    @ApiOperation(value = "用户获取主播信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "java.lang.Long"),
            @ApiImplicitParam(name = "anchorId", value = "主播ID", required = true, dataType = "java.lang.Long")
    })
    @PostMapping("/getAnchorByUid")
    public ResponseResult getAnchorById(Long userId,Long anchorId) {
        ResponseResult responseResult = new ResponseResult();
        User user = userService.selectUserByUserId(anchorId);
        List<Long> anchorIds = consumeInfoDao.findAnchorOrder(userId);
        List<HashMap<Long,String>> rankList = null;
        if(null != anchorIds && anchorIds.size() > 0) {
            rankList = new ArrayList<HashMap<Long,String>>();
            List<User> users = customerService.selectUsersByUserIds(anchorIds);
            for(User tempUser : users) {
                HashMap<Long,String> tempMap = new HashMap<Long,String>();
                tempMap.put(tempUser.getUserId(),tempUser.getPhoto());
                rankList.add(tempMap);
            }
        }

        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        userVo.setRankList(rankList);
        responseResult.setData(userVo);
        return responseResult;
    }

}
