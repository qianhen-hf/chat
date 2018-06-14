package com.fan.service.external;

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

import java.util.LinkedHashMap;
import java.util.Map;

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
    public static final String REGION_CN_HANGZHOU = "cn-hangzhou";
    public static final String STS_API_VERSION = "2015-04-01";

    public Map<String, String> getOssCertificate() {
        Map<String, String> respMap = new LinkedHashMap<String, String>();
        try {
            IClientProfile profile = DefaultProfile.getProfile(REGION_CN_HANGZHOU, ossConfig.getAccessKeyID(), ossConfig.getAccessKeySecret());
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
}
