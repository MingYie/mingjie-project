package com.lu.ming.shop.web.admin.service;

import com.lu.ming.shop.commons.dto.BaseResult;
import com.lu.ming.shop.commons.dto.pageInfo;
import com.lu.ming.shop.commons.persistence.BaseService;
import com.lu.ming.shop.domain.tbUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 14:06 2019/8/9
 * Modified By:
 */

public interface TbUserService extends BaseService<tbUser> {
    /**
     * 用户登录
     * @param email
     * @param password
     * @return
     */
    tbUser login(String email,String password);
}
