package com.csdn.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Toast和Menu
 * @author devilwwj
 *
 */
public class MainActivity extends Activity implements OnClickListener {
	FragmentManager fragmentManager;
	FragmentTransaction transaction;

	
	private MyFragment fragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		fragment = new MyFragment();

		// 得到Fragment的管理实例
		fragmentManager = getFragmentManager();
		
		// 开启一个事务
		transaction = fragmentManager.beginTransaction();
		
		// 添加一个Fragment
		transaction.add(R.id.content_layout, fragment);
		
		// 提交事务
		transaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			transaction = fragmentManager.beginTransaction();
			
			// 替换
//			transaction.replace(R.id.content_layout, new MyFragment2());
//			
			
			
			// 移除Fragment
			transaction.remove(fragment);
			
			transaction.commit();
			
			break;

		default:
			break;
		}
	}

	
}
