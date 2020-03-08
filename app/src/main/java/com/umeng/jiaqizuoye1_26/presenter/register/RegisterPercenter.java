package com.umeng.jiaqizuoye1_26.presenter.register;


import com.umeng.jiaqizuoye1_26.base.BasePresenter;
import com.umeng.jiaqizuoye1_26.bean.RegisterBean;
import com.umeng.jiaqizuoye1_26.interfaces.register.RegisterConstract;
import com.umeng.jiaqizuoye1_26.model.CommonSubscriber;
import com.umeng.jiaqizuoye1_26.model.http.HttpManager;
import com.umeng.jiaqizuoye1_26.utils.RxUtils;

public class RegisterPercenter extends BasePresenter<RegisterConstract.View> implements RegisterConstract.Persenter {

    @Override
    public void getRegisterData(String nickname, String password) {
        addSubscribe(HttpManager.getMyApi().register(nickname,password)
                .compose(RxUtils.<RegisterBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<RegisterBean>(mView) {
                    @Override
                    public void onNext(RegisterBean registerBean) {
                        mView.RegisterReturn(registerBean);
                    }
                })
        );
    }
}
