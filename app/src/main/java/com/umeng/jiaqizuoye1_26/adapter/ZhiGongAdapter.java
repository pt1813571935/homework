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

public class ZhiGongAdapter extends RecyclerView.Adapter {
    private Context baseContext;
    private ArrayList<PageBean.DataBean.BrandListBean> list;

    public ZhiGongAdapter(Context baseContext, ArrayList<PageBean.DataBean.BrandListBean> list) {
        this.baseContext = baseContext;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(baseContext, R.layout.zhigong_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1= (ViewHolder) holder;
        holder1.tv_ck.setText(list.get(position).getName());
        holder1.zhi_price.setText(list.get(position).getFloor_price()+"起");
        Glide.with(baseContext).load(list.get(position).getNew_pic_url()).into( holder1.iv_ck);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{


        private  ImageView iv_ck;
        private  TextView zhi_price;
        private  TextView tv_ck;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_ck = itemView.findViewById(R.id.iv_ck);
            tv_ck = itemView.findViewById(R.id.tv_ck);
            zhi_price = itemView.findViewById(R.id.zhi_price);
        }
    }
}
