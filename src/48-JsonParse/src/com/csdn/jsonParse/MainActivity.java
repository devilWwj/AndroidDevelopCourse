package com.csdn.jsonParse;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import com.google.gson.Gson;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * JSON解析
 * @author devilwwj
 *
 */
public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			// 方法1：构建一个json
			JSONObject person = new JSONObject();
			
			try {
				person.put("name", "zhangsan");
				person.put("age", 10);
				JSONArray phones = new JSONArray();
				phones.put("15998589231").put("15329297323");
				person.put("phone", phones);
				
				String jsonData = person.toString();
				Log.i("info", jsonData);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			String s = "";
			// 方法二：使用JSONStringer构建一个json
			try {
				JSONStringer jsonStringer = new JSONStringer();
				
				jsonStringer.object();
				jsonStringer.key("name").value("zhangsan");
				jsonStringer.key("age").value(19);
				
				jsonStringer.key("phones");
				jsonStringer.array();
				jsonStringer.value("15998589231").value("15329297323");
				jsonStringer.endArray();
				
				jsonStringer.endObject();
				
				s = jsonStringer.toString();
				Log.i("info", s);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			// 对json字符串进行解析
			try {
				JSONObject jsonObject  = new JSONObject(s);
				String name = jsonObject.getString("name");
				int age = jsonObject.getInt("age");
				JSONArray array = jsonObject.getJSONArray("phones");
				String phone1 = array.getString(0);
				String phone2 = array.getString(1);
				List<String> numbers = new ArrayList<String>();
				numbers.add(phone1);
				numbers.add(phone2);
				
				Person p = new Person(name, age, numbers);
				
				Log.i("person", p.toString());
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
				
			break;
		case R.id.button2:
			List<String> phones = new ArrayList<String>();
			phones.add("13889979709");
			phones.add("13989979709");
			phones.add("13989979708");
			Person person2 = new Person("xiaowu", 18, phones);
			
			// 实例化Gson对象 
			Gson gson = new Gson();
			String gsonStr = gson.toJson(person2);  // 对象转换为json字符串
			
			Log.i("info", gsonStr);
			
			// 将字符串转换为对象
			Person person1 = gson.fromJson(gsonStr, Person.class);
			Log.i("info2", person1.toString());
			break;

		default:
			break;
		}
	}

	
}
