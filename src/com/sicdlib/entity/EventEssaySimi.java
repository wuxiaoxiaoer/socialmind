package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/5/9.
 */
public class EventEssaySimi {
    //相似度
    private Float simi;
    //表名称
    private String tablename;
    //每条记录的ID
    private String id;

    public Float getSimi() {
        return simi;
    }

    public void setSimi(Float simi) {
        this.simi = simi;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
