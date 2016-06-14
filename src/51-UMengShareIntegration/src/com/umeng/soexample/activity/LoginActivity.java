package com.umeng.soexample.activity;

import java.io.File;
import java.util.Date;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.bean.StatusCode;
import com.umeng.socialize.controller.UMEvernoteHandler;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.SocializeClientListener;
import com.umeng.socialize.controller.listener.SocializeListeners.UMAuthListener;
import com.umeng.socialize.controller.listener.SocializeListeners.UMDataListener;
import com.umeng.socialize.controller.media.EvernoteShareContent;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.facebook.controller.UMFacebookHandler;
import com.umeng.socialize.facebook.controller.UMFacebookHandler.PostType;
import com.umeng.socialize.facebook.media.FaceBookShareContent;
import com.umeng.socialize.instagram.controller.UMInstagramHandler;
import com.umeng.socialize.instagram.media.InstagramShareContent;
import com.umeng.socialize.laiwang.controller.UMLWHandler;
import com.umeng.socialize.laiwang.media.LWDynamicShareContent;
import com.umeng.socialize.laiwang.media.LWShareContent;
import com.umeng.socialize.linkedin.controller.UMLinkedInHandler;
import com.umeng.socialize.linkedin.media.LinkedInShareContent;
import com.umeng.socialize.media.GooglePlusShareContent;
import com.umeng.socialize.media.MailShareContent;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.RenrenShareContent;
import com.umeng.socialize.media.SinaShareContent;
import com.umeng.socialize.media.SmsShareContent;
import com.umeng.socialize.media.TencentWbShareContent;
import com.umeng.socialize.media.TwitterShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.pinterest.controller.UMPinterestHandler;
import com.umeng.socialize.pinterest.media.PinterestShareContent;
import com.umeng.socialize.pocket.controller.UMPocketHandler;
import com.umeng.socialize.pocket.media.PocketShareContent;
import com.umeng.socialize.sso.EmailHandler;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.RenrenSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.SmsHandler;
import com.umeng.socialize.sso.TencentWBSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.sso.UMSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;
import com.umeng.socialize.yixin.controller.UMYXHandler;
import com.umeng.socialize.ynote.controller.UMYNoteHandler;
import com.umeng.socialize.ynote.media.YNoteShareContent;
import com.umeng.soexample.R;
import com.umeng.soexample.commons.Constants;

/**
 * @date 2014/11/12
 * @author wuwenjie
 * @description 实现友盟第三方登录注销、获取用户信息、多平台分享
 */
public class LoginActivity extends Activity implements OnClickListener {
	// 整个平台的Controller,负责管理整个SDK的配置、操作等处理
	private UMSocialService mController = UMServiceFactory
			.getUMSocialService(Constants.DESCRIPTOR);

	private Button sinaLoginButton;
	private Button sinaLogoutButton;
	private Button qqLoginButton;
	private Button qqLogoutButton;
	private Button wechatLoginButton;
	private Button wechatLogoutButton;
	private Button shareButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sinaLoginButton = (Button) this.findViewById(R.id.btn_sina_login);
		sinaLogoutButton = (Button) this.findViewById(R.id.btn_sina_logout);
		qqLoginButton = (Button) this.findViewById(R.id.btn_qq_login);
		qqLogoutButton = (Button) this.findViewById(R.id.btn_qq_logout);
		shareButton = (Button) this.findViewById(R.id.btn_share);
		wechatLoginButton = (Button) this.findViewById(R.id.btn_wechat_login);
		wechatLogoutButton = (Button) this.findViewById(R.id.btn_wechat_logout);
		
		sinaLoginButton.setOnClickListener(this);
		sinaLogoutButton.setOnClickListener(this);
		qqLoginButton.setOnClickListener(this);
		qqLogoutButton.setOnClickListener(this);
		shareButton.setOnClickListener(this);
		wechatLoginButton.setOnClickListener(this);
		wechatLogoutButton.setOnClickListener(this);

