package com.lcyanxi.config;

import com.lcyanxi.filter.ChannelFilter;
import com.lcyanxi.filter.RequestLogInterceptor;
import org.springframework.aop.SpringProxy;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lichang
 * Date: 2021/06/29/11:14 上午
 */
@Configuration
public class WebConfig implements WebMvcConfigurer,SpringProxy{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestLogInterceptor());
    }
    @Bean
    public FilterRegistrationBean requestFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        ChannelFilter channelFilter = new ChannelFilter();
        registrationBean.setFilter(channelFilter);
        return registrationBean;
    }
}