package com.lu.ming.shop.web.ui.api;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 21:28 2019/8/28
 * Modified By:
 */
public class API {

    //主机地址
    public static final String HOST = "http://localhost:8081/v1/api";

    //版本号 内容查询接口 - 幻灯片
    public static final String API_PPT_CONTENTS = HOST +"/content/ppt";

    //会员登录接口  这是api层的登录地址
    public static final String API_USERS_LOGIN = HOST +"/users/login";

    //会员注册接口 这是api层的注册地址
    public static final String API_USER_REGISTER = HOST +"/users/regist";
}
