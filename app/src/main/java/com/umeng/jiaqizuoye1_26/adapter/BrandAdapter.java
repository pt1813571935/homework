package com.umeng.jiaqizuoye1_26.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.jiaqizuoye1_26.R;
import com.umeng.jiaqizuoye1_26.base.BaseAdapter;

import com.umeng.jiaqizuoye1_26.bean.PageBean;

import java.util.List;

public  class BrandAdapter extends BaseAdapter {


    public BrandAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        return R.layout.brand_layout;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {

        PageBean.DataBean.BrandListBean list = (PageBean.DataBean.BrandListBean) o;
        ImageView img = (ImageView) holder.getView(R.id.iv_ck);
        TextView name = (TextView) holder.getView(R.id.tv_ck);
        TextView price = (TextView) holder.getView(R.id.zhi_price);
        name.setText(list.getName());
        price.setText(list.getFloor_price()+"元起"+"");
        Glide.with(mContext).load(list.getNew_pic_url()).into(img);
    }
}
