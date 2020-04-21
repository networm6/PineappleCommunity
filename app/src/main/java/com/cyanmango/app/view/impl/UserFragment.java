package com.cyanmango.app.view.impl;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cyanmango.app.Base.BaseFragment;
import com.cyanmango.app.R;
import com.cyanmango.app.model.User_Bean;
import com.cyanmango.app.util.ToastUtils;
import com.cyanmango.app.util.WebUtils;
import java.io.IOException;
import simon.tuke.Tuke;

public class UserFragment extends BaseFragment<View>
{
	CardView cv;
	TextView name;
	ImageView img;
    User_Bean UB;
	@Override
	public void mainclick()
	{
	    jump(AboutActivity.class);
	}
	@Override
	public void onResume()
	{
		super.onResume();
		UB=Tuke.get(true,"UB");
		if(UB==null)
		{
			img.setImageResource(R.drawable.nouserimg);
			name.setText("未登录");
		}else{
	    WebUtils.getURLimage(UB.getImg(), new WebUtils.CallBack<Bitmap,IOException>(){
				@Override
				public void reback(Bitmap get)
				{
					img.setImageBitmap(get);
				}
				@Override
				public void error(IOException e)
				{
				}
			});
		name.setText(UB.getName());
		}
	}
	
	@Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        root=(LinearLayout) inflater.inflate(R.layout.user,container,false);
		cv=findViewById(R.id.userCardView);
		name=findViewById(R.id.userTextView);
		img=findViewById(R.id.userImageView);
		cv.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					if(UB==null)
		            jump(LorSActivity.class);
		            else
		            jump(UserInfoActivity.class);
				}
			});
		return root;
	}

	
	
}
