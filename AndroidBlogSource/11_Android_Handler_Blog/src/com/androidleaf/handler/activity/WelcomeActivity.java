package com.androidleaf.handler.activity;
import com.androidleaf.handler.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
          // TODO Auto-generated method stub
          super.onCreate(savedInstanceState);
        //����ȫ��
        requestWindowFeature(Window. FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.welcome);
        welcomeMain();
    }
	  //��ӭ���淽��
	public void welcomeMain()
	{
	     new Handler().postDelayed(new Runnable() {
	         
	          public void run() {
	        	  //3000�������ת��WelcomeMainActivity����
	              Intent mIntent = new Intent(WelcomeActivity.this , WelcomeMainActivity.class );
	              WelcomeActivity. this.startActivity(mIntent);
	              WelcomeActivity. this.finish();
	         }
	       //��ʱ3000����
	     }, 3000);
	}
}
