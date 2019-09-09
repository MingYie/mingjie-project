package com.lu.ming.shop.commons.persistence;

import java.util.List;
import java.util.Map;

/**
 * @Author:MingYie
 * @Description 所有数据访问层的基类
 * @Date:Created in 14:36 2019/8/19
 * Modified By:
 */
public interface BaseDao<T extends BaseEntity> {
    /**
     * 查询所有
     * @return
     */
    public List<T> selectAll();

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

    /**
     * 批量删除
     * @param ids id数组
     */
    void deleteArray(String[] ids);

    /**
     *分页操作
     * @param params 需要3个参数 1个是开始的位置start 1个是每页显示的条数length 还有1个是tbContent对象
     * @return
     */
    List<T> page(Map<String,Object> params);

    /**
     * 查询总记录数
     * @param
     * @return
     */
    int count(T entity);
}
