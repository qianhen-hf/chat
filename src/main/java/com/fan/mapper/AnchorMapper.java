package com.fan.mapper;

import com.fan.po.Anchor;
import com.fan.po.AnchorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnchorMapper {
    int countByExample(AnchorExample example);

    int deleteByExample(AnchorExample example);

    int deleteByPrimaryKey(Long anchorId);

    int insert(Anchor record);

    int insertSelective(Anchor record);

    List<Anchor> selectByExample(AnchorExample example);

    Anchor selectByPrimaryKey(Long anchorId);

    int updateByExampleSelective(@Param("record") Anchor record, @Param("example") AnchorExample example);

    int updateByExample(@Param("record") Anchor record, @Param("example") AnchorExample example);

    int updateByPrimaryKeySelective(Anchor record);

    int updateByPrimaryKey(Anchor record);
}