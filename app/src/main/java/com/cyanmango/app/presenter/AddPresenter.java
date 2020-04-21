package com.cyanmango.app.presenter;
import android.view.View;
import com.cyanmango.app.view.Addview;
import java.util.List;

public interface AddPresenter extends BasePresenter<Addview>
{
	void setonclicklintener(View v)
	void post(boolean ispublic,String token,String text,List<String> imgs)
	void postimg(String token,String path)
}
