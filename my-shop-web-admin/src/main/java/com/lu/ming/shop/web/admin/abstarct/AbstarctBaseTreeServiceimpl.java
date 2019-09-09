package com.lu.ming.shop.web.admin.abstarct;

import com.lu.ming.shop.commons.dto.BaseResult;
import com.lu.ming.shop.commons.persistence.BaseEntity;
import com.lu.ming.shop.commons.persistence.BaseTreeDao;
import com.lu.ming.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author:MingYie
 * @Description 实现BaseTreeService中 通用 的方法 调用dao层的方法，BaseTreeDao层的实现又再交给mybatis实现sql语句的查询
 * @Date:Created in 14:50 2019/8/23
 * Modified By:
 */
public abstract class AbstarctBaseTreeServiceimpl<T extends BaseEntity,D extends BaseTreeDao<T>> implements BaseTreeService<T> {

    /**
     * //变成protected来给子孙类使用
     * 注入数据访问层
     */
    @Autowired
    protected D dao;


    /**-
     * 查询所有
     * @return
     */
    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }


    /**
     * 删除 开启spring事务
     * @param id
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        dao.delete(id);
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void deleteArray(String[] ids){
        dao.deleteArray(ids);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public T findById(Long id){
        return dao.findById(id);
    }

    /**更新信息
     * 编辑
     * @param entity
     */
    @Override
    @Transactional(readOnly = false)
    public void update(T entity){
        dao.update(entity);
    }

    /**
     * 根据pid来查询内容  方便找出子类目
     * @param pid 父类目ID=0时  代表的是一级目录
     * @return
     */
    @Override
    public List<T> selectByPid(Long pid){
        return dao.selectByPid(pid);
    }

}
