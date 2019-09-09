package com.lu.ming.shop.web.ui.controller;

import com.lu.ming.shop.commons.utils.HttpClientUtils;
import com.lu.ming.shop.commons.utils.MapperUtils;
import com.lu.ming.shop.web.ui.api.ContentsApi;
import com.lu.ming.shop.web.ui.dto.TbContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 11:26 2019/8/28
 * Modified By:
 */
@Controller
public class IndexController {

    /**
     * 跳转首页
     * @return
     */
    @RequestMapping(value = {"","index"},method = RequestMethod.GET)
    public String index(Model model){
        List<TbContent> tbContents = ContentsApi.ppt();
        model.addAttribute("ppt",tbContents);
        return "index";
    }





//    /**
//     * 请求幻灯片
//     * @param model
//     */
//    private void requestContentsPPt(Model model){
//
//    }
}
