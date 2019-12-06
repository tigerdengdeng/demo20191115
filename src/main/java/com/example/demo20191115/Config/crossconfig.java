package com.example.demo20191115.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 重写WebMvcConfigurer方法 实现跨域
 */
@Configuration
public class crossconfig  implements WebMvcConfigurer
{
    @Override
    public  void addCorsMappings(CorsRegistry registry)
    {

        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("*");

    }
}
