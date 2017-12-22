package com.androidleaf.jsontest;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

/**
 * Json��JSONObject��JSONArray��JSONStringer��JSONTokener��ʹ��ʵ��
 * @author AndroidLeaf
 *
 */
public class JsonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonTest mJsonTest = new JsonTest();
		//mJsonTest.jsonObjectTest();
		//mJsonTest.jsonArrayTest();
		//mJsonTest.jsonStringTest();
		mJsonTest.jsonTokenerTest();
	}
	
	/**
	 * JsonObject�����ʹ��
	 */
	public void jsonObjectTest(){
		Person mPerson = testDataPerson();
		
		/**
		 * ��Person����ת����Json�ַ���
		 */
		JSONObject mJsonObject = new JSONObject();
		try {
			//���ֵ��mJsonObject������
			mJsonObject.put("id", mPerson.getId());
			mJsonObject.put("userName", mPerson.getUserName());
			mJsonObject.put("password", mPerson.getPassword());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(mJsonObject.toString());
		/**��ӡ������Ϊ��{"id":1,"userName":"AndroidLeaf","password":"123"} */
		
		/**
		 * ��Json�ַ���ת���ɶ�Ӧ��Person����
		 */
		Person mPerson2 = new Person();
		JSONObject mJsonObject2;
		try {
			 mJsonObject2 = new JSONObject(mJsonObject.toString());
			 mPerson2.setId(mJsonObject2.getInt("id"));
			 mPerson2.setUserName(mJsonObject2.getString("userName"));
			 mPerson2.setPassword(mJsonObject2.getString("password"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(mPerson2.toString());
		/**��ӡ������Ϊ��Person [id=1, userName=AndroidLeaf, password=123]*/
	}
	
	/**
	 * JsonArray�����ʹ��
	 */
	public void jsonArrayTest(){
		
		ArrayList<Person> mList = testDataPersons();
		/**
		 * ��Person���϶���ת����Json�ַ���
		 */
		JSONArray mJsonArray = new JSONArray();
		for(int i = 0; i < mList.size();i++){
			Person mPerson = mList.get(i);
			try{
				JSONObject mJsonObject = new JSONObject();
				mJsonObject.put("id", mPerson.getId());
				mJsonObject.put("userName", mPerson.getUserName());
				mJsonObject.put("password", mPerson.getPassword());
				mJsonArray.put(mJsonObject);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(mJsonArray.toString());
		/**��ӡ������Ϊ��[{"id":0,"userName":"AndroidLeaf 0","password":"1230"},
		 * {"id":1,"userName":"AndroidLeaf 1","password":"1231"},
		 * {"id":2,"userName":"AndroidLeaf 2","password":"1232"},
		 * {"id":3,"userName":"AndroidLeaf 3","password":"1233"},
		 * {"id":4,"userName":"AndroidLeaf 4","password":"1234"}]*/
		
		/**
		 * ��Json�ַ���ת����Person���϶���
		 */
		ArrayList<Person> mPersons = new ArrayList<Person>();
		JSONArray mJsonArray2;
		try {
			mJsonArray2 = new JSONArray(mJsonArray.toString());
			for(int j = 0;j < mJsonArray2.length();j++){
				Person mPerson = new Person();
				JSONObject mJsonObject = mJsonArray2.getJSONObject(j);
				mPerson.setId(mJsonObject.getInt("id"));
				mPerson.setUserName(mJsonObject.getString("userName"));
				mPerson.setPassword(mJsonObject.getString("password"));
				mPersons.add(mPerson);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(mPersons.toString());
		/**��ӡ������Ϊ��
		 * [Person [id=0, userName=AndroidLeaf 0, password=1230], 
		 *  Person [id=1, userName=AndroidLeaf 1, password=1231],
		 *  Person [id=2, userName=AndroidLeaf 2, password=1232], 
		 *  Person [id=3, userName=AndroidLeaf 3, password=1233], 
		 *  Person [id=4, userName=AndroidLeaf 4, password=1234]]*/
	}
	
	/**
	 * JsonString�����ʹ��
	 */
	public String jsonStringTest(){
		
		/**
		 * ��Person���󹹽���Json�ַ����ı�
		 */
		JSONStringer mJsonStringer = new JSONStringer();
		Person mPerson = testDataPerson();
		try {
			//object()��endObject()��Գ��֣�������������ʼ����
			mJsonStringer.object();
			mJsonStringer.key("id");
			mJsonStringer.value(mPerson.getId());
			mJsonStringer.key("userName");
			mJsonStringer.value(mPerson.getUserName());
			mJsonStringer.key("password");
			mJsonStringer.value(mPerson.getPassword());
			mJsonStringer.endObject();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(mJsonStringer.toString());
		/**��ӡ������Ϊ�� {"id":1,"userName":"AndroidLeaf","password":"123"}*/
		
		
		/**
		 * ��Person���϶��󹹽���Json�ַ����ı�
		 * 
		 */
		
		JSONStringer mJsonStringer2 = new JSONStringer();
		ArrayList<Person> mList = testDataPersons();
		
		try {
			//array()��endArray()������Գ��֣����������������ʼ����
			mJsonStringer2.array();
			for(int i = 0 ;i < mList.size();i++){
				//��Person���������Json�ַ�������װ��JSONStringer��ӵ����鼯����
				JSONStringer mJsonStringer3 = new JSONStringer();
				mJsonStringer3.object();
				mJsonStringer3.key("id");
				mJsonStringer3.value(mList.get(i).getId());
				mJsonStringer3.key("userName");
				mJsonStringer3.value(mList.get(i).getUserName());
				mJsonStringer3.key("password");
				mJsonStringer3.value(mList.get(i).getPassword());
				mJsonStringer3.endObject();
				mJsonStringer2.value(mJsonStringer3);
			}
			mJsonStringer2.endArray();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(mJsonStringer2.toString());
		return mJsonStringer2.toString();
		/**��ӡ������Ϊ��["{\"id\":0,\"userName\":\"AndroidLeaf 0\",\"password\":\"1230\"}",
		 * "{\"id\":1,\"userName\":\"AndroidLeaf 1\",\"password\":\"1231\"}",
		 * "{\"id\":2,\"userName\":\"AndroidLeaf 2\",\"password\":\"1232\"}",
		 * "{\"id\":3,\"userName\":\"AndroidLeaf 3\",\"password\":\"1233\"}",
		 * "{\"id\":4,\"userName\":\"AndroidLeaf 4\",\"password\":\"1234\"}"]*/
		
	}
	
	/**
	 * JsonTokener�����ʹ��
	 */
	public void jsonTokenerTest(){
		
		/**jsonStr������Ϊ��["{\"id\":0,\"userName\":\"AndroidLeaf 0\",\"password\":\"1230\"}",
		 * "{\"id\":1,\"userName\":\"AndroidLeaf 1\",\"password\":\"1231\"}",
		 * "{\"id\":2,\"userName\":\"AndroidLeaf 2\",\"password\":\"1232\"}",
		 * "{\"id\":3,\"userName\":\"AndroidLeaf 3\",\"password\":\"1233\"}",
		 * "{\"id\":4,\"userName\":\"AndroidLeaf 4\",\"password\":\"1234\"}"]*/
		String jsonStr = initTokenerData();
		JSONTokener mJsonTokener = new JSONTokener(jsonStr);
		try {
			//��ת����ȡ����һ���ַ�
			System.out.println(mJsonTokener.next());
			//��ת����ȡһ��ֵ
			System.out.println(mJsonTokener.nextValue());
			System.out.println(mJsonTokener.next());
			System.out.println(mJsonTokener.nextValue());
			//���ص���һ���ַ�
			mJsonTokener.back();
			System.out.println(mJsonTokener.next());
			//��ת����һ������"f"�ַ���λ�ã�����ȡ���λ�õ���ת��λ��֮����ַ���
			System.out.println(mJsonTokener.nextTo('f'));
			//mJsonTokener.next(c);
			//mJsonTokener.next(arg0);
			//mJsonTokener.skipTo(arg0);
			//mJsonTokener.nextClean();
			
			/**��ӡ������Ϊ��[
				*		{"id":0,"userName":"AndroidLeaf 0","password":"1230"}
				*		,
				*		{"id":1,"userName":"AndroidLeaf 1","password":"1231"}
				*		"
				*		,"{\"id\":2,\"userName\":\"AndroidLea*/
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Person testDataPerson(){
		Person mPerson = new Person();
		mPerson.setId(1);
		mPerson.setUserName("AndroidLeaf");
		mPerson.setPassword("123");
		return mPerson;
	}
	
	public ArrayList<Person> testDataPersons(){
		ArrayList<Person> mList = new ArrayList<Person>();
		for(int i = 0; i < 5; i++){
			Person mPerson = new Person();
			mPerson.setId(i);
			mPerson.setUserName("AndroidLeaf " + i);
			mPerson.setPassword("123" + i);
			mList.add(mPerson);
		}
		return mList;
	}
	
	public String initTokenerData(){
		return jsonStringTest();
	}
}
