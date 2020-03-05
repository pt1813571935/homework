package com.umeng.jiaqizuoye1_26;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.umeng.jiaqizuoye1_26.adapter.BrandInfoAdapter;
import com.umeng.jiaqizuoye1_26.base.BaseActivity;
import com.umeng.jiaqizuoye1_26.base.BaseAdapter;
import com.umeng.jiaqizuoye1_26.bean.BrandManufacturer;
import com.umeng.jiaqizuoye1_26.interfaces.brandinfo.BrandInfo;
import com.umeng.jiaqizuoye1_26.presenter.BrandInfoPresenter;
import com.umeng.jiaqizuoye1_26.view.BrandDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class BrandInfoActivity extends BaseActivity<BrandInfo.View, BrandInfo.Presenter>implements BrandInfo.View,BaseAdapter.ItemClickHandler {

    private RecyclerView mBrandinfoRec;
    private BrandInfoAdapter infoAdapter;
    private List<BrandManufacturer.DataBeanX.DataBean> data;


    @Override
    protected int getLayout() {
        return R.layout.activity_brand_info;
    }

    protected void initView() {
        mBrandinfoRec = (RecyclerView) findViewById(R.id.brandinfo_rec);
        data = new ArrayList<>();
        infoAdapter = new BrandInfoAdapter(data, context);
        mBrandinfoRec.setAdapter(infoAdapter);
        mBrandinfoRec.setLayoutManager(new LinearLayoutManager(context));

        infoAdapter.setOnItemClickHandler( this);
    }

    @Override
    protected void initData() {
        presenter.getBrandInfo(1,1000);
    }

    @Override
    protected BrandInfo.Presenter createPersenter() {
        return new BrandInfoPresenter();
    }

    @Override
    public void returnBrandInfo(BrandManufacturer brandManufacturer) {
       infoAdapter.updata(brandManufacturer.getData().getData());

    }

    @Override
    public void itemClick(int position, BaseAdapter.BaseViewHolder holder) {
        Intent intent = new Intent(this, BrandDetailsActivity.class);
        intent.putExtra("id",data.get(position).getId());
        startActivity(intent);

    }
}
