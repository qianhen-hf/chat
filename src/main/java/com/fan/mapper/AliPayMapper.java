package com.fan.mapper;

import com.fan.po.AliPay;
import com.fan.po.AliPayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AliPayMapper {
    int countByExample(AliPayExample example);

    int deleteByExample(AliPayExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AliPay record);

    int insertSelective(AliPay record);

    List<AliPay> selectByExample(AliPayExample example);

    AliPay selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AliPay record, @Param("example") AliPayExample example);

    int updateByExample(@Param("record") AliPay record, @Param("example") AliPayExample example);

    int updateByPrimaryKeySelective(AliPay record);

    int updateByPrimaryKey(AliPay record);
}