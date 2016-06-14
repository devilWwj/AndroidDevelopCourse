package com.csdn.toastmenu;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Toast和Menu
 * @author devilwwj
 *
 */
public class MainActivity extends Activity implements OnClickListener {
	private Button button;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		button = (Button) this.findViewById(R.id.contextMenu);
		// 注册上下文菜单
		registerForContextMenu(button);
		
	}

	/**
	 * 创建选项菜单
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.main, menu);
		
		// 创建三个菜单项
		MenuItem item1 = menu.add(Menu.NONE, Menu.FIRST,0, "添加");
		item1.setIcon(android.R.drawable.ic_menu_add);
		
		MenuItem item2 = menu.add(Menu.NONE, Menu.FIRST + 1,1, "删除");
		item2.setIcon(android.R.drawable.ic_menu_delete);
		
		MenuItem item3 = menu.add(Menu.NONE, Menu.FIRST + 2,2, "退出");
		item3.setIcon(android.R.drawable.ic_menu_close_clear_cancel);
		
		// 添加子菜单
		SubMenu subMenu = menu.addSubMenu(Menu.NONE, Menu.FIRST + 3, 3, "SubMenu");
		subMenu.add(Menu.NONE, Menu.FIRST + 4, 4, "修改");
		subMenu.add(Menu.NONE, Menu.FIRST + 5, 5, "查找");
		
		return true;
	}

	/**
	 * 选中菜单项
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case Menu.FIRST: // 添加
			Toast.makeText(this, "添加", Toast.LENGTH_SHORT).show();
			break;
		case Menu.FIRST + 1: // 删除
			Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
			break;
		case Menu.FIRST + 2: // 退出
			finish();
			break;
		case Menu.FIRST + 4: // 修改
			
			break;
		case Menu.FIRST + 5: // 查找
			
			break;

		default:
			break;
		}
		
		return true;
	}
	
	/**
	 * 创建上下文菜单
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		
		if (v == button) {
			menu.setHeaderTitle("上下文菜单");
			menu.add(Menu.NONE, 0, 0, "上下文菜单1");
			menu.add(Menu.NONE, 1, 1, "上下文菜单2");
		}
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		return super.onContextItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			// 第一个参数：当前的上下文环境。可用getApplicationContext()或this
			// 第二个参数：要显示的字符串。也可是R.string中字符串ID
			// 第三个参数：显示的时间长短。Toast默认的有两个LENGTH_LONG(长)和LENGTH_SHORT(短)，也可以使用毫秒如2000ms
			Toast toast = Toast.makeText(getApplicationContext(), "默认的Toast",
					Toast.LENGTH_SHORT);
			// 显示toast信息
			toast.show();
			break;
		case R.id.button2:
			Toast toast2 = Toast.makeText(getApplicationContext(),
					"自定义显示位置的Toast", Toast.LENGTH_SHORT);
			// 第一个参数：设置toast在屏幕中显示的位置。我现在的设置是居中靠顶
			// 第二个参数：相对于第一个参数设置toast位置的横向X轴的偏移量，正数向右偏移，负数向左偏移
			// 第三个参数：同的第二个参数道理一样
			// 如果你设置的偏移量超过了屏幕的范围，toast将在屏幕内靠近超出的那个边界显示
			toast2.setGravity(Gravity.TOP | Gravity.CENTER, -50, 100);
			// 屏幕居中显示，X轴和Y轴偏移量都是0
			// toast.setGravity(Gravity.CENTER, 0, 0);
			toast2.show();
			break;
		case R.id.button3:
			Toast toast3 = Toast.makeText(getApplicationContext(),
					"显示带图片的toast", 3000);
			toast3.setGravity(Gravity.CENTER, 0, 0);
			// 创建图片视图对象
			ImageView imageView = new ImageView(getApplicationContext());
			// 设置图片
			imageView.setImageResource(R.drawable.ic_launcher);
			// 获得toast的布局
			LinearLayout toastView = (LinearLayout) toast3.getView();
			// 设置此布局为横向的
			toastView.setOrientation(LinearLayout.HORIZONTAL);
			// 将ImageView在加入到此布局中的第一个位置
			toastView.addView(imageView, 0);
			toast3.show();
			break;
		case R.id.button4:
			// Inflater意思是充气
			// LayoutInflater这个类用来实例化XML文件到其相应的视图对象的布局
			LayoutInflater inflater = getLayoutInflater();
			// 通过制定XML文件及布局ID来填充一个视图对象
			View layout = inflater.inflate(R.layout.custom2,
					(ViewGroup) findViewById(R.id.llToast));

			ImageView image = (ImageView) layout
					.findViewById(R.id.tvImageToast);
			// 设置布局中图片视图中图片
			image.setImageResource(R.drawable.ic_launcher);


			TextView text = (TextView) layout.findViewById(R.id.tvTextToast);
			// 设置内容
			text.setText("完全自定义Toast");

			Toast toast4 = new Toast(getApplicationContext());
			toast4.setGravity(Gravity.CENTER, 0, 0);
			toast4.setDuration(Toast.LENGTH_LONG);
			toast4.setView(layout);
			toast4.show();
			break;
		case R.id.button5:
			//调用方法1
			//Thread th=new Thread(this);
			//th.start();
			//调用方法2
			handler.post(new Runnable() {
				@Override
				public void run() {
					showToast();
				}
			});
			break;

		default:
			break;
		}
	}
	
	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			int what=msg.what;
			switch (what) {
			case 1:
				showToast();
				break;
			default:
				break;
			}
			
			super.handleMessage(msg);
		}
	};
	
	public void showToast(){
		Toast toast=Toast.makeText(getApplicationContext(), "Toast在其他线程中调用显示", Toast.LENGTH_SHORT);
		toast.show();
	}
	
}
