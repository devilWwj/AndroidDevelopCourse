package com.csdn.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
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
	
	private MyReceiver receiver = new MyReceiver();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("onCreate", "------onCreate-------");
		
		IntentFilter filter = new IntentFilter();
		filter.addAction("android.intent.action.MY_BROADCAST");
		// 注册好了一个广播接收器
		registerReceiver(receiver, filter);


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
		// 取消广播注册
		unregisterReceiver(receiver);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.MY_BROADCAST");
		intent.putExtra("msg", "CSDN学院Android视频教程");
		switch (v.getId()) {
		case R.id.btn_send: // 发送一条广播
			sendBroadcast(intent);
			break;
		default:
			break;
		}
	}

}
