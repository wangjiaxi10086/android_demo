package com.andriodleaf.xml.tools;

/**
 * ���������Url��ַ��һЩ����
 * @author AndroidLeaf
 */
public class Constants {

	//��·��
	public static final String BASE_PATH = "http://10.0.2.2:8080/09_Android_XMLServer_Blog/";
	//person.xml��������·��
	public static final String XML_PATH = BASE_PATH + "xmlfile/person.xml";
	
	//ʹ��SAX�����ı�ǩ����
	public static final int REQUEST_SAX_TYPE = 0;
	//ʹ��DOM�����ı�ǩ����
	public static final int REQUEST_DOM_TYPE = 1;
	//ʹ��PULL�����ı�ǩ����
	public static final int REQUEST_PULL_TYPE = 2;
	
	//����person.xml�ļ���ǩ
	public static final int TYPE_STR = 1;
	//����ͼƬ�ļ���ǩ
	public static final int TYPE_STREAM = 2;
	
}
