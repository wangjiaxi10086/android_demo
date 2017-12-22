package com.androidleaf.contentprovider.activity;

import com.androidleaf.contentprovider.R;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.UserDictionary;

public class ImagesProviderActivity extends ListActivity {

	ImagesProviderItemAdapter mAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_images_provider);
		init();
	}

	
	 /** ------------------����ImagesProvider�е����ݣ���ʼ�������б� ----------------------*/
	public void init() {
		mAdapter = new ImagesProviderItemAdapter(getApplicationContext(),
				query(), 0);
		// ��������
		setListAdapter(mAdapter);
		// ��ListView����ע�ᵽ��������
		this.registerForContextMenu(getListView());
	}

	/**
	 * ����ϵͳ�ṩ��Images Provider�е�ͼƬ��Ϣ
	 */
	@SuppressLint("NewApi")
	public Cursor query() {
		Cursor mCursor = getContentResolver().query(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				new String[] { MediaStore.Images.Media._ID,
						MediaStore.Images.Media.TITLE,
						MediaStore.Images.Media.DATE_TAKEN }, null, null,
				"_id desc");
		
		return mCursor;
	}

	/** ----------------------����_IDɾ��ImagesProvider�е����� ---------------------- */
	
	
	/**
	 * ɾ��ϵͳ�ṩ��Images Provider�е�ͼƬ��Ϣ
	 */
	public void deleteData(final String _id) {
		AlertDialog.Builder mDialog = new AlertDialog.Builder(
				ImagesProviderActivity.this).setMessage("�Ƿ�ɾ�����ݣ�")
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						// ִ��ɾ������
						int numberOfRows = delete(_id);
						// ���²�ѯImages Provider�е����ݲ�ΪAdapter���°�����
						mAdapter.changeCursor(query());
						// ���������б�
						mAdapter.notifyDataSetChanged();
						Toast.makeText(ImagesProviderActivity.this,
								"ɾ���� " + numberOfRows + "�����ݣ���",
								Toast.LENGTH_LONG).show();
					}
				}).setNegativeButton("ȡ��", null);
		mDialog.show();
	}

	/**
	 * ����_id����ɾ������
	 * 
	 * @param _id
	 *            Primary Key(����ֵ)
	 * @return numberOfRows ɾ�����ݵ�����
	 */
	public int delete(String _id) {
		String where = MediaStore.Images.Media._ID + "= ?";
		String[] selectionArgs = { _id };
		int numberOfRows = getContentResolver().delete(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, where,
				selectionArgs);
		return numberOfRows;
	}

	
	/** ----------------------����_ID����Images Provider�е�����---------------------- */
	
	/**
	 * ����ϵͳ�ṩ��Images Provider�е�ͼƬ��Ϣ,�����޸�ͼƬ������
	 */
	public void updateData(final String _id) {
		final View mView = LayoutInflater.from(ImagesProviderActivity.this)
				.inflate(R.layout.images_provider_update, null);
		AlertDialog.Builder mDialog = new AlertDialog.Builder(
				ImagesProviderActivity.this).setTitle("���²���").setView(mView)
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						EditText mEditText_name = (EditText) mView
								.findViewById(R.id.images_name_edittext);
						int numberOfRows = 0;
						if (!TextUtils.isEmpty(mEditText_name.getText())) {
							// ִ�и��²���
							numberOfRows = update(_id, mEditText_name.getText()
									.toString());
						} else {
							Toast.makeText(ImagesProviderActivity.this,
									"���Ʋ���Ϊ�գ���", Toast.LENGTH_LONG).show();
						}
						// ���²�ѯImages Provider�е����ݲ�ΪAdapter���°�����
						mAdapter.changeCursor(query());
						// ���������б�
						mAdapter.notifyDataSetChanged();
						Toast.makeText(ImagesProviderActivity.this,
								"������ " + numberOfRows + "�����ݣ���",
								Toast.LENGTH_LONG).show();
					}
				}).setNegativeButton("ȡ��", null);
		mDialog.show();
	}

	/**
	 * ִ�и������ݲ���
	 * 
	 * @param _id
	 * @param title
	 * @return numberOfRows
	 */
	public int update(String _id, String title) {
		String where = MediaStore.Images.Media._ID + "= ?";
		String[] selectionArgs = { _id };
		// ������Ҫ�޸ĵ����ݼ���
		ContentValues mValues = new ContentValues();
		mValues.put(MediaStore.Images.Media.TITLE, title);
		int numberOfRows = getContentResolver().update(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, mValues, where,
				selectionArgs);
		return numberOfRows;
	}

	
	/** ----------------------��ImagesProvider��������---------------------- */
	
	/**
	 * ���ϵͳ�ṩ��Images Provider�е�ͼƬ��Ϣ
	 */
	public void insertData() {
		final View mView = LayoutInflater.from(ImagesProviderActivity.this)
				.inflate(R.layout.images_provider_update, null);
		AlertDialog.Builder mDialog = new AlertDialog.Builder(
				ImagesProviderActivity.this).setTitle("��Ӳ���").setView(mView)
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						EditText mEditText_name = (EditText) mView
								.findViewById(R.id.images_name_edittext);
						String savePath = null;
						if (!TextUtils.isEmpty(mEditText_name.getText())) {
							// ִ����Ӳ���
							savePath = insert(mEditText_name.getText()
									.toString());
						} else {
							Toast.makeText(ImagesProviderActivity.this,
									"���Ʋ���Ϊ�գ���", Toast.LENGTH_LONG).show();
						}
						// ���²�ѯImages Provider�е����ݲ�ΪAdapter���°�����
						mAdapter.changeCursor(query());
						// ���������б�
						mAdapter.notifyDataSetChanged();
						Toast.makeText(ImagesProviderActivity.this,
								"����ɹ���·���� " + savePath + "��", Toast.LENGTH_LONG)
								.show();
					}
				}).setNegativeButton("ȡ��", null);
		mDialog.show();
	}

	/**
	 * ִ�����ͼƬ����
	 * 
	 * @param title
	 *            ͼƬ����
	 * @return savePath ͼƬ�洢·��
	 */
	@SuppressLint("NewApi")
	public String insert(String title) {
		// �õ�����ͼƬ��Bitmap����
		Bitmap mBitmap = ((BitmapDrawable) getResources().getDrawable(
				R.drawable.stevejobs)).getBitmap();
		// ����ͼƬ
		String savePath = MediaStore.Images.Media.insertImage(
				getContentResolver(), mBitmap, title, "�����ͼƬ");

		System.out.println("savePath = "+ savePath);
		ContentValues mContentValues = new ContentValues();
		mContentValues.put(MediaStore.Images.Media.DATE_TAKEN,
				System.currentTimeMillis());
		String _id = savePath.substring(savePath.lastIndexOf("/") + 1);
		String where = MediaStore.Images.Media._ID + "= ?";
		String[] selectionArgs = { _id };
		getContentResolver().update(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, mContentValues,
				where, selectionArgs);
		return savePath;
	}

	/** ----------------------�����Ƿָ��� ---------------------- */
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 1, Menu.NONE, "���");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		insertData();
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		menu.setHeaderTitle("�ļ�����");
		menu.add(0, 1, Menu.NONE, "ɾ��");
		menu.add(0, 2, Menu.NONE, "�޸�");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// �õ���ǰ��ѡ�е�item��Ϣ
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		String _id = null;
		if (mAdapter != null) {
			Cursor mCursor = (Cursor) mAdapter.getItem(menuInfo.position);
			_id = mCursor.getString(mCursor
					.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
		}
		switch (item.getItemId()) {
		case 1:
			deleteData(_id);
			break;
		case 2:
			updateData(_id);
			break;
		default:
			return super.onContextItemSelected(item);
		}
		return true;
	}
}
