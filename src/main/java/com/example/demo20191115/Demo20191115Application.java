package com.example.demo20191115;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*
mybatis 注解  让springboot 知道mapper类在那个路径下
 */
@MapperScan("com.example.demo20191115.Mapper")
public class Demo20191115Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo20191115Application.class, args);
    }

}
