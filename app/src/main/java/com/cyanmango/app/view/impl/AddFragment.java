package com.cyanmango.app.view.impl;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Switch;
import com.bumptech.glide.Glide;
import com.cyanmango.app.Base.BaseFragment;
import com.cyanmango.app.R;
import com.cyanmango.app.model.Add_Bean;
import com.cyanmango.app.model.User_Bean;
import com.cyanmango.app.presenter.impl.AddPresenterImpl;
import com.cyanmango.app.util.DiskUtils;
import com.cyanmango.app.util.ToastUtils;
import com.cyanmango.app.view.Addview;
import simon.tuke.Tuke;

public class AddFragment extends BaseFragment<View> implements Addview
{

	@Override
	public void success(boolean isimg,final String out)
	{
		if(!isimg){
		Tuke.clearDisk("AB");
		AB=new Add_Bean();
		ToastUtils.Toast(out);
		return;
		}
		AB.getImgs().add(out);
		runOnUiThread(new Runnable(){

				@Override
				public void run()
				{
					notifyImgs();
				}
			});
		
	}

	@Override
	public void mainclick()
	{
		mPresenter.post(msw.isChecked(),UB.getToken(),ed.getText().toString(),AB.getImgs());
	}
    
	@Override
	public void onclick(View t)
	{
		switch(t.getId()){
			case R.id.addsave:
				AB.setText(ed.getText().toString());
				Tuke.write("AB",AB);
				break;
			case R.id.addonclick:
				if(UB==null){
					ToastUtils.UIToast("没登录呢亲");
					break;}
				Intent it = new Intent(Intent.ACTION_PICK);
				it.setType("image/*");
				startActivityForResult(it, 1000);
				break;
		}
		
	}
	private AddPresenterImpl mPresenter;
	private EditText ed;
	private LinearLayout imgs;
	private ImageView add;
	private Add_Bean AB;
	private Button save;
	private User_Bean UB;
    private Switch msw;
	@Override
	public void onStart()
	{
		UB=Tuke.get("UB",null);
		super.onStart();
	}
	
	@Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        root=(LinearLayout) inflater.inflate(R.layout.add,container,false);
		ed=findViewById(R.id.addEditText);
		imgs=findViewById(R.id.addLinearLayout);
		add=findViewById(R.id.addonclick);
		save=findViewById(R.id.addsave);
		msw=findViewById(R.id.addSwitch);
		AB=Tuke.get("AB",null);
		mPresenter=new AddPresenterImpl();
		mPresenter.attachView(this);
		mPresenter.setonclicklintener(add);
		mPresenter.setonclicklintener(save);
		if(AB!=null){
			ed.setText(AB.getText());
		}else
		AB=new Add_Bean();
		notifyImgs();
		return root;
	}
    private void notifyImgs(){
		imgs.removeAllViews();
		for(String one:AB.getImgs()){
		ImageView iv=new ImageView(getActivity());
		LayoutParams par=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
	   iv.setLayoutParams(par);
		imgs.addView(iv);
		Glide.with(getActivity()).load(one).into(iv);
		}
		}
	@Override
	public void onDestroy()
	{
		mPresenter.detachView();
		super.onDestroy();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 1000 && resultCode == Activity.RESULT_OK)
			mPresenter.postimg(UB.getToken(),DiskUtils.getRealPathFromUri(data.getData()));
			ToastUtils.UIToast("开始上传");
	}
	
	
}
