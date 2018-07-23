package com.fan.filter;

import com.fan.common.ConstValue;
import com.fan.common.InitUrlData;
import com.fan.common.UserHolder;
import com.fan.config.PrefixConfig;
import com.fan.jwt.JwtHelper;
import com.fan.po.User;
import com.fan.service.redis.RedisOperator;
import com.fan.util.MD5;
import com.fan.vo.ResponseResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class LoginFilter implements Filter {
    private RedisOperator redisOperator;
    private PrefixConfig prefixConfig;
    private InitUrlData initUrlData = new InitUrlData();

    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext context = filterConfig.getServletContext();
        ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(context);
        redisOperator = (RedisOperator) ac.getBean("redisOperator");
        prefixConfig = (PrefixConfig) ac.getBean("prefixConfig");

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        System.out.println(req.getRequestURI());
        if (matchUrl(req.getRequestURI())) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String token = req.getHeader("token");
//        String token = req.getParameter("token");
        if (StringUtils.isBlank(token)) {
            resp.sendRedirect(ConstValue.TOKEN_ISNULL);
            return;
        }
        Map<String, Object> payloadMap = JwtHelper.getPayloadMap(token);
        String uid = StringUtils.join(payloadMap.get("uid"));
        boolean b = JwtHelper.validToken(token, MD5.Md5Encryption(uid));
        if (b) {
            String keyValue = redisOperator.get(prefixConfig.getUserIdPrefix() + uid);
            if (StringUtils.isBlank(keyValue)) {
                resp.sendRedirect(ConstValue.OVER_TIME);
                return;
            }
            User user = objectMapper.readValue(keyValue, User.class);
            UserHolder.add(user);
            ParameterRequestWrapper requestParameterWrapper = new ParameterRequestWrapper(req);
            requestParameterWrapper.addParameter("userId", uid);
            filterChain.doFilter(requestParameterWrapper, servletResponse);
        } else {
            resp.sendRedirect(ConstValue.TOKEN_VALIDATE);
        }


    }

    public ResponseResult getResult(String msg, Integer code) {
        ResponseResult responseResult = new ResponseResult(false);
        responseResult.setCode(code);
        responseResult.setMsg(msg);
        return responseResult;
    }


    public boolean matchUrl(String url) {
        Map<String, Integer> urls = initUrlData.getUrls();
        if (urls.containsKey(url)) {
            return true;
        }
        for (String defaultUrl : urls.keySet()) {
            Integer integer = urls.get(defaultUrl);
            if (integer == 2 && url.contains(defaultUrl)) {
                return true;
            }
        }
        return false;
    }

    public void destroy() {

    }
}
