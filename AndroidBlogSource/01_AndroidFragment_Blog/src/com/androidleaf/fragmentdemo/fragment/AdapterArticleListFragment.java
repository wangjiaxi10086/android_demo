package com.androidleaf.fragmentdemo.fragment;


import com.androidleaf.fragmentdemo.activity.AdapterArticleDetailActivity;
import com.androidleaf.fragmentdemo.activity.R;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

@SuppressLint("NewApi")
public class AdapterArticleListFragment extends ListFragment{

	/**
	 * �жϵ�ǰ���ص��Ƿ�Ϊ����Ļ����
	 */
	private boolean isScreenPad;
	/**
	 * ������¼�ϴ�ѡ�е���
	 */
	private int mCurrentPosition;
	
	/**
	 * �б��������
	 */
	public static String[] articleTitles = {
		"a",
		"b",
		"c",
		"d",
		"e",
		"f",
		"g",
	};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		//�������б�
		setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, articleTitles));
		
		View details = getActivity().findViewById(R.id.details_container);
		//����Ƿ�ʹ�ô���Ļ�ߴ�Ĳ���
		isScreenPad = details != null && details.getVisibility() == View.VISIBLE;
		
		if(savedInstanceState != null){
			//��ȡ�ϴ��뿪����ʱ�б�ѡ��ֵ
			mCurrentPosition = savedInstanceState.getInt("currentChoice", 0);
		}
		if(isScreenPad){
			//�����б�ѡ�е�ѡ�����
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			
			showDetails(mCurrentPosition);
		}
	}

	

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		showDetails(position);
	}

	
	/**
	 * �뿪����ʱ���浱ǰѡ�е�ѡ���״ֵ̬
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putInt("currentChoice", mCurrentPosition);
	}

	/**
	 * 
	 * @param index
	 */
	public void showDetails(int index){
		mCurrentPosition = index;
		if(isScreenPad){
			getListView().setItemChecked(index, true);
			
			AdapterArticleDetailFragment mDetailFragment = (AdapterArticleDetailFragment) getActivity().getFragmentManager().findFragmentById(R.id.details_container);
			//��mDetailFragmentΪ�ջ�ѡ�зǵ�ǰ��ʾ��Fragment
			if(mDetailFragment == null ||  mDetailFragment.showIndex() != index){
				mDetailFragment = AdapterArticleDetailFragment.newInstance(index);
				//�滻Fragmentʵ������
				getActivity().getFragmentManager().beginTransaction().replace(R.id.details_container, mDetailFragment)
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.commit();
			}
		}else{
			Intent mIntent = new Intent();
	    	mIntent.setClass(getActivity(), AdapterArticleDetailActivity.class);
	    	Bundle mBundle = new Bundle();
	    	mBundle.putInt("index", index);
	    	mIntent.putExtras(mBundle);
	    	getActivity().startActivity(mIntent);
		}
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}
