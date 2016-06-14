package com.csdn.scrollview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ScrollView;

/**
 * ExpandableListView的使用
 * @author devilwwj
 *
 */
public class MainActivity extends Activity {
	private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        scrollView = (ScrollView) this.findViewById(R.id.scrollView);
        
        // 指定滑动位置
        scrollView.scrollTo(0, scrollView.getBottom());
    }
    
    
    

}
