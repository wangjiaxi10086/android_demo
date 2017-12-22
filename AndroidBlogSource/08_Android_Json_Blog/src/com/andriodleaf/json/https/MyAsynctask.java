package com.andriodleaf.json.https;

import android.os.AsyncTask;

/**
 * �첽��������
 * @author AndroidLeaf
 */
public class MyAsynctask extends AsyncTask<Object, Void, String> {

	/**
	 * �����������͵�ID
	 */
	 int typeId = -1;
	 
	 /**
	  * ����һ���ص������ڼ����������󣬵�������������ط��ʽ��
	  */
	 public HttpDownloadedListener mHttpDownloadedListener;
	
	 public interface HttpDownloadedListener{
		public void onDownloaded(String result,int typeId);
	 }
	 
	 public void setOnHttpDownloadedListener(HttpDownloadedListener mHttpDownloadedListener){
		 this.mHttpDownloadedListener = mHttpDownloadedListener;
	 }
	 
	@Override
	protected String doInBackground(Object... params) {
		// TODO Auto-generated method stub
		//ִ�������������
		String result = HttpUtils.getStringFromNetWork((String)params[0]);
		typeId = (int) params[1];
		return result;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		mHttpDownloadedListener.onDownloaded(result,typeId);
	}
}
