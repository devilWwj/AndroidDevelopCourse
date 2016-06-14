package com.csdn.radiobutton;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;


public class MainActivity extends Activity {
	private RadioGroup radioGroup;
	private TextView sexyText;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        radioGroup = (RadioGroup) this.findViewById(R.id.radiogroup);
        
        sexyText = (TextView) this.findViewById(R.id.sexyText);
        
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int radioId = group.getCheckedRadioButtonId();
				RadioButton radioButton = (RadioButton) MainActivity.this.findViewById(radioId);
				
				sexyText.setText(radioButton.getText());
				
			}
		});
        
    }

}
