package com.lu.ming.shop.web.api.dao;

import com.lu.ming.shop.domain.tbUser;
import org.springframework.stereotype.Repository;

/**
 *
 * @Author:MingYie
 * @Description 会员管理
 * @Date:Created in 21:34 2019/8/29
 * Modified By:
 */
@Repository
public interface TbUserDao {

    tbUser login(tbUser tbuser);

    int count(tbUser tbuser);

    void insert(tbUser tbuser);
}
