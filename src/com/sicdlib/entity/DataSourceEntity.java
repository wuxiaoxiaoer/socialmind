package com.sicdlib.entity;

import javax.persistence.*;

/**
 * Created by DeMH on 2017/11/2.
 */
@Entity
@Table(name = "data_source", schema = "socialmind", catalog = "")
public class DataSourceEntity {
    private String tableNameId;
    private String tableName;
    private String websiteId;

    @Id
    @Column(name = "tableNameID")
    public String getTableNameId() {
        return tableNameId;
    }

    public void setTableNameId(String tableNameId) {
        this.tableNameId = tableNameId;
    }

    @Basic
    @Column(name = "tableName")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Basic
    @Column(name = "websiteID")
    public String getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(String websiteId) {
        this.websiteId = websiteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataSourceEntity that = (DataSourceEntity) o;

        if (tableNameId != null ? !tableNameId.equals(that.tableNameId) : that.tableNameId != null) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;
        if (websiteId != null ? !websiteId.equals(that.websiteId) : that.websiteId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tableNameId != null ? tableNameId.hashCode() : 0;
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (websiteId != null ? websiteId.hashCode() : 0);
        return result;
    }
}
