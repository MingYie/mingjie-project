package com.lu.ming.shop.commons.dto;

import org.omg.CORBA.BAD_CONTEXT;

import java.io.Serializable;


/**
 * 自定义返回结果集合
 * @Author:MingYie
 * @Description
 * @Date:Created in 15:19 2019/8/11
 * Modified By:
 */
public class BaseResult implements Serializable {
    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_FAIL = 500;

    private int status;//状态码
    private String message;//消息
    private Object data;


    //封装1个BaseResult 返回成功信息
    public static BaseResult success(){
        return BaseResult.creatResult(STATUS_SUCCESS,"成功",null);
    }
    //重载
    public static BaseResult success(String message){
        return BaseResult.creatResult(STATUS_SUCCESS,message,null);
    }

    //BaseResult返回状态码 消息 和数据
    public static BaseResult success(String message,Object data){
        return BaseResult.creatResult(STATUS_SUCCESS,message,data);
    }

    //封装1个BaseResult 返回失败信息
    public static BaseResult fail(){
        return BaseResult.creatResult(STATUS_FAIL,"失败",null);
    }
    //重载
    public static BaseResult fail(String message){
        return BaseResult.creatResult(STATUS_FAIL,message,null);
    }
    //再重载 失败的情况会有很多种
    public static BaseResult fail(int status,String message){
        return BaseResult.creatResult(status,message,null);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    //重构
    private static BaseResult creatResult(int status,String message,Object data){
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        baseResult.setData(data);
        return baseResult;
    }
}
