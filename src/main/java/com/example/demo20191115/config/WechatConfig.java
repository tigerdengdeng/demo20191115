package com.example.demo20191115.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/*
微信配置类
 */
@Configuration
@Data
@PropertySource(value = "classpath:application.properties")   //指定在哪里读取配置文件路径  默认为项目根目录
public class WechatConfig {



    /*
            读取配置微信ID
             */
    @Value("${wx.appid}")
    private String appid;


    /*
           读取微信配置项
            */
    @Value("${wx.appsecret}")
    private String appsecret;
}
