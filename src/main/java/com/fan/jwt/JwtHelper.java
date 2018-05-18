package com.fan.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.factories.DefaultJWSVerifierFactory;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * hf
 */
public class JwtHelper {
    private static Logger logger = LoggerFactory.getLogger(JwtHelper.class);

    private static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");

    public static Claims parseJWT(String jsonWebToken, String signatureKey) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(createTokenSecretData(signatureKey))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            logger.info("toekn解析错误{}",ex.getMessage());
            return null;
        }
    }

    public static String createJWT(String uid, String role,
                                   String audience, String issuer, long TTLMillis, String base64Security) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = createTokenSecretData(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim("role", role)
                .claim("uid", uid)
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (TTLMillis >= 0) {
            long expMillis = nowMillis + TTLMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }
        //生成JWT
        return builder.compact();
    }


    private static byte[] createTokenSecretData(String sid) {
        // 使用[utf8编码]生成[16进制]，[小写]的[md5]字符串的字节数组
        return DigestUtils.md5Hex(sid.getBytes(CHARSET_UTF8)).toLowerCase().getBytes(CHARSET_UTF8);
    }

    public static boolean validToken(String token, String signatureKey) {
        boolean flag = false;
        try {
            Claims claims = parseJWT(token, signatureKey);
            if (null == claims) {
                return false;
            }
            // 解析token
            final SignedJWT parsedJWT = SignedJWT.parse(token);
            // 获取Header
            final JWSHeader jwtHeader = parsedJWT.getHeader();
            // 创建SecretKey对象
            final SecretKeySpec secretKey = new SecretKeySpec(createTokenSecretData(signatureKey),
                    jwtHeader.getAlgorithm().getName());
            // 创建Verifier对象
            final JWSVerifier verifier = new DefaultJWSVerifierFactory().createJWSVerifier(jwtHeader, secretKey);
            // 验证
            flag = parsedJWT.verify(verifier);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static Map<String, Object> getPayloadMap(String token) {
        Map<String, Object> map = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            final SignedJWT signedJWT = SignedJWT.parse(token);
            String dataJson = signedJWT.getPayload().toString();
            map = objectMapper.readValue(dataJson, Map.class);
        } catch (Exception e) {
            logger.error("token解析异常");
        }
        return map;
    }
}
