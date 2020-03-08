package com.umeng.jiaqizuoye1_26.presenter.login;


import com.umeng.jiaqizuoye1_26.base.BasePresenter;
import com.umeng.jiaqizuoye1_26.bean.UserBean;
import com.umeng.jiaqizuoye1_26.interfaces.login.LoginConstract;
import com.umeng.jiaqizuoye1_26.model.CommonSubscriber;
import com.umeng.jiaqizuoye1_26.model.http.HttpManager;
import com.umeng.jiaqizuoye1_26.utils.RxUtils;

public class LoginPercenter extends BasePresenter<LoginConstract.View> implements LoginConstract.Persenter{
    @Override
    public void login(String nickname, String password) {
        addSubscribe(HttpManager.getMyApi().login(nickname,password)
                .compose(RxUtils.<UserBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<UserBean>(mView){

                    @Override
                    public void onNext(UserBean userBean) {
                        if(userBean.getErrno() == 0){
                            mView.loginReturn(userBean);
                        }else{
                            mView.showTips(userBean.getErrmsg());
                        }
                    }
                }));
    }
}
