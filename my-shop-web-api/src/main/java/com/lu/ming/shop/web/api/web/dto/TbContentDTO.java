package com.lu.ming.shop.web.api.web.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:MingYie
 * @Description api的内容数据传输对象 是domain里的数据传输对象
 * @Date:Created in 21:01 2019/8/27
 * Modified By:
 */
@Data
public class TbContentDTO implements Serializable {
    private Long id;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
}
