package com.cyanmango.app.view.impl;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.cyanmango.app.R;
public class AboutActivity extends Activity
{
  ImageView img,back;
  CardView cv;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		img=findViewById(R.id.aboutcardimg);
		back=findViewById(R.id.aboutcardbac);
		cv=findViewById(R.id.aboutCardView);
		Glide.with(this).load("http://img.qm.jcdpd.cn/image/e7de9e0a-aa15-4e5d-9cf6-fe928e2a5eb0.jpg").into(img);
		Glide.with(this).load("http://img.qm.jcdpd.cn/image/efc1ff0c-77c6-48ec-b7b9-4dc6e606b1de.jpg").into(back);
		cv.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					Intent intent= new Intent();        
					intent.setAction("android.intent.action.VIEW");    
					Uri content_url = Uri.parse("https://github.com/networm6");   
					intent.setData(content_url);  
					startActivity(intent);
				}
			});
		}
	
}
