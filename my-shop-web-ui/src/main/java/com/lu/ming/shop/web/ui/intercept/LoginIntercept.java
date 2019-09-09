package com.lu.ming.shop.web.ui.intercept;

import com.lu.ming.shop.web.ui.constants.SystemConstants;
import com.lu.ming.shop.web.ui.dto.TbUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:MingYie
 * @Description 登录拦截器
 * @Date:Created in 15:03 2019/8/31
 * Modified By:
 */
public class LoginIntercept implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //拿到存在session会话里tbUser信息
        TbUser user = (TbUser) httpServletRequest.getSession().getAttribute(SystemConstants.SYSTEM_SESSION_USER);

        //未登录状态 session里没信息
        if (user == null){
            //放行
            return true;
        }
        //登陆过了 session里面有信息 重定向到首页，防止一直登录。登陆过了就不能再让用户看到登录页和注册页
        else {
            httpServletResponse.sendRedirect("/index");
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
