package com.fan.mapper;

import com.fan.po.GiftInfo;
import com.fan.po.GiftInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GiftInfoMapper {
    int countByExample(GiftInfoExample example);

    int deleteByExample(GiftInfoExample example);

    int deleteByPrimaryKey(Long giftId);

    int insert(GiftInfo record);

    int insertSelective(GiftInfo record);

    List<GiftInfo> selectByExample(GiftInfoExample example);

    GiftInfo selectByPrimaryKey(Long giftId);

    int updateByExampleSelective(@Param("record") GiftInfo record, @Param("example") GiftInfoExample example);

    int updateByExample(@Param("record") GiftInfo record, @Param("example") GiftInfoExample example);

    int updateByPrimaryKeySelective(GiftInfo record);

    int updateByPrimaryKey(GiftInfo record);
}