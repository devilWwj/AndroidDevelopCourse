package com.csdn.progressbar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * ImageView使用案例
 * 
 * @author devilwwj
 * 
 */
public class MainActivity extends Activity {
	private Button button;
	private ProgressBar cicleProgress;
	private ProgressBar horizontalProgress;
	private int count; // 用来计算进度
	
	private static final int STOP = 0X001;
	private static final int NEXT = 0X002;
	
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// 进行UI更新
			switch (msg.what) {
			case STOP:
				Thread.currentThread().interrupt();// 停止线程
				Toast.makeText(MainActivity.this
						, "进度已更新完毕", Toast.LENGTH_SHORT).show();
				break;
			case NEXT:
				horizontalProgress.setProgress(count);
				break;
			default:
				break;
			}
		};
	};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		button = (Button) this.findViewById(R.id.botton);
		cicleProgress = (ProgressBar) this
				.findViewById(R.id.large_circle_progress);
		horizontalProgress = (ProgressBar) this
				.findViewById(R.id.horizontal_progress);
		horizontalProgress.setMax(100);
		horizontalProgress.setProgress(0);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							for (int i = 0; i < 20; i++) {
								count = (i + 1) * 5; // 步长为5
								Thread.sleep(1000); // 每一秒更新一次进度
								if (i == 19) {
									// 发送一条消息来停止更新
									Message msg = new Message();
									msg.what = STOP;
									handler.sendMessage(msg);
								} else {
									// 我们继续更新进度条
									Message msg = new Message();
									msg.what = NEXT;
									handler.sendMessage(msg);
									
								}
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						
					}
				}).start();
				
			}
		});


	}

}
