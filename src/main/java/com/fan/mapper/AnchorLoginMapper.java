package com.fan.mapper;

import com.fan.po.AnchorLogin;
import com.fan.po.AnchorLoginExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnchorLoginMapper {
    int countByExample(AnchorLoginExample example);

    int deleteByExample(AnchorLoginExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AnchorLogin record);

    int insertSelective(AnchorLogin record);

    List<AnchorLogin> selectByExample(AnchorLoginExample example);

    AnchorLogin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AnchorLogin record, @Param("example") AnchorLoginExample example);

    int updateByExample(@Param("record") AnchorLogin record, @Param("example") AnchorLoginExample example);

    int updateByPrimaryKeySelective(AnchorLogin record);

    int updateByPrimaryKey(AnchorLogin record);
}