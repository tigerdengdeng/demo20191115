package com.example.demo20191115.Mapper;

import com.example.demo20191115.Domain.TOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

/*
底层实现类
 */
public interface videomapper {
    /*
    查询操作
     */
    @Select("select * from t_order")
    List<TOrder> findall();

   /*
   查询条件
    */
    @Select("select * from t_order where  orderid =#{id}")
    List<TOrder> findbyid(int id);

    /*
    修改操作
     */
    @Update("update t_order set nanme=#{nanme} where orderid=#{orderid}")
    int findupdate(TOrder order);

    /*
    删除操作
     */
    @Delete("delete from t_order where orderid=#{id}")
    int finddelete(int id);


    /*
    新增操作
     */
    @Insert("insert into t_order(orderid,nanme)values(#{orderid},#{nanme})")
    @Options(useGeneratedKeys = true, keyProperty = "orderid",keyColumn = "orderid")   //获取新增用户的主键DI
  int findadd(TOrder order);

}
