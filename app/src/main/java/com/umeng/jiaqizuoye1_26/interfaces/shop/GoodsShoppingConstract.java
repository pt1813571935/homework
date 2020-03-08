package com.umeng.jiaqizuoye1_26.interfaces.shop;


import com.umeng.jiaqizuoye1_26.bean.CartBean;
import com.umeng.jiaqizuoye1_26.bean.DetailBean;
import com.umeng.jiaqizuoye1_26.bean.GoodsShoppingBottomListBean;
import com.umeng.jiaqizuoye1_26.interfaces.IBaseView;
import com.umeng.jiaqizuoye1_26.interfaces.IPresenter;

public interface GoodsShoppingConstract {
    interface View extends IBaseView {
        void DetailDataReturn(DetailBean detailBean);
        void GoodsShoppingBottomListDataReturn(GoodsShoppingBottomListBean goodsShoppingBottomListBean);
        //购物车数据返回
        void CartDataReturn(CartBean cartBean);
    }
    interface Percenter extends IPresenter<View>{
        void getDetailData(int id);
        void getGoodsShoppingBottomListData(int id);
        //添加购物车
        void addCartData(String goodsId, int number, String productId);
    }
}
