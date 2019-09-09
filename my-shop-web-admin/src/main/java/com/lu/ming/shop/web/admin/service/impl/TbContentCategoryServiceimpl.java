package com.lu.ming.shop.web.admin.service.impl;

import com.lu.ming.shop.commons.dto.BaseResult;
import com.lu.ming.shop.commons.dto.pageInfo;
import com.lu.ming.shop.commons.validator.BeanValidator;
import com.lu.ming.shop.domain.TbContentCategory;
import com.lu.ming.shop.web.admin.abstarct.AbstarctBaseTreeServiceimpl;
import com.lu.ming.shop.web.admin.dao.TbContentCategoryDao;
import com.lu.ming.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 11:58 2019/8/15
 * Modified By:
 */
@Service
@Transactional(readOnly = true)
public class TbContentCategoryServiceimpl extends AbstarctBaseTreeServiceimpl<TbContentCategory,TbContentCategoryDao> implements TbContentCategoryService{





//    @Autowired
//    private TbContentCategoryDao tbContentCategoryDao;
//


    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContentCategory entity) {

        String validator = BeanValidator.validator(entity);
        //Spring Validator的验证失败
        if (validator != null){
            return BaseResult.fail(validator);
        }
        //验证成功
        else {
            TbContentCategory parent = entity.getParent();
            //在新增和编辑之前都要判断是否是根目录，，是否有父级类目
            //parent为null就是没有选择任何根目录,父级类目什么都没有就默认设置为根目录
            if (parent == null || parent.getId() == null){
                //设置为0 代表根目录
                parent.setId(0L);
                //既然设置了根目录那就一定有子节点,也就是根目录一定是父级目录
                entity.setIsParent(true);
            }

            //不管新增还是编辑都要给更新时间
            entity.setUpdated(new Date());
            //新增  insert的SQL语句中TbContentCategory的id是空的，是不带id过来的，为空就是新增
            if (entity.getId() == null){
                //设置创建时间
                entity.setCreated(new Date());
                //这时候的新增就是新增子节点了不是根目录，要把IsParent设置为false
                entity.setIsParent(false);

                //如果当前新增的节点有没有父目录，不是根目录
                if (parent.getId() != 0L){
                    //根据之前的parent的id拿到当前的parent
                    TbContentCategory currentCategoryParent = findById(parent.getId());
                    //判断当前的parent不为空
                    if (currentCategoryParent != null){
                        //设置当前节点为父目录
                        currentCategoryParent.setIsParent(true);
                        update(currentCategoryParent);
                    }
                }

                //父级节点为0，表示为根目录
                else {
                    //是根目录就一定是父目录
                    entity.setIsParent(true);
                }

                dao.insert(entity);
            }

            //编辑
            else {
                update(entity);
            }

            //新增和编辑都是保存信息成功
            return BaseResult.success("分类信息保存成功！");
        }

    }

    //    @Override
//    public List<TbContentCategory> selectByPid(Long pid) {
//        return tbContentCategoryDao.selectByPid(pid);
//    }
//
//    @Override
//    public TbContentCategory findById(Long id) {
//        return null;
//    }
//
//    @Override
//    public void update(TbContentCategory entity) {
//
//    }

}
