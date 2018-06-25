package com.fan.mapper;

import com.fan.po.FocusInfo;
import com.fan.po.FocusInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FocusInfoMapper {
    int countByExample(FocusInfoExample example);

    int deleteByExample(FocusInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FocusInfo record);

    int insertSelective(FocusInfo record);

    List<FocusInfo> selectByExample(FocusInfoExample example);

    FocusInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FocusInfo record, @Param("example") FocusInfoExample example);

    int updateByExample(@Param("record") FocusInfo record, @Param("example") FocusInfoExample example);

    int updateByPrimaryKeySelective(FocusInfo record);

    int updateByPrimaryKey(FocusInfo record);
}