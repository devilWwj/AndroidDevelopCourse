package com.csdn.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
/**
 * 自定义Content Provider
 * @author devilwwj
 *
 */
public class BookContentProvider extends ContentProvider{
	private static UriMatcher matcher;
	private static final String TABLENAME = "booktbl";
	private MySqliteOpenHelper helper;
	
	
	// 添加规则
	static {
		matcher = new UriMatcher(UriMatcher.NO_MATCH);
		matcher.addURI("com.csdn.providers.book", "book", 1);// 多值查询
		matcher.addURI("com.csdn.providers.book", "book/#", 2); // 单值查询
	}
	

	@Override
	public boolean onCreate() {
		helper = new MySqliteOpenHelper(getContext());
		return true;
	}

	// 查询
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = null;
		switch (matcher.match(uri)) {
		case 1: // 多值查询
			cursor = db.query(TABLENAME, null, null, null, null, null, null); // 返回整张表的数据
			break;
		case 2: // 单值查询
			
			break;
		default:
			break;
		}
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	// 插入
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = helper.getWritableDatabase();
		
		// 得到行id
		long rowId = db.insert(TABLENAME, null, values);
		
		// 拼接uri
		Uri retUri = ContentUris.withAppendedId(uri, rowId);
		return retUri;
	}

	// 删除
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	// 更新
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}

}
