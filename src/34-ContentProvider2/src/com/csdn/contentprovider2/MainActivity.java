package com.csdn.contentprovider2;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;


/**
 * 五大组件之ContentProvider
 * 
 * @author devilwwj
 * 
 */
public class MainActivity extends Activity implements OnClickListener {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("onCreate", "------onCreate-------");
			

		// 执行插入操作
		Uri uri = Uri.parse("content://com.csdn.providers.book/book");
		ContentValues values = new ContentValues();
		values.put("name", "Java讲义");
		values.put("price", 50.0);
		values.put("publisher", "CSDN出版社");
		Uri retUri = getContentResolver().insert(uri, values);
		long id = ContentUris.parseId(retUri);
		
		Log.i("parseId", "插入的id为：" + id);
		
		// 执行查询操作
		Cursor c = getContentResolver().query(uri, null, null, null, null);
		
		if (c != null) {
			while(c.moveToNext()) {
				String[] cols = c.getColumnNames();
				
				for (String col : cols) {
					Log.i("info", col + ":" + c.getString(c.getColumnIndex(col)));
				}
				
				Log.i("info", "+++++++++++++++++");
			}
		}
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i("onRestart", "------onRestart-------");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("onResume", "------onResume-------");

	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i("onPause", "------onPause-------");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i("onStop", "------onStop-------");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("onDestroy", "------onDestroy-------");
	}

	@Override
	public void onClick(View v) {
		
	}

}
