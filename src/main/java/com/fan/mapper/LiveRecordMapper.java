package com.fan.mapper;

import com.fan.po.LiveRecord;
import com.fan.po.LiveRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LiveRecordMapper {
    int countByExample(LiveRecordExample example);

    int deleteByExample(LiveRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LiveRecord record);

    int insertSelective(LiveRecord record);

    List<LiveRecord> selectByExample(LiveRecordExample example);

    LiveRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LiveRecord record, @Param("example") LiveRecordExample example);

    int updateByExample(@Param("record") LiveRecord record, @Param("example") LiveRecordExample example);

    int updateByPrimaryKeySelective(LiveRecord record);

    int updateByPrimaryKey(LiveRecord record);
}