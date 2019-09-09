package com.lu.ming.shop.commons.persistence;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 14:09 2019/8/24
 * Modified By:
 */
@Data
public abstract class BaseTreeEntity<T extends BaseEntity> extends BaseEntity implements Serializable {

    private T parent;

    private Boolean isParent;
}
