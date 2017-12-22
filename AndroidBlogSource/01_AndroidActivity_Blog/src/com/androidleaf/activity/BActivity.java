package com.androidleaf.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Android�Ĵ����֮Activity
 * @author AndroidLeaf
 */
public class BActivity extends BaseActivity {
    /** Called when the activity is first created. */
	//�����ؼ�����
	private TextView mTextView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_main);
        Log.i(TAG, "B --> onCreate()");
        
        //����ID�õ�����ÿؼ��Ķ���
        mTextView = (TextView)findViewById(R.id.getactivity_id_textview2);
        /**
         * �õ�����ջ��IDֵ
         * �� android:launchMode="singleinstance"ʱ����ȡ��Activity���ڵ�Task
         */
        //mTextView.setText("This BActivity belong Task's ID: "+ this.getTaskId());
        /**
         * �� android:launchMode="standard"ʱ����ȡ��Activity��ʵ��
         */
        mTextView.setText("This BActivity Instance :"+ BActivity.this);
    }
    
    public void startAactivity(View view){
    	intentChange(AActivity.class);
    }
    
    public void startCactivity(View view){
    	intentChange(CActivity.class);
    }

    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		 Log.i(TAG, "B --> onResume()");
	}
    @Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		 Log.i(TAG, "B --> onStart()");
	}
    
    @Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		 Log.i(TAG, "B --> onRestart()");
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		 Log.i(TAG, "B --> onPause()");
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		 Log.i(TAG, "B --> onStop()");
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		 Log.i(TAG, "B --> onDestroy()");
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		 Log.i(TAG, "B --> onRestoreInstanceState(Bundle savedInstanceState)");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		 Log.i(TAG, "B --> onSaveInstanceState(Bundle outState)");
	}
}