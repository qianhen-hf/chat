package com.fan.mapper;

import com.fan.po.MsgRecord;
import com.fan.po.MsgRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MsgRecordMapper {
    int countByExample(MsgRecordExample example);

    int deleteByExample(MsgRecordExample example);

    int deleteByPrimaryKey(Long msgId);

    int insert(MsgRecord record);

    int insertSelective(MsgRecord record);

    List<MsgRecord> selectByExample(MsgRecordExample example);

    MsgRecord selectByPrimaryKey(Long msgId);

    int updateByExampleSelective(@Param("record") MsgRecord record, @Param("example") MsgRecordExample example);

    int updateByExample(@Param("record") MsgRecord record, @Param("example") MsgRecordExample example);

    int updateByPrimaryKeySelective(MsgRecord record);

    int updateByPrimaryKey(MsgRecord record);
}