package com.csdn.actionbar;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * actionBar的使用
 * 
 * @author devilwwj
 * 
 */
public class MainActivity extends Activity implements OnClickListener {
	private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 对ActionBar进行实例化
		actionBar = getActionBar();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			actionBar.show(); // 显示ActionBar
			break;
		case R.id.button2:
			actionBar.hide(); // 隐藏ActionBar
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem add = menu.add(0, 0, 0, "添加");
		MenuItem delete = menu.add(0, 1, 1, "删除");
		MenuItem update = menu.add(0, 2, 2, "更新");
		MenuItem update2 = menu.add(0, 3, 3, "更新");
		MenuItem update3 = menu.add(0, 4, 4, "更新");
		MenuItem update4 = menu.add(0, 5, 5, "更新");

		add.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		delete.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		update.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		update2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		update3.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		update4.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		return true;
	}

}
