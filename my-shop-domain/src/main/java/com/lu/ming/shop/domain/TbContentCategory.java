package com.lu.ming.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lu.ming.shop.commons.persistence.BaseEntity;
import com.lu.ming.shop.commons.persistence.BaseTreeEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 11:48 2019/8/15
 * Modified By:
 */
@Data
public class TbContentCategory extends BaseTreeEntity {
//    private Long parentId;//父类目ID=0时  代表的是一级目录

    @Length(min = 1,max = 20,message = "分类名称必须介于1-20之间")
    private String name;//分类名称
    private Integer status;//状态。可选值:1(正常),2(删除)

    @NotNull(message = "排列序号不能为空")
    private Integer sortOrder;//排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
    private Boolean isParent;//该类目是否为父类目，1为true，0为false
    //提供给TbContentCategoryMapper.xml使用的多表联查
    private TbContentCategory parent;
}
