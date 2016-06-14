package com.csdn.service;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class BindServiceActivity extends Activity implements OnClickListener {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two);
	}

	// 保持所启动的Service的IBinder对象
	BindService.MyBinder binder;

	// 定义一个ServiceConnection对象
	private ServiceConnection conn = new ServiceConnection() {

		// 当该Activity与Service断开连接时回调该方法
		@Override
		public void onServiceDisconnected(ComponentName name) {
			System.out.println("--Service Disconnected--");
		}

		// 当该Activity与Service连接成功后回调该方法
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			System.out.println("--Service Connected--");
			binder = (BindService.MyBinder) service;
		}
	};
	
	

	@Override
	public void onClick(View v) {
		// 创建启动Service的Intent
		final Intent intent = new Intent();
		// 为Intent设置Action
		intent.setAction("com.csdn.BIND_SERVICE");
		switch (v.getId()) {
		case R.id.btn_bind_service:
			bindService(intent, conn, Service.BIND_AUTO_CREATE);
			break;
		case R.id.btn_unbind_service:
			//解除绑定Service  
            unbindService(conn); 
			break;
		case R.id.btn_get_status:
			//获取并显示Service的count值  
            Toast.makeText(BindServiceActivity.this, "Service的count值为: " + binder.getCount(),   
                    4000).show();
			break;
		default:
			break;
		}
	}
}
