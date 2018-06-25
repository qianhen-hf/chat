package com.fan.mapper;

import com.fan.po.ChargeInfo;
import com.fan.po.ChargeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChargeInfoMapper {
    int countByExample(ChargeInfoExample example);

    int deleteByExample(ChargeInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ChargeInfo record);

    int insertSelective(ChargeInfo record);

    List<ChargeInfo> selectByExample(ChargeInfoExample example);

    ChargeInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ChargeInfo record, @Param("example") ChargeInfoExample example);

    int updateByExample(@Param("record") ChargeInfo record, @Param("example") ChargeInfoExample example);

    int updateByPrimaryKeySelective(ChargeInfo record);

    int updateByPrimaryKey(ChargeInfo record);
}