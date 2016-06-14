package com.csdn.linearlayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * ExpandableListView的使用
 * 
 * @author devilwwj
 * 
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 通过代码动态添加控件
		LinearLayout linearLayout = (LinearLayout) this
				.findViewById(R.id.LinearLayout1);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		
		Button button = new Button(this);
		button.setLayoutParams(params);
		button.setText("Button3");
		
		linearLayout.addView(button);
		
		

	}

}
