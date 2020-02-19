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

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {
    private Context baseContext;
    private List<PageBean.DataBean.TopicListBean> topicList;

    public TopicAdapter(Context baseContext, List<PageBean.DataBean.TopicListBean> topicList) {
        this.baseContext = baseContext;
        this.topicList = topicList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(baseContext, R.layout.topic, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(baseContext).load(topicList.get(position).getItem_pic_url()).into(holder.topic_img);
        holder.topic_title.setText(topicList.get(position).getTitle());
        holder.topic_name.setText(topicList.get(position).getSubtitle());
        holder.topic_price.setText("￥"+topicList.get(position).getPrice_info()+"元起");
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView topic_img;
        private final TextView topic_name;
        private final TextView topic_price;
        private final TextView topic_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            topic_img = itemView.findViewById(R.id.topic_img);
            topic_name = itemView.findViewById(R.id.topic_name);
            topic_price = itemView.findViewById(R.id.topic_price);
            topic_title = itemView.findViewById(R.id.topic_title);
        }
    }
}
