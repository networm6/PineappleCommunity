package com.cyanmango.app.constant;

public class Constant
{
	private static String servicepath="https://api.qm.jcdpd.cn/";
	public static String check_upadae=servicepath+"api/v1/updateCheck";
	public static String login=servicepath+"api/v1/login";
	public static String sign=servicepath+"api/v1/email/signUp";
	public static String oneimg="https://api.dujin.org/bing/1366.php";
	public static String follow=servicepath+"api/v1/follow";
	public static String unfollow=servicepath+"api/v1/follow/cancel";
	public static String addimg=servicepath+"api/v1/upload/imageForBase64";
	public static String create_tip=servicepath+"api/v1/post/create";
	public static String search_keyword(String keyword){
		return servicepath+"api/v1/post/pageList?keyword="+keyword;
	}
	public static String search_username(String name){
		return servicepath+"api/v1/user/search?username="+name;
	}
	public static String get_home(int page,boolean flashback){
		String re=servicepath+"api/v1/post/pageList?page="+page+"&desc=";
		if(flashback)re+=0;else re+=1;
		return re;
	}
	public static String get_tips_info(int id){
		return servicepath+"api/v1/post/info?id="+id;
	}
	public static String get_user_info(int id){
		return servicepath+"api/v1/user/info?id="+id;
	}
	public static String get_top(){
		return servicepath+"api/v1/post/pageList?&top=true&page=0";
	}
	public static String uodate;
	public static String updateimg;
}
