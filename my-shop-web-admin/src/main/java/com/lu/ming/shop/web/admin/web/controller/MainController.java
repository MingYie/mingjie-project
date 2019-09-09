package com.lu.ming.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 17:56 2019/8/7
 * Modified By:
 */
@Controller
public class MainController {

    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping(value = "shouye",method = RequestMethod.GET)
    public String main(){
        return "shouye";
    }
}
