package com.cyanmango.app.presenter;
import com.cyanmango.app.view.RVview;

public interface RVPresenter extends BasePresenter<RVview>{

    void loadfromcloud();
	void loadmore()
    void refresh()
}

