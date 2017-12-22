package com.fendou.activity;

import com.fendou.R;
import com.fendou.service.MessengerService;
import com.fendou.service.MyIntentServiceExecuteDemo;
import com.fendou.service.MyServiceDemo;
import com.fendou.service.MyServiceExecuteDemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	
	//�����ؼ�����
	private Button mButton_startservice;
	private Button mButton_stopservice;
	private Button mButton_intentservice_execute;
	private Button mButton_service_execute;
	private Button mButton_start_main_execute;
	private Button mButton_messenger_boundservice;
	private Button mButton_messenger_unboundservice;
	private Button mButton_boundservice;
	private Button mButton_unboundservice;
	private TextView mCallbackText;
	
	//����Binder����
	private MyServiceDemo.MyBinder myBinder;
	private static final String TAG = "androidLeaf";
	private boolean mIsBound = false;
	Messenger mService = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
      
        //����ID�õ�����ÿؼ��Ķ���
        mButton_startservice = (Button)findViewById(R.id.startservice_button);
        mButton_stopservice = (Button)findViewById(R.id.stopservice_button);
        mButton_intentservice_execute = (Button)findViewById(R.id.intentservice_execute_button);
        mButton_start_main_execute = (Button)findViewById(R.id.start_main_execute);
        mButton_service_execute = (Button)findViewById(R.id.service_execute_button);
        mButton_messenger_boundservice = (Button)findViewById(R.id.messenger_boundservice_button);
        mButton_messenger_unboundservice = (Button)findViewById(R.id.messenger_unboundservice_button);
        mButton_boundservice = (Button)findViewById(R.id.boundservice_button);
        mButton_unboundservice = (Button)findViewById(R.id.unboundservice_button);
        mCallbackText = (TextView)findViewById(R.id.callBack_textview);
        //ΪButton�����¼�����
        mButton_startservice.setOnClickListener(this);
        mButton_stopservice.setOnClickListener(this);
        mButton_intentservice_execute.setOnClickListener(this);
        mButton_service_execute.setOnClickListener(this);
        mButton_start_main_execute.setOnClickListener(this);
        mButton_messenger_boundservice.setOnClickListener(this);
        mButton_messenger_unboundservice.setOnClickListener(this);
        mButton_boundservice.setOnClickListener(this);
        mButton_unboundservice.setOnClickListener(this);
        
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		int viewID = v.getId();
		
		switch (viewID) {
		case R.id.startservice_button:
			startServiceMethod();
			break;
		case R.id.stopservice_button:
			stopServiceMethod();
			break;
		case R.id.intentservice_execute_button:
			intentServiceExecute();
			break;
		case R.id.service_execute_button:
			serviceExecute();
			break;
		case R.id.start_main_execute:
			startMainExecute();
			break;
		case R.id.messenger_boundservice_button:
			messengerBoundServiceMethod();
			break;
		case R.id.messenger_unboundservice_button:
			messengerUnBoundServiceMethod();
			break;
		case R.id.boundservice_button:
			boundServiceMethod();
			break;
		case R.id.unboundservice_button:
			unBoundServiceMethod();
			break;
		default:
			break;
		}
			
	}
	//����Service
	public void startServiceMethod()
	{
		Intent mIntent = new Intent(this,MyServiceDemo.class);
		
		this.startService(mIntent);
	}
	//ֹͣService
	public void stopServiceMethod()
	{
		Intent mIntent = new Intent(this,MyServiceDemo.class);
		this.stopService(mIntent);
	}
	//ʹ��Intentserviceִ�к�ʱ����
	public void intentServiceExecute()
	{
		//����Intent����
		Intent mIntent = new Intent(this,MyIntentServiceExecuteDemo.class);
		//����IntentService
		this.startService(mIntent);
	}
	//ʹ��IntentServiceִ�к�ʱ����
	public void serviceExecute()
	{
		//����Intent����
		Intent mIntent = new Intent(this,MyServiceExecuteDemo.class);
		//����IntentService
		this.startService(mIntent);
		
	}
	//�������߳��еĺ�ʱ����(�������߳�˯����ģ��)
	public void startMainExecute()
	{
		waitForMinutes();
	}
	//��ʱ��������
	public void waitForMinutes()
	{	
		for(int  i = 0; i < 50;i++)
		{
			Log.i(TAG, "���߳���ִ�С�������������"+ (i + 1));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//����һ���̳�Handler������IncomingHandler�����ڽ���Service��̨���͹�����Message(������Ĺ�ϣֵ)
	 class IncomingHandler extends Handler {
         @Override
         public void handleMessage(Message msg) {
             switch (msg.what) {
                 case MessengerService.MSG_SET_VALUE:
                     mCallbackText.setText("Received from service: " + msg.arg1);
                     break;
                 default:
                     super.handleMessage(msg);
             }
         }
     }
	 //ʵ����Messenger��IncomingHandler���󲢽�������֮��Ĺ���
	 final Messenger mMessenger = new Messenger(new IncomingHandler());
	 
	 //��Service 
	public void messengerBoundServiceMethod()
	{
		this.bindService(new Intent(MainActivity.this, MessengerService.class), mServiceConnection2, Context.BIND_AUTO_CREATE);
		 mIsBound = true;
		 mCallbackText.setText("Binding.");
	}
	//���Service��
	public void messengerUnBoundServiceMethod()
	{
		 if (mIsBound) {
             // If we have received the service, and hence registered with
             // it, then now is the time to unregister.
             if (mService != null) {
                 try {
                	 //���ͻ���ǰ̨��Messenger������Service��̨����̨�����ж�Ӧ��Messenger�Ķ���ɾ��
                     Message msg = Message.obtain(null,
                             MessengerService.MSG_UNREGISTER_CLIENT);
                     msg.replyTo = mMessenger;
                     mService.send(msg);
                 } catch (RemoteException e) {
                     // There is nothing special we need to do if the service
                     // has crashed.
                 }
             }
             
             // Detach our existing connection.
             unbindService(mServiceConnection2);
             mIsBound = false;
            mCallbackText.setText("Unbinding.");
		 }
	}
	//��Service
	public void boundServiceMethod()
	{
		Intent mIntent = new Intent(this,MyServiceDemo.class);
		this.bindService(mIntent, mServiceConnection, BIND_AUTO_CREATE);
		mIsBound = true;
	}
	//���Service��
	public void unBoundServiceMethod()
	{	if(mIsBound)
		{
			this.unbindService(mServiceConnection);
			Toast.makeText(getApplicationContext(), "unBound Service", Toast.LENGTH_LONG).show();
		}
	}
	
	//ʹ��MyServiceDemo,ʵ����ServiceConnection���󣬽���Activity���̨Service������
	ServiceConnection mServiceConnection = new ServiceConnection() {
		
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			Log.i(TAG, "onServiceDisconnected()");
		}
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub		
			Log.i(TAG, "onServiceConnected()");
			myBinder = (MyServiceDemo.MyBinder)service;
			myBinder.getString("Mr Li");
		}
	};
	
	//ʹ��MessengerService��ʵ����ServiceConnection���󣬽���Activity�ͺ�̨Service������
	ServiceConnection mServiceConnection2 = new ServiceConnection() {
		
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			 // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            mService = null;
            mCallbackText.setText("Disconnected.");

            // As part of the sample, tell the user what happened.
            Toast.makeText(MainActivity.this, "remote_service_disconnected",
                    Toast.LENGTH_SHORT).show();
			
		}
		
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			//�õ���Service���͹�����IBinder���󲢳�ʼ����Messenger
			 mService = new Messenger(service);
			
			 mCallbackText.setText("Attached.");
			// We want to monitor the service for as long as we are
            // connected to it.
            try {
            	//���ͻ���ǰ̨��Messenger��������Service����֪ͨ��̨��Messenger���ϳ��иö���
                Message msg = Message.obtain(null,
                        MessengerService.MSG_REGISTER_CLIENT);
                msg.replyTo = mMessenger;
                //����Message
                mService.send(msg);
                
                // Give it some value as an example.
                //��������Ĺ�ϣֵ���͸�Service��̨
                msg = Message.obtain(null,
                        MessengerService.MSG_SET_VALUE, this.hashCode(), 0);
                mService.send(msg);
            } catch (RemoteException e) {
                // In this case the service has crashed before we could even
                // do anything with it; we can count on soon being
                // disconnected (and then reconnected if it can be restarted)
                // so there is no need to do anything here.
            }
            // As part of the sample, tell the user what happened.
            Toast.makeText(MainActivity.this,"remote_service_connected",
                    Toast.LENGTH_SHORT).show();
		}
	};
}