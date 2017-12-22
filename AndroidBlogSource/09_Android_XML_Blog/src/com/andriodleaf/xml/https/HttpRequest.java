package com.andriodleaf.xml.https;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ���������
 * @author AndroidLeaf
 */
public class HttpRequest {
	/**
	 * @param urlStr �����Url
	 * @return InputStream ���������������
	 */
	public static InputStream getInputStreamFromNetWork(String urlStr)
	{
		URL mUrl = null;
		HttpURLConnection  mConnection= null;
		InputStream mInputStream = null;
		try {
			mUrl = new URL(urlStr);
			mConnection = (HttpURLConnection)mUrl.openConnection();
			mConnection.setDoOutput(true);
			mConnection.setDoInput(true);
			mConnection.setReadTimeout(15 * 1000);
			mConnection.setConnectTimeout(15 * 1000);
			mConnection.setRequestMethod("GET");
			int responseCode = mConnection.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK){
				//��ȡ������Դ�Ĵ�С
				//contentLength = mConnection.getContentLength();
				mInputStream = mConnection.getInputStream();
				return mInputStream;
			}		
		} catch (IOException e) {
			// TODO: handle exception
		}
		return null;
	}
	
	/**
     * �õ�ͼƬ�ֽ��� �����С
     * */
    public static byte[] readStream(InputStream mInputStream){ 
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();      
        byte[] buffer = new byte[2048];      
        int len = 0;      
        try {
			while((len = mInputStream.read(buffer)) != -1){      
			    outStream.write(buffer, 0, len);      
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(outStream != null){
					outStream.close();
				}
				if(mInputStream != null){
					mInputStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}      
		}      
        return outStream.toByteArray();      
    }
	
}
