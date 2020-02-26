package com.umeng.jiaqizuoye1_26.adapter;


import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.umeng.jiaqizuoye1_26.R;
import com.umeng.jiaqizuoye1_26.base.BaseAdapter;
import com.umeng.jiaqizuoye1_26.bean.PageBean;

import java.util.List;

public class HomeAdapter extends BaseAdapter {


    public HomeAdapter(List data, Context context) {
        super(data, context);
    }


    @Override
    public int getLayout() {
        return R.layout.home_layout;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {
        PageBean.DataBean.CategoryListBean.GoodsListBean list= (PageBean.DataBean.CategoryListBean.GoodsListBean) o;
        ImageView home_img = (ImageView) holder.getView(R.id.home_img);
        TextView home_tv_name = (TextView) holder.getView(R.id.home_tv_name);
        TextView home_tv_price = (TextView) holder.getView(R.id.home_tv_price);
        Glide.with(mContext).load(list.getList_pic_url()).into(home_img);
        home_tv_name.setText(list.getName());
        home_tv_price.setText(list.getRetail_price());
    }



}
