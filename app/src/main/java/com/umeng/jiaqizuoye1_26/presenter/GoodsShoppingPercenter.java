package com.umeng.jiaqizuoye1_26.presenter;


import com.umeng.jiaqizuoye1_26.base.BasePresenter;
import com.umeng.jiaqizuoye1_26.bean.CartBean;
import com.umeng.jiaqizuoye1_26.bean.DetailBean;
import com.umeng.jiaqizuoye1_26.bean.GoodsShoppingBottomListBean;
import com.umeng.jiaqizuoye1_26.interfaces.shop.GoodsShoppingConstract;
import com.umeng.jiaqizuoye1_26.model.CommonSubscriber;
import com.umeng.jiaqizuoye1_26.model.http.HttpManager;
import com.umeng.jiaqizuoye1_26.utils.RxUtils;

public class GoodsShoppingPercenter extends BasePresenter<GoodsShoppingConstract.View> implements GoodsShoppingConstract.Percenter {
    @Override
    public void getDetailData(int id) {
        addSubscribe(HttpManager.getMyApi().getDetailData(id)
        .compose(RxUtils.<DetailBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<DetailBean>(mView) {
             @Override
              public void onNext(DetailBean detailBean) {
                     mView.DetailDataReturn(detailBean);
                  }
               })
        );
    }

    @Override
    public void getGoodsShoppingBottomListData(int id) {
        addSubscribe(HttpManager.getMyApi().getGoodsShoppingBottomListlData(id)
                .compose(RxUtils.<GoodsShoppingBottomListBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<GoodsShoppingBottomListBean>(mView) {
                    @Override
                    public void onNext(GoodsShoppingBottomListBean result) {
                        mView.GoodsShoppingBottomListDataReturn(result);
                    }
                })
        );
    }

    @Override
    public void addCartData(String goodsId, int number, String productId) {
        addSubscribe(HttpManager.getMyApi().getCarNum(goodsId,number,productId)
        .compose(RxUtils.<CartBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<CartBean>(mView) {
            @Override
            public void onNext(CartBean cartBean) {
               mView.CartDataReturn(cartBean);
            }
        }));
    }
}
