package com.andriodleaf.json.activity;

import java.util.ArrayList;
import java.util.Map;

import com.andriodleaf.json.entity.Person;
import com.andriodleaf.json.https.MyAsynctask;
import com.andriodleaf.json.https.MyAsynctask.HttpDownloadedListener;
import com.andriodleaf.json.tools.Constants;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public abstract class BaseActivity extends ListActivity implements HttpDownloadedListener{

	 public ProgressDialog mProgressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_json);
		//��ʼ������
		initData();
		
	}

	public void initData(){
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setTitle("���ڼ�����.....");
		mProgressDialog.show();
		downloadData(Constants.GET_PERSON_PATH,R.id.json_person);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TOdO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TOdO Auto-generated method stub
		String urlStr = null;
		int typeId = -1;
		switch (item.getItemId()) {
		case R.id.json_person:
			urlStr = Constants.GET_PERSON_PATH;
			typeId = R.id.json_person;
			break;
		case R.id.json_string:
			urlStr = Constants.GET_LISTSTRING_PATH;
			typeId = R.id.json_string;
		    break;
		case R.id.json_list_person:
			urlStr = Constants.GET_LISTPERSON_PATH;
			typeId = R.id.json_list_person;
			break;
		case R.id.json_listmap_person:
			urlStr = Constants.GET_LISTMAPPERSON_PATH;
			typeId = R.id.json_listmap_person;
			break;
		default:
			break;
		}
		downloadData(urlStr,typeId);
		mProgressDialog.show();
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * ����Url��ַ��������
	 * @param urlStr Url��ַ
	 * @param typeId ���ص�����ID������ʹ�ÿؼ�ID
	 */
	public void downloadData(String urlStr,int typeId){
		MyAsynctask mAsynctask = new MyAsynctask();
		mAsynctask.setOnHttpDownloadedListener(this);
		mAsynctask.execute(urlStr,typeId);
	}
	
	public void bindData(ArrayList<String> mList){
		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, mList);
		setListAdapter(mAdapter);
		if(mProgressDialog.isShowing()){
			mProgressDialog.dismiss();
		}
	}
	
	/**
	 * ��Person����ת��ΪString����
	 * @param mList
	 * @return
	 */
	public ArrayList<String> personsToString(ArrayList<Person> mList){
		ArrayList<String> mStrings = new ArrayList<String>();
		for(int i = 0;i < mList.size();i++){
			mStrings.add(mList.get(i).toString());
		}
		return mStrings;
	}
	
	/**
	 * ��Persons Map����װ��ΪString����
	 * @param listMap
	 * @return
	 */
	public ArrayList<String> listmappersonsToString(ArrayList<Map<String, Person>> listMap){
		ArrayList<String> mStrings = new ArrayList<String>();
		for(Map<String, Person> map: listMap){
			 for(Map.Entry<String, Person> entry: map.entrySet()){
			    	mStrings.add(entry.getKey() +":"+ entry.getValue().toString());
			    }
		}
		return mStrings;
	}
}
