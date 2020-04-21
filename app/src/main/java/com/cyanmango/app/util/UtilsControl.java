package com.cyanmango.app.util;
import android.app.Application;
import android.content.Context;
import simon.tuke.Tuke;

public class UtilsControl
{
	private static Context con;
	public static void setCon(Application in)
	{
		UtilsControl.con = in;
		Tuke.init(in);
		//Loger.init(UtilsControl.con);
	}

	public static Context getCon()
	{
		return con;
	}
}
