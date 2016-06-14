package com.csdn.sharedpreference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

/**
 * SharedPreperences存储
 * @author devilwwj
 *
 */
public class MainActivity extends Activity implements OnClickListener{
	private SharedPreferences preferences;
	private EditText editText;
	private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        editText = (EditText) this.findViewById(R.id.editText1);
        
        textView = (TextView) this.findViewById(R.id.textview);
        
        
        
        preferences = this.getSharedPreferences("csdn", MODE_PRIVATE);
        textView.setText(preferences.getString("key", "CSDN学院"));
    }

    
   
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			String str = editText.getText().toString();
			Editor editor = preferences.edit();
			
			editor.putString("key", str);
			
			editor.commit();
			
			break;

		default:
			break;
		}
	}


}
