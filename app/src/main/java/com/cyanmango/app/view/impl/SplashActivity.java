package com.cyanmango.app.view.impl;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.cyanmango.app.Base.BaseActivity;
import com.cyanmango.app.R;
import com.cyanmango.app.Runnable.SpleshRunnable;
import com.cyanmango.app.constant.Constant;
import com.cyanmango.app.util.BitmapUtils;
import com.cyanmango.app.util.DiskUtils;
import com.cyanmango.app.util.WebUtils;
import java.io.IOException;
import simon.tuke.Tuke;

public class SplashActivity extends BaseActivity
{
  private ImageView back,img;
  private TextView from;
  private Thread thre;
  private Bitmap bit,m;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		hideActionBar();
		setContentView(R.layout.splash);
		from=findViewById(R.id.splashimgfrom);
		back=findViewById(R.id.splashbackground);
		img=findViewById(R.id.splashImageView);
		from.setText("Bing日图");
		thre=new Thread(new SpleshRunnable(this));
		WebUtils.getURLimage( Constant.oneimg, new WebUtils.CallBack<Bitmap,IOException>(){

				@Override
				public void error(IOException e)
				{
				}
				@Override
				public void reback( Bitmap get)
				{
					m=get;
					bit=BitmapUtils.doBlur(get,17);
					runOnUiThread(new Runnable(){

							@Override
							public void run()
							{
								back.setImageBitmap(bit);
								img.setImageBitmap(m);
								doanim();
							}
						});
				}
			});
	}
	public boolean isslide;
	ValueAnimator anim;
	public void doanim(){
		 anim = ValueAnimator.ofInt(0, 255);  
		anim.setDuration(1500);  
		anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {  
				@Override  
				public void onAnimationUpdate(ValueAnimator animation) {  
					int value=animation.getAnimatedValue();
					back.getDrawable().setAlpha(value);
					img.getDrawable().setAlpha(value);
					if(value==255){
						thre.start();
					}
				}  
			});  
			if(!isslide)
		anim.start();  
	}
	public void down(View v){
		if(m==null)
		return;
		try
		{
			DiskUtils.saveBitmap(Environment.getExternalStorageDirectory().getAbsolutePath()+ "/" + System.currentTimeMillis() + ".jpeg", m);
			Toast.makeText(this,"成功",10).show();
			
			}
		catch (IOException e)
		{
			Toast.makeText(this,"出错"+e.getMessage(),10).show();
		}
	}
	public void slide(View v){
		isslide=true;
		if(anim!=null)
			anim.cancel();
		jump(MainActivity.class);
		finish();
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		Tuke.clearDisk(Constant.oneimg);
		if(thre.isAlive())
		thre=null;
		if(m!=null)
		m.recycle();
		if(bit!=null)
		bit.recycle();
	}
	
		
			
}
