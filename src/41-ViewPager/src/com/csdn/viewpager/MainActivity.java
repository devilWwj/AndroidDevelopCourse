package com.csdn.viewpager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

/**
 * ViewPager
 * @author devilwwj
 *
 */
public class MainActivity extends Activity {
	private ViewPager viewPager;
	private MyViewPagerAdapter adapter;
	private List<View> views;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		viewPager = (ViewPager) this.findViewById(R.id.viewpager);
		LayoutInflater inflater = LayoutInflater.from(this);
		
		views = new ArrayList<View>();
		views.add(inflater.inflate(R.layout.view, null));
		views.add(inflater.inflate(R.layout.view2, null));
		views.add(inflater.inflate(R.layout.view3, null));
		
		// 实例化页面适配器
		adapter = new MyViewPagerAdapter(views);
		
		
		// 设置ViewPager的页面适配器
		viewPager.setAdapter(adapter);
		
		
		// 设置ViewPager页面切换事件监听
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				Toast.makeText(MainActivity.this, "页面" + arg0, Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
