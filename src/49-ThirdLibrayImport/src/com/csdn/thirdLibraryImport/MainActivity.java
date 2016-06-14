package com.csdn.thirdLibraryImport;

import com.example.library.Utils;

import android.app.Activity;
import android.os.Bundle;

/**
 * 第三方库引入介绍
 * @author devilwwj
 *
 */
public class MainActivity extends Activity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Utils.showToast(this, "我是一个库项目文件");

	}


	
}
