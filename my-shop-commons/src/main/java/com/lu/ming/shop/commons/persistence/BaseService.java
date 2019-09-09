package com.lu.ming.shop.commons.persistence;

import com.lu.ming.shop.commons.dto.BaseResult;
import com.lu.ming.shop.commons.dto.pageInfo;

import java.util.List;

/**
 * @Author:MingYie
 * @Description 所有业务逻辑层的基类 这里的方法都由AbstractBaseServiceimpl来重写了
 * @Date:Created in 14:57 2019/8/19
 * Modified By:
 */
public interface BaseService<T extends BaseEntity>{
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
     * 批量删除
     * @param ids
     */
    void deleteArray(String[] ids);

    /**
     * 分页操作
     * @param start 开始的位置
     * @param length 每页显示的条数
     * @param draw dataTables表格插件所必须的参数
     * @return
     */
    pageInfo<T> page(int start, int length, int draw, T entity);

    /**
     * 查询总记录数
     * @param
     * @return
     */
    int count(T entity);
}
