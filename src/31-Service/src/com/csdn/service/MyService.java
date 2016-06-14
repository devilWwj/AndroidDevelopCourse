package com.csdn.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * 自定义服务
 * @author devilwwj
 *
 */
public class MyService extends Service {

	// 必须要实现的方法
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	// Service被创建时回调该方法
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("onCreate------>", "onCreate");
	}
	
	// Service被启动的时候回调该方法
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("onStartCommand------>", "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	// Service被关闭之前回调
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("onDestroy------>", "onDestroy");
	}
	

}
