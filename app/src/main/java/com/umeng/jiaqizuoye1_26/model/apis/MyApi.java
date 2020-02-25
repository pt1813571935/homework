package com.umeng.jiaqizuoye1_26.model.apis;





import com.umeng.jiaqizuoye1_26.bean.ClassifyTabBean;
import com.umeng.jiaqizuoye1_26.bean.PageBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;


public interface MyApi {

    @GET("index/index")
    Flowable<PageBean> getPageBean();

    @GET("catalog/index")
    Flowable<ClassifyTabBean>getClassifyBean();
}
