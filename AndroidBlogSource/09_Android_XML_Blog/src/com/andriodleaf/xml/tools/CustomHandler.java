package com.andriodleaf.xml.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.andriodleaf.xml.entity.Person;

/**
 * SAX������
 * @author AndroidLeaf
 */
public class CustomHandler extends DefaultHandler {

	//װ�����н�����ɵ�����
	List<HashMap<String, String>> mListMaps = null;
	//װ�ؽ�������person�ڵ������
	HashMap<String, String> map = null;
	//�ڵ�����
	private String nodeName;
	//��ǰ�����Ľڵ���
	private String currentTag;
	//��ǰ�����Ľڵ�ֵ
	private String currentValue;

	public ArrayList<Person> getList(){
		ArrayList<Person> mList = new ArrayList<Person>();
		if(mListMaps != null && mListMaps.size() > 0){
			for(int i = 0;i < mListMaps.size();i++){
				Person mPerson = new Person();
				HashMap<String, String> mHashMap = mListMaps.get(i);
				mPerson.setId(Integer.parseInt(mHashMap.get("id")));
				mPerson.setUserName(mHashMap.get("name"));
				mPerson.setHeight(Float.parseFloat(mHashMap.get("height")));
				mPerson.setImageUrl(mHashMap.get("imageurl"));
				mList.add(mPerson);
			}
		}
		return mList;
	}
	
	public CustomHandler(String nodeName){
		this.nodeName = nodeName;
	}
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		mListMaps = new ArrayList<HashMap<String, String>>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		if (qName.equals(nodeName)) {
			map = new HashMap<String, String>();
		}
		if (map != null && attributes != null) {
			for (int i = 0; i < attributes.getLength(); i++) {
				map.put(attributes.getQName(i), attributes.getValue(i));
			}
		}
		// ��ǰ�Ľ����Ľڵ�����
		currentTag = qName;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		if (map != null && currentTag != null) {
			currentValue = new String(ch, start, length);
			if (currentValue != null && !currentValue.equals("")
					&& !currentValue.equals("\n")) {
				map.put(currentTag, currentValue);
			}
		}
		currentTag = null;
		currentValue = null;
		super.characters(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		if (qName.equals(nodeName)) {
			mListMaps.add(map);
			map = null;
		}
		super.endElement(uri, localName, qName);
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();

	}
}
