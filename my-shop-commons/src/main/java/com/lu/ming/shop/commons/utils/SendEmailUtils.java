package com.lu.ming.shop.commons.utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:MingYie
 * @Description 发送邮件
 * @Date:Created in 9:44 2019/9/2
 * Modified By:
 */
public class SendEmailUtils {

    @Autowired
    private Email email;

    public void SendEmail(String subject,String msg,String... to) throws EmailException {
//        email.setHostName( "smtp.qq.com");
//        email.setSmtpPort(465);
//        email.setAuthenticator(new DefaultAuthenticator("895900424@qq.com","fztvkbszqtnpbccf"));
//        email.setFrom( "895900424@qq.com");
        email.setSubject(subject);
        email.setMsg(msg);
        email.addTo(to);
        email.send();
    }
}
