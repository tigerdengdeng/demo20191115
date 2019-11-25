package com.example.demo20191115.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/*
微信配置类
 */
@Configuration
@PropertySource(value = "classpath:application.properties")   //指定在哪里读取配置文件路径  默认为项目根目录
public class wechatconfig {

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    /*
            读取配置微信ID
             */
    @Value("${wx.appid}")
    private String appid;

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    /*
           读取微信配置项
            */
    @Value("${wx.appsecret}")
    private String appsecret;
}
