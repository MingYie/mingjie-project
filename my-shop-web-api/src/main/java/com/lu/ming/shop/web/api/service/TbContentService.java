package com.lu.ming.shop.web.api.service;

import com.lu.ming.shop.domain.TbContent;

import java.util.List;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 16:35 2019/8/27
 * Modified By:
 */
public interface TbContentService {

    /**
     * 根据CategoryId查询内容列表
     * @param categoryId
     * @return
     */
    List<TbContent> selectByCategoryId(Long categoryId);
}
