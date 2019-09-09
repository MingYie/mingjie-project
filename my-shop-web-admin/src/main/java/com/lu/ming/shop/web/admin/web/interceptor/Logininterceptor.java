package com.lu.ming.shop.web.admin.web.interceptor;


import com.lu.ming.shop.domain.tbUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 18:50 2019/8/7
 * Modified By:
 */
public class Logininterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        tbUser tbuser= (tbUser) httpServletRequest.getSession().getAttribute("tbuser");
        //未登录
        if (tbuser==null){
            //用户未登录，重定向到登录页
            httpServletResponse.sendRedirect("/index");
        }
        //放行
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        // 如果请求来自登录页
//        if (modelAndView.getViewName().endsWith("index")) {
//            // 则直接重定向到首页不再显示登录页
//            httpServletResponse.sendRedirect("/shouye");
//        }
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
