package com.androidleaf.actionbar.widget;

import com.androidleaf.actionbar.activity.R;
import com.androidleaf.actionbar.activity.R.drawable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ActionProvider;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MyActionProvider extends ActionProvider {

	private Context mContext;
	
	/**
	 * ΪAction Provider�Ӳ˵�����¼��������ص��ӿ�
	 */
	private SubMenuItemClickListener mSubMenuItemClickListener;
	
	public interface SubMenuItemClickListener{
		public void onSubMenuItem(int itemId);
	}
	
	public void setOnSubMenuItemClickListener(SubMenuItemClickListener mSubMenuItemClickListener){
		this.mSubMenuItemClickListener = mSubMenuItemClickListener;
	}
	
	MyProviderOnMenuItemClickListener mProviderOnMenuItemClickListener =
			new MyProviderOnMenuItemClickListener();

	public MyActionProvider(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	@Override
	public View onCreateActionView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasSubMenu() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void onPrepareSubMenu(SubMenu subMenu) {
		// TODO Auto-generated method stub
		//����Ӳ˵�ʵ��
		subMenu.clear();
		//��XML�ļ������MenuItem
		MenuInflater menuInflater = new MenuInflater(mContext);
		menuInflater.inflate(R.menu.provider_submenu, subMenu);
		//Ϊ����MenuItem�����¼�����
		for(int i = 0;i < subMenu.size();i++){
			subMenu.getItem(i).setOnMenuItemClickListener(mProviderOnMenuItemClickListener);
		}
	}

	private class MyProviderOnMenuItemClickListener implements
			OnMenuItemClickListener {

		@Override
		public boolean onMenuItemClick(MenuItem item) {
			// TODO Auto-generated method stub
			mSubMenuItemClickListener.onSubMenuItem(item.getItemId());
			return true;
		}

	}
}
