package com.cyanmango.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils
{
	private static SimpleDateFormat tran=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static Date date=new Date();
	public static String MrTransformTime(long mr){
        date.setTime(mr);
        return tran.format(date);
    }
}
