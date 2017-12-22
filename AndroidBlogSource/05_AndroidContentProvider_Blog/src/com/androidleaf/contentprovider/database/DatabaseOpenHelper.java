package com.androidleaf.contentprovider.database;

import com.androidleaf.contentprovider.myprovider.MyContentProviderMetaData;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

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
	
	private static final String DATABSE_NAME = "manager.db";
	
	/**
	 * ��ʼ�����ݿ���Ϣ
	 * @param context Ӧ�ó���������
	 * @param name ���ݿ�����
	 * @param factory cursor��������
	 * @param version ���ݿ�汾��
	 * @param errorHandler ���ݿ���������
	 */
	public DatabaseOpenHelper(Context context) {
		super(context, DATABSE_NAME, null,
				DATABASE_VERSION, new DatabaseErrorHandler() {
			
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
		//�������ݿ��
		db.execSQL("CREATE TABLE "
				+ MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_TABLE_NAME + " (" + "" 
				+  MyContentProviderMetaData.ProviderMetaData.Persons._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + ""
				+  MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_FIELD_PERSONNAME + " VARCHAR(20) NOT NULL," + ""
				+  MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_FIELD_EMAIL + " VARCHAR(20) NOT NULL," + ""
				+  MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_FIELD_HEIGHT + " FLOAT(10) NOT NULL" + ")");
		
		//����20����������
		for(int i = 0;i < 20;i++){
			ContentValues mContentValues = new ContentValues();
			mContentValues.put(MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_FIELD_PERSONNAME, "Steve.P.Jobs "+ i);
			mContentValues.put(MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_FIELD_EMAIL, "45936455"+ i +"@qq.com");
			mContentValues.put(MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_FIELD_HEIGHT, String.valueOf(170 + i));
			db.insertOrThrow(MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_TABLE_NAME,
					MyContentProviderMetaData.ProviderMetaData.Persons._ID, mContentValues);
		}
	}

	/**
	 * �����ݿ���Ҫ�޸ĵ�ʱϵͳ���Զ����ô˷�����һ������������������ɾ�����ݿ��
	 * �������µ����ݿ����Ȼ�Ƿ���Ҫ������������ȫȡ����Ӧ�õ�����
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " +  MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_TABLE_NAME);
		onCreate(db);
	}
}
