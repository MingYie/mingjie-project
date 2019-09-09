package com.lu.ming.shop.admin.service.test;

import com.lu.ming.shop.domain.tbUser;
import com.lu.ming.shop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 14:34 2019/8/9
 * Modified By:
 */
//spring-text的测试类  还有上下文配置的所需要的spring配置文件  classpath:要指定文件路径在target的web-inf的classes下
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml","classpath:spring-context-druid.xml","classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {

    @Autowired
    private TbUserService tbUserService;

    @Test
    public void selectAll(){
        List<tbUser> tbUsers = tbUserService.selectAll();
        for (tbUser tbuser : tbUsers) {
            System.out.println(tbuser.getUsername());
        }
    }

    @Test
    public void testinsert(){
        tbUser tbuser = new tbUser();
        tbuser.setUsername("MingYie");
        tbuser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        tbuser.setPhone("18921323157");
        tbuser.setEmail("895900424@qq.com");
        tbuser.setCreated(new Date());
        tbuser.setUpdated(new Date());

        tbUserService.save(tbuser);
    }

    @Test
    public void testdelete(){
        tbUserService.delete(37L);
    }

    @Test
    public void testfindById(){
        tbUser tbuser = tbUserService.findById(36L);
        System.out.println(tbuser.getUsername());
    }

    @Test
    public void testupdate(){
        tbUser user = tbUserService.findById(36L);
        user.setUsername("MingYie");
        tbUserService.update(user);
    }



    @Test
    public void test(){
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
    }

    @Test
    public void testgetByEmail(){
        tbUser user = tbUserService.login("895900424@qq.com", "123456");
        System.out.println(user);
    }
}
