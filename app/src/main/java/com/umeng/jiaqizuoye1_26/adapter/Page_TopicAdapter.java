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

public class Page_TopicAdapter extends BaseAdapter {


    public Page_TopicAdapter(List data, Context context) {
        super(data, context);
    }


    @Override
    public int getLayout() {
        return  R.layout.page_topic;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {
        PageBean.DataBean.TopicListBean list= (PageBean.DataBean.TopicListBean) o;
        ImageView page_t_img = (ImageView) holder.getView(R.id.page_t_img);
        TextView page_tv_name = (TextView) holder.getView(R.id.page_tv_name);
        TextView page_tv_price = (TextView) holder.getView(R.id.page_tv_price);
        TextView page_tv_title = (TextView) holder.getView(R.id.page_tv_title);
        Glide.with(mContext).load(list.getItem_pic_url()).into(page_t_img);
        page_tv_name.setText(list.getTitle());
        page_tv_price.setText("￥"+list.getPrice_info()+"元起");
        page_tv_title.setText(list.getSubtitle());
    }


}
