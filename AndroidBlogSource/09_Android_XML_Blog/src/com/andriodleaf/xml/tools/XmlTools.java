package com.andriodleaf.xml.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.andriodleaf.xml.entity.Person;

public class XmlTools {

	/**--------------SAX����XML-------------------*/
	/**
	 * @param mInputStream ��Ҫ������person.xml���ļ�������
	 * @param nodeName �ڵ�����
	 * @return mList Person���󼯺�
	 */
	public static ArrayList<Person> saxAnalysis(InputStream mInputStream,String nodeName){
		//1����ȡ����һ��SAX��������ʵ��
		SAXParserFactory mSaxParserFactory = SAXParserFactory.newInstance();
		try {
			//2�����ù���ʵ���е�newSAXParser()��������SAXParser��������
			SAXParser mSaxParser = mSaxParserFactory.newSAXParser();
			//3��ʵ����CustomHandler(DefaultHandler������)
			CustomHandler mHandler = new CustomHandler(nodeName);
			/**
			 * 4�������¼�Դ����XMLReader���¼�������DefaultHandler��
			 * �鿴parse(InputStream is, DefaultHandler dh)����Դ�����£�
			 * public void parse(InputSource is, DefaultHandler dh)
			 *      throws SAXException, IOException {
			 *      if (is == null) {
			 *           throw new IllegalArgumentException("InputSource cannot be null");
			 *       }
			 *     // ��ȡ�¼�ԴXMLReader����ͨ����Ӧ�¼�������ע�᷽��setXXXX()����ɵ���ContentHander��DTDHander��ErrorHandler��
			 *     // �Լ�EntityResolver��4���ӿڵ����ӡ�
			 *       XMLReader reader = this.getXMLReader();
			 *       if (dh != null) {
			 *           reader.setContentHandler(dh);
			 *           reader.setEntityResolver(dh);
			 *           reader.setErrorHandler(dh);
			 *           reader.setDTDHandler(dh);
			 *       }
			 *       reader.parse(is);
			 *   }
			 */
			mSaxParser.parse(mInputStream, mHandler);
			//5��ͨ��DefaultHandler����������Ҫ�����ݼ���
			return mHandler.getList();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @param is
	 * @param dh
	 * @throws SAXException
	 * @throws IOException
	 */
	 
	
	/**--------------DOM����XML-------------------*/
	/**
	 * @param mInputStream ��Ҫ������person.xml���ļ�������
	 * @return mList Person���󼯺�
	 */
	public static ArrayList<Person> domAnalysis(InputStream mInputStream){
		ArrayList<Person> mList = new ArrayList<Person>();
		
		//1�������ĵ����󹤳�ʵ��
		DocumentBuilderFactory mDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			//2������DocumentBuilderFactory�е�newDocumentBuilder()���������ĵ���������
			DocumentBuilder mDocumentBuilder = mDocumentBuilderFactory.newDocumentBuilder();
			//3�����ļ���������XML�ĵ�����
			Document mDocument = mDocumentBuilder.parse(mInputStream);
			//4��ʹ��mDocument�ĵ�����õ��ĵ����ڵ�
			Element mElement = mDocument.getDocumentElement();
			//5���������ƻ�ȡ���ڵ��е��ӽڵ��б�
			NodeList mNodeList =  mElement.getElementsByTagName("person");
			//6 ����ȡ�ӽڵ��б�����Ҫ��ȡ�Ľڵ���Ϣ
			for(int i = 0 ;i < mNodeList.getLength();i++){
				Person mPerson = new Person();
				Element personElement = (Element) mNodeList.item(i);
				//��ȡperson�ڵ��е�����
				if(personElement.hasAttributes()){
					mPerson.setId(Integer.parseInt(personElement.getAttribute("id")));
				}
				if(personElement.hasChildNodes()){
				//��ȡperson�ڵ���ӽڵ��б�
				 NodeList mNodeList2 = personElement.getChildNodes();
				 //�����ӽڵ��б���ֵ
				 for(int j = 0;j < mNodeList2.getLength();j++){
					    Node mNodeChild = mNodeList2.item(j);
						if(mNodeChild.getNodeType() == Node.ELEMENT_NODE){
							if("name".equals(mNodeChild.getNodeName())){
								mPerson.setUserName(mNodeChild.getFirstChild().getNodeValue());
							}else if("height".equals(mNodeChild.getNodeName())){
								mPerson.setHeight(Float.parseFloat(mNodeChild.getFirstChild().getNodeValue()));
							}else if("imageurl".equals(mNodeChild.getNodeName())){
								mPerson.setImageUrl(mNodeChild.getFirstChild().getNodeValue());
							}
						}
					}
				}
				mList.add(mPerson);
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}
	
	/**--------------PULL����XML-------------------*/
	/**
	 * @param mInputStream ��Ҫ������person.xml���ļ�������
	 * @param encode �����ַ�����
	 * @return mList Person���󼯺�
	 */
	public static ArrayList<Person> PullAnalysis(InputStream mInputStream,String encode){
		ArrayList<Person> mList = null;
		Person mPerson = null;
		try {
			//1����ȡPULL��������ʵ������
			XmlPullParserFactory mXmlPullParserFactory = XmlPullParserFactory.newInstance();
			//2��ʹ��XmlPullParserFactory��newPullParser()����ʵ����PULL����ʵ������
			XmlPullParser mXmlPullParser = mXmlPullParserFactory.newPullParser();
			//3�������������XML�ļ������ַ�����
			mXmlPullParser.setInput(mInputStream, encode);
			//4����ȡ�¼���������
			int eventType = mXmlPullParser.getEventType();
			//5��ѭ���������������ĵ���������ʱ����ѭ��
			while(eventType != XmlPullParser.END_DOCUMENT){
				switch (eventType) {
				//��ʼ�����ĵ�
				case XmlPullParser.START_DOCUMENT:
					mList = new ArrayList<Person>();
					break;
				//��ʼ�����ڵ�
				case XmlPullParser.START_TAG:
					if("person".equals(mXmlPullParser.getName())){
						mPerson = new Person();
						//��ȡ�ýڵ��е����Ե�����
						int attributeNumber = mXmlPullParser.getAttributeCount();
						if(attributeNumber > 0){
							//��ȡ����ֵ
							mPerson.setId(Integer.parseInt(mXmlPullParser.getAttributeValue(0)));
						}
					}else if("name".equals(mXmlPullParser.getName())){
						//��ȡ�ýڵ������
						mPerson.setUserName(mXmlPullParser.nextText());
					}else if("height".equals(mXmlPullParser.getName())){
						mPerson.setHeight(Float.parseFloat(mXmlPullParser.nextText()));
					}else if("imageurl".equals(mXmlPullParser.getName())){
						mPerson.setImageUrl(mXmlPullParser.nextText());
					}
					break;
				//�����ڵ����
				case XmlPullParser.END_TAG:
					if("person".equals(mXmlPullParser.getName())){
						mList.add(mPerson);
						mPerson = null;
					}
					break;
				default:
					break;
				}
				eventType = mXmlPullParser.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}
}
