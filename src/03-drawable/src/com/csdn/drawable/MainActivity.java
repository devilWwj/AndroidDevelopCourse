package com.csdn.drawable;

import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends Activity {
	private ImageView ivLamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //-----图像级别资源
//        ivLamp = (ImageView) findViewById(R.id.imageview_lamp);
//         设置Level为8，显示lamp_off.png
//        ivLamp.setImageLevel(8);
        //------
        
        //-----剪切资源
//        ImageView imageView = (ImageView) findViewById(R.id.image);
//        ClipDrawable drawable = (ClipDrawable) imageView.getBackground();
//        // 截取30％的图像
//        drawable.setLevel(3000);
        //------
    }

    
    public void onClick_LampOn(View view) {
    	// 设置Level为15， 显示lamp_on
//    	ivLamp.setImageLevel(15);
    	
    	// 从第一个图像切换到第二个图像。其中使用1秒的时间完成淡入淡出效果
    	TransitionDrawable drawable = (TransitionDrawable) ivLamp.getDrawable();
    	drawable.startTransition(1000);
    }
    
    public void onClick_LampOff(View view) {
    	// 设置Level为6，显示lamp_off
//    	ivLamp.getDrawable().setLevel(6);
    	
    	
    	
    	// 从第二个图像切换到第二个图像。其中使用1秒的时间完成淡入淡出效果
    	TransitionDrawable drawable = (TransitionDrawable) ivLamp.getDrawable();
    	drawable.reverseTransition(1000);
    }

  
}
