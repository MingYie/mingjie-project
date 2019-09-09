package com.lu.ming.shop.web.api.service.impl;

import com.lu.ming.shop.commons.dto.BaseResult;
import com.lu.ming.shop.domain.tbUser;
import com.lu.ming.shop.web.api.dao.TbUserDao;
import com.lu.ming.shop.web.api.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 21:38 2019/8/29
 * Modified By:
 */
@Service
public class TbUserServiceimpl implements TbUserService {

    @Autowired
    private TbUserDao tbUserDao;

    /**
     * 密码是在代码中判断的不能直接sql语句判断
     * @param tbuser 因为要手机号，邮箱，电话号码3种账号都能登录，所以是tbUser对象
     * @return
     */
    @Override
    public tbUser login(tbUser tbuser) {
        //通过从controller传回来的封装好的tbUser对象来从dao层数据库查询对应的user用户
        tbUser user = tbUserDao.login(tbuser);

        //如果数据库中存在用户
        if (user != null){
            //把前端用户传过来的明文密码加密
            String password = DigestUtils.md5DigestAsHex(tbuser.getPassword().getBytes());
            //如果密码和数据库中的密码匹配
            if (password.equals(user.getPassword())){
                //返回tbUser对象
                return user;
            }
        }
        //否则返回空
        return null;
    }

    @Override
    public int count(tbUser tbuser) {
        return tbUserDao.count(tbuser);
    }

    @Override
    public void save(tbUser tbuser) {
        tbUserDao.insert(tbuser);
    }
}
