package com.androidleaf.actionbar.listener;

import android.annotation.SuppressLint;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

@SuppressLint("NewApi")
public class ActionBarTabListener<T extends Fragment> implements TabListener {

	private Fragment mFragment;
	private Class<T> mFragmentClass;
	private String fragmentTag;
	private Activity mActivity;
	
	public ActionBarTabListener(Activity mActivity,String fragmentTag,Class<T> mFragmentClass){
		this.mActivity = mActivity;
		this.mFragmentClass = mFragmentClass;
		this.fragmentTag = fragmentTag;
	}
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		//���Fragment��ʵ����null�������´���
		if(mFragment == null){
			//����������ʼ��Fragment����
			mFragment = Fragment.instantiate(mActivity, mFragmentClass.getName());
			ft.add(android.R.id.content, mFragment,fragmentTag);
		}else{
			//��ѡ�е�Tab����Ӧ��Fragment��Ϊnullʱ��������Tab������
			ft.attach(mFragment);
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		//��Tab���л�����һ��Tab�������ڵ�ǰTab��Fragment�����Ϊnull����������
		if(mFragment != null){
			ft.detach(mFragment);
		}
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

}
