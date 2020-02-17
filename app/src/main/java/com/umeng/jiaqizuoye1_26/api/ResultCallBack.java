package com.umeng.jiaqizuoye1_26.api;

import com.umeng.jiaqizuoye1_26.bean.PageBean;

public interface ResultCallBack {
    void onSuccess(PageBean pageBean);
    void onFail(String s);
}
