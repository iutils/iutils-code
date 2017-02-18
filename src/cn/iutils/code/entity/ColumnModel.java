package cn.iutils.code.entity;

/**
 * 数据库字段模型
 * Created by cc on 16/5/10.
 */
public class ColumnModel {

    private String dbColumnName;//数据库列名
    private String columnName;//列名
    private String columnType;//类型
    private String columnDesc;//描述

    public String getDbColumnName() {
        return dbColumnName;
    }

    public void setDbColumnName(String dbColumnName) {
        this.dbColumnName = dbColumnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnDesc() {
        return columnDesc;
    }

    public void setColumnDesc(String columnDesc) {
        this.columnDesc = columnDesc;
    }
}
