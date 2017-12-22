package com.androidleaf.handler.activity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * ���������
 * @author AndroidLeaf
 */
public class HttpUtils {
	
	/**
	 * ʹ��HttpURLConnection��ʽ�������л�ȡ����
	 * @param urlStr
	 * @return
	 */
	public static Bitmap getBitmapFromNetWork(String urlStr)
	{
		URL mUrl = null;
		HttpURLConnection  mConnection= null;
		Bitmap result = null;
		try {
			mUrl = new URL(urlStr);
			mConnection = (HttpURLConnection)mUrl.openConnection();
			mConnection.setDoOutput(true);
			mConnection.setDoInput(true);
			mConnection.setReadTimeout(5 * 1000);
			mConnection.setConnectTimeout(15 * 1000);
			mConnection.setRequestMethod("GET");
			int responseCode = mConnection.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK){
				//��ȡ������Դ�Ĵ�С
				//contentLength = mConnection.getContentLength();
				result = getBitmap(changeInputStreamToBytes(mConnection.getInputStream()), 80, 80);
				return result;
			}		
		} catch (IOException e) {
			// TODO: handle exception
		}
		return null;
	}
	
	/**
	 * ���ֽ�������ת�����ֽ�����
	 * @param mInputStream ��Ҫת�����ֽ���
	 * @return result ת����Ľ��
	 */
	private static byte[] changeInputStreamToBytes(InputStream mInputStream)
	{
		ByteArrayOutputStream mArrayOutputStream = new ByteArrayOutputStream();
		byte[] result = null;
		byte[] mBytes = new byte[2048];
		int len = -1;
		try {
			while((len = mInputStream.read(mBytes)) != -1)
			{
				mArrayOutputStream.write(mBytes, 0, len);
				mArrayOutputStream.flush();
			}
			result = mArrayOutputStream.toByteArray();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(mInputStream != null){
					mInputStream.close();
				}
				if(mArrayOutputStream != null){
					mArrayOutputStream.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return null;
	}
	
	private static Bitmap getBitmap(byte[] bytes,int width,int height){
		//��ȡ��Ļ�Ŀ�͸�  
        /** 
         * Ϊ�˼������ŵı�����������Ҫ��ȡ����ͼƬ�ĳߴ磬������ͼƬ 
         * BitmapFactory.Options������һ�������ͱ���inJustDecodeBounds����������Ϊtrue 
         * ���������ǻ�ȡ���ľ���ͼƬ�ĳߴ磬�����ü���ͼƬ�ˡ� 
         * �������������ֵ��ʱ�����ǽ��žͿ��Դ�BitmapFactory.Options��outWidth��outHeight�л�ȡ��ֵ 
         */  
        BitmapFactory.Options op = new BitmapFactory.Options();  
        op.inJustDecodeBounds = true;  
        Bitmap pic = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        
        int wRatio = (int) Math.ceil(op.outWidth / (float) width); //�����ȱ���  
        int hRatio = (int) Math.ceil(op.outHeight / (float) height); //����߶ȱ���
        
        /** 
         * �����������Ǿ���Ҫ�ж��Ƿ���Ҫ�����Լ����׶Կ��Ǹ߽������š� 
         * ����ߺͿ���ȫ����������Ļ����ô�������š� 
         * ����ߺͿ���������Ļ��С�������ѡ�������ء� 
         * ����Ҫ�ж�wRatio��hRatio�Ĵ�С 
         * ���һ���������ţ���Ϊ���Ŵ��ʱ��С��Ӧ���Զ�����ͬ�������š� 
         * ����ʹ�õĻ���inSampleSize���� 
         */  
        if (wRatio > 1 && hRatio > 1) {  
            if (wRatio > hRatio) {  
                op.inSampleSize = wRatio;  
            } else {  
                op.inSampleSize = hRatio;  
            }  
        }  
        op.inJustDecodeBounds = false; //ע�����һ��Ҫ����Ϊfalse����Ϊ�������ǽ�������Ϊtrue����ȡͼƬ�ߴ���  
        pic = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		return pic;
	}
}
