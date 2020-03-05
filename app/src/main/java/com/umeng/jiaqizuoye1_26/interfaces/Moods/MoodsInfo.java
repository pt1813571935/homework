package com.umeng.jiaqizuoye1_26.interfaces.Moods;


import com.umeng.jiaqizuoye1_26.bean.MoodsBean;
import com.umeng.jiaqizuoye1_26.bean.MoodsBeanPhoto;
import com.umeng.jiaqizuoye1_26.interfaces.IBaseView;
import com.umeng.jiaqizuoye1_26.interfaces.IPresenter;



public interface MoodsInfo {
    interface View extends IBaseView {
        void returnMoodsInfo(MoodsBean moodsBean);
        void returnMoodsInfoPhoto(MoodsBeanPhoto moodsBeanPhoto);
    }
    interface Presenter extends IPresenter<View> {
        void getMoodsInfo(int hot, int page,int size, String order, String de, int id);
        void getMoodsInfoPhoto();
    }
}
