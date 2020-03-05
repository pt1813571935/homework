package com.umeng.jiaqizuoye1_26.interfaces.infonew;

import com.umeng.jiaqizuoye1_26.bean.MoodsBeanPhoto;
import com.umeng.jiaqizuoye1_26.bean.NewBean;
import com.umeng.jiaqizuoye1_26.interfaces.IBaseView;
import com.umeng.jiaqizuoye1_26.interfaces.IPresenter;


public interface NewInfo {
    interface View extends IBaseView {
        void returnNewBean(NewBean newBean);
        void returnMoodsInfoPhoto(MoodsBeanPhoto moodsBeanPhoto);

    }
    interface Presenter extends IPresenter<View> {
        void getNewBean(int ne, int page, int size, String order, String sort, int id);
        void getMoodsInfoPhoto();
    }
}
