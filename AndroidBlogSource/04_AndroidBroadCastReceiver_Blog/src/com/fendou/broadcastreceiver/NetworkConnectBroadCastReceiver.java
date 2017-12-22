package com.fendou.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.util.Log;
import android.widget.Toast;

public class NetworkConnectBroadCastReceiver extends BroadcastReceiver {

	private static final String TAG = "leaf";
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//Log.i(TAG, "NetworkConnectBroadCastReceiver");
		doSomething(context);	
	}
	public void doSomething(Context mContext)
	{
		if(isNetworkConnect(mContext))
		{
			Toast.makeText(mContext, "�������ӳɹ�����", Toast.LENGTH_LONG).show();
			Log.i(TAG, "�������ӳɹ�����");
		}
		else
		{
			Toast.makeText(mContext, "�����ѶϿ����ӣ���", Toast.LENGTH_LONG).show();
			Log.i(TAG, "�����ѶϿ����ӣ���");
		}
	}
	//�ж������Ƿ�����
	public boolean isNetworkConnect(Context mContext)
	{
		boolean flag = false;
		//��ȡ�������ӹ������
		ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(mContext.CONNECTIVITY_SERVICE);
		//�õ������������ӵ���Ϣ
		NetworkInfo[] mInfo = manager.getAllNetworkInfo();
		if(mInfo != null){
		for(int i = 0 ; i < mInfo.length;i++)
		{
			//һһ�ж��Ƿ����Ѿ����ӵ�����
			State mState = mInfo[i].getState();
			if(mState == NetworkInfo.State.CONNECTED)
			{
				flag = true;
				return flag;
			}
		}
		}
		return flag;
	}
}
