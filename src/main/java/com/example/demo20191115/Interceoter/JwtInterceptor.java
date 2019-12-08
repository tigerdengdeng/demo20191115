package com.example.demo20191115.Interceoter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 * preHandle 进入到控制器方法之前执行的内容
 *  boolean:
 *    true 执行
 *    false 拦截
 */
public class JwtInterceptor extends HandlerInterceptorAdapter {

        //  拦截器请求前调用
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
            // to do something.....
            return true;
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
