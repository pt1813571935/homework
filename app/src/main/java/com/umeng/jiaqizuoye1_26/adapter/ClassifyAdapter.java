package com.umeng.jiaqizuoye1_26.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.umeng.jiaqizuoye1_26.R;
import com.umeng.jiaqizuoye1_26.base.BaseAdapter;
import com.umeng.jiaqizuoye1_26.bean.ClassityBean;


import java.util.List;


public class ClassifyAdapter extends BaseAdapter {


    public ClassifyAdapter(List data, Context context) {
        super(data, context);
    }


    @Override
    public int getLayout() {
        return R.layout.class_layout;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {
        ClassityBean  list = (ClassityBean) o;
        ImageView cl_img = (ImageView) holder.getView(R.id.cl_img);
        TextView cl_name = (TextView) holder.getView(R.id.cl_name);
        Glide.with(mContext).load(list.image).into(cl_img);
        cl_name.setText(list.name);
    }


}
