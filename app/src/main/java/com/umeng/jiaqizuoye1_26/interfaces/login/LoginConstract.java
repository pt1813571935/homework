package com.umeng.jiaqizuoye1_26.interfaces.login;


import com.umeng.jiaqizuoye1_26.bean.UserBean;
import com.umeng.jiaqizuoye1_26.interfaces.IBaseView;
import com.umeng.jiaqizuoye1_26.interfaces.IPresenter;

public interface LoginConstract {

    interface View extends IBaseView {
        void loginReturn(UserBean result);
        void showTips(String error);
    }

    interface Persenter extends IPresenter<View> {
        void login(String nickname, String password);
    }
}
