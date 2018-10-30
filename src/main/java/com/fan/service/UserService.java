package com.fan.service;

import com.fan.mapper.UserMapper;
import com.fan.po.PhotoInfo;
import com.fan.po.User;
import com.fan.po.UserExample;
import com.fan.service.external.OssService;
import com.fan.vo.PhotoInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/5/17 11:04
 * @UpdateUser: hf
 * @UpdateDate: 2018/5/17 11:04
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    PhotoInfoService photoInfoService;
    @Autowired
    OssService ossService;


    public void insertUser(User user) {
        userMapper.insertSelective(user);
    }

    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    public User selectUserByUserId(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public User selectUserName(String userName) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameEqualTo(userName);
        List<User> users = userMapper.selectByExample(userExample);
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }


    public List<PhotoInfoVo> getUserPhoto(Long userId) {
        List<PhotoInfo> userPhoto = photoInfoService.getUserPhoto(userId);
        List<PhotoInfoVo> resultList = new ArrayList<>();
        List<String> fileNames = userPhoto.stream().map(e -> e.getPhotoUrl()).collect(Collectors.toList());
        Map<String, String> objectUrlMap = ossService.getObjectUrl(fileNames);
        for (PhotoInfo photoInfo : userPhoto) {
            PhotoInfoVo photoInfoVo = new PhotoInfoVo();
            BeanUtils.copyProperties(photoInfo, photoInfoVo);
            photoInfoVo.setPhotoUrl(objectUrlMap.get(photoInfo.getPhotoUrl()));
            resultList.add(photoInfoVo);
        }
        return resultList;
    }

    public void delUserPhotoById(Long userId, Long photoId) {
        photoInfoService.delPhoto(photoId);
    }

}
