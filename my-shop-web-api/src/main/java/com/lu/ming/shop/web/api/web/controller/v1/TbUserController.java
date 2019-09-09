package com.lu.ming.shop.web.api.web.controller.v1;

import com.lu.ming.shop.commons.dto.BaseResult;
import com.lu.ming.shop.domain.tbUser;
import com.lu.ming.shop.web.api.service.TbUserService;
import com.lu.ming.shop.web.api.web.dto.TbUserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 21:54 2019/8/29
 * Modified By:
 */
@RestController
@RequestMapping(value = "${api.path.v1}/users")
public class TbUserController {

    @Autowired
    private TbUserService tbUserService;


    /**
     * 后端api判断会员登录 然后给其他设备的ui提供信息
     * @param tbuser
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public BaseResult login(tbUser tbuser){

        tbUser user = tbUserService.login(tbuser);

        //判断用户是否存在
        if (user == null){
            return BaseResult.fail("账号或密码错误！");
        }
        else {
            //反射封装  把源对象user封装成TbUserDTO类型的
            TbUserDTO dto = new TbUserDTO();
            BeanUtils.copyProperties(user,dto);
            return BaseResult.success("成功",dto);
        }

    }

    /**
     * 后端api判断会员注册 然后给其他设备的ui提供信息
     * @param tbuser
     * @return
     */
    @RequestMapping(value = "regist",method = RequestMethod.POST)
    public BaseResult register(tbUser tbuser){
        int count = tbUserService.count(tbuser);

        if (count >= 1){
            return BaseResult.fail("账号已存在");
        }
        else {
            tbUser tbUser = new tbUser();
            tbUser.setUsername(tbuser.getUsername());
            tbUser.setPhone(tbuser.getPhone());
            tbUser.setEmail(tbuser.getEmail());
            tbUser.setPassword(DigestUtils.md5DigestAsHex(tbuser.getPassword().getBytes()));
            tbUser.setUpdated(new Date());
            tbUser.setCreated(new Date());
            tbUserService.save(tbUser);
            return BaseResult.success("成功",tbUser);
        }
    }
}
