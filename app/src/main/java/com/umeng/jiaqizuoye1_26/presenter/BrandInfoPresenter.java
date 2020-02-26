package com.umeng.jiaqizuoye1_26.presenter;

import com.umeng.jiaqizuoye1_26.base.BasePresenter;
import com.umeng.jiaqizuoye1_26.bean.BrandManufacturer;
import com.umeng.jiaqizuoye1_26.bean.PageBean;
import com.umeng.jiaqizuoye1_26.interfaces.brandinfo.BrandInfo;
import com.umeng.jiaqizuoye1_26.interfaces.topic.Topic;
import com.umeng.jiaqizuoye1_26.model.CommonSubscriber;
import com.umeng.jiaqizuoye1_26.model.http.HttpManager;
import com.umeng.jiaqizuoye1_26.utils.RxUtils;

public class BrandInfoPresenter extends BasePresenter<BrandInfo.View> implements BrandInfo.Presenter {
    @Override
    public void getBrandInfo(int page,int size) {
        addSubscribe(HttpManager.getMyApi().getBrandInfo(page, size)
                .compose(RxUtils.<BrandManufacturer> rxScheduler())
                .subscribeWith(new CommonSubscriber<BrandManufacturer>(mView){
                    @Override
                    public void onNext(BrandManufacturer brandManufacturer) {
                        mView.returnBrandInfo(brandManufacturer );
                    }
                }));
    }

}
