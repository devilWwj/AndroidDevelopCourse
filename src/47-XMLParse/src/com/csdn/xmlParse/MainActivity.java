package com.csdn.xmlParse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * XML解析
 * 
 * @author devilwwj
 * 
 */
public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1: // SAX解析
			try {
				// 1. 创建SAXParserFactory对象
				SAXParserFactory factory = SAXParserFactory.newInstance();
				// 2. 调用newSAXParser创建SAXParser对象
				SAXParser parser = factory.newSAXParser();
				// 3. 调用XMLReader获取XMLReader对象
				XMLReader reader = parser.getXMLReader();
				// 4. 通过XMLReader注册事件处理接口
				MyHandler handler = new MyHandler();
				reader.setContentHandler(handler);
				// 5. 调用parse方法解析字符串对象
				parser.parse(getAssets().open("users.xml"), handler);

				List<User> users = handler.getUsers();
				for (User user : users) {
					Log.i("info", user.toString());
				}
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			break;
		case R.id.button2: // DOM解析
			List<User> users = new ArrayList<User>();
			try {
				// 1. 创建一个DocumentBuilderFactory实例
				DocumentBuilderFactory builderFactory = DocumentBuilderFactory
						.newInstance();
				// 2. 创建DocumentBuilder对象
				DocumentBuilder buider = builderFactory.newDocumentBuilder();
				// 3. 加载XML文档
				Document document = buider.parse(getAssets().open("users.xml"));
				// 4. 获取根节点
				Element root = document.getDocumentElement();
				// 5. 获取根节点中所有子节点的列表
				NodeList nodeList = root.getElementsByTagName("user");
				// 6. 获取列表中读取需要的节点
				for (int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					Element element = (Element) node;
					User user = new User();
					user.setId(Integer.parseInt(element.getAttribute("id")));

					NodeList childNodes = node.getChildNodes();

					for (int j = 0; j < childNodes.getLength(); j++) {
						Node childNode = childNodes.item(j);

						if (childNode.getNodeType() == Node.ELEMENT_NODE) {
							Element e = (Element) childNode;

							if (e.getNodeName().equals("name")) {
								user.setName(childNode.getFirstChild()
										.getNodeValue());
							}

							if (e.getNodeName().equals("password")) {
								user.setPassword(childNode.getFirstChild()
										.getNodeValue());
							}
						}

					}
					users.add(user);
				}

			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			for (User user : users) {
				Log.i("info", user.toString());
			}
			

			break;
		case R.id.button3: // PULL解析
			List<User> users1 = null;
			User user = null;
			String tagName = null; // 标签
			
			try {
				// 1. 创建一个XmlPullParserFactory实例
				XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
				// 2. 创建XmlPullParser对象
				XmlPullParser parser = factory.newPullParser();
				
				// 3. 设置输入
				parser.setInput(getAssets().open("users.xml"), "utf-8");
				
				// 4. 获得事件类型
				int eventType = parser.getEventType();
				
				// 没有到结束文档
				while (eventType != XmlPullParser.END_DOCUMENT) {
					switch (eventType) {
					case XmlPullParser.START_DOCUMENT: // 开始文档
						users1 = new ArrayList<User>(); // 实例化List
						break;
					case XmlPullParser.START_TAG: // 开始标签
						tagName = parser.getName(); // 获得标签名
						if ("user".equals(tagName)) { 
							user = new User();
							user.setId(Integer.parseInt(parser.getAttributeValue(null,"id")));
						}
						
						if ("name".equals(tagName)) {
							eventType = parser.next();
							user.setName(parser.getText());
						}
						
						if ("password".equals(tagName)) {
							eventType = parser.next();
							user.setPassword(parser.getText());
						}
						break;
					case XmlPullParser.END_TAG:
						if ("user".equals(parser.getName())) {
							users1.add(user);
							user = null;
						}
						
						break;

					default:
						break;
					}
					
					// 下一个事件
					eventType = parser.next();
				}
				
				
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			for (User user1 : users1) {
				Log.i("info", user1.toString());
			}
			
			break;

		default:
			break;
		}
	}

}
