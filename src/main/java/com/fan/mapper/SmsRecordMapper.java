package com.fan.mapper;

import com.fan.po.SmsRecord;
import com.fan.po.SmsRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsRecordMapper {
    int countByExample(SmsRecordExample example);

    int deleteByExample(SmsRecordExample example);

    int deleteByPrimaryKey(Long smsId);

    int insert(SmsRecord record);

    int insertSelective(SmsRecord record);

    List<SmsRecord> selectByExample(SmsRecordExample example);

    SmsRecord selectByPrimaryKey(Long smsId);

    int updateByExampleSelective(@Param("record") SmsRecord record, @Param("example") SmsRecordExample example);

    int updateByExample(@Param("record") SmsRecord record, @Param("example") SmsRecordExample example);

    int updateByPrimaryKeySelective(SmsRecord record);

    int updateByPrimaryKey(SmsRecord record);
}