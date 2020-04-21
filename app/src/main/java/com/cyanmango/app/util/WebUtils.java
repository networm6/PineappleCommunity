package com.cyanmango.app.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.cyanmango.app.R;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import simon.tuke.Tuke;

public class WebUtils
{
	public static void Post_tip(final String path,final String token,final String parms,final CallBack back){
		new Thread(new Runnable(){

				@Override
				public void run()
				{
					try {
						// 创建连接 
						URL url = new URL(path);
						HttpURLConnection connection = (HttpURLConnection) url.openConnection();
						connection.setDoOutput(true);
						connection.setDoInput(true);
						connection.setRequestMethod("POST");
						connection.setUseCaches(false);
						connection.setInstanceFollowRedirects(true);
						
						connection.setRequestProperty("Authorization",token);

						connection.setRequestProperty("Content-Type", "application/json");
						
						connection.connect();
						// POST请求 
						DataOutputStream out = new DataOutputStream(connection.getOutputStream());
						out.writeChars(parms);
						out.flush();
						out.close();
						// 读取响应 
						BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
						String lines;
						StringBuffer sb = new StringBuffer();
						while ((lines = reader.readLine()) != null) {
							lines = URLDecoder.decode(lines, "utf-8");
							sb.append(lines);
						}
						back.reback(sb.toString());
						reader.close();
						// 断开连接 
						connection.disconnect(); 
					} catch (MalformedURLException e) { 
						e.printStackTrace(); 
						back.error(e);
					} catch (UnsupportedEncodingException e) { 
						e.printStackTrace(); 
						back.error(e);
					} catch (IOException e) { 
						e.printStackTrace(); 
						back.error(e);
					}
				}
			}).start();
	}
	public static void Post_Img(final String path,final String token,final String base64,final CallBack back) {
		new Thread(new Runnable(){

				@Override
				public void run()
				{
					try {
						// 创建连接 
						URL url = new URL(path);
						HttpURLConnection connection = (HttpURLConnection) url.openConnection();
						connection.setDoOutput(true);
						connection.setDoInput(true);
						connection.setRequestMethod("POST");
						connection.setUseCaches(false);
						connection.setInstanceFollowRedirects(true);

						
							connection.setRequestProperty("Authorization",token);
						
						connection.connect();
						// POST请求 
						DataOutputStream out = new DataOutputStream(connection.getOutputStream());

						out.writeBytes(base64);
						out.flush();
						out.close();
						// 读取响应 
						BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
						String lines;
						StringBuffer sb = new StringBuffer();
						while ((lines = reader.readLine()) != null) {
							lines = URLDecoder.decode(lines, "utf-8");
							sb.append(lines);
						}
						back.reback(sb.toString());
							
						reader.close();
						// 断开连接 
						connection.disconnect(); 
					} catch (MalformedURLException e) { 
						back.error(e);
					} catch (UnsupportedEncodingException e) { 
						back.error(e);
					} catch (IOException e) { 
						back.error(e);
					}
				}
			}).start();
		
	}
	public static void Post_Json(final String in,final String parms,final CallBack back){
		new Thread(new Runnable(){

				@Override
				public void run()
				{
					try {
						// 创建连接 
						URL url = new URL(in);
						HttpURLConnection connection = (HttpURLConnection) url.openConnection();
						connection.setDoOutput(true);
						connection.setDoInput(true);
						connection.setRequestMethod("POST");
						connection.setUseCaches(false);
						connection.setInstanceFollowRedirects(true);
						connection.setRequestProperty("Content-Type", "application/json");
						connection.connect();
						// POST请求 
						DataOutputStream out = new DataOutputStream(connection.getOutputStream());

						out.writeBytes(parms);
						out.flush();
						out.close();
						// 读取响应 
						BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
						String lines;
						StringBuffer sb = new StringBuffer();
						while ((lines = reader.readLine()) != null) {
							lines = URLDecoder.decode(lines, "utf-8");
							sb.append(lines);
						}
						back.reback(sb.toString());
						
						reader.close();
						// 断开连接 
						connection.disconnect(); 
					}  catch (IOException e) { 
						back.error(e);
					}
				}
			}).start();
		
		
	}
	public static void Post(final String in,final String imgpath,final CallBack back,final String... parms) {
		new Thread(new Runnable(){

				@Override
				public void run()
				{
					try {
						// 创建连接 
						URL url = new URL(in);
						HttpURLConnection connection = (HttpURLConnection) url.openConnection();
						connection.setDoOutput(true);
						connection.setDoInput(true);
						connection.setRequestMethod("POST");
						connection.setUseCaches(false);
						connection.setInstanceFollowRedirects(true);
                          for(String one:parms){
		            	  String[] parm=one.split("♤");
		            	  connection.setRequestProperty(parm[0],parm[1]);
		                  }
						connection.connect();
						// POST请求 
						DataOutputStream out = new DataOutputStream(connection.getOutputStream());
						out.writeBytes("fileBase=data:image/jpg;base64,"+ URLEncoder.encode(DiskUtils.imageToBase64(imgpath)));
						out.flush();
						out.close();
						// 读取响应 
						BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
						String lines;
						StringBuffer sb = new StringBuffer();
						while ((lines = reader.readLine()) != null) {
							lines = URLDecoder.decode(lines, "utf-8");
							sb.append(lines);
						}
						back.reback(sb.toString());
							System.out.println(sb);
						reader.close();
						// 断开连接 
						connection.disconnect(); 
					} catch (MalformedURLException e) { 
						back.error(e);
					} catch (UnsupportedEncodingException e) { 
						back.error(e);
					} catch (IOException e) { 
						back.error(e);
					}
				}
			}).start();
	}
	
	public static void Get(final String in,final CallBack back) {
		new Thread(new Runnable(){

				@Override
				public void run()
				{
					try
					{
						URL url = new URL(in);
						HttpURLConnection conn=(HttpURLConnection)url.openConnection();
						conn.setRequestMethod("GET");
						conn.setConnectTimeout(6000);
						conn.setDoOutput(false);
						InputStream is = conn.getInputStream();
						BufferedReader reader = new BufferedReader(new InputStreamReader(is));
						final StringBuilder sb = new StringBuilder();
						String line = "";
						while ((line = reader.readLine()) != null)
						{
							sb.append(line);
						}
						back.reback( sb.toString());
					}
					catch (IOException e)
					{
						back.error((IOException)e);
					}
				}
			}).start();
	}
	
	public static void getURLimage(final String url,final CallBack back) {
		
		new Thread(new Runnable(){

				@Override
				public void run()
				{
					Bitmap  bmp=Tuke.getBitmap(false,url,null);
					//   Bitmap bmp =SpUtils.getBitmapFromSharedPreferences(ac,url);
					if(bmp==null)
						try {
							URL myurl = new URL(url);
							HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
							conn.setConnectTimeout(2000);//设置超时
							conn.setDoInput(true);
							conn.setUseCaches(false);//不缓存
							conn.connect();
							InputStream is = conn.getInputStream();//获得图片的数据流
							bmp = BitmapFactory.decodeStream(is);//读取图像数据
							Tuke.write(false,url,bmp);
							
							is.close();

						} catch (IOException e) {
							back.error(e);
							}
					if(bmp==null)
						bmp=BitmapFactory.decodeResource(UtilsControl.getCon().getResources(),R.drawable.ic_launcher);
					
					back.reback(bmp);
				}
			}).start();
    }
	public interface CallBack<T,E>
	{
		void reback(T get)
		void error(E e)
	}
}
