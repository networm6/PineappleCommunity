package com.cyanmango.app.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Add_Bean implements Serializable
{
	private String text;
	private  ArrayList<String> imgs=new ArrayList();

	public  void setText(String text)
	{
		 this.text = text;
	}

	public String getText()
	{
		return text;
	}

	public void setImgs(ArrayList<String> imgs)
	{
		this.imgs = imgs;
	}

	public List<String> getImgs()
	{
		return imgs;
	}
}
