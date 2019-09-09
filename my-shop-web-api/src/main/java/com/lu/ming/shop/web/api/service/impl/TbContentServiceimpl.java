package com.lu.ming.shop.web.api.service.impl;

import com.lu.ming.shop.domain.TbContent;
import com.lu.ming.shop.domain.TbContentCategory;
import com.lu.ming.shop.web.api.dao.TbContentDao;
import com.lu.ming.shop.web.api.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 16:35 2019/8/27
 * Modified By:
 */
@Service
public class TbContentServiceimpl implements TbContentService {

    @Autowired
    private TbContentDao tbContentDao;

    @Override
    public List<TbContent> selectByCategoryId(Long categoryId) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setId(categoryId);


        TbContent tbContent = new TbContent();
        tbContent.setTbContentCategory(tbContentCategory);

        return tbContentDao.selectByCategoryId(tbContent);
    }
}
