package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/2.
 */
public class CleanLocationEntity {
    private String cleanLocationId;
    private String tableId;
    private String columnName;
    private String rowkey;
    private String columnFamily;
    private Integer timeStamp;

    public String getCleanLocationId() {
        return cleanLocationId;
    }

    public void setCleanLocationId(String cleanLocationId) {
        this.cleanLocationId = cleanLocationId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getRowkey() {
        return rowkey;
    }

    public void setRowkey(String rowkey) {
        this.rowkey = rowkey;
    }

    public String getColumnFamily() {
        return columnFamily;
    }

    public void setColumnFamily(String columnFamily) {
        this.columnFamily = columnFamily;
    }

    public Integer getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Integer timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CleanLocationEntity that = (CleanLocationEntity) o;

        if (cleanLocationId != null ? !cleanLocationId.equals(that.cleanLocationId) : that.cleanLocationId != null)
            return false;
        if (tableId != null ? !tableId.equals(that.tableId) : that.tableId != null) return false;
        if (columnName != null ? !columnName.equals(that.columnName) : that.columnName != null) return false;
        if (rowkey != null ? !rowkey.equals(that.rowkey) : that.rowkey != null) return false;
        if (columnFamily != null ? !columnFamily.equals(that.columnFamily) : that.columnFamily != null) return false;
        if (timeStamp != null ? !timeStamp.equals(that.timeStamp) : that.timeStamp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cleanLocationId != null ? cleanLocationId.hashCode() : 0;
        result = 31 * result + (tableId != null ? tableId.hashCode() : 0);
        result = 31 * result + (columnName != null ? columnName.hashCode() : 0);
        result = 31 * result + (rowkey != null ? rowkey.hashCode() : 0);
        result = 31 * result + (columnFamily != null ? columnFamily.hashCode() : 0);
        result = 31 * result + (timeStamp != null ? timeStamp.hashCode() : 0);
        return result;
    }
}
