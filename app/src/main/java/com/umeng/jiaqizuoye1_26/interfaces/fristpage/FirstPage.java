package com.umeng.jiaqizuoye1_26.interfaces.fristpage;

import com.umeng.jiaqizuoye1_26.bean.PageBean;
import com.umeng.jiaqizuoye1_26.interfaces.IBaseView;
import com.umeng.jiaqizuoye1_26.interfaces.IPresenter;

public interface FirstPage {
    interface View extends IBaseView{
        void returnFirstPage(PageBean pageBean);
    }
    interface Presenter extends IPresenter<View> {
        void getFirstPage();
    }
}
