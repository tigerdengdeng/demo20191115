package com.example.demo20191115.Provider;

import com.example.demo20191115.Domain.TOrder;

import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

/*
动态构建sql语句
 */
public class testprovider {

    /*
    修改动态SQL语句
     */
     public  String updatetorder(final TOrder order){
         return  new SQL(){{
             UPDATE( "order");

             //条件写法
              if(order.getNanme()!=null){
                  SET(  "nanme=#{nanme}");
              }
               WHERE( "orderid=#{orderid}");
         }}.toString();

     }

}
