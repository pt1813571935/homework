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

public class YiSiAdapter extends RecyclerView.Adapter {
    private Context baseContext;
    private ArrayList<PageBean.DataBean.NewGoodsListBean> list1;

    public YiSiAdapter(Context baseContext, ArrayList<PageBean.DataBean.NewGoodsListBean> list1) {
        this.baseContext = baseContext;
        this.list1 = list1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(baseContext, R.layout.yisi_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1= (ViewHolder) holder;
        holder1.tv_name.setText(list1.get(position).getName());
        holder1.tv_price.setText("￥"+list1.get(position).getRetail_price());
        Glide.with(baseContext).load(list1.get(position).getList_pic_url()).into( holder1.iv_qu);
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{


        private  ImageView iv_qu;
        private  TextView tv_name;
        private  TextView tv_price;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_qu = itemView.findViewById(R.id.iv_qu);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
        }
    }
}
