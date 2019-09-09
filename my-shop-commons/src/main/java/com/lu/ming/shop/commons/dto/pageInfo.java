package com.lu.ming.shop.commons.dto;

import com.lu.ming.shop.commons.persistence.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * DataTables是jq的表格插件
 * 封装DataTables所必须的对象
 * @Author:MingYie
 * @Description
 * @Date:Created in 10:01 2019/8/14
 * Modified By:
 */
public class pageInfo<T extends BaseEntity> implements Serializable {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<T> data;
    private String error;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
