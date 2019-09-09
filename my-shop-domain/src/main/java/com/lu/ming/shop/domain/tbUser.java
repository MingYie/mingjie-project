package com.lu.ming.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lu.ming.shop.commons.persistence.BaseEntity;
import com.lu.ming.shop.commons.utils.RegexUtil;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 13:46 2019/8/9
 * Modified By:
 */
@Data
public class tbUser extends BaseEntity {
    @Length(min = 6,max = 20,message = "用户名必须介于6到20位之间！")
    private String username;

    @JsonIgnore
    @Pattern(regexp = RegexUtil.Psw, message = "密码长度在8-20位之间，包含数字和字母")
    private String password;

    @Pattern(regexp = RegexUtil.ChinessPhone,message = "电话格式：13+任意数 15+除4的任意数 18+除1和4的任意数")
    private String phone;

    @Pattern(regexp = RegexUtil.EMail,message = "格式：zhangsan@163.com，zhangsan@xxx.com.cn，xxx代表邮件服务商")
    private String email;

}
