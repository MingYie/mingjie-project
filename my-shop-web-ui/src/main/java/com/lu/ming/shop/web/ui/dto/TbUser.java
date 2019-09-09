package com.lu.ming.shop.web.ui.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:MingYie
 * @Description 因为前端ui不与后端domain打交道，不与数据库打交道，没有实体类，所以自己写dto 数据传输对象
 * @Date:Created in 8:52 2019/8/31
 * Modified By:
 */
@Data
public class TbUser implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
    //验证码
    private String verification;

    private Date updated;
    private Date created;
}
