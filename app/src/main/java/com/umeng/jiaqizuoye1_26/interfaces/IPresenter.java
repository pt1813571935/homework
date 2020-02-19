package com.umeng.jiaqizuoye1_26.interfaces;

public interface IPresenter<V extends IBaseView> {

    void attachView(V view);

    void detachView();

}
