package com.fan.mapper;

import com.fan.po.RechargePackage;
import com.fan.po.RechargePackageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RechargePackageMapper {
    int countByExample(RechargePackageExample example);

    int deleteByExample(RechargePackageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RechargePackage record);

    int insertSelective(RechargePackage record);

    List<RechargePackage> selectByExample(RechargePackageExample example);

    RechargePackage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RechargePackage record, @Param("example") RechargePackageExample example);

    int updateByExample(@Param("record") RechargePackage record, @Param("example") RechargePackageExample example);

    int updateByPrimaryKeySelective(RechargePackage record);

    int updateByPrimaryKey(RechargePackage record);
}