package com.umeng.jiaqizuoye1_26.interfaces.cart;


import com.umeng.jiaqizuoye1_26.bean.CartDeleteBean;
import com.umeng.jiaqizuoye1_26.bean.CartListsBean;
import com.umeng.jiaqizuoye1_26.bean.CartUpdataBean;
import com.umeng.jiaqizuoye1_26.interfaces.IBaseView;
import com.umeng.jiaqizuoye1_26.interfaces.IPresenter;

public interface CartContract {

    interface View extends IBaseView {
        void cartDataReturn(CartListsBean cartListsBean);
        void cartGoodsUpdataReturn(CartUpdataBean cartUpdataBean);
        void cartGoodsDeleteDatareturn(CartDeleteBean cartDeleteBean);
    }
    interface Percenter extends IPresenter<View> {
        void getCartListData();
        void getCartGoodsUpdata(String productId, String goodsId, String number, String id);
        void getCartGoodsDeleteData(String productIds);
    }
}
