package com.androidleaf.sqlite.database;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

/**
 * ������SQLiteDatabase�İ����࣬��Ҫ�������ݿ�Ĵ����Ͱ汾�ĸ���
 * @author AndroidLeaf
 *
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DatabaseOpenHelper extends SQLiteOpenHelper {

	private static DatabaseOpenHelper mDatabaseOpenHelper;
	/**
	 * ���ݿ�汾��
	 */
	private static final int DATABASE_VERSION = 1;
	/**
	 * ���ݿ�����
	 */
	private static final String DATABASE_NAME = "manager.db";
	
	/**
	 * ����һ���¼������ص�����������͸������ݿ��Ĳ���������ʵ��
	 */
	public SQLiteDataTable mDataTable;
	
	public interface SQLiteDataTable{
		public void onCreate(SQLiteDatabase mSqLiteDatabase);
		public void onUpgrade(SQLiteDatabase mSqLiteDatabase);
	}
	
	public void setOnSQLiteDataTable(SQLiteDataTable mDataTable){
		this.mDataTable = mDataTable;
	}
	/**
	 * ��ʼ�����ݿ���Ϣ
	 * @param context Ӧ�ó���������
	 * @param name ���ݿ�����
	 * @param factory cursor��������
	 * @param version ���ݿ�汾��
	 * @param errorHandler ���ݿ���������
	 */
	public DatabaseOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION, new DatabaseErrorHandler() {
			
			@Override
			public void onCorruption(SQLiteDatabase dbObj) {
				// TODO Auto-generated method stub
			}
		});
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * ʹ�õ���ģʽ����ȡ���ݿ�Ψһʵ��
	 * @param mContext Ӧ�ó���������
	 * @return mDatabaseOpenHelper �ö������ڻ�ȡSQLiteDatabaseʵ��
	 */
	public synchronized static DatabaseOpenHelper getDatabaseOpenHelper(Context mContext){
		if(mDatabaseOpenHelper == null){
			mDatabaseOpenHelper = new DatabaseOpenHelper(mContext);
		}
		return mDatabaseOpenHelper;
	} 

	/**
	 * �������ݿ�ʱ���ã�һ��ִ�д�����Ĳ���
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//����һϵ�е����ݿ��
		mDataTable.onCreate(db);
	}

	/**
	 * �����ݿ���Ҫ�޸ĵ�ʱϵͳ���Զ����ô˷�����һ������������������ɾ�����ݿ��
	 * �������µ����ݿ����Ȼ�Ƿ���Ҫ������������ȫȡ����Ӧ�õ�����
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		mDataTable.onUpgrade(db);
		onCreate(db);
	}
}
