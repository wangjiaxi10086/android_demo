package com.androidleaf.actionbar.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

public class BaseActivity extends Activity {

	ActionBar mActionBar = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		/**
		 * ��ȡActionBarʵ����������Android3.0֮ǰ�İ汾����
		 * ����getSupportActionBar()������ȡActionBarʵ������
		 */
		mActionBar = getActionBar();
		//����ActionBar
		//mActionBar.hide();
		//��ʾActionBar
		mActionBar.show();
	}
	
	public <T> void intentChange(Class<T> mClass){
    	Intent mIntent = new Intent();
    	mIntent.setClass(getApplicationContext(), mClass);
    	this.startActivity(mIntent);
	}
	
	public void showInformation(String information){
		Toast.makeText(getApplicationContext(), information, Toast.LENGTH_SHORT).show();
	}
	
}
