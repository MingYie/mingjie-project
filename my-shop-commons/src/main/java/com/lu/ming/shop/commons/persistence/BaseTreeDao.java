package com.lu.ming.shop.commons.persistence;

import java.util.List;

/**
 * 通用的树形结构
 * TbContentCategoryDao的基类
 * @Author:MingYie
 * @Description
 * @Date:Created in 13:52 2019/8/20
 * Modified By:
 */
public interface BaseTreeDao<T extends BaseEntity>{

    /**
     * 根据pid来查询内容  方便找出子类目
     * @param pid 父类目ID=0时  代表的是一级目录
     * @return
     */
    List<T> selectByPid(Long pid);

    /**
     * 查询所有
     * @return
     */
    List<T> selectAll();

    /**
     * 插入
     * @param
     */
    void insert(T entity);

    /**
     * 根据ID删除
     * @param id
     */
    void delete(Long id);

    /**
     * 批量删除
     * @param ids id数组
     */
    void deleteArray(String[] ids);

    /**
     * 根据ID查询信息
     * @param id
     * @return tbUser
     */
    T findById(Long id);

    /**
     * 编辑操作
     * @param
     */
    void update(T entity);
}
