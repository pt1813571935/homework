package com.umeng.jiaqizuoye1_26.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.umeng.jiaqizuoye1_26.R;
import com.umeng.jiaqizuoye1_26.bean.PageBean;

import java.util.List;

public class Page_TopicAdapter extends RecyclerView.Adapter<Page_TopicAdapter.Vh> {
    private Context baseContext;
    private List<PageBean.DataBean.TopicListBean> topicList;

    public Page_TopicAdapter(Context baseContext, List<PageBean.DataBean.TopicListBean> topicList) {
        this.baseContext = baseContext;
        this.topicList = topicList;
    }


    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(baseContext, R.layout.page_topic, null);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {
        Glide.with(baseContext).load(topicList.get(position).getItem_pic_url()).into(holder.page_t_img);
        holder.page_tv_name.setText(topicList.get(position).getTitle());
        holder.page_tv_price.setText("￥"+topicList.get(position).getPrice_info()+"元起");
        holder.page_tv_title.setText(topicList.get(position).getSubtitle());
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    public class Vh extends RecyclerView.ViewHolder {

        private final ImageView page_t_img;
        private final TextView page_tv_name;
        private final TextView page_tv_price;
        private final TextView page_tv_title;

        public Vh(@NonNull View itemView) {
            super(itemView);
            page_t_img = itemView.findViewById(R.id.page_t_img);
            page_tv_name = itemView.findViewById(R.id.page_tv_name);
            page_tv_price = itemView.findViewById(R.id.page_tv_price);
            page_tv_title = itemView.findViewById(R.id.page_tv_title);
        }
    }
}
