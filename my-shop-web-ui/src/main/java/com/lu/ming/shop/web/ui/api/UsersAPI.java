package com.lu.ming.shop.web.ui.api;

import com.lu.ming.shop.commons.dto.BaseResult;
import com.lu.ming.shop.commons.utils.HttpClientUtils;
import com.lu.ming.shop.commons.utils.MapperUtils;
import com.lu.ming.shop.web.ui.dto.TbUser;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:MingYie
 * @Description 会员登录接口
 * @Date:Created in 8:50 2019/8/31
 * Modified By:
 */
public class UsersAPI {

    /**
     * 登录
     * 通过HttpClientUtils请求api层的url地址返回登录信息的json
     * 再通过MapperUtils把指定节点的数据转换为JavaBeam，返回TbUser类型的数据传输对象，提供给LoginController调用
     * @param tbUser
     * @return
     */
    public static TbUser login(TbUser tbUser) throws Exception {
//        BasicNameValuePair通常是用来封装post请求中的参数名称和值
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username",tbUser.getUsername()));
        params.add(new BasicNameValuePair("password",tbUser.getPassword()));
        //params是集合 doPost接收的是数组，所以集合转数组，注意数组里写对应的泛型和数组初始大小
        String json = HttpClientUtils.doPost(API.API_USERS_LOGIN, params.toArray(new BasicNameValuePair[params.size()]));
        TbUser user = MapperUtils.json2pojoByTree(json, "data", TbUser.class);
        System.out.println("api"+user);
        return user;
    }


    /**
     * 注册
     * @param tbUser
     * @return
     * @throws Exception
     */
    public static TbUser register(TbUser tbUser) throws Exception {
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username",tbUser.getUsername()));
        params.add(new BasicNameValuePair("password",tbUser.getPassword()));
        params.add(new BasicNameValuePair("email",tbUser.getEmail()));
        params.add(new BasicNameValuePair("phone",tbUser.getPhone()));
        String json = HttpClientUtils.doPost(API.API_USER_REGISTER, params.toArray(new BasicNameValuePair[params.size()]));
        TbUser user = MapperUtils.json2pojoByTree(json, "data", TbUser.class);
        System.out.println("api.register"+user);
        return user;
    }
}
