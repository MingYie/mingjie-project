package com.lu.ming.shop.web.ui.controller;

import com.lu.ming.shop.commons.dto.BaseResult;
import com.lu.ming.shop.commons.utils.SendEmailUtils;
import com.lu.ming.shop.web.ui.api.UsersAPI;
import com.lu.ming.shop.web.ui.dto.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 9:27 2019/8/31
 * Modified By:
 */
@Controller
public class RegisterController {

    @Autowired
    private SendEmailUtils sendEmailUtils;

    /**
     * 注册
     * @return
     */
    @RequestMapping(value = "regist",method = RequestMethod.GET)
    public String register(){
        return "regist";
    }

    /**
     * 注册 post
     * @param tbUser
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "regist",method = RequestMethod.POST)
    public String register(TbUser tbUser,Model model) throws Exception {
        TbUser user = UsersAPI.register(tbUser);

        if (user == null){
            model.addAttribute("baseResult", BaseResult.fail("用户名或账号或邮箱已存在"));
            return "regist";
        }
        else {
            sendEmailUtils.SendEmail("用户注册：",String.format("用户 【%s】注册了MyShop ",user.getUsername()),"l895900424@163.com");
            return "redirect:/login";
        }
    }
}
