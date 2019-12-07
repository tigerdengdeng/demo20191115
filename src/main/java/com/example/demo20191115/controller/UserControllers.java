package com.example.demo20191115.controller;

import com.example.demo20191115.domain.TUser;
import com.example.demo20191115.domain.common.JsonResult;
import com.example.demo20191115.server.UserSever;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
用户控制器
 */
@RestController
@RequestMapping("/user/webapi")
public class UserControllers {

    @Autowired   //注入查询接口
    private UserSever usersever;

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
     * 根据条件查询操作
     * @param id 条件ID
     * @return  返回状态
     */
    @GetMapping("/userid")
    public Object userId(@RequestParam(value = "id",required = true)int id){

        return usersever.userfindbyid(id);
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
    @Autowired
    private RedisTemplate redisTemplate;
    @PostMapping(value = "/login")
    public  JsonResult userLog(@RequestBody TUser t_User)
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
                //存入Redis
                redisTemplate.opsForValue().set("user",user);
                result.setCode("0");
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
