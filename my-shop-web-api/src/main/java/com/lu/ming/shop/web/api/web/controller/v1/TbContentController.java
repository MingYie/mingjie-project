package com.lu.ming.shop.web.api.web.controller.v1;

import com.lu.ming.shop.commons.dto.BaseResult;
import com.lu.ming.shop.domain.TbContent;
import com.lu.ming.shop.web.api.service.TbContentService;
import com.lu.ming.shop.web.api.web.dto.TbContentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 17:01 2019/8/27
 * Modified By:
 * @RestController 注解相当于 @ResponseBody ＋ @Controller合在一起的作用
 */
@RestController
@RequestMapping(value = "${api.path.v1}/content")
public class TbContentController {

    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id){
        TbContent tbContent = null;

        if (id == null){
            tbContent = new TbContent();
        }

        return null;
    }


    /**
     * 幻灯片接口
     * 根据类别ID查询内容列表
     * @param  查询id：89  http://localhost:8081/v1/api/content/89
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "ppt",method = RequestMethod.GET)
    public BaseResult findPPT(){
        //new一个DTO内容数据传输对象集合
        List<TbContentDTO> tbContentDTOS = null;
        //调用底层dao返回个装满TbContent对象的集合
        List<TbContent> tbContents = tbContentService.selectByCategoryId(89L);
        if (tbContents != null && tbContents.size()>0){
            tbContentDTOS = new ArrayList<>();
            //遍历TbContent对象的集合
            for (TbContent tbContent1 : tbContents) {
                //反射封装DTO  tbContent1是源对象，dto是目标对象
                TbContentDTO dto = new TbContentDTO();
                BeanUtils.copyProperties(tbContent1,dto);
                tbContentDTOS.add(dto);
            }
        }
        //返回DTO内容数据传输对象集合
        return BaseResult.success("成功",tbContentDTOS);
    }

}


