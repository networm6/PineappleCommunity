package com.cyanmango.app.util;
import android.content.Context;
import android.widget.Toast;
import android.os.Looper;

public class ToastUtils
{
	public static void Toast(String out){
		Looper.getMainLooper().prepare();
		Toast.makeText(UtilsControl.getCon(),out,10).show();
	    Looper.getMainLooper().loop();
	}
	public static void UIToast(String in){
		Toast.makeText(UtilsControl.getCon(),in,10).show();
	}
}
