package cn.iutils.code.service;

import cn.iutils.code.entity.TableModel;
import cn.iutils.code.utils.db.DBMangerPool;

import java.util.List;

/**
 * 数据库服务
 */
public class DbService {

    DBMangerPool dBMangerPool = DBMangerPool.getInstance();

    /**
     * 获取数据看列表
     * @param key
     * @return
     */
    public List<String> getDatabase(String key) {
        return dBMangerPool.getDBManger(key).getAllDatabase();
    }

    /**
     * 获取
     * @param key
     * @param catalog
     * @return
     */
    public List<TableModel> getTables(String key,String catalog) {
        return dBMangerPool.getDBManger(key).getTablesByDb(catalog);
    }


}
