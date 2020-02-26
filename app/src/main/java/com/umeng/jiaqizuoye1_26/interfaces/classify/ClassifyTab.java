package com.umeng.jiaqizuoye1_26.interfaces.classify;

import com.umeng.jiaqizuoye1_26.bean.ClassifyListBean;
import com.umeng.jiaqizuoye1_26.bean.ClassifyTabBean;

import com.umeng.jiaqizuoye1_26.interfaces.IBaseView;
import com.umeng.jiaqizuoye1_26.interfaces.IPresenter;

public interface ClassifyTab {
    interface View extends IBaseView {
        void returnClassifyTab(ClassifyTabBean classifyTabBean);
        void returnClassifyList(ClassifyListBean classifyListBean);
    }
    interface Presenter extends IPresenter<View> {
        void getClassifyTab();
        void getClassifyList(int id);
    }
}
