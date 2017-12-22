package com.androidleaf.fragmentdemo.activity;

import com.androidleaf.fragmentdemo.fragment.ActivityForCallBackFragmentB;
import com.androidleaf.fragmentdemo.fragment.AddFragmentFirst;
import com.androidleaf.fragmentdemo.fragment.AddFragmentSecond;
import com.androidleaf.fragmentdemo.fragment.ActivityForCallBackFragmentA.OnArticleSelectedListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Toast;

/**
 * 
 * @author AndroidLeaf
 *	��ʾ����Ҫʵ�������Ķ�����
 */
public class ActivityForCallBackActivity extends Activity implements OnArticleSelectedListener{

	private ActivityForCallBackFragmentB mFragmentB;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activityforcallback);
		//��ȡActivityForCallBackFragmentB������ϸ���ݶ���
		mFragmentB = (ActivityForCallBackFragmentB) getFragmentManager().findFragmentById(R.id.activityforcallback_fragment_b);
	}

	@Override
	public void onArticleSelected(int itemID,String title) {
		// TODO Auto-generated method stub
		//��ϸҳ��ʾ����Title
		mFragmentB.setContent(title);
	}
}
