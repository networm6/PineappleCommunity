package com.cyanmango.app.presenter;
import com.cyanmango.app.view.BaseView;

public interface BasePresenter<T extends BaseView> {
    void attachView(T t);

    void detachView();
}

