package com.umeng.jiaqizuoye1_26.interfaces.register;


import com.umeng.jiaqizuoye1_26.bean.RegisterBean;
import com.umeng.jiaqizuoye1_26.interfaces.IBaseView;
import com.umeng.jiaqizuoye1_26.interfaces.IPresenter;

public interface RegisterConstract {

    interface View extends IBaseView {
        void RegisterReturn(RegisterBean registerBean);
    }

    interface Persenter extends IPresenter<View> {
        void getRegisterData(String nickname, String password);
    }
}
