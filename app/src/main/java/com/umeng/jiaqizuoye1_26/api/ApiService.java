package com.umeng.jiaqizuoye1_26.api;

import com.umeng.jiaqizuoye1_26.bean.PageBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String PageUrl="https://cdwan.cn/api/";
    @GET("index/index")
    Observable<PageBean>getData();
}
