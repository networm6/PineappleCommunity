package com.cyanmango.app.Base;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import com.cyanmango.app.view.BaseView;
import java.io.Serializable;

public class BaseFragment<V> extends Fragment implements BaseView<V>
{

	@Override
	public void onclick(V t)
	{
	}
    public void mainclick(){}
	@Override
	public void onerror(Exception e)
	{
		((BaseActivity)getActivity()).onerror(e);
	}
	
	public View root;
	public <K extends android.view.View> K findViewById(int id) {
		return root.findViewById(id);
	}
	public final void runOnUiThread(java.lang.Runnable action) {
		getActivity().runOnUiThread(action);
	}
	public void jump(Class<?> in){
		startActivity(new Intent(getActivity(),in));
	}
	public<T extends Serializable> void jump(Class<?> in,T t){
		Intent ent=new Intent(getActivity(),in);
		ent.putExtra("key",t);
		startActivity(ent);
	}
}
