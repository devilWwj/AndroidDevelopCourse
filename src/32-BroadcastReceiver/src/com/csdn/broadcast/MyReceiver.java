package com.csdn.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 通过继承BroadcastReceiver来实现一个广播接收器
 * @author devilwwj
 *
 */
public class MyReceiver extends BroadcastReceiver {
	
	private static final String TAG = "MyRecevier";

	@Override
	public void onReceive(Context context, Intent intent) {
		String msg = intent.getStringExtra("msg");
		Log.i(TAG, msg);
	}

}
