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
import com.umeng.jiaqizuoye1_26.bean.ClassityBean;

import java.util.ArrayList;

public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyAdapter.Vh> {
    private Context baseContext;
    private ArrayList<ClassityBean> beans;

    public ClassifyAdapter(Context baseContext, ArrayList<ClassityBean> beans) {
        this.baseContext = baseContext;
        this.beans = beans;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(baseContext, R.layout.class_layout, null);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {
        Glide.with(baseContext).load(beans.get(position).image).into(holder.cl_img);
        holder.cl_name.setText(beans.get(position).name);
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    public class Vh extends RecyclerView.ViewHolder {

        private  ImageView cl_img;
        private  TextView cl_name;

        public Vh(@NonNull View itemView) {
            super(itemView);
            cl_img = itemView.findViewById(R.id.cl_img);
            cl_name = itemView.findViewById(R.id.cl_name);
        }
    }
}
