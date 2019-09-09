package com.lu.ming.shop.web.admin.abstarct;

import com.lu.ming.shop.commons.dto.pageInfo;
import com.lu.ming.shop.commons.persistence.BaseDao;
import com.lu.ming.shop.commons.persistence.BaseEntity;
import com.lu.ming.shop.commons.persistence.BaseService;
import com.lu.ming.shop.domain.TbContent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:MingYie
 * @Description 实现BaseService中的 通用 方法 调用dao层的方法，BaseDao层的实现又再交给mybatis实现sql语句的查询
 * @Date:Created in 16:36 2019/8/23
 * Modified By:
 */
public abstract class AbstractBaseServiceimpl<T extends BaseEntity,D extends BaseDao<T>> implements BaseService<T> {

    /**
     * //变成protected来给子孙类使用
     * 注入数据访问层
     */
    @Autowired
    protected D dao;

    /**
     * 查询全部
     * @return
     */
    @Override
    public List<T> selectAll(){
        return dao.selectAll();
    }

    /**
     * 删除信息
     * @param id
     */
    @Override
    public void delete(Long id){
        dao.delete(id);
    }

    /**
     * 根据id获取信息
     * @param id
     * @return
     */
    @Override
    public T findById(Long id){
        return dao.findById(id);
    }

    /**
     * 更新信息
     * @param
     */
    @Override
    public void update(T entity){
        dao.update(entity);
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
     * 查询总记录数
     * @param
     * @return
     */
    @Override
    public int count(T entity){
        return dao.count(entity);
    }


    /**
     * 分页查询
     * @param start 开始的位置
     * @param length 每页显示的条数
     * @param draw dataTables表格插件所必须的参数
     * @param entity
     * @return 封装好的pageInfo对象
     */
    @Override
    public pageInfo<T> page(int start, int length, int draw, T entity) {
        int count = count(entity);

        //封装dataTables表格插件所必须的参数，存放在list集合中，为什么嘛呢？因为pageInfo实体类需要List<T> data类型的对象啊
        Map<String,Object> params = new HashMap<>();
        params.put("start",start);
        params.put("length",length);
        params.put("pageParams",entity);

        //封装pageInfo实体类
        pageInfo<T> tbContentPageInfo = new pageInfo<>();
        tbContentPageInfo.setDraw(draw);
        tbContentPageInfo.setRecordsTotal(count);
        tbContentPageInfo.setRecordsFiltered(count);
        tbContentPageInfo.setData(dao.page(params));

        return tbContentPageInfo;
    }

//    /**
//     *分页操作
//     * @param params 需要2个参数 1个是开始的位置start 1个是每页显示的条数length
//     * @return
//     */
//    public List<T> page(Map<String,Object> params){
//        return dao.page(params);
//    }
}
