package com.fan.mapper;

import com.fan.po.PhotoInfo;
import com.fan.po.PhotoInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhotoInfoMapper {
    int countByExample(PhotoInfoExample example);

    int deleteByExample(PhotoInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PhotoInfo record);

    int insertSelective(PhotoInfo record);

    List<PhotoInfo> selectByExample(PhotoInfoExample example);

    PhotoInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PhotoInfo record, @Param("example") PhotoInfoExample example);

    int updateByExample(@Param("record") PhotoInfo record, @Param("example") PhotoInfoExample example);

    int updateByPrimaryKeySelective(PhotoInfo record);

    int updateByPrimaryKey(PhotoInfo record);
}