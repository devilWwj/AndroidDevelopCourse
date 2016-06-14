package com.csdn.networkdata;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * File存储
 * @author devilwwj
 *
 */
public class MainActivity extends Activity implements OnClickListener{
	private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) this.findViewById(R.id.textview);
        
    }

    
   
	private String readData(InputStream is, String charsetName) throws IOException {
		// 字节数组输出流
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = is.read(buffer)) != -1) {
			// 写入到缓冲区
			outputStream.write(buffer, 0, len);
		}
		// 得到输出流的字节数组
		byte[] data = outputStream.toByteArray();
		// 关闭流
		outputStream.close();
		is.close();
		// 最终返回获得的字符串
		return new String(data, charsetName);
	}



	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			// 在子线程中进行网络操作，不然会报错
			new Thread() {
				public void run() {
					try {
						URL url = new URL("http://www.suhu.com");
						// 打开连接
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						// 设置连接超时
						conn.setConnectTimeout(5000); 
						// 设置请求方法
						conn.setRequestMethod("GET");
						// 如果请求码不等于
						if (conn.getResponseCode() != 200) {
							throw new RuntimeException("请求url失败");
						}
						// 得到网络返回的输入流
						InputStream is = conn.getInputStream(); 
						String result = readData(is, "GBK");
						
						Log.i("info", result);
						
						conn.disconnect();
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				};
			}.start();
			break;

		default:
			break;
		}
	}


}
