package com.devilwwj.ormlite.db;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.devilwwj.ormlite.model.Img;
import com.devilwwj.ormlite.model.PackageInfo;
import com.devilwwj.ormlite.model.Photographer;
import com.devilwwj.ormlite.model.Theme;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * 功能：数据库帮助类
 * @author devilwwj
 *
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {
	/**
	 * 数据库名字
	 */
	private static final String DB_NAME = "test.db";
	/**
	 * 数据库版本
	 */
	private static final int DB_VERSION = 1;
	
	/**
	 * 用来存放Dao的地图
	 */
	private Map<String, Dao> daos = new HashMap<String, Dao>();
	
	
	
	private static DBHelper instance;
	
	
	/**
	 * 获取单例
	 * @param context
	 * @return
	 */
	public static synchronized DBHelper getHelper(Context context) {
		context = context.getApplicationContext();
		if (instance == null) {
			synchronized (DBHelper.class) {
				if (instance == null) {
					instance = new DBHelper(context);
				}
			}
		}
		return instance;
	}
	
	
	/**
	 * 构造方法
	 * @param context
	 */
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	/**
	 * 这里创建表
	 */
	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
		// 创建表
		try {
			TableUtils.createTable(connectionSource, PackageInfo.class);
			TableUtils.createTable(connectionSource, Photographer.class);
			TableUtils.createTable(connectionSource, Theme.class);
			TableUtils.createTable(connectionSource, Img.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 这里进行更新表操作
	 */
	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion,
			int newVersion) {
		try  
        {  
            TableUtils.dropTable(connectionSource, PackageInfo.class, true);  
            TableUtils.dropTable(connectionSource, Photographer.class, true);  
            TableUtils.dropTable(connectionSource, Theme.class, true);  
            TableUtils.dropTable(connectionSource, Img.class, true);
            onCreate(sqLiteDatabase, connectionSource);  
        } catch (SQLException e)  
        {  
            e.printStackTrace();  
        }  
	}
	
	/**
	 * 通过类来获得指定的Dao
	 */
	public synchronized Dao getDao(Class clazz) throws SQLException {
		Dao dao = null;
		String className = clazz.getSimpleName();
		if (daos.containsKey(className)) {
			dao = super.getDao(clazz);
			daos.put(className, dao); 
		}
		return dao;
	}
	
	
	/**
	 * 释放资源
	 */
	@Override
	public void close() {
		super.close();
		for (String key : daos.keySet()) {
			Dao dao = daos.get(key);
			dao = null;
		}
	}
	
	
	
	

}
