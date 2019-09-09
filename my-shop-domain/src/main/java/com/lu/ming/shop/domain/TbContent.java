package com.lu.ming.shop.domain;

import com.lu.ming.shop.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 *内容管理
 * @Author:MingYie
 * @Description
 * @Date:Created in 17:25 2019/8/15
 * Modified By:
 */
@Data
public class TbContent extends BaseEntity {
    @Length(min = 1,max = 20,message = "标题在1-20个字之间")
    private String title; //内容标题

    @Length(min = 1,max = 20,message = "子标题在1-20个字之间")
    private String subTitle; //子标题

    @Length(min = 1,max = 50,message = "标题描述在1-50个字之间")
    private String titleDesc; //标题描述

    private String url; //链接
    private String pic; //图片绝对路径
    private String pic2; //图片2

    @Length(min = 1,message = "内容不能为空")
    private String content; //内容

    //提供给TbContentMapper.xml使用的多表联查
    @NotNull(message = "ID不能为空")
    private TbContentCategory tbContentCategory;
}
