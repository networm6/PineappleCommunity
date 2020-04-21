package com.cyanmango.app.model;
import java.io.Serializable;

public class Item_Bean implements Serializable
{
	private int tip_id,userid,thumb,comment,share,favorite;
	private String username,text,time,img;
	private String[] imgs;

	public void setTip_id(int tip_id)
	{
		this.tip_id = tip_id;
	}

	public int getTip_id()
	{
		return tip_id;
	}

	public void setUserid(int userid)
	{
		this.userid = userid;
	}

	public int getUserid()
	{
		return userid;
	}

	public void setThumb(int thumb)
	{
		this.thumb = thumb;
	}

	public int getThumb()
	{
		return thumb;
	}

	public void setComment(int comment)
	{
		this.comment = comment;
	}

	public int getComment()
	{
		return comment;
	}

	public void setShare(int share)
	{
		this.share = share;
	}

	public int getShare()
	{
		return share;
	}

	public void setFavorite(int favorite)
	{
		this.favorite = favorite;
	}

	public int getFavorite()
	{
		return favorite;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getUsername()
	{
		return username;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getText()
	{
		return text;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

	public String getTime()
	{
		return time;
	}

	public void setImg(String img)
	{
		this.img = img;
	}

	public String getImg()
	{
		return img;
	}

	public void setImgs(String[] imgs)
	{
		this.imgs = imgs;
	}

	public String[] getImgs()
	{
		return imgs;
	}
}

