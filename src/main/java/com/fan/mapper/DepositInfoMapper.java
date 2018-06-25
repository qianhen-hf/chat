package com.fan.mapper;

import com.fan.po.DepositInfo;
import com.fan.po.DepositInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepositInfoMapper {
    int countByExample(DepositInfoExample example);

    int deleteByExample(DepositInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DepositInfo record);

    int insertSelective(DepositInfo record);

    List<DepositInfo> selectByExample(DepositInfoExample example);

    DepositInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DepositInfo record, @Param("example") DepositInfoExample example);

    int updateByExample(@Param("record") DepositInfo record, @Param("example") DepositInfoExample example);

    int updateByPrimaryKeySelective(DepositInfo record);

    int updateByPrimaryKey(DepositInfo record);
}