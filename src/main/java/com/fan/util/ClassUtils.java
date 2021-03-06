package com.fan.util;

import com.fan.Exception.VRabbitException;
import com.fan.Exception.VRabbitUserErrors;
import com.fan.po.AliPay;
import com.fan.requestVo.RequestAliPay;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.apache.commons.beanutils.BeanUtils;
import org.omg.CORBA.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/2/22 17:02
 * @UpdateUser: hf
 * @UpdateDate: 2018/2/22 17:02
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class ClassUtils {
    private static final Logger log = LoggerFactory.getLogger(ClassUtils.class);
    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    public static Object newInstance(Class clazz) {
        if (clazz == null) {
            String msg = "Class method parameter cannot be null.";
            throw new IllegalArgumentException(msg);
        }
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new VRabbitException(VRabbitUserErrors.ORDER_TYPE_ERROR);
        }
    }


    //返回值为随意的类型   传入参数为request 和该随意类型
    public static <T> T requestToBean(HttpServletRequest request, Class<T> beanClass, Boolean isLineToHump) {
        try {
            T bean = beanClass.newInstance();   //实例化随意类型
            Enumeration en = request.getParameterNames();   //获得参数的一个列举
            while (en.hasMoreElements()) {         //遍历列举来获取所有的参数
                String name = (String) en.nextElement();
                String lineName = name;
                if (isLineToHump) {
                    lineName = lineToHump(name);
                }
                String value = request.getParameter(name);
                //注意这里导入的是  import org.apache.commons.beanutils.BeanUtils;
                BeanUtils.setProperty(bean, lineName, value);
            }
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);  //如果错误 则向上抛运行时异常
        }
    }


    public static String humpToLine(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String reqJson = mapper.writeValueAsString(object);
        return reqJson;
    }


    public static <T> T toSnakeObject(String json, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        T reqJson = mapper.readValue(json, clazz);
        return reqJson;
    }


    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线(淘汰)
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    private static String getMethodName(String fieldName) throws Exception {
        byte[] items = fieldName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }


    public static Object mapToObject(Map<String, String> map, Class<?> beanClass, boolean isLineToHump) throws Exception {
        if (map == null)
            return null;
        Map<String, String> mapHump = new HashMap<>();
        if (isLineToHump) {
            for (String s : map.keySet()) {
                mapHump.put(lineToHump(s), map.get(s));
            }
        } else {
            mapHump = map;
        }
        Object obj = beanClass.newInstance();
        BeanUtils.populate(obj, mapHump);
        return obj;
    }

    public static Map<String, String> getParameterMap(HttpServletRequest request) {
        Map<String, String> result = new LinkedHashMap<>();
        Map<String, String[]> map = request.getParameterMap();
        for (Map.Entry<String, String[]> stringEntry : map.entrySet()) {
            if (stringEntry.getValue() != null) {
                result.put(stringEntry.getKey(), stringEntry.getValue()[0]);
            } else {
                result.put(stringEntry.getKey(), "");
            }
        }
        return result;
    }


    public static <T, V> void lineBeanToHumpBean(T t, V v) {
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(t);
                if (value == null) {
                    continue;
                }
                String name = field.getName();
                String newName = lineToHump(name);
                Type genericType = field.getGenericType();
                Class<?> aClass = Class.forName(genericType.getTypeName());
                System.out.println(aClass);
                Method m = v.getClass().getMethod("set" + getMethodName(newName), aClass);
                m.invoke(v, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static <E> List<E> copyTo(List<?> source, Class<E> destinationClass) {
        if (source.size() == 0)
            return Collections.emptyList();
        List<E> res = new ArrayList<E>(source.size());
        try {
            for (Object o : source) {
                E e = destinationClass.newInstance();
                org.springframework.beans.BeanUtils.copyProperties(o, e);
                res.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

//    public static void main(String[] args) {
//        RequestAliPay requestAliPay = new RequestAliPay();
//        requestAliPay.setApp_id("aaaa");
//        requestAliPay.setGmt_close(new Date());
//        AliPay aliPay = new AliPay();
//        lineBeanToHumpBean(requestAliPay, aliPay);
//        System.out.println(aliPay.getAppId());
//        System.out.println(aliPay.getGmtClose());
//    }
}
