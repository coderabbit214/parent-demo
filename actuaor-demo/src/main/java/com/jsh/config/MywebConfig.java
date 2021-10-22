package com.jsh.config;

import com.jsh.filter.InterfaceStatisticsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MywebConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean filterRegist() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new InterfaceStatisticsFilter());
        frBean.addUrlPatterns("/*");
        return frBean;
    }
}