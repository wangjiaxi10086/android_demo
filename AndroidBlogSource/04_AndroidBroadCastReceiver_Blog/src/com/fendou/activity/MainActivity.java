package com.fendou.activity;

import com.fendou.R;
import com.fendou.utils.StaticContens;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */

	//�����ؼ�����
	private Button mButton_normalbroadcast;
	private Button mButton_orderbroadcast;
	private Button mButton_send_use_normalreceiver;
	private Button mButton_send_use_stickyreceiver;
	private Button mButton_startactivity;
	
	private static final String TAG = "leaf";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //����ID�õ�����ÿؼ��Ķ���
        mButton_normalbroadcast = (Button)findViewById(R.id.normal_broadcastreceiver);
        mButton_orderbroadcast = (Button)findViewById(R.id.order_broadcastreceiver);
        mButton_send_use_normalreceiver = (Button)findViewById(R.id.send_use_normal_broadcastreceiver);
        mButton_send_use_stickyreceiver = (Button)findViewById(R.id.send_use_sticky_broadcastreceiver);
        mButton_startactivity = (Button)findViewById(R.id.startactivity);
        
        mButton_send_use_normalreceiver.setOnClickListener(this);
        mButton_send_use_stickyreceiver.setOnClickListener(this);
        mButton_startactivity.setOnClickListener(this);
        mButton_normalbroadcast.setOnClickListener(this);
        mButton_orderbroadcast.setOnClickListener(this);
        
        //ע��BroadcastReceiver
        
      
    }

    public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.normal_broadcastreceiver:
			sendBroadcastReceiverMethod();
			break;
		case R.id.order_broadcastreceiver:
			sendOrderBroadcastReceiverMethod();
			break;
		case R.id.send_use_normal_broadcastreceiver:
			sendUseNormalBroadcastReceiverMethod();
			break;
		case R.id.send_use_sticky_broadcastreceiver:
			sendUseStickyBroadcastReceiverMethod();
			break;
		case R.id.startactivity:
			startReceiveActivity();
			break;
		default:
			break;
		}
	}
    
   /* //ע��BroadcastReceiver����
    public void filter()
    {
    	IntentFilter mFilter = new IntentFilter();
    	mFilter.addAction("com.fendou.NORMAL_BROADCASTRECEIVER");
    	registerReceiver(mBroadcastReceiver, mFilter);
    }
    
    //ʵ����BroadcastReceiver
    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if(intent.getAction().equals("com.fendou.NORMAL_BROADCASTRECEIVER"))
			{
				//dosomething
			}
		}
	};*/
    //����һ����ͨ�㲥
    public void sendBroadcastReceiverMethod()
    {
    	//ʵ����һ��Intent����
    	Intent mIntent = new Intent();
    	//mIntent.setAction(StaticContens.NORMAL_BROADCASTRECEIVER);
    	mIntent.setAction(StaticContens.ORDER_NORMAL_BROADCASTRECEIVER);
    	mIntent.putExtra("info", "Hello");
    	//������ͨ�㲥
    	this.sendBroadcast(mIntent);
    }
    //����һ������㲥
    public void sendOrderBroadcastReceiverMethod()
    {
    	//ʵ����һ��Intent����
    	Intent mIntent = new Intent();
    	mIntent.setAction(StaticContens.ORDER_NORMAL_BROADCASTRECEIVER);
    	mIntent.putExtra("info", "Hello");
    	 //��������㲥
    	this.sendOrderedBroadcast(mIntent, "com.fendou.order_broadcastreceiver_permission");
    }
    //����ճ�Թ㲥
    public void sendUseStickyBroadcastReceiverMethod()
    {
    	//ʵ����Intent����
    	Intent mIntent = new Intent();
    	//����Action
    	mIntent.setAction(StaticContens.SEND_USE_STICKY_RECEIVER);
    	//���͹㲥
    	this.sendStickyBroadcast(mIntent);
    }
    //������ͨ�㲥
    public void sendUseNormalBroadcastReceiverMethod()
    {
    	//ʵ����Intent����
    	Intent mIntent = new Intent();
    	//����Action
    	mIntent.setAction(StaticContens.SEND_USE_NORMAL_RECEIVER);
    	//���͹㲥
    	this.sendBroadcast(mIntent);
    }
    //����ReceiveActivity
    public void startReceiveActivity()
    {
    	//ʵ����Intent����
    	Intent mIntent = new Intent();
    	mIntent.setClass(getApplicationContext(), ReceiveActivity.class);
    	//����Activity
    	this.startActivity(mIntent);
    }
  
}