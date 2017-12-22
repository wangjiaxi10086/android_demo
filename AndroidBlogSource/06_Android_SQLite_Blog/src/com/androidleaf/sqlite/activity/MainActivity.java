package com.androidleaf.sqlite.activity;

import java.util.ArrayList;

import com.androidleaf.sqlite.R;
import com.androidleaf.sqlite.adapter.PersonListAdapter;
import com.androidleaf.sqlite.database.PersonTableBusiness;
import com.androidleaf.sqlite.entity.Person;

import android.os.Bundle;
import android.provider.MediaStore;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class MainActivity extends ListActivity {

	PersonTableBusiness mPersonTableBusiness;
	
	public static final int INSERT_ACTION = 0;
	public static final int UPDATE_ACTION = 1;
	public static final int DELETE_ACTION = 2;
	
	PersonListAdapter mAdapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	
	/**
	 * ��ʼ�����ݣ�չ�������б�
	 */
	public void init(){
		mPersonTableBusiness = new PersonTableBusiness(this);
		//���һЩĬ������
		mPersonTableBusiness.insertListPerson(initData());
		mAdapter = new PersonListAdapter(this,mPersonTableBusiness.queryAllPersons());
		//������������
		setListAdapter(mAdapter);
		//��ListView�б���ӵ�������Menu��
		this.registerForContextMenu(getListView());
	}
	
	/**
	 * ��������
	 */
	public void insertPerson(){
		createDialog("���", INSERT_ACTION, -1,null);
	}
	
	/**
	 * ɾ������
	 * @param id �б�ID
	 */
	public void deletePerson(final int id){
		AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this)
		.setTitle("�Ƿ�ɾ����")
		.setPositiveButton("ȷ��", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				mPersonTableBusiness.deletePersonById(id);
				//������������
				mAdapter.setData(mPersonTableBusiness.queryAllPersons());
				//���������б�
				mAdapter.notifyDataSetChanged();
			}
		})
		.setNegativeButton("ȡ��", null);
		mBuilder.show();
	}
	
	/**
	 * ��������
	 * @param id �б�ID
	 */
	public void updatePerson(int id,Person mPerson){
		createDialog("����", UPDATE_ACTION, id,mPerson);
	}
	
	/**
	 * ��ʼ��һЩ��������
	 * @return
	 */
	public ArrayList<Person> initData(){
		ArrayList<Person> mList = new ArrayList<Person>();
		for(int i = 0; i < 20;i++){
			Person mPerson = new Person(-1,"Steve P.Jobs "+ i, "45936455"+ i +"@qq.com", (170 + i));
			mList.add(mPerson);
		}
		return mList;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(0, 1, Menu.NONE, "���");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		insertPerson();
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		menu.setHeaderTitle("�ļ�����");
		menu.add(0, 1, Menu.NONE, "�޸�");
		menu.add(0, 2, Menu.NONE, "ɾ��");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// �õ���ǰ��ѡ�е�item��Ϣ
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		int id = -1;
		Person mPerson = null;
		if(mAdapter != null){
			mPerson = (Person)mAdapter.getItem(menuInfo.position);
			id = mPerson.getId();
		}
		
		switch (item.getItemId()) {
		case UPDATE_ACTION:
			updatePerson(id,mPerson);
			break;
		case DELETE_ACTION:
			deletePerson(id);
			break;
		default:
			break;
		}
		return true;
	}
	
	/**
	 * ����Dialogʵ��
	 * @param title ����Dialog����
	 * @param action ���ò������� ��UPDATE_ACTIONΪ���²�����INSERT_ACTIONΪ�������
	 * @param id ��ѡ�е�ѡ��ID
	 */
	public void createDialog(String title,final int action,final int id,final Person mPerson){
		
		final View mView = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_operation, null);
		String titlName = getResources().getString(R.string.operation, new Object[]{title});
		
		final EditText mEditText_name = (EditText)mView.findViewById(R.id.name);
		final EditText mEditText_email = (EditText)mView.findViewById(R.id.email);
		final EditText mEditText_height = (EditText)mView.findViewById(R.id.height);
		
		//��ʼ������
		if(action == UPDATE_ACTION){
			mEditText_name.setText(mPerson.getName());
			mEditText_email.setText(mPerson.getEmail());
			mEditText_height.setText(String.valueOf(mPerson.getHeight()));
		}
		
		//����Dialogʵ������
		AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this)
		.setTitle(titlName)
		.setView(mView)
		.setPositiveButton("ȷ��", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				String name = mEditText_name.getText().toString().trim();
				String email = mEditText_email.getText().toString().trim();
				float height = Float.parseFloat(mEditText_height.getText().toString().trim());
				
				if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && 
						!TextUtils.isEmpty(mEditText_height.getText().toString().trim())){
					switch (action) {
					case INSERT_ACTION:
						mPersonTableBusiness.insertPerson(name, email, height);
						break;
					case UPDATE_ACTION:
						mPersonTableBusiness.updatePerson(id, name, email, height);
						break;
					default:
						break;
					}
					//������������
					mAdapter.setData(mPersonTableBusiness.queryAllPersons());
					//���������б�
					mAdapter.notifyDataSetChanged();
				}
			}
		})
		.setNegativeButton("ȡ��", null);
		mBuilder.show();
	}
	
	/**
	 * ���˳���ǰ����ʱ���ر����ݿ�
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mPersonTableBusiness.closeDB();
	}
}
