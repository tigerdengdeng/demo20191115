package com.example.demo20191115.Config;

import com.github.pagehelper.PageHelper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/*
MyBatis分页 配置类
 */
@Configuration   //配置类
public class MyBatisConfig {
  @Bean   //表示优先用这个Bea
    public PageHelper pageHelper(){
       PageHelper pageHelper=new PageHelper();
       Properties  P= new Properties();
       //配置为true时 会将RowBounds第一个参数offset当成pageNum页码使用
       P.setProperty("offsetAsPageNum","true");
       //设置为true时 使用RowBounds分页会进行count查询
      P.setProperty("rowBoundsWithCount","true");
      P.setProperty("reasonable","true");
      pageHelper.setProperties(P);
      return pageHelper;


  }

}
