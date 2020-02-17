package com.umeng.jiaqizuoye1_26.interfaces.fristpage;

import com.umeng.jiaqizuoye1_26.bean.PageBean;
import com.umeng.jiaqizuoye1_26.interfaces.IBaseView;
import com.umeng.jiaqizuoye1_26.interfaces.IPersenter;

public interface FristPage {
    interface View extends IBaseView{
        void returnFristPage(PageBean pageBean);
    }
    interface Presenter extends IPersenter<View>{
        void getFristPage();
    }
}
