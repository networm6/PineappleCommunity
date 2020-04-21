package com.cyanmango.app.Runnable;
import com.cyanmango.app.view.impl.MainActivity;
import com.cyanmango.app.view.impl.SplashActivity;

public  class SpleshRunnable implements Runnable
{
	public SplashActivity root;
	public SpleshRunnable(SplashActivity in){
		root=in;
	}
	@Override
	public void run()
	{
		try
		{
			Thread.sleep(3000);
			if(!root.isslide)
			root.jump(MainActivity.class);
			root.finish();
		}
		catch (InterruptedException e)
		{}
		root=null;
	}
}
