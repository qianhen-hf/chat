package com.fan.controller.callback;

import com.aliyun.oss.common.utils.BinaryUtil;
import com.fan.po.PhotoInfo;
import com.fan.service.PhotoInfoService;
import com.fan.util.ClassUtils;
import com.fan.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/7/11 9:46
 * @UpdateUser: hf
 * @UpdateDate: 2018/7/11 9:46
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@RestController
@Slf4j
@ApiIgnore
@RequestMapping("callback")
public class OssCallBackController {
    @Autowired
    PhotoInfoService photoInfoService;

    @RequestMapping("ossCallBack")
    public ResponseResult ossCallBack(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("--------ossCallBack------------");
        ResponseResult responseResult = new ResponseResult(true);
        try {
            Map<String, String> parameterMap = ClassUtils.getParameterMap(request);
            StringJoiner stringJoiner = new StringJoiner("&");
            for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
                stringJoiner.add(entry.getKey().concat("=") + URLEncoder.encode(entry.getValue(), "utf-8").replace("+", "%20"));
            }
            log.info("body----{}", stringJoiner.toString());
            boolean b = VerifyOSSCallbackRequest(request, stringJoiner.toString());
            log.info("验证结果:{}", b);
            if (!b) {
                return new ResponseResult(false);
            }
            PhotoInfo photoInfo = new PhotoInfo();
            photoInfo.setCreateTime(new Date());
            photoInfo.setUserId(Long.parseLong(parameterMap.get("userId")));
            photoInfo.setPhotoUrl(parameterMap.get("filename"));
            photoInfoService.insertPhoto(photoInfo);
            responseResult.setData(photoInfo.getId());
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseResult(false);
        }
        return responseResult;
    }


    protected boolean VerifyOSSCallbackRequest(HttpServletRequest request, String ossCallbackBody) throws NumberFormatException, IOException {
        boolean ret = false;
        String autorizationInput = request.getHeader("Authorization");
        String pubKeyInput = request.getHeader("x-oss-pub-key-url");
        byte[] authorization = BinaryUtil.fromBase64String(autorizationInput);
        byte[] pubKey = BinaryUtil.fromBase64String(pubKeyInput);
        String pubKeyAddr = new String(pubKey, "utf-8");
        if (!pubKeyAddr.startsWith("http://gosspublic.alicdn.com/") && !pubKeyAddr.startsWith("https://gosspublic.alicdn.com/")) {
            System.out.println("pub key addr must be oss addrss");
            return false;
        }
        String retString = executeGet(pubKeyAddr);
        retString = retString.replace("-----BEGIN PUBLIC KEY-----", "");
        retString = retString.replace("-----END PUBLIC KEY-----", "");
        String queryString = request.getQueryString();
        String uri = request.getRequestURI();
        String decodeUri = java.net.URLDecoder.decode(uri, "UTF-8");
        String authStr = decodeUri;
        if (queryString != null && !queryString.equals("")) {
            authStr += "?" + queryString;
        }
        authStr += "\n" + ossCallbackBody;
        ret = doCheck(authStr, authorization, retString);
        return ret;
    }

    public static boolean doCheck(String content, byte[] sign, String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = BinaryUtil.fromBase64String(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
            java.security.Signature signature = java.security.Signature.getInstance("MD5withRSA");
            signature.initVerify(pubKey);
            signature.update(content.getBytes());
            boolean bverify = signature.verify(sign);
            return bverify;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String executeGet(String url) {
        BufferedReader in = null;

        String content = null;
        try {
            // 定义HttpClient
            @SuppressWarnings("resource")
            DefaultHttpClient client = new DefaultHttpClient();
            // 实例化HTTP方法
            HttpGet request = new HttpGet();
            request.setURI(new URI(url));
            HttpResponse response = client.execute(request);

            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            content = sb.toString();
        } catch (Exception e) {
        } finally {
            if (in != null) {
                try {
                    in.close();// 最后要关闭BufferedReader
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return content;
        }

    }


}
