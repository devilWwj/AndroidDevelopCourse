package com.csdn.anim;

import java.util.Timer;
import java.util.TimerTask;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 05-menu资源教程
 * @author devilwwj
 *
 */
public class MainActivity extends Activity implements OnClickListener{
	
	private ImageView frameAnim;
	private Button startButton, stopButton;
	private Button tweenAnimButton;
	private Button propertyAnimButton;
	
	private ImageView tweenAnim;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        
        frameAnim = (ImageView) this.findViewById(R.id.anim);
        
        startButton = (Button) this.findViewById(R.id.btn_start);
        stopButton = (Button) this.findViewById(R.id.btn_stop);
        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        
        tweenAnimButton = (Button) this.findViewById(R.id.btn_play_tween_anim);
        tweenAnimButton.setOnClickListener(this);
        tweenAnim = (ImageView) this.findViewById(R.id.tween_anim);
        
        propertyAnimButton = (Button) this.findViewById(R.id.btn_play_property_anim);
        propertyAnimButton.setOnClickListener(this);
        
    }
    
    private Handler handler = new Handler() {
    	public void handleMessage(android.os.Message msg) {
    		if (msg.what == 0x123) {
    			Animation reverseAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.tween_anim_reverse);
    			tweenAnim.startAnimation(reverseAnim);
    		}
    	};
    };


    /**
     * 启动逐帧动画
     */
    public void startFrameAnim() {
    	AnimationDrawable animationDrawable = (AnimationDrawable) frameAnim.getBackground();
    	animationDrawable.start();
    }
    /**
     * 停止逐帧动画
     */
    public void stopFrameAnim() {
    	AnimationDrawable animationDrawable = (AnimationDrawable) frameAnim.getBackground();
    	animationDrawable.stop();
    }
    
    /**
     * 加载第一份动画资源
     */
    public void loadFristTweenAnim() {
    	Animation animation = AnimationUtils.loadAnimation(this, R.anim.tween_anim);
    	// 设置动画结束后保留结束状态
    	animation.setFillAfter(true);
    	
    	tweenAnim.startAnimation(animation);
    	
    	// 设置0.5秒后启动第二个布局动画
    	new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				handler.sendEmptyMessage(0x123);
			}
		}, 500);
    	
    }
    
    // ValueAnimator的使用
    public void propertyAnimation() {
    	ValueAnimator anim = ValueAnimator.ofFloat(0f, 1f);
    	anim.setDuration(300);
    	
    	anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float currentValue = (Float) animation.getAnimatedValue();
				Log.d("TAG", ""+currentValue);
			}
		});
    	
    }
    
    // ObjectAnimator的使用
    public void objectAnimation() {
//    	ObjectAnimator animator = ObjectAnimator.ofFloat(tweenAnimButton, "alpha", 1f, 0f, 1f);
//    	animator.setDuration(5000);
//    	animator.start();
    	
    	
    	// propertyName
    	// alpha rotation translationX tranlationY scaleX scaleY...
    	
    	// 组合动画
//    	ObjectAnimator moveIn = ObjectAnimator.ofFloat(tweenAnim, "translationX", -500f, 0);
//    	ObjectAnimator rotate = ObjectAnimator.ofFloat(tweenAnim, "rotation", 0f, 360f);
//    	ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(tweenAnim, "alpha", 0f, 1f);
//    	AnimatorSet animSet = new AnimatorSet();
//    	animSet.play(rotate).with(fadeInOut).after(moveIn);
//    	animSet.setDuration(3000);
//    	animSet.start();
//    	
    	// 加载xml动画
    	Animator animator = AnimatorInflater.loadAnimator(this, R.animator.object_animator);
    	animator.setTarget(tweenAnim);
    	animator.start();
    	
    	// 方式一：设置动画监听事件
    	animator.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				
			}
		});
    	
    	// 方式二：设置监听适配类
    	animator.addListener(new AnimatorListenerAdapter() {
    		// 选择你需要实现的回调方法
    		@Override
    		public void onAnimationEnd(Animator animation) {
    			// TODO Auto-generated method stub
    			super.onAnimationEnd(animation);
    		}
		});
    }


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_start:
			startFrameAnim();
			break;
		case R.id.btn_stop:
			stopFrameAnim();
			break;
		case R.id.btn_play_tween_anim:
			loadFristTweenAnim();
			break;
		case R.id.btn_play_property_anim:
			objectAnimation();
			break;
		default:
			break;
		}
	}
}
