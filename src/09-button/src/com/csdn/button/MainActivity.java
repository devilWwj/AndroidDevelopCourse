package com.csdn.button;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener{
	private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        
        // 第一种
//        button.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(MainActivity.this, "小巫无敌帅", Toast.LENGTH_SHORT).show();
//			}
//		});
        
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			Toast.makeText(MainActivity.this, "小巫无敌帅", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}

    
    

}
