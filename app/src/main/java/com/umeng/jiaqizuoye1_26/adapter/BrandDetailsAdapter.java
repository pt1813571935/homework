package com.umeng.jiaqizuoye1_26.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.jiaqizuoye1_26.R;
import com.umeng.jiaqizuoye1_26.base.BaseAdapter;
import com.umeng.jiaqizuoye1_26.bean.GoodsDescListBean;
import com.umeng.jiaqizuoye1_26.bean.PageBean;


import java.util.List;

public class BrandDetailsAdapter extends BaseAdapter {


    public BrandDetailsAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        return R.layout.branddetails;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {

        GoodsDescListBean.DataBeanX.DataBean list= (GoodsDescListBean.DataBeanX.DataBean) o;
        ImageView brand_img = (ImageView) holder.getView(R.id.brand_img);
        TextView brand_tv_name = (TextView) holder.getView(R.id.brand_tv_name);
        TextView brand_tv_title = (TextView) holder.getView(R.id.brand_tv_price);
        brand_tv_name.setText(list.getName());
        brand_tv_title.setText(list.getRetail_price()+"元起");
        Glide.with(mContext).load(list.getList_pic_url()).into(brand_img);

    }
}
