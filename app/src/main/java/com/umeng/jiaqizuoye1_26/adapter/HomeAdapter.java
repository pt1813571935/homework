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
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private Context baseContext;
    private ArrayList<PageBean.DataBean.CategoryListBean.GoodsListBean> list4;

    public HomeAdapter(Context baseContext, ArrayList<PageBean.DataBean.CategoryListBean.GoodsListBean> list4) {
        this.baseContext = baseContext;
        this.list4 = list4;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(baseContext, R.layout.home_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(baseContext).load(list4.get(position).getList_pic_url()).into(holder.home_img);
        holder.home_tv_name.setText(list4.get(position).getName());
        holder.home_tv_price.setText(list4.get(position).getRetail_price());

    }

    @Override
    public int getItemCount() {
        return list4.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView home_img;
        private final TextView home_tv_name;
        private final TextView home_tv_price;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            home_img = itemView.findViewById(R.id.home_img);
            home_tv_name = itemView.findViewById(R.id.home_tv_name);
            home_tv_price = itemView.findViewById(R.id.home_tv_price);

        }
    }
}
