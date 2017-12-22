package com.androidleaf.jsonserver.tools;

import java.text.DateFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sf.json.JSONObject;

public class JsonTools {

	public static String createJsonString(String key, Object value) {
		/**
		 * ʹ��Jsonԭ�����������ʱ��ʹ�ø÷�������Json�ַ���
		 */
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(key, value);
		return jsonObject.toString();
		
		/**
		 * ʹ��Gson,Jackson��FastJson��������ʱ��ʹ�ø÷�������Json�ַ���
		 */
		
		
		/*Gson mGson = new GsonBuilder().registerTypeAdapter(java.util.Date.class, new UtilDateSerializer())
				.setDateFormat(DateFormat.LONG).create();*/
		
		/*Gson mGson = new Gson();
		return mGson.toJson(value);*/
		
	}
}
