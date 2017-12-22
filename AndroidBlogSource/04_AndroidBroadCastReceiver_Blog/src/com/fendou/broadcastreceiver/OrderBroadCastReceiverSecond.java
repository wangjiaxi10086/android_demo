package com.fendou.broadcastreceiver;

import com.fendou.utils.StaticContens;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class OrderBroadCastReceiverSecond extends BroadcastReceiver {

	private static final String TAG = "leaf";
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if(intent.getAction().equals(StaticContens.ORDER_NORMAL_BROADCASTRECEIVER))
		{
			
			//�õ���OrderBroadCastReceiverFirst���õĽ��ֵ
			String info = getResultData();
			
			info += "Second";
			Log.i(TAG, "OrderBroadCastReceiverSecond  "+ info);
			//���ý������
			setResultData(info);				
		}
	}
}
