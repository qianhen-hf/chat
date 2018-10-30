package com.fan.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ConsumeInfoDao {
    //用户查询打赏主播排行榜id集合（根据倒序排列）
    @Select("select a.anchor,SUM(b.vcorn) as vcorns from v_consume_info a ,v_gift_info b where a.user_id = #{userId} and a.gift_id = b.gift_id  ORDER BY vcorns desc")
    public List<Long> findAnchorOrder(Long userId);
}