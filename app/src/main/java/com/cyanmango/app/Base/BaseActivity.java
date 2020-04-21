package com.cyanmango.app.Base;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.cyanmango.app.util.ToastUtils;
import com.cyanmango.app.view.BaseView;
import simon.tuke.Tuke;

public class BaseActivity<T> extends AppCompatActivity implements BaseView<T>
{

	@Override
	public void onclick(T t)
	{
	}

	@Override
	public void onerror(Exception e)
	{
		//ToastUtils.Toast(e.getClass().getName());
		Tuke.write(e.getClass().getName()+"!"+System.currentTimeMillis(),e);
		ToastUtils.Toast("加载出错");
	}

	@Override
	protected void onStart()
	{
		setStatusBar();
		super.onStart();
	}
	
	private void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) 
			if((getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)==16)
				getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏图标和文字颜色为暗色
            else
				getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |View.SYSTEM_UI_FLAG_VISIBLE);
    }
	
	public void jump(Class<?> in){
		startActivity(new Intent(this,in));
	}
	
	public void hideActionBar(){
       // getActionBar().hide();
        getWindow().addFlags(67108864);
    }
	
	
}
