package com.umeng.jiaqizuoye1_26.presenter;

import com.umeng.jiaqizuoye1_26.base.BasePresenter;
import com.umeng.jiaqizuoye1_26.bean.ClassifyTabBean;
import com.umeng.jiaqizuoye1_26.interfaces.classify.ClassifyTab;
import com.umeng.jiaqizuoye1_26.model.CommonSubscriber;
import com.umeng.jiaqizuoye1_26.model.http.HttpManager;
import com.umeng.jiaqizuoye1_26.utils.RxUtils;

public class ClassifyTabPresenter extends BasePresenter<ClassifyTab.View> implements ClassifyTab.Presenter {
    @Override
    public void getClassifyTab() {
        addSubscribe(HttpManager.getMyApi().getClassifyBean()
                .compose(RxUtils.<ClassifyTabBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<ClassifyTabBean>(mView){
                    @Override
                    public void onNext(ClassifyTabBean classifyTabBean) {
                        mView.returnClassifyTab(classifyTabBean );
                    }
                }));
    }

    
}