		// 配置需要分享的相关平台
		configPlatforms();
		// 设置分享的内容
		setShareContent();

	}

	/**
	 * 配置分享平台参数
	 */
	private void configPlatforms() {
		// 添加新浪sso授权
		mController.getConfig().setSsoHandler(new SinaSsoHandler());
		// 添加腾讯微博SSO授权
		mController.getConfig().setSsoHandler(new TencentWBSsoHandler());
		// 添加人人网SSO授权
		RenrenSsoHandler renrenSsoHandler = new RenrenSsoHandler(
				LoginActivity.this, "201874",
				"28401c0964f04a72a14c812d6132fcef",
				"3bf66e42db1e4fa9829b955cc300b737");
		mController.getConfig().setSsoHandler(renrenSsoHandler);

		// 添加QQ、QZone平台
		addQQQZonePlatform();

		// 添加微信、微信朋友圈平台
		addWXPlatform();

	}

	/**
	 * 根据不同的平台设置不同的分享内容</br>
	 */
	private void setShareContent() {

		// 配置SSO
		mController.getConfig().setSsoHandler(new SinaSsoHandler());
		mController.getConfig().setSsoHandler(new TencentWBSsoHandler());

		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(
				LoginActivity.this, "100424468",
				"c7394704798a158208a74ab60104f0ba");
		qZoneSsoHandler.addToSocialSDK();
		mController.setShareContent("友盟社会化组件（SDK）让移动应用快速整合社交分享功能");

		// APP ID：201874, API
		// * KEY：28401c0964f04a72a14c812d6132fcef, Secret
		// * Key：3bf66e42db1e4fa9829b955cc300b737.
		RenrenSsoHandler renrenSsoHandler = new RenrenSsoHandler(
				LoginActivity.this, "201874",
				"28401c0964f04a72a14c812d6132fcef",
				"3bf66e42db1e4fa9829b955cc300b737");
		mController.getConfig().setSsoHandler(renrenSsoHandler);

		UMImage localImage = new UMImage(LoginActivity.this, R.drawable.device);
		UMImage urlImage = new UMImage(LoginActivity.this,
				"http://www.umeng.com/images/pic/social/integrated_3.png");
		// UMImage resImage = new UMImage(LoginActivity.this, R.drawable.icon);

		// 视频分享
		UMVideo video = new UMVideo(
				"http://v.youku.com/v_show/id_XNTc0ODM4OTM2.html");
		// vedio.setThumb("http://www.umeng.com/images/pic/home/social/img-1.png");
		video.setTitle("友盟社会化组件视频");
		video.setThumb(urlImage);

		UMusic uMusic = new UMusic(
				"http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3");
		uMusic.setAuthor("umeng");
		uMusic.setTitle("天籁之音");
		uMusic.setThumb(urlImage);
		// uMusic.setThumb("http://www.umeng.com/images/pic/social/chart_1.png");

		WeiXinShareContent weixinContent = new WeiXinShareContent();
		weixinContent.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能，微信");
		weixinContent.setTitle("友盟社会化分享组件-微信");
		weixinContent.setTargetUrl("http://www.umeng.com");
		weixinContent.setShareMedia(urlImage);
		mController.setShareMedia(weixinContent);

		// 设置朋友圈分享的内容
		CircleShareContent circleMedia = new CircleShareContent();
		circleMedia.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能，朋友圈");
		circleMedia.setTitle("友盟社会化分享组件-朋友圈");
		circleMedia.setShareImage(urlImage);
		// circleMedia.setShareMedia(uMusic);
		// circleMedia.setShareMedia(video);
		circleMedia.setTargetUrl("http://www.umeng.com");
		mController.setShareMedia(circleMedia);

		// 设置renren分享内容
		RenrenShareContent renrenShareContent = new RenrenShareContent();
		renrenShareContent.setShareContent("人人分享内容");
		UMImage image = new UMImage(LoginActivity.this,
				BitmapFactory.decodeResource(getResources(), R.drawable.device));
		image.setTitle("thumb title");
		image.setThumb("http://www.umeng.com/images/pic/social/integrated_3.png");
		renrenShareContent.setShareImage(image);
		renrenShareContent.setAppWebSite("http://www.umeng.com/social");
		mController.setShareMedia(renrenShareContent);

		UMImage qzoneImage = new UMImage(LoginActivity.this,
				"http://www.umeng.com/images/pic/social/integrated_3.png");
		qzoneImage
				.setTargetUrl("http://www.umeng.com/images/pic/social/integrated_3.png");

		// 设置QQ空间分享内容
		QZoneShareContent qzone = new QZoneShareContent();
		qzone.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能 -- QZone");
		qzone.setTargetUrl("http://www.umeng.com/social");
		qzone.setTitle("QZone title");
		qzone.setShareImage(urlImage);
		mController.setShareMedia(qzone);

		video.setThumb(new UMImage(LoginActivity.this, BitmapFactory
				.decodeResource(getResources(), R.drawable.device)));

		QQShareContent qqShareContent = new QQShareContent();
		qqShareContent.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能 -- QQ");
		qqShareContent.setTitle("hello, title");
		qqShareContent.setShareImage(urlImage);
		// qqShareContent.setShareMusic(uMusic);
		// qqShareContent.setShareVideo(video);
		qqShareContent.setTargetUrl("http://www.umeng.com/social");
		mController.setShareMedia(qqShareContent);

		// 视频分享
		UMVideo umVideo = new UMVideo(
				"http://v.youku.com/v_show/id_XNTc0ODM4OTM2.html");
		umVideo.setThumb("http://www.umeng.com/images/pic/home/social/img-1.png");
		umVideo.setTitle("友盟社会化组件视频");

		TencentWbShareContent tencent = new TencentWbShareContent();
		tencent.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能，腾讯微博");
		// 设置tencent分享内容
		mController.setShareMedia(tencent);

		// 设置邮件分享内容， 如果需要分享图片则只支持本地图片
		MailShareContent mail = new MailShareContent(localImage);
		mail.setTitle("share form umeng social sdk");
		mail.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能，email");
		// 设置tencent分享内容
		mController.setShareMedia(mail);

		// 设置短信分享内容
		SmsShareContent sms = new SmsShareContent();
		sms.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能，短信");
		sms.setShareImage(urlImage);
		mController.setShareMedia(sms);

		SinaShareContent sinaContent = new SinaShareContent(urlImage);
		sinaContent.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能，新浪微博");
		mController.setShareMedia(sinaContent);

		TwitterShareContent twitterShareContent = new TwitterShareContent();
		twitterShareContent
				.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能，TWITTER");
		twitterShareContent.setShareMedia(localImage);
		mController.setShareMedia(twitterShareContent);

		GooglePlusShareContent googlePlusShareContent = new GooglePlusShareContent();
		googlePlusShareContent
				.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能，G+");
		googlePlusShareContent.setShareMedia(localImage);
		mController.setShareMedia(googlePlusShareContent);

		// 来往分享内容
		LWShareContent lwShareContent = new LWShareContent();
		// lwShareContent.setShareImage(urlImage);
		// lwShareContent.setShareMedia(uMusic);
		lwShareContent.setShareMedia(umVideo);
		lwShareContent.setTitle("友盟社会化分享组件-来往");
		lwShareContent.setMessageFrom("友盟分享组件");
		lwShareContent.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能，来往");
		mController.setShareMedia(lwShareContent);

		// 来往动态分享内容
		LWDynamicShareContent lwDynamicShareContent = new LWDynamicShareContent();
		// lwDynamicShareContent.setShareImage(urlImage);
		// lwDynamicShareContent.setShareMedia(uMusic);
		lwDynamicShareContent.setShareMedia(umVideo);
		lwDynamicShareContent.setTitle("友盟社会化分享组件-来往动态");
		lwDynamicShareContent.setMessageFrom("来自友盟");
		lwDynamicShareContent.setShareContent("来往动态分享测试");
		lwDynamicShareContent.setTargetUrl("http://www.umeng.com");
		mController.setShareMedia(lwDynamicShareContent);

	}

	/**
	 * 添加所有的平台</br>
	 */
	private void addCustomPlatforms() {
		// 添加微信平台
		addWXPlatform();
		// 添加QQ平台
		addQQQZonePlatform();
		// 添加印象笔记平台
		addEverNote();
		// 添加facebook平台
		addFacebook();
		// 添加Instagram平台
		addInstagram();
		// 添加来往、来往动态平台
		addLaiWang();
		// 添加LinkedIn平台
		addLinkedIn();
		// 添加Pinterest平台
		addPinterest();
		// 添加Pocket平台
		addPocket();
		// 添加有道云平台
		addYNote();
		// 添加易信平台
		addYXPlatform();
		// 添加短信平台
		addSMS();
		// 添加email平台
		addEmail();

		mController.getConfig().setPlatforms(SHARE_MEDIA.WEIXIN,
				SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,
				SHARE_MEDIA.SINA, SHARE_MEDIA.TENCENT, SHARE_MEDIA.DOUBAN,
				SHARE_MEDIA.RENREN, SHARE_MEDIA.EMAIL, SHARE_MEDIA.EVERNOTE,
				SHARE_MEDIA.FACEBOOK, SHARE_MEDIA.GOOGLEPLUS,
				SHARE_MEDIA.INSTAGRAM, SHARE_MEDIA.LAIWANG,
				SHARE_MEDIA.LAIWANG_DYNAMIC, SHARE_MEDIA.LINKEDIN,
				SHARE_MEDIA.PINTEREST, SHARE_MEDIA.POCKET, SHARE_MEDIA.SMS,
				SHARE_MEDIA.TWITTER, SHARE_MEDIA.YIXIN,
				SHARE_MEDIA.YIXIN_CIRCLE, SHARE_MEDIA.YNOTE);
		mController.openShare(LoginActivity.this, false);
	}

	/**
	 * 添加短信平台</br>
	 */
	private void addSMS() {
		// 添加短信
		SmsHandler smsHandler = new SmsHandler();
		smsHandler.addToSocialSDK();
	}

	/**
	 * 添加Email平台</br>
	 */
	private void addEmail() {
		// 添加email
		EmailHandler emailHandler = new EmailHandler();
		emailHandler.addToSocialSDK();
	}

	/**
	 * Pocket分享。pockect只支持分享网络链接</br>
	 */
	private void addPocket() {
		UMPocketHandler pocketHandler = new UMPocketHandler(LoginActivity.this);
		pocketHandler.addToSocialSDK();
		PocketShareContent pocketShareContent = new PocketShareContent();
		pocketShareContent.setShareContent("http://www.umeng.com/social");
		mController.setShareMedia(pocketShareContent);
	}

	/**
	 * LinkedIn分享。LinkedIn只支持图片，文本，图文分享</br>
	 */
	private void addLinkedIn() {
		UMLinkedInHandler linkedInHandler = new UMLinkedInHandler(
				LoginActivity.this);
		linkedInHandler.addToSocialSDK();
		LinkedInShareContent linkedInShareContent = new LinkedInShareContent();
		linkedInShareContent
				.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能，LinkedIn");
		mController.setShareMedia(linkedInShareContent);
	}

	/**
	 * 有道云笔记分享。有道云笔记只支持图片，文本，图文分享</br>
	 */
	private void addYNote() {
		UMYNoteHandler yNoteHandler = new UMYNoteHandler(LoginActivity.this);
		yNoteHandler.addToSocialSDK();
		YNoteShareContent yNoteShareContent = new YNoteShareContent();
		yNoteShareContent
				.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能，云有道笔记");
		yNoteShareContent.setTitle("友盟分享组件");
		yNoteShareContent.setShareImage(new UMImage(LoginActivity.this,
				new File("/storage/sdcard0/test12.png")));
		mController.setShareMedia(yNoteShareContent);
	}

	/**
	 * 添加印象笔记平台
	 */
	private void addEverNote() {
		UMEvernoteHandler evernoteHandler = new UMEvernoteHandler(
				LoginActivity.this);
		evernoteHandler.addToSocialSDK();

		// 设置evernote的分享内容
		EvernoteShareContent shareContent = new EvernoteShareContent("印象笔记文本内容");
		shareContent.setShareMedia(new UMImage(LoginActivity.this,
				R.drawable.test));
		mController.setShareMedia(shareContent);
	}

	/**
	 * 添加Pinterest平台
	 */
	private void addPinterest() {
		/**
		 * app id需到pinterest开发网站( https://developers.pinterest.com/ )自行申请.
		 */
		UMPinterestHandler pinterestHandler = new UMPinterestHandler(
				LoginActivity.this, "1439206");
		pinterestHandler.addToSocialSDK();

		// 设置Pinterest的分享内容
		PinterestShareContent shareContent = new PinterestShareContent(
				"Pinterest文本内容");
		shareContent.setShareMedia(new UMImage(LoginActivity.this,
				R.drawable.test));
		mController.setShareMedia(shareContent);
	}

	/**
	 * 添加来往和来往动态平台</br>
	 */
	private void addLaiWang() {

		String appToken = "laiwangd497e70d4";
		String secretID = "d497e70d4c3e4efeab1381476bac4c5e";
		// laiwangd497e70d4:来往appToken,d497e70d4c3e4efeab1381476bac4c5e:来往secretID
		// 添加来往的支持
		UMLWHandler umlwHandler = new UMLWHandler(LoginActivity.this, appToken,
				secretID);
		umlwHandler.addToSocialSDK();

		// 添加来往动态的支持
		UMLWHandler lwDynamicHandler = new UMLWHandler(LoginActivity.this,
				appToken, secretID);
		lwDynamicHandler.setToCircle(true);
		lwDynamicHandler.addToSocialSDK();
	}

	/**
	 * @功能描述 : 添加微信平台分享
	 * @return
	 */
	private void addWXPlatform() {
		// 注意：在微信授权的时候，必须传递appSecret
		// wx967daebe835fbeac是你在微信开发平台注册应用的AppID, 这里需要替换成你注册的AppID
		String appId = "wx967daebe835fbeac";
		String appSecret = "5bb696d9ccd75a38c8a0bfe0675559b3";
		// 添加微信平台
		UMWXHandler wxHandler = new UMWXHandler(LoginActivity.this, appId,
				appSecret);
		wxHandler.addToSocialSDK();

		// 支持微信朋友圈
		UMWXHandler wxCircleHandler = new UMWXHandler(LoginActivity.this,
				appId, appSecret);
		wxCircleHandler.setToCircle(true);
		wxCircleHandler.addToSocialSDK();
	}

	/**
	 * @功能描述 : 添加QQ平台支持 QQ分享的内容， 包含四种类型， 即单纯的文字、图片、音乐、视频. 参数说明 : title, summary,
	 *       image url中必须至少设置一个, targetUrl必须设置,网页地址必须以"http://"开头 . title :
	 *       要分享标题 summary : 要分享的文字概述 image url : 图片地址 [以上三个参数至少填写一个] targetUrl
	 *       : 用户点击该分享时跳转到的目标地址 [必填] ( 若不填写则默认设置为友盟主页 )
	 * @return
	 */
	private void addQQQZonePlatform() {
		String appId = "100424468";
		String appKey = "c7394704798a158208a74ab60104f0ba";
		// 添加QQ支持, 并且设置QQ分享内容的target url
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(LoginActivity.this,
				appId, appKey);
		qqSsoHandler.setTargetUrl("http://www.umeng.com");
		qqSsoHandler.addToSocialSDK();

		// 添加QZone平台
		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(
				LoginActivity.this, appId, appKey);
		qZoneSsoHandler.addToSocialSDK();
	}

	/**
	 * @Title: addYXPlatform
	 * @Description:
	 * @throws
	 */
	private void addYXPlatform() {

		// 添加易信平台
		UMYXHandler yixinHandler = new UMYXHandler(LoginActivity.this,
				"yxc0614e80c9304c11b0391514d09f13bf");
		// 关闭分享时的等待Dialog
		yixinHandler.enableLoadingDialog(false);
		// 设置target Url, 必须以http或者https开头
		yixinHandler.setTargetUrl("http://www.umeng.com");
		yixinHandler.addToSocialSDK();

		// 易信朋友圈平台
		UMYXHandler yxCircleHandler = new UMYXHandler(LoginActivity.this,
				"yxc0614e80c9304c11b0391514d09f13bf");
		yxCircleHandler.setToCircle(true);
		yxCircleHandler.addToSocialSDK();

	}

	/**
	 * @Title: addFacebook
	 * @Description:
	 * @throws
	 */
	private void addFacebook() {

		UMImage mUMImgBitmap = new UMImage(LoginActivity.this, R.drawable.test);
		UMFacebookHandler mFacebookHandler = new UMFacebookHandler(
				LoginActivity.this, "567261760019884", PostType.FEED);
		mFacebookHandler.addToSocialSDK();

		FaceBookShareContent fbContent = new FaceBookShareContent(
				"facebook 分享组件");
		fbContent.setShareImage(mUMImgBitmap);
		fbContent.setShareContent("This is my facebook social share sdk."
				+ new Date().toString());
		fbContent.setTitle("Title - FB");
		fbContent.setCaption("Caption - Fb");
		fbContent.setDescription("独立拆分测试");
		fbContent.setTargetUrl("http://www.umeng.com/");
		mController.setShareMedia(fbContent);

		mController.setShareContent("facebook share");
		mController.setShareMedia(mUMImgBitmap);

	}

	/**
	 * </br> Instagram只支持图片分享, 只支持纯图片分享.</br>
	 */
	private void addInstagram() {
		// 构建Instagram的Handler
		UMInstagramHandler instagramHandler = new UMInstagramHandler(
				LoginActivity.this);
		instagramHandler.addToSocialSDK();

		UMImage localImage = new UMImage(LoginActivity.this, R.drawable.device);

		// // 添加分享到Instagram的内容
		InstagramShareContent instagramShareContent = new InstagramShareContent(
				localImage);
		mController.setShareMedia(instagramShareContent);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_sina_login: // 新浪微博登录
			login(SHARE_MEDIA.SINA);
			break;
		case R.id.btn_qq_login: // qq登录
			login(SHARE_MEDIA.QQ);
			break;
		case R.id.btn_wechat_login: // 微信登陆
			login(SHARE_MEDIA.WEIXIN);
			break;
		case R.id.btn_share: // 一键分享
			addCustomPlatforms();
			break;
		case R.id.btn_sina_logout: // 注销新浪账号
			logout(SHARE_MEDIA.SINA);
			break;
		case R.id.btn_qq_logout: // 注销qq账号
			logout(SHARE_MEDIA.QQ);
			break;
		case R.id.btn_wechat_logout:
			logout(SHARE_MEDIA.WEIXIN); // 注销微信账号
			break;
		default:
			break;
		}
	}

	/**
	 * 授权。如果授权成功，则获取用户信息
	 * 
	 * @param platform
	 */
	private void login(final SHARE_MEDIA platform) {
		mController.doOauthVerify(LoginActivity.this, platform,
				new UMAuthListener() {

					@Override
					public void onStart(SHARE_MEDIA platform) {
						Toast.makeText(LoginActivity.this, "授权开始",
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onError(SocializeException e,
							SHARE_MEDIA platform) {
						Toast.makeText(LoginActivity.this, "授权失败",
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onComplete(Bundle value, SHARE_MEDIA platform) {
						// 获取uid
						String uid = value.getString("uid");
						if (!TextUtils.isEmpty(uid)) {
							// uid不为空，获取用户信息
							getUserInfo(platform);
						} else {
							Toast.makeText(LoginActivity.this, "授权失败...",
									Toast.LENGTH_LONG).show();
						}
					}

					@Override
					public void onCancel(SHARE_MEDIA platform) {
						Toast.makeText(LoginActivity.this, "授权取消",
								Toast.LENGTH_SHORT).show();
					}
				});
	}

	/**
	 * 获取用户信息
	 * 
	 * @param platform
	 */
	private void getUserInfo(SHARE_MEDIA platform) {
		mController.getPlatformInfo(LoginActivity.this, platform,
				new UMDataListener() {

					@Override
					public void onStart() {

					}

					@Override
					public void onComplete(int status, Map<String, Object> info) {
						// String showText = "";
						// if (status == StatusCode.ST_CODE_SUCCESSED) {
						// showText = "用户名：" +
						// info.get("screen_name").toString();
						// Log.d("#########", "##########" + info.toString());
						// } else {
						// showText = "获取用户信息失败";
						// }

						if (info != null) {
							Toast.makeText(LoginActivity.this, info.toString(),
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}
	
	/**
	 * 注销本次登陆
	 * @param platform
	 */
	private void logout(final SHARE_MEDIA platform) {
		mController.deleteOauth(LoginActivity.this, platform, new SocializeClientListener() {
			
			@Override
			public void onStart() {
				
			}
			
			@Override
			public void onComplete(int status, SocializeEntity entity) {
				String showText = "解除" + platform.toString() + "平台授权成功";
				if (status != StatusCode.ST_CODE_SUCCESSED) {
					showText = "解除" + platform.toString() + "平台授权失败[" + status + "]";
				}
				Toast.makeText(LoginActivity.this, showText, Toast.LENGTH_SHORT).show();
			}
		});
	}
	

	// 如果有使用任一平台的SSO授权, 则必须在对应的activity中实现onActivityResult方法, 并添加如下代码
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// 根据requestCode获取对应的SsoHandler
		UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(
				resultCode);
		if (ssoHandler != null) {
			ssoHandler.authorizeCallBack(requestCode, resultCode, data);
		}
	}
}
