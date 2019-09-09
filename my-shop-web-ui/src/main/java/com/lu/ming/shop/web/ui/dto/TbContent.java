package com.lu.ming.shop.web.ui.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:MingYie
 * @Description 因为前端ui不与后端domain打交道，不与数据库打交道，没有实体类，所以自己写dto 数据传输对象
 * @Date:Created in 19:45 2019/8/28
 * Modified By:
 */
@Data
public class TbContent implements Serializable {
    private Long id;
    private String title; //内容标题
    private String subTitle; //子标题
    private String titleDesc; //标题描述
    private String url; //链接
    private String pic; //图片绝对路径
    private String pic2; //图片2
    private String content; //内容
}
