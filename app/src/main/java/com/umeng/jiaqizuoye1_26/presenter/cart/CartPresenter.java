package com.umeng.jiaqizuoye1_26.presenter.cart;


import com.umeng.jiaqizuoye1_26.base.BasePresenter;
import com.umeng.jiaqizuoye1_26.bean.CartDeleteBean;
import com.umeng.jiaqizuoye1_26.bean.CartListsBean;
import com.umeng.jiaqizuoye1_26.bean.CartUpdataBean;
import com.umeng.jiaqizuoye1_26.interfaces.cart.CartContract;
import com.umeng.jiaqizuoye1_26.model.CommonSubscriber;
import com.umeng.jiaqizuoye1_26.model.http.HttpManager;
import com.umeng.jiaqizuoye1_26.utils.RxUtils;

public class CartPresenter extends BasePresenter<CartContract.View> implements CartContract.Percenter {
    @Override
    public void getCartListData() {
        addSubscribe(HttpManager.getMyApi().getCartListsData()
        .compose(RxUtils.<CartListsBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CartListsBean>(mView) {
                    @Override
                    public void onNext(CartListsBean cartListsBean) {
                        mView.cartDataReturn(cartListsBean);
                    }
                })
        );
    }

    @Override
    public void getCartGoodsUpdata(String productId, String goodsId, String number, String id) {
        addSubscribe(HttpManager.getMyApi().setGoodsMum(productId,goodsId,number,id)
                .compose(RxUtils.<CartUpdataBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CartUpdataBean>(mView) {
                    @Override
                    public void onNext(CartUpdataBean cartUpdataBean) {
                        mView.cartGoodsUpdataReturn(cartUpdataBean);
                    }
                })
        );
    }

    @Override
    public void getCartGoodsDeleteData(String productIds) {
        addSubscribe(HttpManager.getMyApi().getCartGoodsDeleteData(productIds)
                .compose(RxUtils.<CartDeleteBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CartDeleteBean>(mView) {
                    @Override
                    public void onNext(CartDeleteBean result) {
                        mView.cartGoodsDeleteDatareturn(result);
                    }
                })
        );
    }
}
