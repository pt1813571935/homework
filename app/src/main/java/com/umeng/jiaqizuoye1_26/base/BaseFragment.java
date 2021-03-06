package com.umeng.jiaqizuoye1_26.base;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.umeng.jiaqizuoye1_26.interfaces.IBaseView;
import com.umeng.jiaqizuoye1_26.interfaces.IPresenter;
import com.umeng.jiaqizuoye1_26.utils.SystemUtils;


public abstract class BaseFragment<V extends IBaseView,P extends IPresenter> extends Fragment implements IBaseView {

    protected Context context;
    protected P presenter;
    protected Activity activity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        if(!SystemUtils.checkNetWork()){
            /*view = inflater.inflate(R.layout.layout_network_error,null);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);*/
        }else{
            view = inflater.inflate(getLayout(),null);
            context = this.getContext();
            activity = getActivity();
            initView(view);
            presenter = createPresenter();
            presenter.attachView(this);
            initData();
        }
        return view;
    }



    //获取布局
    protected abstract int getLayout();

    //初始化布局
    protected abstract void initView(View view);

    //初始化数据
    protected abstract void initData();

    //创建P
    protected abstract P createPresenter();
    @Override
    public void onResume() {
        super.onResume();
        if(presenter != null){
            presenter.attachView(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter != null){
            presenter.detachView();
        }

    }
}
