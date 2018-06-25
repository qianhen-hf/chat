package com.fan.mapper;

import com.fan.po.ConsumeInfo;
import com.fan.po.ConsumeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConsumeInfoMapper {
    int countByExample(ConsumeInfoExample example);

    int deleteByExample(ConsumeInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ConsumeInfo record);

    int insertSelective(ConsumeInfo record);

    List<ConsumeInfo> selectByExample(ConsumeInfoExample example);

    ConsumeInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ConsumeInfo record, @Param("example") ConsumeInfoExample example);

    int updateByExample(@Param("record") ConsumeInfo record, @Param("example") ConsumeInfoExample example);

    int updateByPrimaryKeySelective(ConsumeInfo record);

    int updateByPrimaryKey(ConsumeInfo record);
}