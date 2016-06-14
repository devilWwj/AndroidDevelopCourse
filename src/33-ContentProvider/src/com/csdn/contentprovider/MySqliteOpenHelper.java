package com.csdn.contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库帮助类
 * @author devilwwj
 *
 */
public class MySqliteOpenHelper extends SQLiteOpenHelper {
	public static final String DBNAME = "book.db";
	public static final String TABLENAME = "booktbl";

	

	public MySqliteOpenHelper(Context context) {
		super(context, DBNAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 创建一张表
		String sql = "CREATE TABLE "
				+ TABLENAME
				+ " ( _id INTEGER PRIMARY KEY AUTOINCREMENT," +
				" name TEXT NOT NULL, " +
				" price FLOAT NOT NULL, " +
				" publisher TEXT NOT NULL )";
		db.execSQL(sql);
		
		// 插入数据
		ContentValues values = new ContentValues();
		values.put("name", "Android入门实战");
		values.put("price", 30.0);
		values.put("publisher", "CSDN学院出版社");
		db.insert(TABLENAME, null, values);
		values.clear();
		
		// 插入第二条数据
		values.put("name", "Android高级编程");
		values.put("price", 50.0);
		values.put("publisher", "CSDN学院出版社");
		db.insert(TABLENAME, null, values);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// 更新数据库
	}

}
