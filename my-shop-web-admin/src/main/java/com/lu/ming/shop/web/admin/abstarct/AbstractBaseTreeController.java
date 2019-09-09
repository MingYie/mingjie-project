package com.lu.ming.shop.web.admin.abstarct;

import com.lu.ming.shop.commons.dto.BaseResult;
import com.lu.ming.shop.commons.persistence.BaseEntity;
import com.lu.ming.shop.commons.persistence.BaseTreeEntity;
import com.lu.ming.shop.commons.persistence.BaseTreeService;
import com.lu.ming.shop.domain.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @Author:MingYie
 * @Description ContentCategoryController的抽象类
 * @Date:Created in 13:41 2019/8/24
 * Modified By:
 */
public abstract class AbstractBaseTreeController<T extends BaseTreeEntity,S extends BaseTreeService<T>>{


    @Autowired
    protected S service;

    /**
     * 跳转内容分类列表
     * @param model
     * @return
     */
    public abstract String list(Model model);

    /**
     * 跳转内容分类表单
     * @param entity tbContentCategory
     * @return
     */
    public abstract String form(T entity);

    /**
     * 保存
     * @param entity tbContentCategory
     * @param redirectAttributes
     * @param model
     * @return
     */
    public abstract String save(T entity, RedirectAttributes redirectAttributes, Model model);

    /**
     * 树形结构
     * @param id
     * @return
     */
    public abstract List<T> TreeData(Long id);

    /**
     * 批量删除用户信息
     * @param ids
     * @return
     */
    public abstract BaseResult delete(String ids);

    /**
     *排序用treeTable插件显示的树形结构数据 当前节点的parentId等于上一节点的ID，那就是上一节点的子节点
     * @param sourceList 原来的数据列表
     * @param targetList 排序后的数据列表
     * @param Id 手动传进来的一个Id 其实就是数据表中的第一个的父节点为的0的数据
     */
    protected void sortList(List<T> sourceList,List<T> targetList,Long Id){
        for (T sourceEntity : sourceList) {
            //如果当前节点ParentId是否等于0L 是就就添加到排序后的数据列表
            if (sourceEntity.getParent().getId().equals(Id)){
                targetList.add(sourceEntity);

                //判断有没有子节点，如果有就继续追加
                if (sourceEntity.getIsParent()){
                    //那就继续遍历原来的sourceList
                    for (T contentCategory : sourceList) {
                        //再判断当前节点的ParentId是否等于上一个节点的Id
                        if (contentCategory.getParent().getId().equals(sourceEntity.getId())){
                            //递归  注意是tbContentCategory的id
                            sortList(sourceList,targetList,sourceEntity.getId());
                            break;
                        }
                    }
                }
            }
        }
    }
}
