package com.umeng.jiaqizuoye1_26.interfaces;

public interface IPersenter<V extends IBaseView> {

    void attchView(V view);

    void detachView();

}
