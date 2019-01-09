package com.fan.config;

import com.fan.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/7/18 15:13
 * @UpdateUser: hf
 * @UpdateDate: 2018/7/18 15:13
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
//@Configuration
public class FilterConfig {

//    @Bean
    public FilterRegistrationBean loginFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LoginFilter());
        registration.addUrlPatterns("/*");
        registration.setName("loginFilter");
        registration.setOrder(FilterRegistrationBean.REQUEST_WRAPPER_FILTER_MAX_ORDER);
        return registration;
    }
}
