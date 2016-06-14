package com.csdn.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.net.Uri;
import android.util.Xml;

import com.csdn.appConnectNetwork.domain.Contact;
import com.csdn.appConnectNetwork.utils.MD5;


public class ContactService {

	/**
	 * 获取联系人
	 * @return
	 */
	public static List<Contact> getContacts() throws Exception{
		// 服务器文件路径
		String path = "http://192.168.1.108:8080/web/list.xml";
		HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
		conn.setConnectTimeout(5000);	//设置超时5秒
		conn.setRequestMethod("GET");	//设置请求方式
		if(conn.getResponseCode() == 200){	//连接成功返回码200
			return parseXML(conn.getInputStream());
		}
		return null;
	}
	/**
	 * 利用pull解析器对xml文件进行解析
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	private static List<Contact> parseXML(InputStream xml) throws Exception{
		List<Contact> contacts = new ArrayList<Contact>();
		Contact contact = null;
		XmlPullParser pullParser = Xml.newPullParser();
		pullParser.setInput(xml, "UTF-8");	
		int event = pullParser.getEventType();	//取得开始文档语法
		while(event != XmlPullParser.END_DOCUMENT){		//只要不等于文档结束事件，循环解析
			switch (event) {
			case XmlPullParser.START_TAG:		//开始标签
				if("contact".equals(pullParser.getName())){	
					contact = new Contact();
					contact.setId(new Integer(pullParser.getAttributeValue(0)));	
				}else if("name".equals(pullParser.getName())){
					contact.setName(pullParser.nextText());	//取得后面节点的文本值
				}else if("image".equals(pullParser.getName())){
					contact.setImg(pullParser.getAttributeValue(0));	//取得第一个属性的值
				}
				break;
				
			case XmlPullParser.END_TAG:		//结束标签
				if("contact".equals(pullParser.getName())){
					contacts.add(contact);	//将contact对象添加到集合中
					contact = null;
				}
				break;
			}
			event = pullParser.next();	//去下一个标签
		}
		return contacts;
	}
	/**
	 * 获取网络图片,如果图片存在于缓存中，就返回该图片，否则从网络中加载该图片并缓存起来
	 * @param path 图片路径
	 * @return
	 */
	public static Uri getImage(String path, File cacheDir) throws Exception{// path -> MD5 ->32字符串.jpg
		File localFile = new File(cacheDir, MD5.getMD5(path)+ path.substring(path.lastIndexOf(".")));
		if(localFile.exists()){
			return Uri.fromFile(localFile);
		}else{
			HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			if(conn.getResponseCode() == 200){
				FileOutputStream outStream = new FileOutputStream(localFile);
				InputStream inputStream = conn.getInputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while( (len = inputStream.read(buffer)) != -1){
					outStream.write(buffer, 0, len);
				}
				inputStream.close();
				outStream.close();
				return Uri.fromFile(localFile);
			}
		}
		return null;
	}

}
