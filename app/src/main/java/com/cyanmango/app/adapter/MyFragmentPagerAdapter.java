package com.cyanmango.app.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.cyanmango.app.Base.BaseFragment;
import java.util.List;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private FragmentManager mfragmentManager;
    private List<BaseFragment> mlist;


    public MyFragmentPagerAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        this.mlist = list;
    }

    

    @Override
    public Fragment getItem(int arg0) {
        return mlist.get(arg0);//显示第几个页面
    }

    @Override
    public int getCount() {
        return mlist.size();//有几个页面
    }
}


