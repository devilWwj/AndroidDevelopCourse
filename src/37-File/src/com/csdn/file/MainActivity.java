package com.csdn.file;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * File存储
 * @author devilwwj
 *
 */
public class MainActivity extends Activity implements OnClickListener{
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // 内部存储，getFilesDir()获取文件内部存储路径
        File file = new File(getFilesDir(), "csdn.txt");
        try {
        	// 创建一个新的文件
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        // getCacheDir()获取缓存目录
        File file2 = new File(getCacheDir(), "csdn2.txt");
        try {
        	// 创建一个新的文件
			file2.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        // 外部存储
        if(Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)){
        	// 外部存储存在
        	File file3 = new File(Environment.getExternalStorageDirectory(), "hello.txt");
        	Log.i("path", Environment.getExternalStorageDirectory().toString());
        	 try {
             	// 创建一个新的文件
     			file3.createNewFile();
     		} catch (IOException e) {
     			e.printStackTrace();
     		}
        }
        
        
    }

    
   
	@Override
	public void onClick(View v) {
		
	}


}
