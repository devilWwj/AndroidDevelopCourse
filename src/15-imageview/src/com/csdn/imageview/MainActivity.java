package com.csdn.imageview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

/**
 * ImageView使用案例
 * @author devilwwj
 *
 */
public class MainActivity extends Activity {
	private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        imageview = (ImageView)this.findViewById(R.id.imageview);
        
        // 通过代码设置图片
        imageview.setImageResource(R.drawable.ic_launcher);
        
        // 通过代码设置imageview的缩放
        imageview.setScaleType(ScaleType.FIT_XY);
        
    }


}
