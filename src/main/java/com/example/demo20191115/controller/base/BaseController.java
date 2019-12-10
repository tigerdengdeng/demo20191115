package com.example.demo20191115.controller.base;

import io.jsonwebtoken.Claims;
import io.lettuce.core.dynamic.CommandCreationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.naming.CommunicationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 父类控制器
 */
@Component
public class BaseController {

    //JWT  存取claims  已经被拦截器放入request域当中
    protected  HttpServletResponse response;
    protected  HttpServletRequest request;
    protected Claims claims;  //公共claims
    protected  String username;   //公共用户名
    protected  Integer id;  //公共用户id

    /**
     * 获取用户信息JWT
     * @param request
     * @param response
     */
    //被ModelAttribute注释的方法会在此controller每个方法执行前被执行，因此对于一个controller映射多个URL的用法来说，要谨慎使用。
    @ModelAttribute
    public  void  setResAnReq( HttpServletRequest request, HttpServletResponse response) throws  Exception {
         this.request=request;
         this.response=response;
         //取出拦截器request域当中存取的用户信息
         Claims obj= (Claims) request.getAttribute("user_claims");
         if(obj!=null)
         {
           //赋值给当前公共变量
             this.id= (Integer) obj.get("id");
             this.username= (String)obj.get("name");

         }else{
          throw new CommunicationException("500");
         }



    }
}
