package com.umeng.jiaqizuoye1_26.presenter;

import com.umeng.jiaqizuoye1_26.base.BasePresenter;
import com.umeng.jiaqizuoye1_26.bean.BrandDetailsBean;
import com.umeng.jiaqizuoye1_26.bean.GoodsDescListBean;
import com.umeng.jiaqizuoye1_26.interfaces.brandinfo.BrandDetails;
import com.umeng.jiaqizuoye1_26.model.CommonSubscriber;
import com.umeng.jiaqizuoye1_26.model.http.HttpManager;
import com.umeng.jiaqizuoye1_26.utils.RxUtils;

public class BrandDetailsPresenter extends BasePresenter<BrandDetails.View> implements BrandDetails.Presenter {
    @Override
    public void getBrandDetails(int id) {
        addSubscribe(HttpManager.getMyApi().getBrandDetails(id)
                .compose(RxUtils.<BrandDetailsBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<BrandDetailsBean>(mView){
                    @Override
                    public void onNext(BrandDetailsBean brandDetailsBean) {
                        mView.returnBrandDetails(brandDetailsBean );
                    }
                }));
    }
    @Override
    public void getBrandDetailsList(int id) {
        addSubscribe(HttpManager.getMyApi().getGoodsList(id,1,100)
                .compose(RxUtils.<GoodsDescListBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<GoodsDescListBean>(mView){

                    @Override
                    public void onNext(GoodsDescListBean goodsDescListBean) {
                        mView.returnBrandDetailsList(goodsDescListBean);
                    }
                }));
    }
}
