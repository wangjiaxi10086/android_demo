package com.fendou.broadcastreceiver;

import com.fendou.utils.StaticContens;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AirPlaneModeChangeBroadCastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		StaticContens.showNotification(context,"֪ͨ","����ģʽ״̬�ı��ˣ���");
	}

}
