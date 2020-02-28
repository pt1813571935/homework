package com.umeng.jiaqizuoye1_26.interfaces.brandinfo;

import com.umeng.jiaqizuoye1_26.bean.BrandDetailsBean;
import com.umeng.jiaqizuoye1_26.bean.GoodsDescListBean;
import com.umeng.jiaqizuoye1_26.interfaces.IBaseView;
import com.umeng.jiaqizuoye1_26.interfaces.IPresenter;

public interface BrandDetails {
    interface View extends IBaseView{
        void returnBrandDetails(BrandDetailsBean brandDetailsBean);
        void returnBrandDetailsList(GoodsDescListBean goodsDescListBean);

    }
    interface Presenter extends IPresenter<View>{
        void getBrandDetails(int id);
        void getBrandDetailsList(int id);
    }
}
