package com.umeng.jiaqizuoye1_26.presenter.fristpagepresenter;

import com.umeng.jiaqizuoye1_26.base.BaseFragment;
import com.umeng.jiaqizuoye1_26.base.BasePersenter;
import com.umeng.jiaqizuoye1_26.bean.PageBean;
import com.umeng.jiaqizuoye1_26.interfaces.fristpage.FristPage;
import com.umeng.jiaqizuoye1_26.model.CommonSubscriber;
import com.umeng.jiaqizuoye1_26.model.http.HttpManager;
import com.umeng.jiaqizuoye1_26.utils.RxUtils;

public class PagePresenter  extends BasePersenter<FristPage.View> implements FristPage.Presenter {
    @Override
    public void getFristPage() {
        addSubscribe(HttpManager.getMyApi().getPageBean()
                .compose(RxUtils.<PageBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<PageBean>(mView){
                    @Override
                    public void onNext(PageBean pageBean) {
                        mView.returnFristPage(pageBean );
                    }
                }));
    }
}
