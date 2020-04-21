package com.cyanmango.app.view.impl;
import android.os.Bundle;
import android.support.mediacompat.R;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyanmango.app.Base.BaseActivity;
import com.cyanmango.app.Base.BaseFragment;
import com.cyanmango.app.adapter.MyFragmentPagerAdapter;
import com.irozon.justbar.BarItem;
import com.irozon.justbar.JustBar;
import com.irozon.justbar.interfaces.OnBarItemClickListener;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity
{
   ViewPager pages;
   JustBar bar;
   String[] all={"主页","发布","个人"};
   TextView title;
	 List<BaseFragment> list;
    ImageView back,titleimg;
     MyFragmentPagerAdapter adapter;
	int[] imgs={R.drawable.search,R.drawable.send,R.drawable.setting};
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		hideActionBar();
		setContentView(R.layout.main);
		bar=findViewById(R.id.mainJustBar);
		title=findViewById(R.id.mainTitle);
		pages=findViewById(R.id.mainViewPager);
		back=findViewById(R.id.mainback);
		titleimg=findViewById(R.id.maintitleimg);
		list = new ArrayList<>();
        list.add(new HomeFragment(back));
        list.add(new AddFragment() );
        list.add(new UserFragment() );
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), list);
        pages.setAdapter(adapter);
        pages.setCurrentItem(0);  //初始化显示第一个页面
		pages.setOffscreenPageLimit(2);
		bar.setOnBarItemClickListener(new OnBarItemClickListener(){

				@Override
				public void onBarItemClick(BarItem p1, int p2)
				{
					title.setText(all[p2]);
					pages.setCurrentItem(p2,true);
					titleimg.setImageResource(imgs[p2]);
					back.setVisibility(View.GONE);
				}
		});
		titleimg.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					list.get(bar.getselect()).mainclick();
				}
			});
		pages.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){

				@Override
				public void onPageScrolled(int p1, float p2, int p3)
				{
				}

				@Override
				public void onPageSelected(int p1)
				{
					bar.setSelected(p1);
				}

				@Override
				public void onPageScrollStateChanged(int p1)
				{
				}
			}) ;
        
	}
	
}
