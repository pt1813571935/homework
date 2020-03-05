package com.umeng.jiaqizuoye1_26.presenter;

import com.umeng.jiaqizuoye1_26.base.BasePresenter;
import com.umeng.jiaqizuoye1_26.bean.BrandDetailsBean;
import com.umeng.jiaqizuoye1_26.bean.GoodsDescListBean;
import com.umeng.jiaqizuoye1_26.bean.MoodsBean;
import com.umeng.jiaqizuoye1_26.bean.MoodsBeanPhoto;
import com.umeng.jiaqizuoye1_26.interfaces.Moods.MoodsInfo;
import com.umeng.jiaqizuoye1_26.interfaces.brandinfo.BrandDetails;
import com.umeng.jiaqizuoye1_26.model.CommonSubscriber;
import com.umeng.jiaqizuoye1_26.model.http.HttpManager;
import com.umeng.jiaqizuoye1_26.utils.RxUtils;

public class MoodsPresenter extends BasePresenter<MoodsInfo.View> implements MoodsInfo.Presenter {
    @Override
    public void getMoodsInfo(int hot, int page,int size, String order, String de,int id) {
        addSubscribe(HttpManager.getMyApi().getMoodsBean(hot,page,size,order,de,id)
                .compose(RxUtils.<MoodsBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<MoodsBean>(mView){
                    @Override
                    public void onNext(MoodsBean moodsBean) {
                        mView.returnMoodsInfo(moodsBean );
                    }
                }));
    }

    @Override
    public void getMoodsInfoPhoto() {
        addSubscribe(HttpManager.getMyApi().getMoodsBeanPhoto()
                .compose(RxUtils.<MoodsBeanPhoto> rxScheduler())
                .subscribeWith(new CommonSubscriber<MoodsBeanPhoto>(mView){
                    @Override
                    public void onNext(MoodsBeanPhoto moodsBeanPhoto) {
                        mView.returnMoodsInfoPhoto(moodsBeanPhoto );
                    }
                }));
    }

}
