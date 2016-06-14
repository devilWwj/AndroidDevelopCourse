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
 * @description ʵ�����˵�������¼ע������ȡ�û���Ϣ����ƽ̨����
 */
public class LoginActivity extends Activity implements OnClickListener {
	// ����ƽ̨��Controller,�����������SDK�����á������ȴ���
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

		// ������Ҫ��������ƽ̨
		configPlatforms();
		// ���÷��������
		setShareContent();

	}

	/**
	 * ���÷���ƽ̨����
	 */
	private void configPlatforms() {
		// �������sso��Ȩ
		mController.getConfig().setSsoHandler(new SinaSsoHandler());
		// �����Ѷ΢��SSO��Ȩ
		mController.getConfig().setSsoHandler(new TencentWBSsoHandler());
		// ���������SSO��Ȩ
		RenrenSsoHandler renrenSsoHandler = new RenrenSsoHandler(
				LoginActivity.this, "201874",
				"28401c0964f04a72a14c812d6132fcef",
				"3bf66e42db1e4fa9829b955cc300b737");
		mController.getConfig().setSsoHandler(renrenSsoHandler);

		// ���QQ��QZoneƽ̨
		addQQQZonePlatform();

		// ���΢�š�΢������Ȧƽ̨
		addWXPlatform();

	}

	/**
	 * ���ݲ�ͬ��ƽ̨���ò�ͬ�ķ�������</br>
	 */
	private void setShareContent() {

		// ����SSO
		mController.getConfig().setSsoHandler(new SinaSsoHandler());
		mController.getConfig().setSsoHandler(new TencentWBSsoHandler());

		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(
				LoginActivity.this, "100424468",
				"c7394704798a158208a74ab60104f0ba");
		qZoneSsoHandler.addToSocialSDK();
		mController.setShareContent("������ữ�����SDK�����ƶ�Ӧ�ÿ��������罻������");

		// APP ID��201874, API
		// * KEY��28401c0964f04a72a14c812d6132fcef, Secret
		// * Key��3bf66e42db1e4fa9829b955cc300b737.
		RenrenSsoHandler renrenSsoHandler = new RenrenSsoHandler(
				LoginActivity.this, "201874",
				"28401c0964f04a72a14c812d6132fcef",
				"3bf66e42db1e4fa9829b955cc300b737");
		mController.getConfig().setSsoHandler(renrenSsoHandler);

		UMImage localImage = new UMImage(LoginActivity.this, R.drawable.device);
		UMImage urlImage = new UMImage(LoginActivity.this,
				"http://www.umeng.com/images/pic/social/integrated_3.png");
		// UMImage resImage = new UMImage(LoginActivity.this, R.drawable.icon);

		// ��Ƶ����
		UMVideo video = new UMVideo(
				"http://v.youku.com/v_show/id_XNTc0ODM4OTM2.html");
		// vedio.setThumb("http://www.umeng.com/images/pic/home/social/img-1.png");
		video.setTitle("������ữ�����Ƶ");
		video.setThumb(urlImage);

		UMusic uMusic = new UMusic(
				"http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3");
		uMusic.setAuthor("umeng");
		uMusic.setTitle("����֮��");
		uMusic.setThumb(urlImage);
		// uMusic.setThumb("http://www.umeng.com/images/pic/social/chart_1.png");

		WeiXinShareContent weixinContent = new WeiXinShareContent();
		weixinContent.setShareContent("����������ữ�����SDK�����ƶ�Ӧ�ÿ��������罻�����ܣ�΢��");
		weixinContent.setTitle("������ữ�������-΢��");
		weixinContent.setTargetUrl("http://www.umeng.com");
		weixinContent.setShareMedia(urlImage);
		mController.setShareMedia(weixinContent);

		// ��������Ȧ���������
		CircleShareContent circleMedia = new CircleShareContent();
		circleMedia.setShareContent("����������ữ�����SDK�����ƶ�Ӧ�ÿ��������罻�����ܣ�����Ȧ");
		circleMedia.setTitle("������ữ�������-����Ȧ");
		circleMedia.setShareImage(urlImage);
		// circleMedia.setShareMedia(uMusic);
		// circleMedia.setShareMedia(video);
		circleMedia.setTargetUrl("http://www.umeng.com");
		mController.setShareMedia(circleMedia);

		// ����renren��������
		RenrenShareContent renrenShareContent = new RenrenShareContent();
		renrenShareContent.setShareContent("���˷�������");
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

		// ����QQ�ռ��������
		QZoneShareContent qzone = new QZoneShareContent();
		qzone.setShareContent("����������ữ�����SDK�����ƶ�Ӧ�ÿ��������罻������ -- QZone");
		qzone.setTargetUrl("http://www.umeng.com/social");
		qzone.setTitle("QZone title");
		qzone.setShareImage(urlImage);
		mController.setShareMedia(qzone);

		video.setThumb(new UMImage(LoginActivity.this, BitmapFactory
				.decodeResource(getResources(), R.drawable.device)));

		QQShareContent qqShareContent = new QQShareContent();
		qqShareContent.setShareContent("����������ữ�����SDK�����ƶ�Ӧ�ÿ��������罻������ -- QQ");
		qqShareContent.setTitle("hello, title");
		qqShareContent.setShareImage(urlImage);
		// qqShareContent.setShareMusic(uMusic);
		// qqShareContent.setShareVideo(video);
		qqShareContent.setTargetUrl("http://www.umeng.com/social");
		mController.setShareMedia(qqShareContent);

		// ��Ƶ����
		UMVideo umVideo = new UMVideo(
				"http://v.youku.com/v_show/id_XNTc0ODM4OTM2.html");
		umVideo.setThumb("http://www.umeng.com/images/pic/home/social/img-1.png");
		umVideo.setTitle("������ữ�����Ƶ");

		TencentWbShareContent tencent = new TencentWbShareContent();
		tencent.setShareContent("����������ữ�����SDK�����ƶ�Ӧ�ÿ��������罻�����ܣ���Ѷ΢��");
		// ����tencent��������
		mController.setShareMedia(tencent);

		// �����ʼ��������ݣ� �����Ҫ����ͼƬ��ֻ֧�ֱ���ͼƬ
		MailShareContent mail = new MailShareContent(localImage);
		mail.setTitle("share form umeng social sdk");
		mail.setShareContent("����������ữ�����SDK�����ƶ�Ӧ�ÿ��������罻�����ܣ�email");
		// ����tencent��������
		mController.setShareMedia(mail);

		// ���ö��ŷ�������
		SmsShareContent sms = new SmsShareContent();
		sms.setShareContent("����������ữ�����SDK�����ƶ�Ӧ�ÿ��������罻�����ܣ�����");
		sms.setShareImage(urlImage);
		mController.setShareMedia(sms);

		SinaShareContent sinaContent = new SinaShareContent(urlImage);
		sinaContent.setShareContent("����������ữ�����SDK�����ƶ�Ӧ�ÿ��������罻�����ܣ�����΢��");
		mController.setShareMedia(sinaContent);

		TwitterShareContent twitterShareContent = new TwitterShareContent();
		twitterShareContent
				.setShareContent("����������ữ�����SDK�����ƶ�Ӧ�ÿ��������罻�����ܣ�TWITTER");
		twitterShareContent.setShareMedia(localImage);
		mController.setShareMedia(twitterShareContent);

		GooglePlusShareContent googlePlusShareContent = new GooglePlusShareContent();
		googlePlusShareContent
				.setShareContent("����������ữ�����SDK�����ƶ�Ӧ�ÿ��������罻�����ܣ�G+");
		googlePlusShareContent.setShareMedia(localImage);
		mController.setShareMedia(googlePlusShareContent);

		// ������������
		LWShareContent lwShareContent = new LWShareContent();
		// lwShareContent.setShareImage(urlImage);
		// lwShareContent.setShareMedia(uMusic);
		lwShareContent.setShareMedia(umVideo);
		lwShareContent.setTitle("������ữ�������-����");
		lwShareContent.setMessageFrom("���˷������");
		lwShareContent.setShareContent("����������ữ�����SDK�����ƶ�Ӧ�ÿ��������罻�����ܣ�����");
		mController.setShareMedia(lwShareContent);

		// ������̬��������
		LWDynamicShareContent lwDynamicShareContent = new LWDynamicShareContent();
		// lwDynamicShareContent.setShareImage(urlImage);
		// lwDynamicShareContent.setShareMedia(uMusic);
		lwDynamicShareContent.setShareMedia(umVideo);
		lwDynamicShareContent.setTitle("������ữ�������-������̬");
		lwDynamicShareContent.setMessageFrom("��������");
		lwDynamicShareContent.setShareContent("������̬�������");
		lwDynamicShareContent.setTargetUrl("http://www.umeng.com");
		mController.setShareMedia(lwDynamicShareContent);

	}

	/**
	 * ������е�ƽ̨</br>
	 */
	private void addCustomPlatforms() {
		// ���΢��ƽ̨
		addWXPlatform();
		// ���QQƽ̨
		addQQQZonePlatform();
		// ���ӡ��ʼ�ƽ̨
		addEverNote();
		// ���facebookƽ̨
		addFacebook();
		// ���Instagramƽ̨
		addInstagram();
		// ���������������̬ƽ̨
		addLaiWang();
		// ���LinkedInƽ̨
		addLinkedIn();
		// ���Pinterestƽ̨
		addPinterest();
		// ���Pocketƽ̨
		addPocket();
		// ����е���ƽ̨
		addYNote();
		// �������ƽ̨
		addYXPlatform();
		// ��Ӷ���ƽ̨
		addSMS();
		// ���emailƽ̨
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
	 * ��Ӷ���ƽ̨</br>
	 */
	private void addSMS() {
		// ��Ӷ���
		SmsHandler smsHandler = new SmsHandler();
		smsHandler.addToSocialSDK();
	}

	/**
	 * ���Emailƽ̨</br>
	 */
	private void addEmail() {
		// ���email
		EmailHandler emailHandler = new EmailHandler();
		emailHandler.addToSocialSDK();
	}

	/**
	 * Pocket����pockectֻ֧�ַ�����������</br>
	 */
	private void addPocket() {
		UMPocketHandler pocketHandler = new UMPocketHandler(LoginActivity.this);
		pocketHandler.addToSocialSDK();
		PocketShareContent pocketShareContent = new PocketShareContent();
		pocketShareContent.setShareContent("http://www.umeng.com/social");
		mController.setShareMedia(pocketShareContent);
	}

	/**
	 * LinkedIn����LinkedInֻ֧��ͼƬ���ı���ͼ�ķ���</br>
	 */
	private void addLinkedIn() {
		UMLinkedInHandler linkedInHandler = new UMLinkedInHandler(
				LoginActivity.this);
		linkedInHandler.addToSocialSDK();
		LinkedInShareContent linkedInShareContent = new LinkedInShareContent();
		linkedInShareContent
				.setShareContent("����������ữ�����SDK�����ƶ�Ӧ�ÿ��������罻�����ܣ�LinkedIn");
		mController.setShareMedia(linkedInShareContent);
	}

	/**
	 * �е��ƱʼǷ����е��Ʊʼ�ֻ֧��ͼƬ���ı���ͼ�ķ���</br>
	 */
	private void addYNote() {
		UMYNoteHandler yNoteHandler = new UMYNoteHandler(LoginActivity.this);
		yNoteHandler.addToSocialSDK();
		YNoteShareContent yNoteShareContent = new YNoteShareContent();
		yNoteShareContent
				.setShareContent("����������ữ�����SDK�����ƶ�Ӧ�ÿ��������罻�����ܣ����е��ʼ�");
		yNoteShareContent.setTitle("���˷������");
		yNoteShareContent.setShareImage(new UMImage(LoginActivity.this,
				new File("/storage/sdcard0/test12.png")));
		mController.setShareMedia(yNoteShareContent);
	}

	/**
	 * ���ӡ��ʼ�ƽ̨
	 */
	private void addEverNote() {
		UMEvernoteHandler evernoteHandler = new UMEvernoteHandler(
				LoginActivity.this);
		evernoteHandler.addToSocialSDK();

		// ����evernote�ķ�������
		EvernoteShareContent shareContent = new EvernoteShareContent("ӡ��ʼ��ı�����");
		shareContent.setShareMedia(new UMImage(LoginActivity.this,
				R.drawable.test));
		mController.setShareMedia(shareContent);
	}

	/**
	 * ���Pinterestƽ̨
	 */
	private void addPinterest() {
		/**
		 * app id�赽pinterest������վ( https://developers.pinterest.com/ )��������.
		 */
		UMPinterestHandler pinterestHandler = new UMPinterestHandler(
				LoginActivity.this, "1439206");
		pinterestHandler.addToSocialSDK();

		// ����Pinterest�ķ�������
		PinterestShareContent shareContent = new PinterestShareContent(
				"Pinterest�ı�����");
		shareContent.setShareMedia(new UMImage(LoginActivity.this,
				R.drawable.test));
		mController.setShareMedia(shareContent);
	}

	/**
	 * ���������������̬ƽ̨</br>
	 */
	private void addLaiWang() {

		String appToken = "laiwangd497e70d4";
		String secretID = "d497e70d4c3e4efeab1381476bac4c5e";
		// laiwangd497e70d4:����appToken,d497e70d4c3e4efeab1381476bac4c5e:����secretID
		// ���������֧��
		UMLWHandler umlwHandler = new UMLWHandler(LoginActivity.this, appToken,
				secretID);
		umlwHandler.addToSocialSDK();

		// ���������̬��֧��
		UMLWHandler lwDynamicHandler = new UMLWHandler(LoginActivity.this,
				appToken, secretID);
		lwDynamicHandler.setToCircle(true);
		lwDynamicHandler.addToSocialSDK();
	}

	/**
	 * @�������� : ���΢��ƽ̨����
	 * @return
	 */
	private void addWXPlatform() {
		// ע�⣺��΢����Ȩ��ʱ�򣬱��봫��appSecret
		// wx967daebe835fbeac������΢�ſ���ƽ̨ע��Ӧ�õ�AppID, ������Ҫ�滻����ע���AppID
		String appId = "wx967daebe835fbeac";
		String appSecret = "5bb696d9ccd75a38c8a0bfe0675559b3";
		// ���΢��ƽ̨
		UMWXHandler wxHandler = new UMWXHandler(LoginActivity.this, appId,
				appSecret);
		wxHandler.addToSocialSDK();

		// ֧��΢������Ȧ
		UMWXHandler wxCircleHandler = new UMWXHandler(LoginActivity.this,
				appId, appSecret);
		wxCircleHandler.setToCircle(true);
		wxCircleHandler.addToSocialSDK();
	}

	/**
	 * @�������� : ���QQƽ̨֧�� QQ��������ݣ� �����������ͣ� �����������֡�ͼƬ�����֡���Ƶ. ����˵�� : title, summary,
	 *       image url�б�����������һ��, targetUrl��������,��ҳ��ַ������"http://"��ͷ . title :
	 *       Ҫ������� summary : Ҫ��������ָ��� image url : ͼƬ��ַ [������������������дһ��] targetUrl
	 *       : �û�����÷���ʱ��ת����Ŀ���ַ [����] ( ������д��Ĭ������Ϊ������ҳ )
	 * @return
	 */
	private void addQQQZonePlatform() {
		String appId = "100424468";
		String appKey = "c7394704798a158208a74ab60104f0ba";
		// ���QQ֧��, ��������QQ�������ݵ�target url
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(LoginActivity.this,
				appId, appKey);
		qqSsoHandler.setTargetUrl("http://www.umeng.com");
		qqSsoHandler.addToSocialSDK();

		// ���QZoneƽ̨
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

		// �������ƽ̨
		UMYXHandler yixinHandler = new UMYXHandler(LoginActivity.this,
				"yxc0614e80c9304c11b0391514d09f13bf");
		// �رշ���ʱ�ĵȴ�Dialog
		yixinHandler.enableLoadingDialog(false);
		// ����target Url, ������http����https��ͷ
		yixinHandler.setTargetUrl("http://www.umeng.com");
		yixinHandler.addToSocialSDK();

		// ��������Ȧƽ̨
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
				"facebook �������");
		fbContent.setShareImage(mUMImgBitmap);
		fbContent.setShareContent("This is my facebook social share sdk."
				+ new Date().toString());
		fbContent.setTitle("Title - FB");
		fbContent.setCaption("Caption - Fb");
		fbContent.setDescription("������ֲ���");
		fbContent.setTargetUrl("http://www.umeng.com/");
		mController.setShareMedia(fbContent);

		mController.setShareContent("facebook share");
		mController.setShareMedia(mUMImgBitmap);

	}

	/**
	 * </br> Instagramֻ֧��ͼƬ����, ֻ֧�ִ�ͼƬ����.</br>
	 */
	private void addInstagram() {
		// ����Instagram��Handler
		UMInstagramHandler instagramHandler = new UMInstagramHandler(
				LoginActivity.this);
		instagramHandler.addToSocialSDK();

		UMImage localImage = new UMImage(LoginActivity.this, R.drawable.device);

		// // ��ӷ���Instagram������
		InstagramShareContent instagramShareContent = new InstagramShareContent(
				localImage);
		mController.setShareMedia(instagramShareContent);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_sina_login: // ����΢����¼
			login(SHARE_MEDIA.SINA);
			break;
		case R.id.btn_qq_login: // qq��¼
			login(SHARE_MEDIA.QQ);
			break;
		case R.id.btn_wechat_login: // ΢�ŵ�½
			login(SHARE_MEDIA.WEIXIN);
			break;
		case R.id.btn_share: // һ������
			addCustomPlatforms();
			break;
		case R.id.btn_sina_logout: // ע�������˺�
			logout(SHARE_MEDIA.SINA);
			break;
		case R.id.btn_qq_logout: // ע��qq�˺�
			logout(SHARE_MEDIA.QQ);
			break;
		case R.id.btn_wechat_logout:
			logout(SHARE_MEDIA.WEIXIN); // ע��΢���˺�
			break;
		default:
			break;
		}
	}

	/**
	 * ��Ȩ�������Ȩ�ɹ������ȡ�û���Ϣ
	 * 
	 * @param platform
	 */
	private void login(final SHARE_MEDIA platform) {
		mController.doOauthVerify(LoginActivity.this, platform,
				new UMAuthListener() {

					@Override
					public void onStart(SHARE_MEDIA platform) {
						Toast.makeText(LoginActivity.this, "��Ȩ��ʼ",
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onError(SocializeException e,
							SHARE_MEDIA platform) {
						Toast.makeText(LoginActivity.this, "��Ȩʧ��",
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onComplete(Bundle value, SHARE_MEDIA platform) {
						// ��ȡuid
						String uid = value.getString("uid");
						if (!TextUtils.isEmpty(uid)) {
							// uid��Ϊ�գ���ȡ�û���Ϣ
							getUserInfo(platform);
						} else {
							Toast.makeText(LoginActivity.this, "��Ȩʧ��...",
									Toast.LENGTH_LONG).show();
						}
					}

					@Override
					public void onCancel(SHARE_MEDIA platform) {
						Toast.makeText(LoginActivity.this, "��Ȩȡ��",
								Toast.LENGTH_SHORT).show();
					}
				});
	}

	/**
	 * ��ȡ�û���Ϣ
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
						// showText = "�û�����" +
						// info.get("screen_name").toString();
						// Log.d("#########", "##########" + info.toString());
						// } else {
						// showText = "��ȡ�û���Ϣʧ��";
						// }

						if (info != null) {
							Toast.makeText(LoginActivity.this, info.toString(),
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}
	
	/**
	 * ע�����ε�½
	 * @param platform
	 */
	private void logout(final SHARE_MEDIA platform) {
		mController.deleteOauth(LoginActivity.this, platform, new SocializeClientListener() {
			
			@Override
			public void onStart() {
				
			}
			
			@Override
			public void onComplete(int status, SocializeEntity entity) {
				String showText = "���" + platform.toString() + "ƽ̨��Ȩ�ɹ�";
				if (status != StatusCode.ST_CODE_SUCCESSED) {
					showText = "���" + platform.toString() + "ƽ̨��Ȩʧ��[" + status + "]";
				}
				Toast.makeText(LoginActivity.this, showText, Toast.LENGTH_SHORT).show();
			}
		});
	}
	

	// �����ʹ����һƽ̨��SSO��Ȩ, ������ڶ�Ӧ��activity��ʵ��onActivityResult����, ��������´���
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// ����requestCode��ȡ��Ӧ��SsoHandler
		UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(
				resultCode);
		if (ssoHandler != null) {
			ssoHandler.authorizeCallBack(requestCode, resultCode, data);
		}
	}
}
