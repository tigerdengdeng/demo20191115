package com.example.demo20191115.Controller;

import com.example.demo20191115.Config.wechatconfig;
import com.example.demo20191115.Mapper.videomapper;
import com.example.demo20191115.Server.testsever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {


    /*
     测试类 默认路径路由
     */
    @RequestMapping("/")
    public String helloword() {
        return "hello spring boot";
    }

    /*
    测试读取配置类；
     */
    @Autowired    //注入配置类
    private wechatconfig wechatconfig;
    @RequestMapping("/testconfig")
    public String testconfig() {
        return wechatconfig.getAppid();

    }


    /*
    测试数据库连接
     */
    @Autowired   //注入查询接口
    private testsever testsever;

    @GetMapping("/testlistdb")
    public Object testlistdb(){
        return  testsever.findbyid(1);
    }


}
