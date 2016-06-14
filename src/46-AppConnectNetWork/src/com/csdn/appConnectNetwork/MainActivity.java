package com.csdn.appConnectNetwork;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.ListView;

import com.csdn.appConnectNetwork.domain.Contact;
import com.csdn.service.ContactService;

public class MainActivity extends Activity {
	ListView listView;
	File cache; // 缓存文件

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// 这里去显示我们的列表内容
			listView.setAdapter(new ContactAdapter(MainActivity.this, (List<Contact>) msg.obj, R.layout.listview_item, cache));
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) this.findViewById(R.id.listView);

		// 实例化缓存文件
		cache = new File(Environment.getExternalStorageDirectory(), "cache");
		
		// 如果文件不存在，创建
		if (!cache.exists()) {
			cache.mkdirs();
		}

		// 开一个线程来加载数据
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					List<Contact> contacts = ContactService.getContacts();
					handler.sendMessage(handler.obtainMessage(22, contacts));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		
	}

	@Override
	protected void onDestroy() {
		// 删除缓存
		for (File file : cache.listFiles()) {
			file.delete();
		}
		cache.delete();
		super.onDestroy();
	}

}