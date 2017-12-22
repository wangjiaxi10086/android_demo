package com.androidleaf.fragmentdemo.activity;

import com.androidleaf.fragmentdemo.fragment.AddFragmentFirst;
import com.androidleaf.fragmentdemo.fragment.AddFragmentSecond;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class AddByDynamicActivity extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addbydynamic);
		
		//��ȡ��Ļ���
		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
		//1.���������Ļ�ȡFragmentManager����
		FragmentManager manager = this.getFragmentManager();
	
		if(mDisplayMetrics.widthPixels < mDisplayMetrics.heightPixels){
			//2.ʹ�û�ȡ����manager������һ������
			FragmentTransaction mFragmentTransaction01 = manager.beginTransaction();
			//3.�滻Fragment
			mFragmentTransaction01.replace(R.id.container, new AddFragmentFirst());
			//4.�ύ����
			mFragmentTransaction01.commit();
		}else{
			FragmentTransaction mFragmentTransaction02 = manager.beginTransaction();
			mFragmentTransaction02.replace(R.id.container, new AddFragmentSecond()).commit();
		}
		
	}
}
