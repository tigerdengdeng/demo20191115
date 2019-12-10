package com.example.demo20191115.config;

import com.example.demo20191115.Interceoter.JwtInterceptor;
import com.example.demo20191115.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义拦截器配置文件
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
  @Autowired
  protected JwtInterceptor jwtInterceptor;

    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**").excludePathPatterns("/user/webapi/login");
    }


}