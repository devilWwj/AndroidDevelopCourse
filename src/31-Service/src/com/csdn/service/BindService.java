package com.csdn.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BindService extends Service {
	private int count;
	private boolean quit;

	private MyBinder binder = new MyBinder();

	// 通过继承Binder来实现IBinder类
	public class MyBinder extends Binder {
		public int getCount() {
			// 获取Service的运行状态
			return count;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("Service is Binded ");  
		// 返回IBinder对象
		return binder;
	}

	// Service被创建的时候回调
	@Override
	public void onCreate() {
		super.onCreate();

		// 启动一个线程，动态修改count值

		new Thread() {
			public void run() {
				while (!quit) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					count++;
				}
			};
		}.start();

	}

	// Service被断开连接时回调该方法
	@Override
	public boolean onUnbind(Intent intent) {
		System.out.println("Service is Unbinded");
		return true;
	}

	// Service被关闭之前回调
	@Override
	public void onDestroy() {
		super.onDestroy();
		this.quit = true;
		 System.out.println("Service is Destroyed"); 
	}
}
