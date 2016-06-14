package com.csdn.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

/**
 * 05-menu资源教程
 * @author devilwwj
 *
 */
public class MainActivity extends Activity  implements OnMenuItemClickListener{
	private Button showContextBtn;
	private Button showPopBtn;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showContextBtn = (Button) findViewById(R.id.btn_showContextMenu);
        
        showPopBtn = (Button) findViewById(R.id.btn_showPopupMenu);
        
        // 注册上下文菜单
        registerForContextMenu(showContextBtn);
    }


    /**
     * 创建选项菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    // 创建上下文菜单
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.context_menu, menu);
    }
    
    /**
     * 上下文菜单项选中
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case R.id.item1:
			Toast.makeText(getApplicationContext(), "上下文菜单1", Toast.LENGTH_SHORT).show();
			break;
		case R.id.item2:
			Toast.makeText(getApplicationContext(), "上下文菜单2", Toast.LENGTH_SHORT).show();
			break;
		case R.id.item3:
			Toast.makeText(getApplicationContext(), "上下文菜单3", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
    	return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	Toast.makeText(getApplicationContext(), "这是OptionMenu", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
    /**
     * 显示弹出菜单
     * @param view
     */
    public void showPopupMenu(View view) {
    	PopupMenu popupMenu = new PopupMenu(this, view);
    	MenuInflater inflater = popupMenu.getMenuInflater();
    	popupMenu.setOnMenuItemClickListener(this);
    	inflater.inflate(R.menu.main, popupMenu.getMenu());
    	popupMenu.show();
    }


	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			Toast.makeText(getApplicationContext(), "这是popupMenu", Toast.LENGTH_SHORT).show();
			return true;
		default:
			break;
		}
		return false;
	}
}
