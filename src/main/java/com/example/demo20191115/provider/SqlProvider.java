package com.example.demo20191115.provider;

import com.example.demo20191115.domain.TUser;
import org.apache.ibatis.jdbc.SQL;

/*
动态构建sql语句
 */
public class SqlProvider {

    /*
    修改动态SQL语句  根据传入的条件 是否为null 来判断要修改哪些字段
     */
     public  String updatetuser(final TUser user){
         return  new SQL(){{
             UPDATE( "user");

             //条件写法
              if(user.getUsername()!=null){
                  SET(  "username=#{username}");
              }
              if(user.getStatus()>0)
              {
                  SET( "status=#{status}");
              }
               WHERE( "id=#{id}");
         }}.toString();

     }

}
