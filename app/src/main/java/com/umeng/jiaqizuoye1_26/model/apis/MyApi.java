package com.umeng.jiaqizuoye1_26.model.apis;





import com.umeng.jiaqizuoye1_26.bean.BrandDetailsBean;
import com.umeng.jiaqizuoye1_26.bean.BrandManufacturer;
import com.umeng.jiaqizuoye1_26.bean.ClassifyListBean;
import com.umeng.jiaqizuoye1_26.bean.ClassifyTabBean;
import com.umeng.jiaqizuoye1_26.bean.ClassityBean;
import com.umeng.jiaqizuoye1_26.bean.GoodsDescListBean;
import com.umeng.jiaqizuoye1_26.bean.PageBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MyApi {

    @GET("index/index")
    Flowable<PageBean> getPageBean();

    @GET("catalog/index")
    Flowable<ClassifyTabBean>getClassifyTab();

    @GET("catalog/current")
    Flowable<ClassifyListBean>getClassList(@Query("id") int id);

    //制造商品牌列表
    @GET("brand/list")
    Flowable<BrandManufacturer> getBrandInfo(@Query("page") int page, @Query("size") int size);
    //MuJI制造商详情页
    @GET("brand/detail")
    Flowable<BrandDetailsBean>getBrandDetails(@Query("id")int id);
    @GET("goods/list")
    Flowable<GoodsDescListBean> getGoodsList(@Query("brandId") int id, @Query("page") int page, @Query("size") int size);
}
