package com.cyanmango.app.view.impl;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.cyanmango.app.Base.BaseActivity;
import com.cyanmango.app.R;
import com.cyanmango.app.model.User_Bean;
import com.cyanmango.app.presenter.impl.SignPresenterImpl;
import com.cyanmango.app.view.Signview;
import simon.tuke.Tuke;

public class LorSActivity extends BaseActivity<View> implements Signview
{

	@Override
	public void onLongClick(View v)
	{
		if(islogin){
			tv.setText("注册");
			bt.setText("注册");
			islogin=false;
		}
		else{
			tv.setText("登录");
			bt.setText("登录");
			islogin=true;
		}
	}


	@Override
	public void onclick(View t)
	{
		presenter.post(islogin,ed1.getText().toString(),ed2.getText().toString());
	}
	private boolean islogin=true;
	@Override
	public void onsuccess(Object out)
	{

		if(out instanceof User_Bean){
			User_Bean ub=(User_Bean)out;
			if(ub.getToken()!=null){
				Tuke.write(true,"UB",ub);
				onuitoa("欢迎回来"+ub.getName());
				jump(UserInfoActivity.class);
				finish();
				}
		    else
				onuitoa(ub.getName());
		}else
			onuitoa((String)out);
		//finish();
	}
    private void onuitoa(final String in){
		runOnUiThread(new Runnable(){

				@Override
				public void run()
				{
					Toast.makeText(LorSActivity.this,in,10).show();
				}
			});
	}
	
	SignPresenterImpl presenter;
	EditText ed1,ed2;
	TextView tv;
	Button bt;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_login);
		ed1=findViewById(R.id.usered1);
		ed2=findViewById(R.id.usered2);
		bt=findViewById(R.id.userbutton);
		tv=findViewById(R.id.userloginTitle);
		presenter=new SignPresenterImpl();
		presenter.attachView(this);
		presenter.setOnClicklinstener(bt);
		presenter.setOnLongClicklinstener(bt);
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		presenter.detachView();
	}

}
