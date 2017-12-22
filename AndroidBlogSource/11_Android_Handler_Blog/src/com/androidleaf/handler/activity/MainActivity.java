package com.androidleaf.handler.activity;

import com.androidleaf.handler.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity{
	
	private ImageView mImageView ;
	private static final String TAG = "androidleaf";
	
	private  String urlStr = "http://img.my.csdn.net/uploads/201408/25/1408936379_4781.png" ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mImageView = (ImageView)findViewById(R.id.dialog_imageview );
	}
	
	public void mainHanlderMainLooper(View view){
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
			 Bitmap mBitmap = HttpUtils.getBitmapFromNetWork(urlStr);
             //1��ʵ����һ��Message����
             Message message = Message.obtain();
             //��ͼƬ����ֵ��Message����
             message. obj = mBitmap;
             //�����ǩ
             message. what = 0;
             //3��������Ϣ�����߳��е�Handler
             mHandler .sendMessage(message);
			}
		}).start();
		
	}
	//2�������߳���ʵ����Handler����
	Handler  mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			 //4��������Ϣ��ִ��UI�ĸ��²���
            if (msg.obj != null)
            {
                Bitmap mBitmap = (Bitmap) msg.obj;
                mImageView .setImageBitmap(mBitmap);
            }
            else
            {
                Log. i( TAG, "Can not download the image source!!");
            }
        }
			
	};
	
	public void subHanlderMainLooper(View view){
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Bitmap mBitmap = HttpUtils.getBitmapFromNetWork(urlStr);
		        //ʵ����һ��Message����
		        Message message = Message.obtain();
		        //��ͼƬ����ֵ��Message����
		        message. obj = mBitmap;
				 Handler mHandler3 = new Handler(Looper.getMainLooper())
	             {
	                  @Override
	                  public void handleMessage(Message msg) {
	                       // TODO Auto-generated method stub
	                      super .handleMessage(msg);
	                       if (msg.obj != null)
	                      {
	                          Log. i( TAG, "ʹ�����̵߳�Handler�����̵߳�Looper" );
	                      }
	                 }
	             };
	            mHandler3.sendMessage(message);
			}
		}).start();
		
	}
	
	public void subHanlderSubLooper(View view){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Bitmap mBitmap = HttpUtils.getBitmapFromNetWork(urlStr);
				//1������Looper��MessageQueue����
                Looper. prepare();
			    //2��ʵ����Handler����
                Handler mHandler4 = new Handler()
                {
                     @Override
                     public void handleMessage(Message msg) {
                          // TODO Auto-generated method stub
                         super .handleMessage(msg);
                          //6��������Ϣ��ִ��UI�ĸ��²���
                          if (msg.obj != null)
                         {
                             Log. i( TAG, "ʹ�����̵߳�Handler�����̵߳�Looper" );
                         }
                    }
                };
                //3��ʵ����һ��Message����
                Message message = Message.obtain();
                //��ͼƬ����ֵ��Message����
                message. obj = mBitmap;
                //4��������Ϣ
                mHandler4.sendMessage(message);
                //5��������Ϣѭ������
                Looper. loop();
			}
		}).start();
	}
	
	public void welcome(View view){
		Intent mIntent = new Intent();
        mIntent.setClass(getApplicationContext(), WelcomeActivity.class );
        MainActivity.this.startActivity(mIntent);
	}
}
