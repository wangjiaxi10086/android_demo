package com.fendou.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentServiceExecuteDemo extends IntentService {
	
	private static final String TAG = "androidLeaf";

	public MyIntentServiceExecuteDemo() {
		super("MyIntentServiceExecuteDemo");
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub
		//�����߳�˯����ģ���ʱ����
		for(int  i = 0; i < 50;i++)
		{
			Log.i(TAG, "IntentService�����߳�ִ�С�������������"+ (i + 1));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
