package com.cyanmango.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.cyanmango.app.R;
import com.cyanmango.app.model.Item_Bean;
import com.cyanmango.app.util.UnitUtils;
import com.cyanmango.app.view.BaseView;
import java.util.List;
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.RepositoryViewHolder>
{

    private Context mContext ;
    private List<Item_Bean> repositoryList ;
    private BaseView bv;
	

	
    public HomeAdapter(Context context, List<Item_Bean> repositoryList){
        this.mContext = context ;
        this.repositoryList = repositoryList ;
    }
    public void setClick(BaseView in){
		bv=in;
	}

    @Override
    public HomeAdapter.RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_item,parent,false) ;
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeAdapter.RepositoryViewHolder holder, int position) {
        final Item_Bean bean = repositoryList.get(position);
		holder.imgs.removeAllViews();
		if(bean.getImgs()==null)
		holder.imgs.setVisibility(View.GONE);
		else for(String one:bean.getImgs()){
				ImageView oneimg= new ImageView(mContext);
				int px=UnitUtils.dip2px(60);
				LayoutParams parms=new LayoutParams(px,px);
				oneimg.setLayoutParams(parms);
				holder.imgs.addView(oneimg);
				Glide.with(mContext).load(one).into(oneimg);
			}
		Glide.with(mContext).load(bean.getImg()).into(holder.img);
        holder.title.setText(bean.getUsername());
		holder.text.setText(bean.getText());
		holder.time.setText(bean.getTime());
		holder.itemView.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					bv.onclick(bean);
				}
			});
		holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

    public class RepositoryViewHolder extends RecyclerView.ViewHolder{
        public CardView cv;
        public ImageView img;
		public TextView title,time,text;
		public LinearLayout imgs;
        public RepositoryViewHolder(View itemView) {
            super(itemView);
			cv=(CardView) itemView;
            img=itemView.findViewById(R.id.homeitemImageView);
			title=itemView.findViewById(R.id.homeitemTitle);
			time=itemView.findViewById(R.id.homeitemTime);
			text=itemView.findViewById(R.id.homeitemText);
            imgs=itemView.findViewById(R.id.homeitemimgs);
			cv.setRadius(UnitUtils.dip2px(25));
			cv.setCardElevation(12);
			cv.setCardBackgroundColor(Color.parseColor("#EFEFEF"));
        }


    }
}

