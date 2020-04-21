package com.cyanmango.app.view.impl;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.appcompat.R;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.cyanmango.app.Base.BaseActivity;
import com.cyanmango.app.model.User_Bean;
import com.cyanmango.app.util.BitmapUtils;
import com.cyanmango.app.util.UnitUtils;
import com.cyanmango.app.util.WebUtils;
import java.io.IOException;
import simon.tuke.Tuke;

public class UserInfoActivity extends BaseActivity
{
   ImageView img,bac;
   User_Bean UB;
   TextView name,time;
   Button id,level;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		hideActionBar();
		//这个activity没必要用mvp了，盲目使用mvp只会增加应用体积
		setContentView(R.layout.user_card);
		name=findViewById(R.id.usercardname);
		time=findViewById(R.id.usercardtime);
		id=findViewById(R.id.usercardid);
		level=findViewById(R.id.usercardlevel);
		img=findViewById(R.id.usercardimg);
		bac=findViewById(R.id.usercardbac);
		UB=Tuke.get(false,"UB");
		WebUtils.getURLimage(UB.getImg(), new WebUtils.CallBack<Bitmap,IOException>(){

				@Override
				public void reback(Bitmap get)
				{
					final Bitmap doo=BitmapUtils.bimapRound(get,UnitUtils.dip2px(10));
					runOnUiThread(new Runnable(){

							@Override
							public void run()
							{
								img.setImageBitmap(doo);
							}
						});
				}

				@Override
				public void error(IOException e)
				{
				}
			});
		Glide.with(this).load(UB.getBackimg()).into(bac);
		name.setText(UB.getName());
		time.setText("创建时间"+UB.getCreatetime());
		id.setText("ID\n"+UB.getId());
		level.setText("Level\n"+UB.getLevel());
	}
	public void logout(View v){
		Tuke.clearDisk("UB");
		Tuke.clearMemory("UB");
		finish();
	}
	
}
