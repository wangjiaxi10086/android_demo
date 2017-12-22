package com.androidleaf.fragmentdemo.fragment;

import java.util.ArrayList;

import com.androidleaf.fragmentdemo.activity.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

@SuppressLint("NewApi")
public class ActivityForCallBackFragmentA extends Fragment implements OnItemClickListener{

	private ListView mListView;
	ArrayAdapter<String> adapter = null;
	ArrayList<String> list = null;
	
	/**
	 * ����һ���¼��ص����������������û�����б�ѡ��Ĳ���
	 */
	public OnArticleSelectedListener mArticleSelectedListener;
	
	public interface OnArticleSelectedListener{
		public void onArticleSelected(int itemID,String title);
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			//������Activity����ǿ��ת����OnArticleSelectedListenerʵ������Ҫ��Ϊ��ȷ������Activityʵ�ּ����ӿ�
			mArticleSelectedListener = (OnArticleSelectedListener) activity;
		} catch (ClassCastException e) {
			// TODO: handle exception
			 throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
		}
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		/**
		 * ��ʼ�������б��ֵ
		 */
		list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		list.add("g");
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, list);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View inView = inflater.inflate(R.layout.fragment_activityforcallback_a, container, false);
		mListView = (ListView)inView.findViewById(R.id.callback_listview);
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(this);
		return inView;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		//��ѡ�е��б�ѡ��ID��Title���ݸ�ʵ�ּ����ӿڵ�����Activity
		mArticleSelectedListener.onArticleSelected(position,list.get(position));
	}
}
