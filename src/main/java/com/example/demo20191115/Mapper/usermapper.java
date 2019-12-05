package com.example.demo20191115.Mapper;
import com.example.demo20191115.Domain.TUser;
import com.example.demo20191115.Provider.sqlprovider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/*
简单的使用注解方式完成  复杂的sql 使用xml
 */
public interface usermapper {
    /**
     * 查询操作
     * @return  返回符合结果集合
     */
    @Select("select * from t_user")
    List<TUser> userfindall();

    /**
     * 条件查询
     * @param id 主键id
     * @return  返回符合结果集合
     */
    @Select("select * from t_user where  id =#{id}")
    List<TUser> userfindbyid(int id);

    /**
     * 修改操作
     * @param user 用户模型
     * @return  返回状态
     */
    //@Update("update t_user set username=#{username} where id=#{id}")
    @UpdateProvider(type = sqlprovider.class,method = "updatetuser")
     int userfindupdate(TUser user);

    /**
     * 删除操作
     * @param id  主键ID
     * @return   返回状态
     */
    @Delete("delete from t_user where id=#{id}")
    int userfinddelete(int id);


    /**
     * 新增操作
     * @param user  用户数据模型
     * @return  返回状态
     */
    @Insert("insert into t_user(id,username)values(#{id},#{username})")
    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn = "id")
  int userfindadd(TUser user);

}
