package com.umeng.jiaqizuoye1_26.interfaces.topic;

import com.umeng.jiaqizuoye1_26.bean.PageBean;
import com.umeng.jiaqizuoye1_26.interfaces.IBaseView;
import com.umeng.jiaqizuoye1_26.interfaces.IPresenter;

public interface Topic {
    interface View extends IBaseView {
        void returnTopic(PageBean pageBean);
    }
    interface Presenter extends IPresenter<View> {
        void getTopic();
    }
}
