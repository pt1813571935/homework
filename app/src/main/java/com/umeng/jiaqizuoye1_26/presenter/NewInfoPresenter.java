package com.umeng.jiaqizuoye1_26.presenter;

import com.umeng.jiaqizuoye1_26.base.BasePresenter;
import com.umeng.jiaqizuoye1_26.bean.MoodsBeanPhoto;
import com.umeng.jiaqizuoye1_26.bean.NewBean;
import com.umeng.jiaqizuoye1_26.interfaces.infonew.NewInfo;
import com.umeng.jiaqizuoye1_26.model.CommonSubscriber;
import com.umeng.jiaqizuoye1_26.model.http.HttpManager;
import com.umeng.jiaqizuoye1_26.utils.RxUtils;

public class NewInfoPresenter extends BasePresenter<NewInfo.View> implements NewInfo.Presenter {
    @Override
    public void getNewBean(int ne, int page, int size, String order, String sort, int id) {
        addSubscribe(HttpManager.getMyApi().getNewBean(ne,page,size,order,sort,id)
                .compose(RxUtils.<NewBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<NewBean>(mView){
                    @Override
                    public void onNext(NewBean newBean) {
                        mView.returnNewBean(newBean);
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
