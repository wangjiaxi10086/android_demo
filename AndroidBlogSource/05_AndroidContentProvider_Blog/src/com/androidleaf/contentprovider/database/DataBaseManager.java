package com.androidleaf.contentprovider.database;

import com.androidleaf.contentprovider.myprovider.MyContentProviderMetaData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class DataBaseManager implements IDatabaseManager{

	private static final String TAG = "sqlite_log";
	private static final String PERSON_TABLE = MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_TABLE_NAME;
	private static final String PERSON_ID = MyContentProviderMetaData.ProviderMetaData.Persons._ID;
	
	private SQLiteDatabase mSqLiteDatabase;
	DatabaseOpenHelper mDatabaseOpenHelper;
	
	public DataBaseManager(Context mContext){
		mDatabaseOpenHelper = DatabaseOpenHelper.getDatabaseOpenHelper(mContext);
		//��ȡSQLiteDatabase���󣬴���������ݿ�
		mSqLiteDatabase = mDatabaseOpenHelper.getWritableDatabase();
	}
	
	/**
	 * ���뵥�����ݲ���
	 * @param mContentValues ��������ݼ���
	 * @return long �������ݵ��к�
	 */
	@Override
	public long insert(ContentValues mContentValues) {
		// TODO Auto-generated method stub
		mSqLiteDatabase.beginTransaction();
		try {
			long rowId = mSqLiteDatabase.insertOrThrow(PERSON_TABLE,PERSON_ID, mContentValues);
			mSqLiteDatabase.setTransactionSuccessful();
			return rowId;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(TAG, "The insert operation failed");
		}finally{
			mSqLiteDatabase.endTransaction();
		}
		return -1;
	}

	/**
	 * ���µ������ݲ���
	 * @param mContentValues ��Ҫ���µ����ݼ���
	 * @return int �������ݵ�����
	 */
	@Override
	public int update(ContentValues mContentValues) {
		// TODO Auto-generated method stub
		mSqLiteDatabase.beginTransaction();
		try {
			int rows = mSqLiteDatabase.update(PERSON_TABLE, mContentValues, PERSON_ID +"= ?",
					new String[]{String.valueOf(mContentValues.get(PERSON_ID))});
			mSqLiteDatabase.setTransactionSuccessful();
			return rows;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(TAG, "The update operation failed");
		}finally{
			mSqLiteDatabase.endTransaction();
		}
		return 0;
	}
	
	/**
	 * ���¶������ݲ���
	 * @param mContentValues ��Ҫ���µ����ݼ���
	 * @param whereClause
	 * @param whereArgs
	 * @return int ���µ���������
	 */
	public int update(ContentValues mContentValues,String whereClause,String[] whereArgs) {
		// TODO Auto-generated method stub
		mSqLiteDatabase.beginTransaction();
		try {
			int rows = mSqLiteDatabase.update(PERSON_TABLE, mContentValues, whereClause, whereArgs);
			mSqLiteDatabase.setTransactionSuccessful();
			return rows;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(TAG, "The update operation failed");
		}finally{
			mSqLiteDatabase.endTransaction();
		}
		return 0;
	}

	/**
	 * ɾ���������ݲ���
	 * @param mContentValues ��Ҫ��ɾ��������ѡ��ID
	 * @return int ɾ������������
	 */
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		mSqLiteDatabase.beginTransaction();
		try {
			int rows = mSqLiteDatabase.delete(PERSON_TABLE, PERSON_ID +"= ?", new String[]{String.valueOf(id)});
			mSqLiteDatabase.setTransactionSuccessful();
			return rows;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(TAG, "The delete operation failed");
		}finally{
			mSqLiteDatabase.endTransaction();
		}
		return 0;
	}
	/**
	 * ɾ���������ݲ���
	 * @param whereClause
	 * @param whereArgs
	 * @return int ɾ������������
	 */
	public int delete(String whereClause,String[] whereArgs) {
		// TODO Auto-generated method stub
		mSqLiteDatabase.beginTransaction();
		try {
			int rows = mSqLiteDatabase.delete(PERSON_TABLE, whereClause, whereArgs);
			mSqLiteDatabase.setTransactionSuccessful();
			return rows;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(TAG, "The delete operation failed");
		}finally{
			mSqLiteDatabase.endTransaction();
		}
		return 0;
	}
	
	/**
	 * ʹ�ñ�׼SQL����ѯ�����б�
	 * @param <T>
	 * @param sql ��׼SQL���
	 * @return mList ��ѯ��������б�
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Cursor query(String sql) {
		return mSqLiteDatabase.rawQuery(sql, null);
	}
	
	/**
	 * ����ID��ѯ����
	 * @param <T>
	 * @param id ��Ҫ��ѯ������ID
	 * @return T ��ѯ���ȡ���Ķ���
	 */
	@Override
	public Cursor query(int id) {
		// TODO Auto-generated method stub
		return mSqLiteDatabase.query(PERSON_TABLE, null, PERSON_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
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
}
