package com.umeng.soexample;

import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.UMAuthListener;
import com.umeng.socialize.controller.listener.SocializeListeners.UMDataListener;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;

/**
 * 第三方平台登录
 * @author devilwwj
 *
 */
public class MainActivity extends Activity implements OnClickListener {
	
	private static final String TAG = "MainActivity";
	// 整个平台的Controller,负责整个SDK的配置、操作等处理
	private UMSocialService mController = UMServiceFactory.getUMSocialService(Constants.DESCRIPTOR);
	
	
	// 第三方平台获取的访问token，有效时间，uid
	private String accessToken;
	private String expires_in;
	private String uid;
	private String sns_avatar;
	private String sns;
	private String sns_loginname;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 第一步：配置平台
		configPlatforms();
		
		
	}


	private void configPlatforms() {
		// 添加新浪sso授权
		mController.getConfig().setSsoHandler(new SinaSsoHandler());
		
		// 添加QQ、QZone平台
		addQQQZonePlatform();
		
		// 添加微信、微信朋友圈平台
		addWXPlatform();
		
	}


	private void addQQQZonePlatform() {
		String appId = Constants.QQZONE_APPID;
		String appKey = Constants.QQZONE_APPKEY;
		// 添加QQ支持,并且设置QQ分享内容的target url
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(this, appId, appKey);
		qqSsoHandler.setTargetUrl("www.sina.com");
		qqSsoHandler.addToSocialSDK();
		
		// 添加QZone平台
		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(this, appId, appKey);
		qZoneSsoHandler.addToSocialSDK();
	}


	private void addWXPlatform() {
		String appId = Constants.WEIXIN_APPID;
		String appSecret = Constants.WEIXIN_APPSECRET;
		
		// 添加微信平台
		UMWXHandler wxHandler = new UMWXHandler(this, appId, appSecret);
		wxHandler.addToSocialSDK();
		
		// 支持朋友圈
		UMWXHandler wxCircleHandler = new UMWXHandler(this, appId, appSecret);
		wxCircleHandler.setToCircle(true);
		wxHandler.addToSocialSDK();
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_sina:
			// 第二步：调用登录验证方法，传入相应的平台
			loginThirdPlatform(SHARE_MEDIA.SINA);
			break;
		case R.id.btn_wechat:
			loginThirdPlatform(SHARE_MEDIA.WEIXIN);
			break;
		case R.id.btn_qzone:
			loginThirdPlatform(SHARE_MEDIA.QZONE);
			break;

		default:
			break;
		}
	}
	
	/**
	 * 授权登录第三方平台
	 * @param platform
	 */
	private void loginThirdPlatform(final SHARE_MEDIA platform) {
		mController.doOauthVerify(this, platform, new UMAuthListener() {
			// 开始授权调用这个方法
			@Override
			public void onStart(SHARE_MEDIA arg0) {
				
			}
			// 授权失败会调用这个方法
			@Override
			public void onError(SocializeException arg0, SHARE_MEDIA arg1) {
				
			}
			// 授权完成之后会调用这个方法
			@Override
			public void onComplete(Bundle bundle, SHARE_MEDIA platform) {
				Log.i("onComplete", bundle.toString());
				// bunlde存放了平台授权的信息
				if (platform == SHARE_MEDIA.SINA) {
					accessToken = bundle.getString("access_key");
				} else {
					accessToken = bundle.getString("access_token");
				}
				// 获取有效时间
				expires_in = bundle.getString("expires_in");
				// 获取uid
				uid = bundle.getString("uid");
				
				if (bundle != null && !TextUtils.isEmpty(uid)) {
					Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
					// 第三步：授权成功之后获取用户信息
					getUserInfo(platform);
				}
			}
			// 授权取消的时候会调用这个方法
			@Override
			public void onCancel(SHARE_MEDIA arg0) {
				
			}
		});
	}
	
	/**
	 * 获取用户信息
	 * @param platform
	 */
	private void getUserInfo(final SHARE_MEDIA platform) {
		mController.getPlatformInfo(this, platform, new UMDataListener() {
			// 开始获取用户调用此方法
			@Override
			public void onStart() {
				
			}
			// 获取用户信息成功之后调用此方法
			@Override
			public void onComplete(int status, Map<String, Object> info) {
				Log.i("info", info.toString());
				// info保存了用户的信息
				if (info != null) { // 根据不同平台来取,注：不同平台返回的字段可能不一样，这个可能需要开发者自己去调试
					if (platform == SHARE_MEDIA.SINA) {
						sns = "sina";
						sns_avatar = info.get("profile_image_url").toString();
						sns_loginname = info.get("screen_name").toString();
					} else if (platform == SHARE_MEDIA.QZONE) {
						sns = "qzone";
						sns_avatar = info.get("profile_image_url").toString();
						sns_loginname = info.get("screen_name").toString();
					} else if (platform == SHARE_MEDIA.WEIXIN) {
						sns = "weixin";
						sns_avatar = info.get("headimgurl").toString();
						sns_loginname = info.get("nickname").toString();

					}
				}
				
				// 实际开发中，可以保存第三方返回的用户信息
				
				// 这里只想第三方连接，注：这里请求自己应有的服务器
				
			}
		});
	}
}
