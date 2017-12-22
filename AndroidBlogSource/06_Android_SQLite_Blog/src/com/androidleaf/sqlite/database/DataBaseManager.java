package com.androidleaf.sqlite.database;

import java.util.ArrayList;

import com.androidleaf.sqlite.database.DatabaseOpenHelper.SQLiteDataTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;


public abstract class DataBaseManager<T> implements IDatabaseManager,SQLiteDataTable{

	private static final String TAG = "sqlite_log";
	private SQLiteDatabase mSqLiteDatabase;
	
	public DataBaseManager(Context mContext){
		DatabaseOpenHelper mDatabaseOpenHelper = DatabaseOpenHelper.getDatabaseOpenHelper(mContext);
		//�����¼�����
		mDatabaseOpenHelper.setOnSQLiteDataTable(this);
		//��ȡSQLiteDatabase���󣬴���������ݿ�
		mSqLiteDatabase = mDatabaseOpenHelper.getWritableDatabase();
	}
	
	/**
	 * �������ݲ���
	 * @param mContentValues ��������ݼ���
	 * @return boolean ����ֵ��trueΪ����ɹ���false����ʧ��
	 */
	@Override
	public boolean insert(ContentValues mContentValues) {
		// TODO Auto-generated method stub
		mSqLiteDatabase.beginTransaction();
		try {
			long rowId = mSqLiteDatabase.insertOrThrow(getTableName(), null, mContentValues);
			mSqLiteDatabase.setTransactionSuccessful();
			return rowId != -1;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(TAG, "The insert operation failed");
		}finally{
			mSqLiteDatabase.endTransaction();
		}
		return false;
	}

	/**
	 * �������ݲ���
	 * @param mContentValues ��Ҫ���µ����ݼ���
	 * @return boolean ����ֵ��trueΪ���³ɹ���false����ʧ��
	 */
	@Override
	public boolean update(ContentValues mContentValues) {
		// TODO Auto-generated method stub
		mSqLiteDatabase.beginTransaction();
		try {
			int rows = mSqLiteDatabase.update(getTableName(), mContentValues, getPrimayKeyID() +"= ?",
					new String[]{String.valueOf(mContentValues.get(getPrimayKeyID()))});
			mSqLiteDatabase.setTransactionSuccessful();
			return rows > 0;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(TAG, "The update operation failed");
		}finally{
			mSqLiteDatabase.endTransaction();
		}
		return false;
	}

	/**
	 * ɾ�����ݲ���
	 * @param mContentValues ��Ҫ��ɾ��������ѡ��ID
	 * @return boolean ����ֵ��trueΪɾ���ɹ���falseɾ��ʧ��
	 */
	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		mSqLiteDatabase.beginTransaction();
		try {
			int rows = mSqLiteDatabase.delete(getTableName(), getPrimayKeyID() +"= ?", new String[]{String.valueOf(id)});
			mSqLiteDatabase.setTransactionSuccessful();
			return rows > 0;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(TAG, "The delete operation failed");
		}finally{
			mSqLiteDatabase.endTransaction();
		}
		return false;
	}

	/**
	 * ʹ�ñ�׼SQL����ѯ�����б�
	 * @param sql ��׼SQL���
	 * @return mList ��ѯ��������б�
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<T> query(String sql) {
		
		ArrayList<T> mList = new ArrayList<T>();
		Cursor mCursor = mSqLiteDatabase.rawQuery(sql, null);
		while(mCursor.moveToNext()){
			T mObject = getResultFromCursor(mCursor);
			mList.add(mObject);
		}
		return mList;
	}
	
	/**
	 * ����ID��ѯ����
	 * @param id ��Ҫ��ѯ������ID
	 * @return T ��ѯ���ȡ���Ķ���
	 */
	@Override
	public T query(int id) {
		// TODO Auto-generated method stub
		Cursor mCursor = mSqLiteDatabase.query(getTableName(), null, getPrimayKeyID() + "=?", new String[]{String.valueOf(id)}, null, null, null);
		return getResultFromCursor(mCursor);
	}
	
	/**
	 * ִ��һЩ��Ϊ���ӵ�CRUD����
	 */
	@Override
	public void execSQL(String sql) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * ִ�ж����ݿ������ݵĲ�����ر����ݿ�
	 */
	@Override
	public void closeDB() {
		// TODO Auto-generated method stub
		mSqLiteDatabase.close();
	}
	
	
	/**
	 * �������ȡ����
	 * @return String �������
	 */
	public abstract String getTableName();
	/**
	 * ��ȡ�����������
	 * @return String ������
	 */
	public abstract String getPrimayKeyID();
	/**
	 * ʹ��Cursorת������
	 * @param mCursor ��Ҫת����Cursor����
	 * @return T �����Ķ���
	 */
	public abstract T getResultFromCursor(Cursor mCursor);
}
