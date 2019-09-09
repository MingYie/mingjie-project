package com.lu.ming.shop.commons.persistence;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类的基类 就是把实体类tbUser里共同的属性抽出来作为父类 以后的实体类就都要继承BaseEntity
 * @Author:MingYie
 * @Description
 * @Date:Created in 10:10 2019/8/14
 * Modified By:
 */
public abstract class BaseEntity implements Serializable{
    private Long id;
    private Date updated;
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
