package com.cyanmango.app;
import android.app.Application;
import com.cyanmango.app.util.UtilsControl;
import simon.tuke.Tuke;

public class App extends Application
{

	@Override
	public void onCreate()
	{
		UtilsControl.setCon(this);
		Tuke.setException(new Tuke.OnException(){

				@Override
				public void error(Exception e)
				{
			     }
			});
	
       super.onCreate();
	}
	
}
