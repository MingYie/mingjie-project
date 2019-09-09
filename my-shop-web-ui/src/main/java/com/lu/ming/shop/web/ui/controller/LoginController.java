package com.lu.ming.shop.web.ui.controller;

import com.google.code.kaptcha.Constants;
import com.lu.ming.shop.commons.dto.BaseResult;
import com.lu.ming.shop.commons.utils.SendEmailUtils;
import com.lu.ming.shop.web.ui.api.UsersAPI;
import com.lu.ming.shop.web.ui.constants.SystemConstants;
import com.lu.ming.shop.web.ui.dto.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 9:26 2019/8/31
 * Modified By:
 */
@Controller
public class LoginController {

    @Autowired
    private SendEmailUtils sendEmailUtils;

    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    /**
     * 登录
     * @param tbUser
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(TbUser tbUser, Model model, HttpServletRequest request) throws Exception {

        boolean b = CheckVerification(tbUser,request);
        //验证码错误
        if (b != true){
            model.addAttribute("baseResult",BaseResult.fail("验证码错误，请重新输入"));
            return "login";
        }

        //拿到api接口信息返回的TbUser后，这里设备的ui再根据自己的业务给出对应的提示
        TbUser user = UsersAPI.login(tbUser);

        //前端这里ui拿到api模块提供的信息
        if (user == null){
            model.addAttribute("baseResult",BaseResult.fail("用户名或者密码错误，请重新输入"));
            return "login";
        }
        //登录成功把用户信息存到session中
        else {
            request.getSession().setAttribute(SystemConstants.SYSTEM_SESSION_USER,user);
            return "redirect:/index";
        }
    }

    /**
     * 注销
     * @param request
     * @return
     */
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        //销毁存在session中的user信息
        request.getSession().invalidate();
        return "redirect:/login";
    }

    /**
     * 验证验证码的一致性
     * @param tbUser
     * @param request
     * @return
     */
    public boolean CheckVerification(TbUser tbUser,HttpServletRequest request){
        //拿到kaptcha存在session里的验证码
        String checkCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //判断用户输入的验证码和kaptcha存在session里的验证码是否一致
        if (StringUtils.equals(checkCode,tbUser.getVerification())){
            return true;
        }
        return false;
    }
}
