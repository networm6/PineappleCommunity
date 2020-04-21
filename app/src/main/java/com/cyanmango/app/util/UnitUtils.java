package com.cyanmango.app.util;


public class UnitUtils
{
	public static int dip2px(float dpValue) {
		final float scale = UtilsControl.getCon().getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
}
