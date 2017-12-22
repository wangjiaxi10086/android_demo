package com.androidleaf.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AActivity extends BaseActivity {
	//�����ؼ�����
	private TextView mTextView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.i(TAG, "A --> onCreate()");
        //����ID�õ�����ÿؼ��Ķ���
        mTextView = (TextView)findViewById(R.id.getactivity_id_textview);
        
        /**
         * �õ�����ջ��IDֵ
         * �� android:launchMode="singleinstance"ʱ����ȡ��Activity���ڵ�Task
         */
        //mTextView.setText("This AActivity belong Task's ID: "+ this.getTaskId());
        
        /**
         * �� android:launchMode="standard"ʱ����ȡ��Activity��ʵ��
         */
        mTextView.setText("This AActivity Instance :"+ AActivity.this);
    }
    
    public void startAactivity(View view){
    	intentChange(AActivity.class);
    }
    
    public void startBactivity(View view){
    	intentChange(BActivity.class);
    }
    
    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		 Log.i(TAG, "A --> onResume()");
	}
    @Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		 Log.i(TAG, "A --> onStart()");
	}
    
    @Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		 Log.i(TAG, "A --> onRestart()");
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		 Log.i(TAG, "A --> onPause()");
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		 Log.i(TAG, "A --> onStop()");
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		 Log.i(TAG, "A --> onDestroy()");
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//��ȡ�ַ���
		String str = savedInstanceState.getString("parameters");
		//��ʾ�ַ���
		Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
		super.onRestoreInstanceState(savedInstanceState);
		 Log.i(TAG, "A --> onRestoreInstanceState(Bundle savedInstanceState)");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		//����һ���ַ���
		outState.putString("parameters", "Save somethings");
		super.onSaveInstanceState(outState);
		 Log.i(TAG, "A --> onSaveInstanceState(Bundle outState)");
	}
}