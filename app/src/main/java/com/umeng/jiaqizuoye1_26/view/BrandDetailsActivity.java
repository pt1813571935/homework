package com.umeng.jiaqizuoye1_26.view;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.umeng.jiaqizuoye1_26.R;
import com.umeng.jiaqizuoye1_26.adapter.BrandDetailsAdapter;
import com.umeng.jiaqizuoye1_26.base.BaseActivity;
import com.umeng.jiaqizuoye1_26.bean.BrandDetailsBean;
import com.umeng.jiaqizuoye1_26.bean.GoodsDescListBean;
import com.umeng.jiaqizuoye1_26.interfaces.brandinfo.BrandDetails;
import com.umeng.jiaqizuoye1_26.presenter.BrandDetailsPresenter;

import java.util.ArrayList;
import java.util.List;

public class BrandDetailsActivity extends BaseActivity<BrandDetails.View, BrandDetails.Presenter> implements BrandDetails.View {

    private ImageView mIvDesc;
    /**
     * 123
     */
    private TextView mTvDescTitle;
    /**
     * 123
     */
    private TextView mTvDescDesc;
    private RecyclerView mRecDesc;
    private BrandDetailsAdapter detailsAdapter;
    private List<GoodsDescListBean.DataBeanX.DataBean> list;



    @Override
    protected int getLayout() {
        return R.layout.activity_brand_details;
    }

    @Override
    protected void initView() {

        mIvDesc = (ImageView) findViewById(R.id.iv_desc);
        mTvDescTitle = (TextView) findViewById(R.id.tv_desc_title);
        mTvDescDesc = (TextView) findViewById(R.id.tv_desc_desc);
        mRecDesc = (RecyclerView) findViewById(R.id.rec_desc);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRecDesc.setLayoutManager(gridLayoutManager);


        list = new ArrayList<>();
        detailsAdapter = new BrandDetailsAdapter(list,context);
        mRecDesc.setAdapter(detailsAdapter);
    }

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id", -1);
        if (id != -1){
           presenter.getBrandDetails(id);
            presenter.getBrandDetailsList(id);
        }
    }

    @Override
    protected BrandDetails.Presenter createPersenter() {
        return new BrandDetailsPresenter();
    }

    @Override
    public void returnBrandDetails(BrandDetailsBean brandDetailsBean) {
        BrandDetailsBean.DataBean.BrandBean brand = brandDetailsBean.getData().getBrand();
        mTvDescTitle.setText(brand.getName());
        mTvDescDesc.setText(brand.getSimple_desc());
        Glide.with(context).load(brand.getNew_pic_url()).into(mIvDesc);
    }

    @Override
    public void returnBrandDetailsList(GoodsDescListBean goodsDescListBean) {
        List<GoodsDescListBean.DataBeanX.GoodsListBean> goodsList = goodsDescListBean.getData().getGoodsList();
        List<GoodsDescListBean.DataBeanX.DataBean> data = goodsDescListBean.getData().getData();
        detailsAdapter.updata(data);
    }
}
