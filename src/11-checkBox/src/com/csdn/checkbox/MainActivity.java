package com.csdn.checkbox;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;


public class MainActivity extends Activity {
	private CheckBox checkBox1;
	private CheckBox checkBox2;
	private CheckBox checkBox3;
	private TextView textView;
	
	private List<String> strs;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        checkBox1 = (CheckBox) this.findViewById(R.id.cb_color1);
        checkBox2 = (CheckBox) this.findViewById(R.id.cb_color2);
        checkBox3 = (CheckBox) this.findViewById(R.id.cb_color3);
        textView = (TextView) this.findViewById(R.id.textView);
        
        // 设置checkbox的一个监听事件
        checkBox1.setOnCheckedChangeListener(new CheckedChangeListener());
        checkBox2.setOnCheckedChangeListener(new CheckedChangeListener());
        checkBox3.setOnCheckedChangeListener(new CheckedChangeListener());
        
    }
    
    private class CheckedChangeListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			String str = "";
			if (checkBox1.isChecked()) {
				str = str +"," + checkBox1.getText();
			}
			if (checkBox2.isChecked()) {
				str = str +"," + checkBox2.getText();
			}
			if (checkBox3.isChecked()) {
				str = str +"," + checkBox3.getText();
			}
			if (str.indexOf(",") == 0) {
				str = str.substring(1, str.length());
			}
			
			textView.setText("您选中了:" + str);
		}
    	
    }
    

}
