package com.csdn.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 五大组件之Activity
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

	}

	/**
	 * 隐式Intent启动
	 */
	public void implicitIntent() {
		Intent intent = new Intent();
		intent.setAction("com.csdn.action");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.setDataAndType(Uri.parse("xiaowu://www.baidu.com/person"),
				"person/people");
		startActivity(intent);
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
		case R.id.button1:
//			Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//			startActivity(intent);
			implicitIntent();
			break;

		default:
			break;
		}
	}

}
