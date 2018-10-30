package com.fan.service;

import com.fan.mapper.PhotoInfoMapper;
import com.fan.po.PhotoInfo;
import com.fan.po.PhotoInfoExample;
import com.fan.service.external.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/8/6 11:29
 * @UpdateUser: hf
 * @UpdateDate: 2018/8/6 11:29
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

@Service
public class PhotoInfoService {

    @Autowired
    PhotoInfoMapper photoInfoMapper;
    @Autowired
    OssService ossService;

    public List<PhotoInfo> getUserPhoto(Long userId) {
        PhotoInfoExample photoInfoExample = new PhotoInfoExample();
        PhotoInfoExample.Criteria criteria = photoInfoExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<PhotoInfo> photoInfos = photoInfoMapper.selectByExample(photoInfoExample);
        for (PhotoInfo photoInfo : photoInfos) {
//            ossService.getObjectUrl();
        }
        return  photoInfos;
    }

    public void insertPhoto(PhotoInfo photoInfo) {
        photoInfoMapper.insertSelective(photoInfo);
    }


    public void delPhoto(Long id) {
        photoInfoMapper.deleteByPrimaryKey(id);
    }

}
