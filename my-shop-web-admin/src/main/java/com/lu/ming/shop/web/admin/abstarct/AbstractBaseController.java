package com.lu.ming.shop.web.admin.abstarct;

import com.lu.ming.shop.commons.dto.BaseResult;
import com.lu.ming.shop.commons.dto.pageInfo;
import com.lu.ming.shop.commons.persistence.BaseEntity;
import com.lu.ming.shop.commons.persistence.BaseService;
import com.lu.ming.shop.domain.tbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:MingYie
 * @Description UserController和ContentController的抽象类
 * @Date:Created in 11:58 2019/8/24
 * Modified By:
 */
public abstract class AbstractBaseController<T extends BaseEntity,S extends BaseService<T>>{

    /**
     * 注入业务逻辑层
     */
    @Autowired
    protected S service;

    /**
     * 跳转到用户列表页
     * @return
     */
    public abstract String list();

    /**
     * 跳转到用户表单页面
     * @return
     */
    public abstract String form();

    /**
     * 保存用户信息
     * @param entity
     * @param redirectAttributes
     * @param model
     * @return
     */
    public abstract String save(T entity, RedirectAttributes redirectAttributes, Model model);

    /**
     * 批量删除用户信息
     * @param ids
     * @return
     */
    public abstract BaseResult delete(String ids);

    /**
     * 分页查询
     * @param request
     * @param entity
     * @return
     */
    public abstract pageInfo<tbUser> page(HttpServletRequest request, T entity);

    /**
     * 跳转用户详情页
     * @param entity
     * @return
     */
    public abstract String detail(T entity);
}
