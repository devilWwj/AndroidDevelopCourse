package com.csdn.ratingbar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;

/**
 * seekbar使用案例
 * @author devilwwj
 *
 */
public class MainActivity extends Activity {
	private RatingBar ratingBar = null;
	private ImageView imageView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ratingBar = (RatingBar) this.findViewById(R.id.ratingbar);
        imageView = (ImageView) this.findViewById(R.id.imageview);
        
        // 设置ratingBar监听事件
        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				// 动态改变图片的透明度
				imageView.setAlpha((int)(rating * 255) / 5);
			}
		});
        
    }


}
