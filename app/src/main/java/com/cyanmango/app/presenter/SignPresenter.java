package com.cyanmango.app.presenter;
import com.cyanmango.app.view.Signview;
import android.view.View;

public interface SignPresenter extends BasePresenter<Signview>
{
	void post(boolean islogin,String name,String pass)
	void setOnClicklinstener(View v)
	void setOnLongClicklinstener(View v)
}
