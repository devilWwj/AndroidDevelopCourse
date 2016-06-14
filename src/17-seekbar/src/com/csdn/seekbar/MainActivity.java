package com.csdn.seekbar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * seekbar使用案例
 * @author devilwwj
 *
 */
public class MainActivity extends Activity {
	private SeekBar seekBar = null;
	private ImageView imageView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        seekBar = (SeekBar) this.findViewById(R.id.seekbar);
        imageView = (ImageView) this.findViewById(R.id.imageview);
        
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// 动态设置图片的透明度
				imageView.setAlpha(progress);
			}
		});
    }


}
