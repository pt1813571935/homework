package com.umeng.jiaqizuoye1_26.model.apis;





import com.umeng.jiaqizuoye1_26.bean.BrandDetailsBean;
import com.umeng.jiaqizuoye1_26.bean.BrandManufacturer;
import com.umeng.jiaqizuoye1_26.bean.CartBean;
import com.umeng.jiaqizuoye1_26.bean.CartDeleteBean;
import com.umeng.jiaqizuoye1_26.bean.CartListsBean;
import com.umeng.jiaqizuoye1_26.bean.CartUpdataBean;
import com.umeng.jiaqizuoye1_26.bean.ClassifyListBean;
import com.umeng.jiaqizuoye1_26.bean.ClassifyTabBean;
import com.umeng.jiaqizuoye1_26.bean.ClassityBean;
import com.umeng.jiaqizuoye1_26.bean.DetailBean;
import com.umeng.jiaqizuoye1_26.bean.GoodsDescListBean;
import com.umeng.jiaqizuoye1_26.bean.GoodsShoppingBottomListBean;
import com.umeng.jiaqizuoye1_26.bean.MoodsBean;
import com.umeng.jiaqizuoye1_26.bean.MoodsBeanPhoto;
import com.umeng.jiaqizuoye1_26.bean.NewBean;
import com.umeng.jiaqizuoye1_26.bean.PageBean;
import com.umeng.jiaqizuoye1_26.bean.RegisterBean;
import com.umeng.jiaqizuoye1_26.bean.RessBean;
import com.umeng.jiaqizuoye1_26.bean.SortItemListBean;
import com.umeng.jiaqizuoye1_26.bean.UserBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    //新品列表
    @GET("goods/list")
    Flowable<NewBean>getNewBean(@Query("isNew") int ne,
                                @Query("page") int page,
                                @Query("size") int size,
                                @Query("order") String order,
                                @Query("sort") String sort,
                                @Query("categoryId") int id);

    //人气列表图片
    @GET("goods/hot")
    Flowable<MoodsBeanPhoto>getMoodsBeanPhoto();

    //人气列表
    @GET("goods/list")

    Flowable<MoodsBean>getMoodsBean(@Query("isHot")int hot,
                                    @Query("page") int page,
                                    @Query("size") int size,
                                    @Query("order") String order,
                                    @Query("sort") String de,
                                    @Query("categoryId") int id);

    //商品购买详情页
    @GET("goods/detail")
    Flowable<DetailBean> getDetailData(@Query("id") int id);

    //商品详情页底部list数据
    @GET("goods/related")
    Flowable<GoodsShoppingBottomListBean> getGoodsShoppingBottomListlData(@Query("id") int id);

    //添加购物车
    @POST("cart/add")
    @FormUrlEncoded
    Flowable<CartBean> getCarNum(@Field("goodsId") String goodsid,
                                 @Field("number") int number,
                                 @Field("productId") String productid);

    //登录
    @POST("auth/login")
    @FormUrlEncoded
    Flowable<UserBean> login(@Field("nickname") String nickname, @Field("password") String password);

    //注册
    @POST("auth/register")
    @FormUrlEncoded
    Flowable<RegisterBean> register(@Field("nickname") String nickname, @Field("password") String password);

    //分类详情页的list数据
    @GET("goods/list")
    Flowable<SortItemListBean> getSortItemListData(@Query("categoryId") int id, @Query("page") int page, @Query("size") int size);

    //获取购物车数据
    @GET("cart/index")
    Flowable<CartListsBean> getCartListsData();

    //修改商品数据
    @POST("cart/update")
    @FormUrlEncoded
    Flowable<CartUpdataBean> setGoodsMum(@Field("productId") String productId,
                                         @Field("goodsId") String goodsId,
                                         @Field("number") String number,
                                         @Field("id") String id);
    //删除购物车数据
    @POST("cart/delete")
    @FormUrlEncoded
    Flowable<CartDeleteBean> getCartGoodsDeleteData(@Field("productIds") String productIds);

    //获取用户的收货地址
    @GET("address/list")
    Flowable<RessBean> getAddress();
}
