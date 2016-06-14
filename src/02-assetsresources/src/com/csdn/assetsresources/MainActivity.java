package com.csdn.assetsresources;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.util.EncodingUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity {
	private TextView assetsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
       assetsText =  (TextView) this.findViewById(R.id.tv_assets);
       assetsText.setText(getFromAssets("test.txt"));
    }
    
    private String getFromAssets(String filename) {
    	String result = "";
    	try {
			InputStream in = getResources().getAssets().open(filename);
			int length = in.available();
			
			byte[] buffer = new byte[length];
			
			in.read(buffer);
			
			result = EncodingUtils.getString(buffer, "UTF-8");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return result;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
