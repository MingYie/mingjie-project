package com.lu.ming.shop.web.admin.service.impl;

import com.lu.ming.shop.commons.dto.BaseResult;
import com.lu.ming.shop.commons.dto.pageInfo;
import com.lu.ming.shop.commons.utils.RegexUtil;
import com.lu.ming.shop.commons.validator.BeanValidator;
import com.lu.ming.shop.domain.tbUser;
import com.lu.ming.shop.web.admin.abstarct.AbstractBaseServiceimpl;
import com.lu.ming.shop.web.admin.dao.TbUserDao;
import com.lu.ming.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 14:07 2019/8/9
 * Modified By:
 */
@Service
public class TbUserServiceimpl extends AbstractBaseServiceimpl<tbUser,TbUserDao> implements TbUserService {


    @Override
    public BaseResult save(tbUser tbuser) {
        /**
         *  因为插入操作是不带id过来的，所以判断id就能看是新增insert 还是编辑updated
         */
        //BeanValidator工具类验证返回的BaseResult类中的自定义消息
        String validator = BeanValidator.validator(tbuser);
        //验证失败 把validator里的字符串返回
        if (validator != null){
            return BaseResult.fail(validator);
        }
        //验证成功
        else {
            tbuser.setUpdated(new Date());

            //id为null就是新增insert
            if (tbuser.getId()==null){
                //新增用户的密码要md5加密 封装的时候就加密
                tbuser.setPassword(DigestUtils.md5DigestAsHex(tbuser.getPassword().getBytes()));
                tbuser.setCreated(new Date());
                dao.insert(tbuser);
            }

            //有id传过来就是编辑updated
            else {
                update(tbuser);
            }
            //新增和编辑都是保存信息成功
            return BaseResult.success("保存用户信息成功");
        }
    }

    /**
     * 登录逻辑
     * @param email  邮箱
     * @param password 密码
     * @return tbUser
     */
    @Override
    public tbUser login(String email, String password) {
        tbUser tbuser = dao.getByEmail(email);
        System.out.println(tbuser);
        if (tbuser!=null){
            //md5加密 密码
            String md5Password= DigestUtils.md5DigestAsHex(password.getBytes());
            //如果加密的密码和数据库中的密码是一致的
            if (md5Password.equals(tbuser.getPassword())){
                return tbuser;
            }
        }
        //没有就返回null
        return null;
    }


//    /**
//     * 封装pageInfo
//     * @param start 开始的位置
//     * @param length 每页显示的条数
//     * @param draw dataTables表格插件所必须的参数
//     * @return 封装好的pageInfo对象
//     */
//    @Override
//    public pageInfo<tbUser> page(int start, int length, int draw,tbUser tbUser) {
//        int count = tbUserDao.count(tbUser);
//
//        Map<String,Object> params = new HashMap<>();
//        params.put("start",start);
//        params.put("length",length);
//        params.put("tbUser",tbUser);
//
//        pageInfo<tbUser> tbUserpageInfo = new pageInfo<>();
//        tbUserpageInfo.setDraw(draw);
//        tbUserpageInfo.setRecordsTotal(count);
//        tbUserpageInfo.setRecordsFiltered(count);
//        tbUserpageInfo.setData(tbUserDao.page(params));
//
//        return tbUserpageInfo;
//    }


//    /**
//     * 后台验证用户信息的正确
//     * @param tbuser
//     * return BaseResult自定义消息
//     */
//    public BaseResult checkTbUser(tbUser tbuser){
//        BaseResult baseResult =new BaseResult();
//
//        if (StringUtils.isBlank(tbuser.getEmail())){
//            baseResult = BaseResult.fail("邮箱不能为空，请重新输入");
//            return baseResult;
//        }
//        else if (!RegexUtil.checkEmail(tbuser.getEmail())){
//            baseResult = BaseResult.fail("邮箱格式不正确，请重新输入");
//            return baseResult;
//        }
//
//        else if (StringUtils.isBlank(tbuser.getPassword())){
//            baseResult = BaseResult.fail("密码不能为空，请重新输入");
//            return baseResult;
//        }
//
//        //用commons.lang3的工具包节约代码量 判断姓名是否为空 空就返回信息
//        else if (StringUtils.isBlank(tbuser.getUsername())){
//            baseResult = BaseResult.fail("姓名不能为空，请重新输入");
//            return baseResult;
//        }
//
//
//        else if (StringUtils.isBlank(tbuser.getPhone())){
//            baseResult = BaseResult.fail("手机号不能为空，请重新输入");
//            return baseResult;
//        }
//        else if (!RegexUtil.isChinaPhoneLegal(tbuser.getPhone())){
//            baseResult = BaseResult.fail("手机号格式不正确，请重新输入");
//            return baseResult;
//        }
//
//        baseResult.setStatus(200);
//        //没有就直接返回 默认成功
//        return baseResult;
//    }
}
