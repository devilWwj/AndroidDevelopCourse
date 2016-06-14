package com.csdn.service;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 五大组件之Service
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
		Intent intent = new Intent();
		intent.setAction("com.csdn.FIRST_SERVICE");
		switch (v.getId()) {
		case R.id.btn_start_service:
			startService(intent);
			break;
		case R.id.btn_stop_service:
			stopService(intent);
			break;
		default:
			break;
		}
	}

}
