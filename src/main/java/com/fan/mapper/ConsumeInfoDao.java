package com.fan.mapper;

import com.fan.vo.AccountDetailVo;
import com.fan.vo.GiftDetailVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ConsumeInfoDao {
    //用户查询打赏主播排行榜id集合（根据倒序排列）
    @Select("select a.anchor,SUM(b.vcorn) as vcorns from v_consume_info a ,v_gift_info b where a.user_id = #{userId} and a.gift_id = b.gift_id  ORDER BY vcorns desc")
    public List<Long> findAnchorOrder(Long userId);


    @Select("SELECT a.user_id,b.gift_name,b.vcorn,c.photo as photo_url,c.nick_name,DATE_FORMAT(a.create_time,'%Y-%m-%d') as receive_time from v_consume_info a ,v_gift_info b,v_user c where  a.anchor = #{userId} and a.gift_id = b.gift_id and a.user_id = c.user_id ORDER BY a.create_time desc LIMIT #{start},#{end}")
    public List<GiftDetailVo> findGiftInfoByPage(@Param("userId") long userId, @Param("start") long start, @Param("end") long end);



    @Select("SELECT temp.title, DATE_FORMAT( temp.occur_time, '%Y-%m-%d %H:%i:%s' ) as occur_time, temp.vcorn, temp.type FROM \n" +
            "( SELECT '提现' AS title, a.apply_time AS occur_time, a.apply_amount AS vcorn, '1' AS type FROM v_deposit_info a WHERE a.user_id = #{userId} \n" +
            "UNION SELECT a.gift_name AS title, b.create_time AS occur_time, a.vcorn, '2' AS type FROM v_gift_info a, v_consume_info b WHERE b.anchor = #{userId} AND b.gift_id = a.gift_id ) temp \n" +
            "ORDER BY temp.occur_time DESC LIMIT #{start}, #{end}")
    public List<AccountDetailVo> findAccountDetail(@Param("userId") long userId, @Param("start") long start, @Param("end") long end);


    @Select("SELECT IFNULL(sum(a.vcorn),0) as account_total FROM v_gift_info a, v_consume_info b WHERE b.anchor = #{userId} AND b.gift_id = a.gift_id")
    public long findAccountTotal(long userId);

    @Select("SELECT IFNULL(sum(a.vcorn),0) as account_day FROM v_gift_info a, v_consume_info b WHERE b.anchor = #{userId} AND b.gift_id = a.gift_id and b.create_time >= DATE_FORMAT( NOW(), '%Y-%m-%d')")
    public long findAccountDay(long userId);

}