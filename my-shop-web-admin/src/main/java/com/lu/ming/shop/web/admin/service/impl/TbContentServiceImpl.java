package com.lu.ming.shop.web.admin.service.impl;

import com.lu.ming.shop.commons.dto.BaseResult;
import com.lu.ming.shop.commons.dto.pageInfo;
import com.lu.ming.shop.commons.utils.RegexUtil;
import com.lu.ming.shop.commons.validator.BeanValidator;
import com.lu.ming.shop.domain.TbContent;
import com.lu.ming.shop.web.admin.abstarct.AbstractBaseServiceimpl;
import com.lu.ming.shop.web.admin.dao.TbContentDao;
import com.lu.ming.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 17:51 2019/8/15
 * Modified By:
 */
@Service
public class TbContentServiceImpl extends AbstractBaseServiceimpl<TbContent,TbContentDao> implements TbContentService {
//
//    @Autowired
//    private TbContentDao tbContentDao;
//
//    @Override
//    public List<TbContent> selectAll() {
//        return tbContentDao.selectAll();
//    }

    @Override
    public BaseResult save(TbContent tbContent) {
        /**
         *  因为插入操作是不带id过来的，所以判断id就能看是新增insert 还是编辑updated
         */
        //BeanValidator工具类验证返回的BaseResult类中的自定义消息
        String validator = BeanValidator.validator(tbContent);
        //验证失败 把validator里的字符串返回
        if (validator != null){
            return BaseResult.fail(validator);
        }
//        验证成功，才能进行新增和编辑操作
        else {
            tbContent.setUpdated(new Date());

            //id为null就是新增insert
            if (tbContent.getId()==null){
                tbContent.setCreated(new Date());
                dao.insert(tbContent);
            }

            //有id传过来就是编辑updated
            else {
                update(tbContent);
            }
            //新增和编辑都是保存信息成功
            return BaseResult.success("内容信息保存成功！");
        }
    }




//    @Override
//    public void delete(Long id) {
//        tbContentDao.delete(id);
//    }
//
//    @Override
//    public TbContent findById(Long id) {
//        return tbContentDao.findById(id);
//    }
//
//    @Override
//    public void update(TbContent tbContent) {
//        tbContentDao.update(tbContent);
//    }
//
//    @Override
//    public void deleteArray(String[] ids) {
//        tbContentDao.deleteArray(ids);
//    }

//    @Override
//    public int count(TbContent tbContent) {
//        return tbContentDao.count(tbContent);
//    }

}
