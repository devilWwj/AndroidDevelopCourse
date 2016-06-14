package com.csdn.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/**
 * ListView使用案例
 * 
 * @author devilwwj
 * 
 */
public class MainActivity extends Activity {

	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) this.findViewById(R.id.listview);

		// step1. 定义数据源
		// List<Map<String, String>> data = new ArrayList<Map<String,
		// String>>();
		// for (int i = 0; i < 20; i++) {
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("TITLE", "CSDN学院课程" + i);
		// data.add(map);
		// }
		//
		// // step2. 定义我们的adapter
		// SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, data,
		// android.R.layout.simple_list_item_1, new String[] { "TITLE" },
		// new int[] { android.R.id.text1 });

		List<Course> data = new ArrayList<Course>();
		for (int i = 0; i < 20; i++) {
			Course course = new Course();
			course.setTitle("CSDN学院Android实战课程" + i);
			course.setContent("listView教程" + i);
			data.add(course);
		}

		ListAdapter adapter = new ListAdapter(this, data);

		// step3. 为listView设置它的适配器
		listView.setAdapter(adapter);

		// step4. 设置listView的列表项点击事件
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(MainActivity.this, "列表项" + position,
						Toast.LENGTH_SHORT).show();
			}
		});

	}

}
