package com.androidleaf.storage.activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 *ʹ�� SharedPreference�洢��ʽ�����û���������
 *ʹ��SharedPreference�������ݴ洢�Ĳ���һ���Ϊ���¼�����
 *	1����ȡ��ǰ�����ĵ�SharedPreferences����
 *	2����ȡSharedPreferences�ı༭����
 *	3������putString(),putInt()������getString(),getInt()�ȷ�����ӻ��ȡ����
 *  4������commit()�����ύ���ݣ�(���ǻ�ȡ���ݲ����ύ)
 * @author Leaf
 */
public class SharedPreferenceActivity extends Activity {

	private EditText editTextName;
	private EditText editTextPassword;
	private SharedPreferences mPreferences;
	private static final String SHAREDPREFERENCE_NAME = "sharepreference_name";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpreference);
        /**
         * ��ϣ��Ϊһ��Activityʹ��SharedPreferences��ʽ�ֶ���ļ����������ݣ�ʹ�����·�����ȡSharedPreferences����
         * ��һ������Ϊ�ļ�ָ���ļ���
         * mPreferences = this.getSharedPreferences(SHAREDPREFERENCE_NAME, Context.MODE_PRIVATE);
         */
        //1.��ֻ��ʹ��һ���ļ�Ϊ����Activity���������ݣ�����ʹ�����·�����ȡSharedPreferences�����ļ���Ĭ��Ϊ����Activity������
        mPreferences = this.getPreferences( Context.MODE_PRIVATE);
        editTextName = (EditText)findViewById(R.id.username_edittext);
        editTextPassword = (EditText)findViewById(R.id.password_edittext);
        getPreferences();
    }
   
    public void saveButton(View view){
    	//2.��ȡSharedPreferences�ı༭����
    	SharedPreferences.Editor mEditor = mPreferences.edit();
    	String username = editTextName.getText().toString().trim();
    	String password = editTextPassword.getText().toString().trim();
    	if(username.equals("")||password.equals("")){
    		Toast.makeText(getApplicationContext(), "�û��������벻��Ϊ�գ���", Toast.LENGTH_LONG).show();
    	}else{
    		mEditor.putString(USERNAME, username);
        	mEditor.putString(PASSWORD, password);
        	if(mEditor.commit()){
        		Toast.makeText(getApplicationContext(), "����ɹ�����", Toast.LENGTH_LONG).show();
        		 getPreferences();
        	}
    	}
    }
    
    public void cancelButton(View view){
    	//3.��ȡSharedPreferences�ı༭����
    	SharedPreferences.Editor mEditor = mPreferences.edit();
    	mEditor.putString(USERNAME, "");
    	mEditor.putString(PASSWORD, "");
    	//4.�ύ����
    	if(mEditor.commit()){
    		Toast.makeText(getApplicationContext(), "��ɾ������", Toast.LENGTH_LONG).show();
    		 getPreferences();
    	}
    }
    /**
     * ��ȡ������û���������
     */
    public void getPreferences(){
        editTextName.setText(mPreferences.getString(USERNAME, ""));
        editTextPassword.setText(mPreferences.getString(PASSWORD, ""));
    }
}
