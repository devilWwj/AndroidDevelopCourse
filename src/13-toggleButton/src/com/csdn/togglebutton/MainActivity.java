package com.csdn.togglebutton;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * 状态按钮实例
 * @author devilwwj
 *
 */
public class MainActivity extends Activity {
	private ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        toggleButton = (ToggleButton) this.findViewById(R.id.togglebutton);
        
        toggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (toggleButton.isChecked()) {
					Toast.makeText(MainActivity.this, "帅", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(MainActivity.this, "不帅", Toast.LENGTH_SHORT).show();
				}
			}
		});
    }


}
