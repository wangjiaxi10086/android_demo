package com.andriodleaf.xml.xmltest;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX������
 * @author AndroidLeaf
 */
public class MyHandler extends DefaultHandler {

	//����ʼ��ȡ�ĵ���ǩʱ���ø÷���
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
	}

	//����ʼ��ȡ�ڵ�Ԫ�ر�ǩʱ���ø÷���
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		//do something
	}

	//����ȡ�ڵ�Ԫ�ص�������Ϣʱ���ø÷���
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		//do something
	}
	
	//��������ȡ�ڵ�Ԫ�ر�ǩʱ���ø÷���
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		//do something
	}
	
	//��������ȡ�ĵ���ǩʱ���ø÷���
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		//do something
	}
}
