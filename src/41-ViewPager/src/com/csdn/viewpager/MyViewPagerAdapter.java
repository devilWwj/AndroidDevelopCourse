package com.csdn.viewpager;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
/**
 * 自定义ViewPager视图适配器
 * @author devilwwj
 *
 */
public class MyViewPagerAdapter extends PagerAdapter {
	
	private List<View> views;
	
	public MyViewPagerAdapter(List<View> views) {
		super();
		this.views = views;
	}

	// 获取当前窗体界面数
	@Override
	public int getCount() {
		return views.size();
	}

	/**
	 * 初始化position位置的界面
	 */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// 添加视图
		container.addView(views.get(position));
		return views.get(position);
	}
	
	/**
	 * 判断对象生成界面
	 */
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// 官方推荐写法
		return arg0 == arg1;
	}
	
	
	/**
	 * 销毁position位置的界面
	 */
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// 移除界面
		container.removeView(views.get(position));
	}

}
