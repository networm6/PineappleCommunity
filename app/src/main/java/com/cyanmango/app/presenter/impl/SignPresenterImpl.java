package com.cyanmango.app.presenter.impl;
import android.view.View;
import com.cyanmango.app.constant.Constant;
import com.cyanmango.app.model.User_Bean;
import com.cyanmango.app.presenter.SignPresenter;
import com.cyanmango.app.util.TimeUtils;
import com.cyanmango.app.util.WebUtils;
import com.cyanmango.app.view.Signview;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public class SignPresenterImpl implements SignPresenter
{

	@Override
	public void setOnLongClicklinstener(View v)
	{
		v.setOnLongClickListener(new View.OnLongClickListener(){

				@Override
				public boolean onLongClick(View p1)
				{
					view.onLongClick(p1);
					return true;
				}
			});
	}

	@Override
	public void setOnClicklinstener(View v)
	{
		v.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					view.onclick(p1);
				}
			});
	}

 Signview view;
 
	@Override
	public void attachView(Signview t)
	{
		view=t;
	}
	@Override
	public void detachView()
	{
		view=null;
	}

	@Override
	public void post(final boolean islogin,String name, String pass)
	{
		String path=islogin?Constant.login:Constant.sign;
		WebUtils.Post_Json(path,"{\"password\":\""+pass+"\",\"identifier\":\""+name+"\"}\"",
			new WebUtils.CallBack<String,IOException>(){

				@Override
				public void reback(String get)
				{
				try{
					if(islogin)
						view.onsuccess(getUB(get));
					else
						view.onsuccess(back_message(get));
					}
					catch (JSONException e)
					{
						view.onerror(e);
					}
				}

				@Override
				public void error(IOException e)
				{
					view.onerror(e);
				}
			});
		
	}
	private User_Bean getUB(String get) throws JSONException{
		User_Bean UB=new User_Bean();
		JSONObject first=new JSONObject(get);
		if(first.getInt("status")!=200){
			UB.setName(first.getString("message"));
			return UB;
		}
			
		JSONObject info=first.getJSONObject("result");
		UB.setId(info.getInt("id"));
		UB.setName(info.getString("username"));
		UB.setBackimg(info.getString("background"));
		UB.setImg(info.getString("avatar"));
		UB.setLevel(info.getInt("level"));
		UB.setToken(info.getString("token"));
		UB.setCreatetime(TimeUtils.MrTransformTime(info.getLong("createTime")));
		return UB;
	}
	private String back_message(String in) throws JSONException{
		JSONObject first=new JSONObject(in);
		String re ="";
		if(first.getInt("status")!=200)
			re="请求失败,";
		re+=first.getString("message");
		return re;
	}

}
