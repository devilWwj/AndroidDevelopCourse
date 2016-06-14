package com.csdn.listview;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
	List<Course> list = null;
	Context context;
	
	public ListAdapter(Context context, List<Course> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.list_item_layout, null);
			
			holder = new ViewHolder();
			holder.imageView = (ImageView) convertView.findViewById(R.id.imageview);
			holder.titleText = (TextView) convertView.findViewById(R.id.textview);
			holder.content = (TextView) convertView.findViewById(R.id.content);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		Course course = list.get(position);
		
		holder.imageView.setImageResource(R.drawable.cat);
		holder.titleText.setText(course.getTitle());
		holder.content.setText(course.getContent());
		
		
		return convertView;
	}
	
	class ViewHolder {
		ImageView imageView;
		TextView titleText;
		TextView content;
	}
	
	
}
