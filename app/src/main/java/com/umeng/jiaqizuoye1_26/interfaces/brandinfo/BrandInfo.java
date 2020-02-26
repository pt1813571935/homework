package com.umeng.jiaqizuoye1_26.interfaces.brandinfo;

import com.umeng.jiaqizuoye1_26.bean.BrandManufacturer;
import com.umeng.jiaqizuoye1_26.interfaces.IBaseView;
import com.umeng.jiaqizuoye1_26.interfaces.IPresenter;

public interface BrandInfo {
    interface View extends IBaseView {
        void returnBrandInfo(BrandManufacturer brandManufacturer);
    }
    interface Presenter extends IPresenter<View> {
        void getBrandInfo(int page,int size);
    }
}
