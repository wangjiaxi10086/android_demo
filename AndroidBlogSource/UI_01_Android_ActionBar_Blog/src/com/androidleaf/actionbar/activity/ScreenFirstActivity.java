package com.androidleaf.actionbar.activity;

import com.androidleaf.actionbar.widget.MyActionProvider;
import com.androidleaf.actionbar.widget.MyActionProvider.SubMenuItemClickListener;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.MediaRouteActionProvider;
import android.app.SearchManager;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.ShareActionProvider;

public class ScreenFirstActivity extends BaseActivity implements SubMenuItemClickListener,OnClickListener{

	private Button mButtonSkip;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_first);
		
		mButtonSkip = (Button)findViewById(R.id.skip_screensecond_button);
		mButtonSkip.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.screen_first, menu);

		MenuItem searchMenuItem = menu.findItem(R.id.action_search); 
		//2������getActionView()��ȡSearchView����
		SearchView mSearchView = (SearchView) searchMenuItem.getActionView();
		//��ȡ�����Ĺ������
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		//��ǰ��ActivityΪ��������Activity
		mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		//��Ĭ�ϵķ�ʽչ��
		mSearchView.setIconifiedByDefault(true);
		//3��ִ�������������
		//......

		/**
		 * ��ȡMyActionProvider���󣬲������¼�����
		 */
		MenuItem writeMenuItem = menu.findItem(R.id.action_write);
		MyActionProvider myActionProvider = (MyActionProvider)writeMenuItem.getActionProvider();
		myActionProvider.setOnSubMenuItemClickListener(this);
		
		return true;
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onSubMenuItem(int itemId) {
		// TODO Auto-generated method stub
		switch (itemId) {
		case R.id.user_like:
			showInformation("��������");
			break;
		case R.id.user_fuck:
			showInformation("Fuck����");
			break;
		default:
			break;
		}
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		intentChange(ScreenSecondActivity.class);
	}
}
