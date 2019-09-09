package com.lu.ming.shop.web.admin.dao;

import com.lu.ming.shop.commons.persistence.BaseDao;
import com.lu.ming.shop.domain.tbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 13:53 2019/8/9
 * Modified By:
 */
@Repository
public interface TbUserDao extends BaseDao<tbUser> {

    /**
     * 根据email来查询用户信息
     * @param email
     * @return
     */
    tbUser getByEmail(String email);

//    /**
//     * 根据用户名来进行模糊匹配
//     * @param username
//     * @return
//     */
//    List<tbUser> selectByUsername(String username);

//    /**
//     * 搜索
//     * @param tbuser
//     * @return
//     */
//    List<tbUser> search(tbUser tbuser);

}
