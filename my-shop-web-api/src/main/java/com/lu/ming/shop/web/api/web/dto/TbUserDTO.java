package com.lu.ming.shop.web.api.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import sun.rmi.runtime.Log;

import java.io.Serializable;

/**
 * @Author:MingYie
 * @Description 会员数据传输对象
 * @Date:Created in 10:10 2019/8/31
 * Modified By:
 */
@Data
public class TbUserDTO implements Serializable {
    private Long id;
    private String username;

    @JsonIgnore
    private String password;
    private String phone;
    private String email;

}
