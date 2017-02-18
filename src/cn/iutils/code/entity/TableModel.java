package cn.iutils.code.entity;

import java.util.List;

/**
 * 实体类Model
 *
 * @author hc
 */
public class TableModel extends JavaModel {

    public String tableName;//表明
    public String tableDesc;//描述

    /**
     * 列集合
     */
    public List<ColumnModel> columns;

    public List<ColumnModel> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableDesc() {
        return tableDesc;
    }

    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
    }

}
