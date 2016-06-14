package com.csdn.propertyAnimator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 
 * property Animator
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
		case R.id.button1: // 透明度
			Animator animator1 = AnimatorInflater.loadAnimator(MainActivity.this, R.animator.alpha_animator);
			animator1.setTarget(textView);
			animator1.start();
			break;
		case R.id.button2: // 平移
			Animator animator2 = AnimatorInflater.loadAnimator(MainActivity.this, R.animator.translate_animator);
			animator2.setTarget(textView);
			animator2.start();
			break;
		case R.id.button3: // 缩放
			Animator animator3 = AnimatorInflater.loadAnimator(MainActivity.this, R.animator.scale_animator);
			animator3.setTarget(textView);
			animator3.start();
			break;
		case R.id.button4: // 旋转
			Animator animator4 = AnimatorInflater.loadAnimator(MainActivity.this, R.animator.rotate_animator);
			animator4.setTarget(textView);
			animator4.start();
			break;
		case R.id.button5: // 多重动画
			AnimatorSet animatorSet = new AnimatorSet();
			Animator alphaAnimator = AnimatorInflater.loadAnimator(MainActivity.this, R.animator.alpha_animator);
			Animator scaleAnimator = AnimatorInflater.loadAnimator(MainActivity.this, R.animator.scale_animator);
			Animator rotateAnimator = AnimatorInflater.loadAnimator(MainActivity.this, R.animator.rotate_animator);
			animatorSet.play(alphaAnimator).after(scaleAnimator).with(rotateAnimator);
			animatorSet.setTarget(textView); // 绑定
			animatorSet.start();
			
			break;

		default:
			break;
		}
	}

}
