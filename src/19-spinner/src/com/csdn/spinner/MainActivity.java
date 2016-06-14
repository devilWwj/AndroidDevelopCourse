package com.csdn.spinner;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity {
	private Spinner mSpinner;
	private List<String> list = new ArrayList<String>();
	private ArrayAdapter<String> adapter = null;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSpinner = (Spinner) this.findViewById(R.id.spinner);
        
        // step1: 添加数据源
        list.add("北京");
        list.add("上海");
        list.add("广州");
        list.add("深圳");
        
        // step2: 为spinner定义一个适配器
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        
        // step3: 设置spinner下拉的样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        
        // step4: 为spinner设置适配器
        mSpinner.setAdapter(adapter);
        
        // step5：设置列表项的监听事件
        mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(), adapter.getItem(position), Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
        
        
    }

}
