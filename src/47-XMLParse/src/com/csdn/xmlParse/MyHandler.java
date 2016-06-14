package com.csdn.xmlParse;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * SAX解析处理句柄
 * @author devilwwj
 *
 */
public class MyHandler extends DefaultHandler {
	private List<User> users;
	private User user;
	private String content;
	
	public List<User> getUsers() {
		return this.users;
	}

	/**
	 * 解析到根标签触发此方法
	 */
	@Override
	public void startDocument() throws SAXException {
		this.users = new ArrayList<User>();
	}

	/**
	 * 解析到结束标签触发此方法
	 */
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	/**
	 * 解析到子元素的时候触发此方法
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if ("user".equals(localName)) {
			user = new User();
			user.setId(Integer.parseInt(attributes.getValue("id")));
		}
	}

	/**
	 * 解析到子元素的反标签时触发此方法
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("name".equals(localName)) {
			user.setName(content);
		}
		
		if ("password".equals(localName)) {
			user.setPassword(content);
		}
		
		if ("user".equals(localName)) {
			users.add(user);
			user = null;
		}
	}

	/**
	 * 解析一个节点的时候触发此方法
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		content = new String(ch, start, length);
	}
}
