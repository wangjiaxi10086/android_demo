package com.fendou.broadcastreceiver;

import com.fendou.utils.StaticContens;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NormalBroadReceiver extends BroadcastReceiver {

	
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		
		if(arg1.getAction().equals(StaticContens.NORMAL_BROADCASTRECEIVER))
		{
			//showһ��֪ͨ
			//StaticContens.showNotification(arg0,"�¹㲥","��������һ���¹㲥");
		}
	}
	
}
