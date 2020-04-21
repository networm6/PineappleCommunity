package com.cyanmango.app.presenter.impl;
import com.cyanmango.app.constant.Constant;
import com.cyanmango.app.model.Item_Bean;
import com.cyanmango.app.presenter.RVPresenter;
import com.cyanmango.app.util.TimeUtils;
import com.cyanmango.app.util.WebUtils;
import com.cyanmango.app.view.RVview;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RvPresenterImpl implements RVPresenter
{

	@Override
	public void loadmore()
	{  page++;
	   loadfromcloud();
	}

	public RVview mMainBaseView;
	
	public int page=1;
	@Override
	public void refresh()
	{
		page=1;
		mMainBaseView.onRefresh();
		loadfromcloud();
	}

	@Override
	public void attachView(RVview t)
	{
		mMainBaseView=t;
	}
	
	@Override
	public void detachView()
	{
		mMainBaseView=null;
		
	}
	
	@Override
	public void loadfromcloud()
	{
		WebUtils.Get(Constant.get_home(page,false), new WebUtils.CallBack<String,IOException>(){

				@Override
				public void reback(String get)
				{
					try{
				mMainBaseView.showRecyclerView(get(get));
				       }
				catch (JSONException e) {
					mMainBaseView.onerror(e);
					   }
				}

				@Override
				public void error(IOException e)
				{
					mMainBaseView.onerror(e);
				}
			});

	}
	
    
   
   
    public List<Item_Bean> get(String json) throws JSONException{
		List re=new ArrayList();
			JSONObject first=new JSONObject(json);
			if(first.getInt("status")!=200)
				return re;
			JSONArray two=first.getJSONArray("result");
			for (int i=0;i < two.length();i++) {
				Item_Bean bean=new Item_Bean();
                JSONObject one=two.getJSONObject(i);
				boolean a=one.getBoolean("deleted");
				boolean b=!one.getBoolean("display");
				boolean c=one.getBoolean("banned");
				if(a&&b&&c)continue;
				bean.setTip_id(one.getInt("id"));
			    bean.setUserid(one.getInt("userId"));
				bean.setText(one.getString("summary"));
				bean.setUsername(one.getString("username"));
				bean.setImg(one.getString("avatar"));
				bean.setTime(TimeUtils.MrTransformTime(one.getLong("createTime")));
				System.out.println(bean.getTime());
				String imgs=one.getString("imageStr");
				if(!imgs.equals(""))
					bean.setImgs(imgs.split(","));
				bean.setThumb(one.getInt("thumbCount"));
				bean.setComment(one.getInt("commentCount"));
				bean.setFavorite(one.getInt("favoriteCount"));
				bean.setShare(one.getInt("shareCount"));
				re.add(bean);
			}
			return re;
			
	}
    
}


