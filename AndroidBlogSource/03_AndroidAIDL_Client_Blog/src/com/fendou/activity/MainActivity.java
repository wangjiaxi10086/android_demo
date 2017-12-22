package com.fendou.activity;

import com.fendou.aidl.MyIPerson;
import com.fendou.aidl.Person;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	private static final String TAG = "ygh";
	//�����ؼ�����
	private Button mButton_boundservice;
	private Button mButton_unboundservice;
	private Button mButton_getmethod_button;
	private Button mButton_cleartext;
	private TextView mTextView_showresult;
	
	private MyIPerson myIPerson;
	private boolean isBound = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //����ID�õ�����ÿؼ��Ķ���
        mButton_boundservice = (Button)findViewById(R.id.bound_service_button);
        mButton_unboundservice = (Button)findViewById(R.id.unbound_service_button);
        mButton_getmethod_button = (Button)findViewById(R.id.getmethod_from_service_button);
        mButton_cleartext = (Button)findViewById(R.id.clear_text_textview);
        mTextView_showresult = (TextView)findViewById(R.id.showresult_textview);
        
        //Ϊ�ؼ������¼�����
        mButton_boundservice.setOnClickListener(this);
        mButton_unboundservice.setOnClickListener(this);
        mButton_getmethod_button.setOnClickListener(this);
        mButton_cleartext.setOnClickListener(this);
        mTextView_showresult.setOnClickListener(this);
    }
    
    //ʵ����ServiceConnection������Ҫ���ڽ��մ�Service�˴��ݹ�����ֵ
    ServiceConnection mServiceConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			Log.i(TAG, "onServiceDisconnected");
			isBound = false;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			Log.i(TAG, "onServiceConnected");
			//�õ�MyIPersonʵ��������ʹ���ø�ʵ��ȥ����Զ�̵�Service�еķ���
			myIPerson = MyIPerson.Stub.asInterface(service);
			isBound = true;	
		}
	};
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bound_service_button:
			boundService();
			break;
		case R.id.getmethod_from_service_button:
			getMethodFromService();
			break;
		case R.id.clear_text_textview:
			mTextView_showresult.setText("");
			break;
		case R.id.unbound_service_button:
			unBoundService();
			break;
		default:
			break;
		}
	}
	//��Service
	public void boundService()
	{
		//����Intentʵ������
		Intent mIntent = new Intent("com.fendou.MainService");
		//��Service
		this.bindService(mIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
	}
	//����Service�еķ���
	public void getMethodFromService()
	{
		Person mPerson = new Person();
		mPerson.setId(1);
		mPerson.setUserName("Jack");
		mPerson.setHeight(1.71);
		try {
			//Զ�̵���Service�˵ķ���
			String personInfo = myIPerson.getPerson(mPerson);
			mTextView_showresult.setText(personInfo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//���Service�İ�
	public void unBoundService()
	{
		if(isBound)
		{
			this.unbindService(mServiceConnection);
		}
	}
}