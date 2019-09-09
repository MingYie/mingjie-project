package com.lu.ming.shop.web.api.dao;

import com.lu.ming.shop.domain.TbContent;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 20:07 2019/8/26
 * Modified By:
 */
@Repository
public interface TbContentDao {

    /**
     * 根据CategoryId查询内容列表
     * @param tbContent
     * @return
     */
    List<TbContent> selectByCategoryId(TbContent tbContent);
}
