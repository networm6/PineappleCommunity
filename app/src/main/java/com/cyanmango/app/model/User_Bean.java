package com.cyanmango.app.model;
import java.io.Serializable;

public class User_Bean implements Serializable
{
	private int id,level;
	private String name,token,createtime,backimg,img;


	public void setId(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}

	public int getLevel()
	{
		return level;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public String getToken()
	{
		return token;
	}

	public void setCreatetime(String createtime)
	{
		this.createtime = createtime;
	}

	public String getCreatetime()
	{
		return createtime;
	}

	public void setBackimg(String backimg)
	{
		this.backimg = backimg;
	}

	public String getBackimg()
	{
		return backimg;
	}

	public void setImg(String img)
	{
		this.img = img;
	}

	public String getImg()
	{
		return img;
	}
}

