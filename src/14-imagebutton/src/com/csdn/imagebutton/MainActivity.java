package com.csdn.imagebutton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * ImageButton使用案例
 * @author devilwwj
 *
 */
public class MainActivity extends Activity {
	private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        imageButton = (ImageButton) this.findViewById(R.id.imagebutton);
        
        imageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "我是小黄人，我很贱", Toast.LENGTH_SHORT).show();
			}
		});
    }


}
