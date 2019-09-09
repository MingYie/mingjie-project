package com.lu.ming.shop.web.admin.web.controller;

import com.lu.ming.shop.domain.User;

import com.lu.ming.shop.domain.tbUser;
import com.lu.ming.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 17:00 2019/8/7
 * Modified By:
 */
//让spring-mvc扫描到@Controller控制器
@Controller
public class LoginController {

    @Autowired
    private TbUserService tbUserService;
    /**
     * 跳转登录页面
     * @return
     */
    //请求资源路径value = {"","login"}   通过get方式请求 在浏览器上请求"","login"资源路径，如果是get请求的话就执行这个方法
    @RequestMapping(value ="index",method = RequestMethod.GET)
    public String login(){
        //返回视图的名字给spring-mvc.xml里的DispatcherServlet，DispatcherServlet再根据视图解析器里的配置前缀和后缀
        //匹配上我的"index"，再返回到到页面
        return "index";//跳转
    }

    /**
     * 登录逻辑
     * @param email
     * @param password
     * @return
     */
    //    @RequestParam(required = true) 调用这个方法的时候必须带这个参数
    @RequestMapping(value =("index"),method = RequestMethod.POST)
    public String login(String email, @RequestParam(required = true) String password, HttpServletRequest httpServletRequest, Model model){

        tbUser tbuser = tbUserService.login(email, password);
        if (tbuser==null){
            model.addAttribute("message","登录名或者密码错误,请重新登录");
            return login();
        }
        else {
            httpServletRequest.getSession().setAttribute("tbuser",tbuser);
            return "redirect:/shouye";
        }


    }

    /**
     * 注销
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = ("logout"),method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest){
        //清除存在session中的值
        httpServletRequest.getSession().invalidate();
        //返回到首页
        return login();
    }
}
