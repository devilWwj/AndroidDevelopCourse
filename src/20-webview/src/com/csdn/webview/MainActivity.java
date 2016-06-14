package com.csdn.webview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * webview使用案例
 * @author devilwwj
 *
 */
public class MainActivity extends Activity {
	private WebView webView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        webView = (WebView) this.findViewById(R.id.webview);
        // 加载html页面
//        webView.loadUrl("http://www.baidu.com");
//        
//        WebSettings settings = webView.getSettings();
//        settings.setJavaScriptEnabled(true);
//        
//        webView.setWebViewClient(new WebViewClient() {
//        	@Override
//        	public boolean shouldOverrideUrlLoading(WebView view, String url) {
//        		if (Uri.parse(url).getHost().equals("www.baidu.com")) {
//        			// 判断是否是我们服务器的一个网页
//        			return false;
//        		}
//        		
//        		// 不是我们的网页，就使用浏览器显示
//        		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//        		startActivity(intent);
//        		return true;
//        	}
//        });
        
        // 加载assets目录的资源
        webView.loadUrl("file:///android_asset/agreement.html");
        
    }
}
