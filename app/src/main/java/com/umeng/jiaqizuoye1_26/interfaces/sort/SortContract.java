package com.umeng.jiaqizuoye1_26.interfaces.sort;

import com.umeng.jiaqizuoye1_26.bean.SortItemListBean;
import com.umeng.jiaqizuoye1_26.interfaces.IBaseView;
import com.umeng.jiaqizuoye1_26.interfaces.IPresenter;

public interface SortContract {

    interface View extends IBaseView {
        void SortListDataReturn(SortItemListBean sortItemListBean);
    }
    interface Percenter extends IPresenter<View> {
        void getSortListData(int id, int page, int size);
    }
}
