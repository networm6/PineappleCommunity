package com.cyanmango.app.view.impl;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.cyanmango.app.Base.BaseFragment;
import com.cyanmango.app.R;
import com.cyanmango.app.adapter.HomeAdapter;
import com.cyanmango.app.model.Item_Bean;
import com.cyanmango.app.presenter.impl.RvPresenterImpl;
import com.cyanmango.app.util.ToastUtils;
import com.cyanmango.app.view.RVview;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<Item_Bean> implements RVview
{
   
   public HomeFragment(View in){
	   back=in;
   }
	private View back;
    private List<Item_Bean> datas=new ArrayList();
    private RvPresenterImpl PresenterImpl;
	private RecyclerView Rv;
	private HomeAdapter adapter;
	private SwipeRefreshLayout fresh;
	@Override
	public void onRefresh()
	{
		Toast.makeText(getActivity(),"清除",10).show();
		datas.clear();
	}
	@Override
	public void mainclick()
	{

		ToastUtils.UIToast("home");
	}
	@Override
	public void onerror(Exception e)
	{
		fresh.setRefreshing(false);
		super.onerror(e);
	}
	

	@Override
	public void onclick(final Item_Bean bean)
	{
		runOnUiThread(new Runnable(){

				@Override
				public void run()
				{
					jump(TipInfoActivity.class,bean);
				}
			});
	}
	

	@Override
	public void showRecyclerView( List<Item_Bean> tList)
	{
		
		datas.addAll(tList);
		runOnUiThread(new Runnable(){

				@Override
				public void run()
				{
				fresh.setRefreshing(false);
				adapter.notifyDataSetChanged();
				}
			});
	}

	@Override
	public void onDestroyView()
	{
		PresenterImpl.detachView();
		super.onDestroyView();
	}
	@Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        root=inflater.inflate(R.layout.home,container,false);
		Rv=findViewById(R.id.homeRecyclerView);
		fresh=findViewById(R.id.homeSwipeRefreshLayout);
		back.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					Rv.scrollToPosition(0);
					p1.setVisibility(View.GONE);
				}
			});
		PresenterImpl=new RvPresenterImpl();
		PresenterImpl.attachView(this);
		PresenterImpl.loadfromcloud();
		Rv.addOnScrollListener(new RecyclerView.OnScrollListener(){
				@Override
				public final void onScrolled(RecyclerView recyclerView, int dx, int dy) {
					if (!recyclerView.canScrollVertically(-1)) 
						back.setVisibility(View.GONE);
					else if (!recyclerView.canScrollVertically(1)) 
						PresenterImpl.loadmore();
					else if (dy < 0) 
						back.setVisibility(View.VISIBLE);
					else if (dy > 0) 
						back.setVisibility(View.GONE);
				}
		});
		adapter = new HomeAdapter(getActivity(),datas);
		adapter.setClick(this);
		Rv.setLayoutManager(new LinearLayoutManager(getActivity()));
		Rv.setAdapter(adapter);
		fresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
				@Override
				public void onRefresh()
				{
					PresenterImpl.refresh();
				}
			});
		return root;
	}
    
	
}
