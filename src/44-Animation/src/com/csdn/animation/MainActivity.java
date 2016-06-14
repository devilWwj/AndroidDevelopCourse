package com.csdn.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * 
 * Animation动画
 * 
 * @author devilwwj
 * 
 */
public class MainActivity extends Activity implements OnClickListener {
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textView = (TextView) findViewById(R.id.textView1);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			Animation animation = AnimationUtils.loadAnimation(this,
					R.anim.alpha_anim);
			textView.startAnimation(animation);
			break;
		case R.id.button2:
			Animation animation2 = AnimationUtils.loadAnimation(this,
					R.anim.translate_anim);
			textView.startAnimation(animation2);
			break;
		case R.id.button3:
			Animation animation3 = AnimationUtils.loadAnimation(this,
					R.anim.scale_anim);
			textView.startAnimation(animation3);
			break;
		case R.id.button4:
			Animation animation4 = AnimationUtils.loadAnimation(this,
					R.anim.rotate_anim);
			textView.startAnimation(animation4);
			break;
		case R.id.button5:
			Animation animation5 = AnimationUtils.loadAnimation(this,
					R.anim.set_anim);
			textView.startAnimation(animation5);
			break;

		default:
			break;
		}
	}

}
