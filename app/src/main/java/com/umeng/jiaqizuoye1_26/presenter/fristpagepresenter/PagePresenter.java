package com.umeng.jiaqizuoye1_26.presenter.fristpagepresenter;

import com.umeng.jiaqizuoye1_26.base.BasePresenter;
import com.umeng.jiaqizuoye1_26.bean.PageBean;
import com.umeng.jiaqizuoye1_26.interfaces.fristpage.FirstPage;
import com.umeng.jiaqizuoye1_26.model.CommonSubscriber;
import com.umeng.jiaqizuoye1_26.model.http.HttpManager;
import com.umeng.jiaqizuoye1_26.utils.RxUtils;

public class PagePresenter  extends BasePresenter<FirstPage.View> implements FirstPage.Presenter {
    @Override
    public void getFirstPage() {
        addSubscribe(HttpManager.getMyApi().getPageBean()
                .compose(RxUtils.<PageBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<PageBean>(mView){
                    @Override
                    public void onNext(PageBean pageBean) {
                        mView.returnFirstPage(pageBean );
                    }
                }));
    }
}
