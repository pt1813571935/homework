package com.umeng.jiaqizuoye1_26.presenter;


import com.umeng.jiaqizuoye1_26.base.BasePresenter;
import com.umeng.jiaqizuoye1_26.bean.SortItemListBean;
import com.umeng.jiaqizuoye1_26.interfaces.sort.SortContract;
import com.umeng.jiaqizuoye1_26.model.CommonSubscriber;
import com.umeng.jiaqizuoye1_26.model.http.HttpManager;
import com.umeng.jiaqizuoye1_26.utils.RxUtils;

public class SortListPercenter extends BasePresenter<SortContract.View> implements SortContract.Percenter {

    @Override
    public void getSortListData(int id, int page, int size) {
        addSubscribe(HttpManager.getMyApi().getSortItemListData(id,page,size)
        .compose(RxUtils.<SortItemListBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<SortItemListBean>(mView){

            @Override
            public void onNext(SortItemListBean sortItemListBean) {
                mView.SortListDataReturn(sortItemListBean);
            }
        }));
    }
}
