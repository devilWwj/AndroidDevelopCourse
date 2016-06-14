package com.csdn.values;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;

/**
 * 07-values资源教程
 * @author devilwwj
 *
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Resources res = getResources();
        // 通过getString(int)方法得到字符串对象
        String str = res.getString(R.string.app_name);
        
        // 通过getStringArray(int)方法得到字符串数组对象
        String[] strArray = res.getStringArray(R.array.planets_array);
       
    }


}
