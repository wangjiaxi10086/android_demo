package com.androidleaf.fragmentdemo.fragment;

import com.androidleaf.fragmentdemo.activity.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi")
public class ExampleFirstFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		/**
		 * ������Ҫ��ʼ��һЩ��Fragment��ʹ�úͳ־û��ı�Ҫ���
		 */
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		/**
		 * ʹ��inflate()��������һ���Զ����Layout���֣��ò�����Fragment�ĸ�����view.
		 * inflate()������3������:
		 * 1.��Ҫ���ص�Layout Resource Id��
		 * 2.���� Layout�ĸ�ViewGroup,Ŀ���ǽ�Layout�ҿ���container�ϣ�
		 * 3.����ֵ��ָʾ�Ƿ�Layout���ŵ�ViewGroup�У�����һ��ָ��false��
		 * ��ΪLayout�Ѿ����ŵ�container�ϣ���Ϊtrue��ϵͳ��ΪLayout�½�һ��ViewGroup��Ϊ���Ŷ��󣬶��ࣻ
		 */
		View inView = inflater.inflate(R.layout.fragment_main, container, false);
		
		/**
		 * ������Ҫ�ǳ�ʼ��Layout�еĿؼ�����
		 */
		return inView;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		/**
		 * ���û��뿪��Fragment����ʱ��ϵͳ���ô˷�����������Ҫ����һЩ��Ҫ�־û��Ķ���״̬
		 */
	}
}
