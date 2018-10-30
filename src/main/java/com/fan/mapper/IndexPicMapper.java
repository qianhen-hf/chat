package com.fan.mapper;

import com.fan.po.IndexPic;
import com.fan.po.IndexPicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IndexPicMapper {
    int countByExample(IndexPicExample example);

    int deleteByExample(IndexPicExample example);

    int deleteByPrimaryKey(Long id);

    int insert(IndexPic record);

    int insertSelective(IndexPic record);

    List<IndexPic> selectByExample(IndexPicExample example);

    IndexPic selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") IndexPic record, @Param("example") IndexPicExample example);

    int updateByExample(@Param("record") IndexPic record, @Param("example") IndexPicExample example);

    int updateByPrimaryKeySelective(IndexPic record);

    int updateByPrimaryKey(IndexPic record);
}