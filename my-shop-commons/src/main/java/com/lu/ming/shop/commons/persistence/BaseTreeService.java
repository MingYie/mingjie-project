package com.lu.ming.shop.commons.persistence;

import com.lu.ming.shop.commons.dto.BaseResult;

import java.util.List;

/**
 * 通用的树形结构
 * @Author:MingYie
 * @Description TbContentCategoryService的基类
 * @Date:Created in 13:57 2019/8/20
 * Modified By:
 */
public interface BaseTreeService<T extends BaseEntity>{
    /**
     * 查询全部
     * @return
     */
    List<T> selectAll();

    /**
     * 保存信息
     * @param
     * @return
     */
    BaseResult save(T entity);

    /**
     * 删除信息
     * @param id
     */
    void delete(Long id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteArray(String[] ids);

    /**
     * 根据id获取信息
     * @param id
     * @return
     */
    T findById(Long id);

    /**
     * 更新信息
     * @param
     */
    void update(T entity);

    /**
     * 根据pid来查询内容  方便找出子类目
     * @param pid 父类目ID=0时  代表的是一级目录
     * @return
     */
    List<T> selectByPid(Long pid);
}
