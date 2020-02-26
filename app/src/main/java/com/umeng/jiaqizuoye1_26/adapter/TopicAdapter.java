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

public class TopicAdapter extends BaseAdapter {


    public TopicAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        return  R.layout.topic;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {
        PageBean.DataBean.TopicListBean list= (PageBean.DataBean.TopicListBean) o;
        ImageView topic_img = (ImageView) holder.getView(R.id.topic_img);
        TextView topic_name = (TextView) holder.getView(R.id.topic_name);
        TextView topic_price = (TextView) holder.getView(R.id.topic_price);
        TextView topic_title = (TextView) holder.getView(R.id.topic_title);
        Glide.with(mContext).load(list.getItem_pic_url()).into(topic_img);
        topic_title.setText(list.getTitle());
        topic_name.setText(list.getSubtitle());
        topic_price.setText("￥"+list.getPrice_info()+"元起");

    }


}
