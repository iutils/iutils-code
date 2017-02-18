package cn.iutils.code.utils.db;

import cn.iutils.code.entity.ColumnModel;
import cn.iutils.code.entity.TableModel;
import cn.iutils.code.utils.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库操作类
 *
 * @author hc
 */
public class DBManger {

    /**
     * 连接
     */
    Connection conn = null;
    /**
     * 库级
     */
    DatabaseMetaData metaData = null;
    /**
     * 库级
     */
    ResultSetMetaData rsMetaData = null;
    /**
     * sql执行类
     */
    Statement stmt = null;
    /**
     * 结果集
     */
    ResultSet rs = null;

    String db_host;
    String db_user;
    String db_password;

    /**
     * 初始化DBManger
     */
    public DBManger(String db_host, String db_user, String db_password) {
        try {
            this.db_host = db_host;
            this.db_user = db_user;
            this.db_password = db_password;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + db_host + "/", db_user, db_password);
            metaData = conn.getMetaData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库集
     *
     * @return
     */
    public List<String> getAllDatabase() {
        List<String> list = new ArrayList<String>();
        try {
            rs = metaData.getCatalogs();
            while (rs.next()) {
                list.add(rs.getString("TABLE_CAT"));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据库名获取表名
     *
     * @return
     */
    public List<TableModel> getTablesByDb(String catalog) {
        List<TableModel> list = new ArrayList<TableModel>();
        try {
            String sql = "SELECT t.table_name AS name,t.TABLE_COMMENT AS comments FROM information_schema.`TABLES` t WHERE t.TABLE_SCHEMA='" + catalog + "' ORDER BY t.TABLE_NAME ";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TableModel item = new TableModel();
                item.setTableName(rs.getString("name"));
                item.setTableDesc(rs.getString("comments"));
                list.add(item);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取列
     *
     * @param catalog
     * @param table
     * @return
     */
    public List<ColumnModel> getColumnsByTable(String catalog, String table) {
        List<ColumnModel> list = new ArrayList<ColumnModel>();
        try {
            String sql = "SELECT t.COLUMN_NAME AS name, (CASE WHEN t.IS_NULLABLE = 'YES' THEN '1' ELSE '0' END) AS isNull,(t.ORDINAL_POSITION * 10) AS sort,t.COLUMN_COMMENT AS comments,t.COLUMN_TYPE AS jdbcType FROM information_schema.`COLUMNS` t WHERE t.TABLE_SCHEMA = '" + catalog + "' AND t.TABLE_NAME = upper('" + table + "') ORDER BY t.ORDINAL_POSITION";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ColumnModel item = new ColumnModel();
                item.setDbColumnName(rs.getString("name"));
                item.setColumnName(StringUtils.toCamelCase(rs.getString("name")));
                item.setColumnType(rs.getString("jdbcType"));
                item.setColumnDesc(rs.getString("comments"));
                list.add(item);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * test
     *
     * @param args
     */
    public static void main(String[] args) {
        //获取数据库
        DBManger dBManger = new DBManger("127.0.0.1:3306", "root", "");
        List<String> list = dBManger.getAllDatabase();
        System.out.println(list.get(1));
        //获取表
        List<TableModel> lits2 = dBManger.getTablesByDb(list.get(1));
        System.out.println(lits2.get(1));
        //获取表的列
        List<ColumnModel> list3 = dBManger.getColumnsByTable(list.get(1), lits2.get(1).getTableName());
        System.out.println(list3);
    }

}
