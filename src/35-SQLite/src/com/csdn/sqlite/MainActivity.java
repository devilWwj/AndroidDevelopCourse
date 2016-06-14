package com.csdn.sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * SQLite数据库课程
 * @author devilwwj
 *
 */
public class MainActivity extends Activity implements OnClickListener {
	private MySQLiteOpenHelper helper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1: // 创建数据库
			helper = new MySQLiteOpenHelper(this, "person.db");
			db = helper.getReadableDatabase();

			db.close(); // 关闭
			break;
		case R.id.button2: // 更新数据库
			// 传一个更高版本的verson
			helper = new MySQLiteOpenHelper(this, "person.db", 2);

			db = helper.getReadableDatabase();

			db.close();

			break;
		case R.id.button3: // 添加
			helper = new MySQLiteOpenHelper(this, "person.db", 2);

			db = helper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("name", "xiaowu");
			db.insert("person", null, values);

			db.close();

			break;
		case R.id.button4: // 修改
			helper = new MySQLiteOpenHelper(this, "person.db", 2);

			db = helper.getWritableDatabase();
			ContentValues values2 = new ContentValues();
			values2.put("name", "zhangsan");
			// 修改id为1的值
			db.update("person", values2, " _id = ?", new String[] { "1" });

			db.close();
			break;
		case R.id.button5: // 查询
			helper = new MySQLiteOpenHelper(this, "person.db", 2);

			db = helper.getReadableDatabase();

			// 全表查询
			Cursor cursor = db.query("person", null, null, null, null, null,
					null);
			while (cursor.moveToNext()) {
				// 通过字段名获取字段索引，根据索引来获取字段的值
				int id = cursor.getInt(cursor.getColumnIndex("_id"));
				String name = cursor.getString(cursor.getColumnIndex("name"));

				Log.i("info-->", "_id:" + id + " name: " + name);
			}

			// 根据条件查询
			// Cursor cursor2 = db.query("person", new String[]{"name"},
			// " _id > ?", new String[] {"5"}, null, null,
			// null);
			// 关闭游标
			cursor.close();
			db.close();
			break;
		case R.id.button6: // 删除
			helper = new MySQLiteOpenHelper(this, "person.db", 2);

			db = helper.getReadableDatabase();

			// 删除id为5的数据
			int count = db.delete("person", " _id = ?", new String[] { "5" });

			Log.i("info-->", "删除条数:" + count);
			break;
		case R.id.button7: // 事务处理
			helper = new MySQLiteOpenHelper(this, "person.db", 2);
			db = helper.getWritableDatabase();
			
			// 开始事务
			db.beginTransaction();
			for (int i = 0; i < 100; i++) {
				ContentValues contentValues = new ContentValues();
				contentValues.put("name", "xiaowu" + i);

				db.insert("person", null, contentValues);
			}

			// 全表查询
			Cursor cursor2 = db.query("person", null, null, null, null, null,
					null);
			while (cursor2.moveToNext()) {
				int id = cursor2.getInt(cursor2.getColumnIndex("_id"));
				String name = cursor2.getString(cursor2.getColumnIndex("name"));

				Log.i("info-->", "_id:" + id + " name: " + name);
			}
			
			// 设置事务成功
			db.setTransactionSuccessful();
			
			// 结束事务
			db.endTransaction();
			
			cursor2.close();
			db.close();

			break;
		default:
			break;
		}
	}

}
