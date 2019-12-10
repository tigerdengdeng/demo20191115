package com.example.demo20191115.Interceoter;

import com.alibaba.druid.util.StringUtils;
import com.example.demo20191115.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.lettuce.core.dynamic.CommandCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.naming.CommunicationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 * preHandle 进入到控制器方法之前执行的内容
 *  boolean:
 *    true 执行
 *    false 拦截
 * 1.简化获取token数据的代码编写
 *   统一用户的权限校验（是否登录）（是否有访问该接口的权限)
 */
@Component
public class   JwtInterceptor implements HandlerInterceptor {
        @Autowired
        private JwtUtils jwtUtils;//注入jwt

        //  拦截器请求前调用
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
                //1.通过request 获取请求token信息
                String authorization=request.getHeader("Authorzation");
                System.out.println("dewdwdwd"+authorization.startsWith("Bearer"));
                //2.判断请求头是否为null，或者是否已Bearer开头
                if(!StringUtils.isEmpty(authorization) && authorization.startsWith("Bearer")==true)
                {
                        //移除空白字符
                        String token=authorization.replace( "Bearer", "");
                        //解析token
                        Claims claims=jwtUtils.checkJwt(token);

                        if(claims!=null)
                        {
                                //绑定到request域
                                request.setAttribute("user_claims",claims);
                                System.out.println(request.getAttribute("user_claims")+"我要去洗澡了");
                                return  true;

                        }
                }
                throw  new CommunicationException(authorization+"当前token"+authorization.startsWith("Bearer"));
               //return true;
        }

        // 后处理回调方法，实现处理器的后处理（但在渲染视图之前）
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        }

        // 整个请求处理完毕回调方法，即在视图渲染完毕时回调
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        }

        // 方法用于处理异步请求，当Controller中有异步请求方法的时候会触发该方法时，异步请求先支持preHandle、然后执行afterConcurrentHandlingStarted。异步线程完成之后执行preHandle、postHandle、afterCompletion
        public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        }





}
