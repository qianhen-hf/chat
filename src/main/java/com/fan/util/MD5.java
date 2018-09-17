package com.fan.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/5/17 15:41
 * @UpdateUser: hf
 * @UpdateDate: 2018/5/17 15:41
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class MD5 {
    public static String Md5Encryption(String password) {
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (Integer i = 0; i < 6; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("a", i);
            list.add(map);
        }
        Integer a = list.stream().map(e -> (Integer) e.get("a")).collect(Collectors.summingInt(e -> e));
        System.out.println(a);
    }
}
