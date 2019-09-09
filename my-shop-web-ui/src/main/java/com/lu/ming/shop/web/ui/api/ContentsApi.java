package com.lu.ming.shop.web.ui.api;

import com.lu.ming.shop.commons.utils.HttpClientUtils;
import com.lu.ming.shop.commons.utils.MapperUtils;
import com.lu.ming.shop.web.ui.dto.TbContent;

import java.util.List;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 21:42 2019/8/28
 * Modified By:
 */
public class ContentsApi {

    /**
     * 拿到对应节点的DTO数据传输对象TbContent
     * @return
     */
    public static List<TbContent> ppt(){

        String result = HttpClientUtils.doGet(API.API_PPT_CONTENTS);
        //调用MapperUtils工具里新封装的方法返回对应节点data的json集合
        List<TbContent> tbContents = null;
        try {
            tbContents = MapperUtils.json2listByTree(result, "data", TbContent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbContents;
    }

}
