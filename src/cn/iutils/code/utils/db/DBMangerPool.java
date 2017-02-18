package cn.iutils.code.utils.db;

import java.util.HashMap;

/**
 * DBManger pool
 * @author hc
 *
 */
public class DBMangerPool {

	private static DBMangerPool dbMangerPool = null;
	
	private HashMap dbMangerPools = new HashMap();
	
	private DBMangerPool(){}
	
	public static DBMangerPool getInstance(){
		if (dbMangerPool == null) {
			dbMangerPool = new DBMangerPool();
	    }
	    return dbMangerPool;
	}
	
	/**
	 * 放置对象
	 */
	public void setDbMangerPools(String key, DBManger dbManger){
		dbMangerPools.put(key, dbManger);
	}
	
	/**
	 * 获取对象
	 * @param key
	 * @return
	 */
	public DBManger getDBManger(String key){
		return (DBManger) dbMangerPools.get(key);
	}
}
