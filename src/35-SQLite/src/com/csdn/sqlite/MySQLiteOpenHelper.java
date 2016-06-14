package com.csdn.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 数据库辅助类
 * 
 * @author devilwwj
 * 
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

	/**
	 * 
	 * @param context
	 *            上下文
	 * @param name
	 *            数据库名字
	 * @param factory
	 *            工厂
	 * @param version
	 *            版本号
	 */
	public MySQLiteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public MySQLiteOpenHelper(Context context, String name, int version) {
		super(context, name, null, version);
	}

	public MySQLiteOpenHelper(Context context, String name) {
		super(context, name, null, 1);
	}

	// 创建数据库
	@Override
	public void onCreate(SQLiteDatabase db) {
		// 建立一张表
		String sql = "CREATE TABLE person ( _id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL)";
		db.execSQL(sql);

		Log.i("onCreate", "创建数据库");
	}

	// 更新数据库
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i("onUpgrade", "更新数据库");
	}

}
