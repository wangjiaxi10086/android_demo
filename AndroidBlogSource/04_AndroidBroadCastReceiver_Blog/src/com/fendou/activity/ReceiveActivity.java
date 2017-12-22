package com.fendou.activity;

import com.fendou.R;
import com.fendou.utils.StaticContens;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ReceiveActivity extends Activity {
	
	//�����ؼ�����
	private TextView mTextView;
	
	private static final String TAG = "leaf";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.receive);
		//����ID�õ�����ÿؼ��Ķ���
		mTextView = (TextView)findViewById(R.id.show_action_textview);
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//ʵ��������������
		IntentFilter mFilter = new IntentFilter();
		//���Action
		mFilter.addAction(StaticContens.SEND_USE_NORMAL_RECEIVER);
		mFilter.addAction(StaticContens.SEND_USE_STICKY_RECEIVER);
		//ע��㲥
		registerReceiver(mBroadcastReceiver, mFilter);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//���ע��
		unregisterReceiver(mBroadcastReceiver);
	}
	//ʵ����BroadcastReceiver����
	BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			//��ȡ���͹�����Action
			String action = intent.getAction();
			Log.i(TAG, "The Receive Action is: "+ action);
			mTextView.setText("The Receive Action is: "+ action);	
		}
	};
}
