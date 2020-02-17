package com.umeng.jiaqizuoye1_26.presenter;

import com.umeng.jiaqizuoye1_26.api.ResultCallBack;
import com.umeng.jiaqizuoye1_26.bean.PageBean;
import com.umeng.jiaqizuoye1_26.model.MainModel;
import com.umeng.jiaqizuoye1_26.view.MainView;

public class MainPresenter {
    private MainView view;
    private final MainModel model;

    public MainPresenter(MainView view) {
        model = new MainModel();
        this.view = view;
    }

    public void getData() {
        model.getData(new ResultCallBack() {
            @Override
            public void onSuccess(PageBean pageBean) {
                if (pageBean!=null&& pageBean.getData()!=null&& pageBean.getData().getBanner()!=null&& pageBean.getData().getBanner().size()>0&&view!=null){
                    view.setData(pageBean);
                }
            }

            @Override
            public void onFail(String s) {

            }
        });
    }
}
