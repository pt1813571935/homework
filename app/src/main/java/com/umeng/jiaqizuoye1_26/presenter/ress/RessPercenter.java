package com.umeng.jiaqizuoye1_26.presenter.ress;


import com.umeng.jiaqizuoye1_26.base.BasePresenter;
import com.umeng.jiaqizuoye1_26.bean.RessBean;
import com.umeng.jiaqizuoye1_26.interfaces.ress.RessConstract;
import com.umeng.jiaqizuoye1_26.model.CommonSubscriber;
import com.umeng.jiaqizuoye1_26.model.http.HttpManager;
import com.umeng.jiaqizuoye1_26.utils.RxUtils;

public class RessPercenter extends BasePresenter<RessConstract.View> implements RessConstract.Percenter {
    @Override
    public void getressData() {
        addSubscribe(HttpManager.getMyApi().getAddress()
        .compose(RxUtils.<RessBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<RessBean>(mView) {
            @Override
            public void onNext(RessBean ressBean) {
                mView.RessDataReturn(ressBean);
            }
        }));
    }
}
