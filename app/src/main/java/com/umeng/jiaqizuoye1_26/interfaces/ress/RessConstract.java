package com.umeng.jiaqizuoye1_26.interfaces.ress;


import com.umeng.jiaqizuoye1_26.bean.RessBean;
import com.umeng.jiaqizuoye1_26.interfaces.IBaseView;
import com.umeng.jiaqizuoye1_26.interfaces.IPresenter;

public interface RessConstract {

    interface View extends IBaseView {
        void RessDataReturn(RessBean ressBean);
    }
    interface Percenter extends IPresenter<View> {
        void getressData();
    }
}
