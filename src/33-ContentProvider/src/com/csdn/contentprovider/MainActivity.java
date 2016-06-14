package com.csdn.contentprovider;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;


/**
 * 五大组件之Service
 * 
 * @author devilwwj
 * 
 */
public class MainActivity extends Activity implements OnClickListener {
	private EditText etName, etPhone;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("onCreate", "------onCreate-------");
			
		etName = (EditText) findViewById(R.id.edittext1);
		etPhone = (EditText) findViewById(R.id.edittext2);


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
		switch (v.getId()) {
		case R.id.button1: // 添加联系人
			// 获取输入名称
			String name = etName.getText().toString();
			// 获取输入电话号
			String phone = etPhone.getText().toString();
			
			
			// 插入一条假数据，来获取这个插入的id值
			ContentValues values = new ContentValues();
			Uri rawContactUri = getContentResolver().insert(RawContacts.CONTENT_URI, values);
			
			long id = ContentUris.parseId(rawContactUri);
			// 首先插入名称
			values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
			values.put(Data.RAW_CONTACT_ID, id);
			values.put(StructuredName.GIVEN_NAME, name);
			
			getContentResolver().insert(Data.CONTENT_URI, values);
			values.clear();
			
			// 插入电话号
			values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
			values.put(Data.RAW_CONTACT_ID, id);
			values.put(Phone.NUMBER, phone);
			values.put(Phone.TYPE, Phone.TYPE_MOBILE);
			getContentResolver().insert(Data.CONTENT_URI, values);
			
			Log.i("info", "联系人信息已插入");
			
			break;
		default:
			break;
		}
	}

}
