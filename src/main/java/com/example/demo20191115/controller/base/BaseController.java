package com.example.demo20191115.controller.base;

import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 父类控制器
 */
public class BaseController {

    //JWT  存取claims  已经被拦截器放入request域当中
    protected  HttpServletResponse response;
    protected  HttpServletRequest request;
    protected Claims claims;  //公共claims
    protected  String useeName;   //公共用户名
    protected  String id;  //公共用户id

    /**
     * 获取用户信息JWT
     * @param request
     * @param response
     */
    public  void  setResAnReq(HttpServletRequest request,
                              HttpServletResponse response)
    {
         this.request=request;
         this.response=response;
         //取出拦截器request域当中存取的用户信息
         Object obj=request.getAttribute("user_claims");
         if(obj!=null)
         {
           //赋值给当前公共变量
             this.claims=(Claims)obj;
             this.id= (String) claims.get("id");
             this.useeName= (String) claims.get("username");

         }

    }
}
