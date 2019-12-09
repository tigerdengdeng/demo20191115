package com.example.demo20191115.controller;

import com.alibaba.druid.util.StringUtils;
import com.example.demo20191115.config.RedisConfig;
import com.example.demo20191115.controller.base.BaseController;
import com.example.demo20191115.domain.TUser;
import com.example.demo20191115.domain.common.JsonResult;
import com.example.demo20191115.server.UserSever;
import com.example.demo20191115.utils.JwtUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.jsonwebtoken.Claims;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.naming.CommunicationException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/*
用户控制器
 */
@RestController
@RequestMapping("/user/webapi")
public class UserControllers extends BaseController {

    @Autowired   //注入查询接口
    private UserSever usersever;

    @Autowired
    private RedisConfig redisConfig; //注入Redis配置类

     @Autowired
     private  JwtUtils jwtUtils;//注入jwt

    /**
     * 分页接口
     * @param page 当前第几页 默认第一页
     * @param size 每页显示几条
     * @return  返回符合条件的集合
     */
    @GetMapping("/userlist")
    public  Object userlist(@RequestParam(value = "page",required = true)int page,
                             @RequestParam(value = "size",required = true)int size){
        PageHelper.startPage(page, size);
        List<TUser> list= usersever.userfindall();
        PageInfo<TUser>pageInfo=new PageInfo<>(list);
        Map<String,Object> data =new HashMap<>();
         data.put("total_size",pageInfo.getTotal());
         data.put("totlal_list",pageInfo.getList());
        return data;
    }


    /**
     * 查询用户ID
     * @param token
     * @return
     * @throws Exception
     */
    @PostMapping("/userwhere")
    public Object userId(HttpServletRequest request) throws  Exception{
        /**
         * 从请求头当中获取token数据
         * 1.获取请求头信息：名称=Ahthorization
         * 2.替换Bearer+空格
         * 3.解析token
         * 4.获取clamis
         */
        //获取请求头信息，名称=Authorization
//        String token =request.getHeader("token");
//      if(StringUtils.isEmpty(token))  //判断是否为空
//        {
//       throw  new CommunicationException("500");
//        }
//        //去除空格
//        String toke1n=token.replace( "Bearer", "");
//        //解析token
//        Claims claims=jwtUtils.checkJwt(toke1n);
//       //获取用户id
      return  usersever.userfindbyid((Integer) claims.get("id"));
    }

    /**
     * 登录操作
     * @param username 用户名
     * @param password 密码
     * @return 返回状态
     */
    /**
     * redis data 注解
     */
    @PostMapping(value = "/login")
    public  JsonResult userLog(@RequestBody TUser t_User) throws  Exception
    {

        JsonResult result=new JsonResult();
        if(t_User.getUsername()==null)
        {
            result.setCode("1");
            result.setMsg("参数为空");
            return  result;
        }
        if(t_User.getPassword()==null)
        {
            result.setCode("1");
            result.setMsg("参数为空");
            return  result;
        }
        TUser user= usersever.loinguser(t_User.getUsername(),t_User.getPassword());
        if(user==null)
        {
            result.setCode("1");
            result.setMsg("用户名或密码错误");
            return  result;
        }else{
            if(t_User.getUsername().equals(user.getUsername())&&t_User.getPassword().equals((user.getPassword())))
            {
//                //存入Redis  fong 后面需要些L
//                //redisConfig.set("user",user,  60l, TimeUnit.SECONDS);
//                //取出Redis
//              TUser item= (TUser) redisConfig.get("user");
//              if(item==null)
//              {
//                  result.setCode("1");
//                  result.setMsg("token已经过期");
//                  return  result;
//
//              }
//                System.out.println(item+"111111");
//                //删除redis
//                System.out.println(redisConfig.delete("user"));

                Map<String,Object>map =new HashMap<>();
                map.put("id",user.getId());
                map.put("name",user.getUsername());
                map.put("password",user.getPassword());
                map.put("status",1);
                String token=jwtUtils.geneJsonWebToken(user.getId().toString(),user.getUsername(),map,6000l);
                System.out.println(token);
                Claims tokenjm=jwtUtils.checkJwt(token);
               result.setData(token);
                result.setCode("200");
                result.setMsg("登录成功");
                return  result;
            }
        }
        return result;

    }

    /**
     * 删除操作
     * @param id 删除条件
     * @return  返回状态
     */
    @DeleteMapping("/userdele")
    public  int userDelete(@RequestParam(value = "id",required = true)int id){
     return  usersever.userfinddelete(id);
    }


    /**
     * 修改操作
     * @param user  用户数据模型
     * @return   返回状态
     */
    @PutMapping("/userupdate")
    public  int userUpdate(@RequestBody TUser user){ return usersever.userfindupdate(user); }

    /**
     * 新增操作
     * @param user  用户模型
     * @return  返回状态
     */
    @PostMapping("/useradd")
    public  int userAdd(@RequestBody TUser user)
    {
        int rows= usersever.userfindadd(user);
        System.out.println(user.getId()+"获取保存对象的ID");
        return rows;
    }
}
