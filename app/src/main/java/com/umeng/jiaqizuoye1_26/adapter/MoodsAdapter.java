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

import java.util.ArrayList;

public class MoodsAdapter extends RecyclerView.Adapter {
    private Context baseContext;
    private ArrayList<PageBean.DataBean.HotGoodsListBean> list2;

    public MoodsAdapter(Context baseContext, ArrayList<PageBean.DataBean.HotGoodsListBean> list2) {
        this.baseContext = baseContext;
        this.list2 = list2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(baseContext, R.layout.moods_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1= (ViewHolder) holder;
        holder1.renqi_tv_name.setText(list2.get(position).getName());
        holder1.renqi_tv_title.setText(list2.get(position).getGoods_brief());
        holder1.renqi_tv_price.setText("￥"+list2.get(position).getRetail_price());
        Glide.with(baseContext).load(list2.get(position).getList_pic_url()).into( holder1.renqi_img);
    }

    @Override
    public int getItemCount() {
        return list2.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{


        private  ImageView renqi_img;
        private  TextView renqi_tv_name;
        private  TextView renqi_tv_title;
        private  TextView renqi_tv_price;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            renqi_img= itemView.findViewById(R.id.renqi_img);
            renqi_tv_name = itemView.findViewById(R.id.renqi_tv_name);
            renqi_tv_title = itemView.findViewById(R.id.renqi_tv_title);
            renqi_tv_price = itemView.findViewById(R.id.renqi_tv_price);
        }
    }
}
