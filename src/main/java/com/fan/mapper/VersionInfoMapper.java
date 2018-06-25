package com.fan.mapper;

import com.fan.po.VersionInfo;
import com.fan.po.VersionInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VersionInfoMapper {
    int countByExample(VersionInfoExample example);

    int deleteByExample(VersionInfoExample example);

    int deleteByPrimaryKey(Integer versionId);

    int insert(VersionInfo record);

    int insertSelective(VersionInfo record);

    List<VersionInfo> selectByExample(VersionInfoExample example);

    VersionInfo selectByPrimaryKey(Integer versionId);

    int updateByExampleSelective(@Param("record") VersionInfo record, @Param("example") VersionInfoExample example);

    int updateByExample(@Param("record") VersionInfo record, @Param("example") VersionInfoExample example);

    int updateByPrimaryKeySelective(VersionInfo record);

    int updateByPrimaryKey(VersionInfo record);
}