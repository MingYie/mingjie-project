package com.lu.ming.shop.web.api.service;

import com.lu.ming.shop.commons.dto.BaseResult;
import com.lu.ming.shop.domain.tbUser;

/**
 * @Author:MingYie
 * @Description 会员管理
 * @Date:Created in 21:37 2019/8/29
 * Modified By:
 */
public interface TbUserService {

    /**
     * 登录
     * @param tbuser
     * @return
     */
    tbUser login(tbUser tbuser);

    int count(tbUser tbuser);

    void save(tbUser tbuser);
}
