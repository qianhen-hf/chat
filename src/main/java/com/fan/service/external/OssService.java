package com.fan.service.external;

import com.aliyun.oss.OSSClient;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.fan.config.OssConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.*;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/6/11 9:53
 * @UpdateUser: hf
 * @UpdateDate: 2018/6/11 9:53
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

@Service
public class OssService {
    @Autowired
    OssConfig ossConfig;
    public static final String REGION_CN_SHENZHEN = "cn-shangzhou";   //"cn-hangzhou  cn-shanghai 都不行"
    public static final String STS_API_VERSION = "2015-04-01";

    public Map<String, String> getOssCertificate() {
        Map<String, String> respMap = new LinkedHashMap<String, String>();
        try {
            String endpoint = "sts.aliyuncs.com";
            DefaultProfile.addEndpoint(REGION_CN_SHENZHEN, REGION_CN_SHENZHEN, "Sts", endpoint);
            IClientProfile profile = DefaultProfile.getProfile(REGION_CN_SHENZHEN, ossConfig.getAccessKeyID(), ossConfig.getAccessKeySecret());
            DefaultAcsClient client = new DefaultAcsClient(profile);
            // 创建一个 AssumeRoleRequest 并设置请求参数
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setVersion(STS_API_VERSION);
            request.setMethod(MethodType.POST);
            request.setProtocol(ProtocolType.HTTPS);
            request.setRoleArn(ossConfig.getRoleArn());
            request.setRoleSessionName("alice-001");
            request.setPolicy(ossConfig.getPolicy());
            request.setDurationSeconds(ossConfig.getTokenExpireTime());

            // 发起请求，并得到response
            final AssumeRoleResponse stsResponse = client.getAcsResponse(request);

            respMap.put("StatusCode", "200");
            respMap.put("AccessKeyId", stsResponse.getCredentials().getAccessKeyId());
            respMap.put("AccessKeySecret", stsResponse.getCredentials().getAccessKeySecret());
            respMap.put("SecurityToken", stsResponse.getCredentials().getSecurityToken());
            respMap.put("Expiration", stsResponse.getCredentials().getExpiration());
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return respMap;
    }

    public Map<String, String> getObjectUrl(List<String> fileNames) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String bucketName = "<yourBucketName>";
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, ossConfig.getAccessKeyID(), ossConfig.getAccessKeySecret());
        // 设置URL过期时间为1小时。
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        Map<String, String> map = new HashMap<>();
        for (String objectName : fileNames) {
            Date expiration = new Date(new Date().getTime() + 3600 * 1000);
            URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
            map.put(objectName, url.toString());
        }
        // 关闭OSSClient。
        ossClient.shutdown();
        return map;
    }
}
