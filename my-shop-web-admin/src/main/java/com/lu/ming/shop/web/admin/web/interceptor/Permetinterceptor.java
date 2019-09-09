package com.lu.ming.shop.web.admin.web.interceptor;

import com.lu.ming.shop.domain.User;
import com.lu.ming.shop.domain.tbUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 19:16 2019/8/7
 * Modified By:
 */
public class Permetinterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        以index结尾的请求 当返回json数据的时候，就不再是返回视图modelAndView了，所以要设置modelAndView != null && modelAndView.getViewName() != null
        if (modelAndView != null && modelAndView.getViewName() != null && modelAndView.getViewName().endsWith("index")){
            tbUser tbuser= (tbUser) httpServletRequest.getSession().getAttribute("tbuser");
            if (tbuser!=null){
                httpServletResponse.sendRedirect("/shouye");
            }
        }
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
