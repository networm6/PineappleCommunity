package com.cyanmango.app.presenter.impl;
import android.view.View;
import com.cyanmango.app.constant.Constant;
import com.cyanmango.app.presenter.AddPresenter;
import com.cyanmango.app.util.DiskUtils;
import com.cyanmango.app.util.ToastUtils;
import com.cyanmango.app.util.WebUtils;
import com.cyanmango.app.view.Addview;
import java.net.URLEncoder;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;


public class AddPresenterImpl implements AddPresenter
{

	@Override
	public void postimg(String token, String path)
	{
		WebUtils.Post_Img(Constant.addimg, token,"fileBase=data:image/png;base64,"+URLEncoder.encode(DiskUtils.imageToBase64(path)), new WebUtils.CallBack<String,Exception>(){

				@Override
				public void reback(String get)
				{
					//{"status":200,"result":"http://img.qm.jcdpd.cn/image/da99573a-d3b1-47f4-a8ed-deceda989fc2.jpg","message":"OK","code":"OK"}
					try
					{
						JSONObject first=new JSONObject(get);
						if (first.getInt("status") != 200)
							ToastUtils.Toast(first.getString("message"));
						else
							view.success(true,first.getString("result"));
					}
					catch (JSONException e)
					{
						view.onerror(e);
					}
				}

				@Override
				public void error(Exception e)
				{
					view.onerror(e);
				}
			});
			
	
	}


	@Override
	public void post(boolean ispublic,String token, String text, List<String> imgs)
	{
		String ing="";
		for(String one:imgs){
			ing+="\""+one+"\"";
		}
	//	text=URLEncoder.encode(text);
		String two= "{\"images\":["+ing+"],\"content\":\""+text+"\",\"summary\":\""+text+"\",\"display\":\""+ispublic+"\"}";
		
		WebUtils.Post_tip(Constant.create_tip, token, two, new WebUtils.CallBack<String,Exception>(){

				@Override
				public void reback(String get)
				{
					try
					{
						JSONObject first=new JSONObject(get);
						view.success(false,first.getString("message"));
					}
					catch (JSONException e)
					{
						view.onerror(e);
					}
				}

				@Override
				public void error(Exception e)
				{
					view.onerror(e);
				}
			});
		
	}

  Addview view;
	@Override
	public void setonclicklintener(View v)
	{
		v.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
               view.onclick(p1);
				}
			    });
	}
	

	@Override
	public void attachView(Addview t)
	{
		view=t;
	}

	@Override
	public void detachView()
	{
		view=null;
	}
   
	
	
}
