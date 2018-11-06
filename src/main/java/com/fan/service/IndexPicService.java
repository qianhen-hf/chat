package com.fan.service;

import com.fan.mapper.IndexPicMapper;
import com.fan.mapper.UserMapper;
import com.fan.po.IndexPic;
import com.fan.po.PhotoInfo;
import com.fan.po.User;
import com.fan.po.UserExample;
import com.fan.service.external.OssService;
import com.fan.vo.IndexPicVo;
import com.fan.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/10/30 9:32
 * @UpdateUser: hf
 * @UpdateDate: 2018/10/30 9:32
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Service
public class IndexPicService {
    @Autowired
    IndexPicMapper indexPicMapper;
    @Autowired
    OssService ossService;
    @Autowired
    PhotoInfoService photoInfoService;
    @Autowired
    UserMapper userMapper;


    public List<IndexPicVo> getIndexPics() {
        List<IndexPic> indexPics = indexPicMapper.selectByExample(null);
        List<IndexPicVo> resultList = new ArrayList<>();
        for (IndexPic indexPic : indexPics) {
            IndexPicVo indexPicVo = new IndexPicVo();
            BeanUtils.copyProperties(indexPic, indexPicVo);
            PhotoInfo photoInfo = photoInfoService.selectPhotoInfoById(indexPic.getPhotoId());
            String url = ossService.getObjectUrl(photoInfo.getPhotoUrl());
            indexPicVo.setUrl(url);
            resultList.add(indexPicVo);
        }
        return resultList;
    }

    public List<UserVo> getIndexCustomers() {
        List<UserVo> resultList = new ArrayList<>();
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andStatusIn(Arrays.asList(2, 3));
        criteria.andUserTypeEqualTo(2);
        List<User> users = userMapper.selectByExample(userExample);
        for (User user : users) {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            resultList.add(userVo);
        }
        return resultList;
    }
}
