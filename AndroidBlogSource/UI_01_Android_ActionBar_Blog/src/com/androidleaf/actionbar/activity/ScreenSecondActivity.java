package com.androidleaf.actionbar.activity;

import java.lang.reflect.Method;

import com.androidleaf.actionbar.fragment.ApplicationFragment;
import com.androidleaf.actionbar.fragment.GameFragment;
import com.androidleaf.actionbar.listener.ActionBarTabListener;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.TaskStackBuilder;
import android.app.ActionBar.OnNavigationListener;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TabHost;
import android.widget.TextView;

public class ScreenSecondActivity extends BaseActivity {

	private SpinnerAdapter mSpinnerAdapter;
	private TextView mTextView;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_second);
		
		//�Ƿ�����ActionBarͼ��ĵ�������
		mActionBar.setDisplayHomeAsUpEnabled(true);
		
		/*mActionBar.setTitle("");
		 * //1������Action Bar�ĵ���ģʽ
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		//2����ʼ���������������������б�����
		mSpinnerAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.navigation_list_array,
				android.R.layout.simple_spinner_dropdown_item);
		//4��ΪActionBar����Adapter��Ϊ�������¼�����
		mActionBar.setListNavigationCallbacks(mSpinnerAdapter, new MyOnNavigationListener());
		//mTextView = (TextView)findViewById(R.id.screen_second_textview);
		 */	
		
		
	    //2�����õ���ģʽ
	    mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    //3������ActionBar.Tab����Ϊ������ActionBar.TabListener��������������Tab�ı������ƻ�ͼ��
		Tab mTabApp = mActionBar
				.newTab()
				.setText("Ӧ��")
				.setTabListener(new ActionBarTabListener<ApplicationFragment>(this,
								"application", ApplicationFragment.class));
		Tab mTabGame = mActionBar
				.newTab()
				.setText("��Ϸ")
				.setTabListener(new ActionBarTabListener<GameFragment>(this, "game",
								GameFragment.class));
		//4����ActionBar.TAB������ӵ�ActionBar��
		mActionBar.addTab(mTabApp);
		mActionBar.addTab(mTabGame);
		//mActionBar.addTab(mTabApp);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.screen_second, menu);
		return true;
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		// TODO Auto-generated method stub
		if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {  
	        if (menu.getClass().getSimpleName().equals("MenuBuilder")) {  
	            try {  
	                Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);  
	                m.setAccessible(true);  
	                m.invoke(menu, true);  
	            } catch (Exception e) {  
	            }  
	        }  
	    }  
		return super.onMenuOpened(featureId, menu);
	}
	
	@SuppressLint("NewApi")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		//ActionBar Home��ID
		case android.R.id.home:
			//��ȡ��ת������Activity��Intent
			Intent mIntent = NavUtils.getParentActivityIntent(this);
			//�жϸ���Activity�ͱ�Activity�Ƿ�ͬ����һ��Task��true��ֱ�Ӹ���Intent��ת���������´���һ��Task
			if (NavUtils.shouldUpRecreateTask(this, mIntent)) {  
		           TaskStackBuilder.create(this)  
		                    .addNextIntentWithParentStack(mIntent)  
		                    .startActivities();  
		       } else {  
		    	   mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
		           NavUtils.navigateUpTo(this, mIntent);  
		       }  
			break;
		default:
			break;
		}
		return true;
	}
	
	 //3�� ����Action Bar���������¼�������
	private class MyOnNavigationListener implements OnNavigationListener{

		@Override
		public boolean onNavigationItemSelected(int itemPosition, long itemId) {
			// TODO Auto-generated method stub
			switch (itemPosition) {
			case 0:
				mTextView.setText("ȫ��");
				break;
			case 1:
				mTextView.setText("������Ϣ");
				break;
			case 2:
				mTextView.setText("����");
				break;
			case 3:
				mTextView.setText("����");
				break;
			default:
				break;
			}
			return true;
		}
	}
}
