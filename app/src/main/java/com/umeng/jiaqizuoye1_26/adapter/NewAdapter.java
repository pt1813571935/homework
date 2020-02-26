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

public class NewAdapter extends BaseAdapter {


    public NewAdapter(List data, Context context) {
        super(data, context);
    }


    @Override
    public int getLayout() {
        return  R.layout.new_layout;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {
        PageBean.DataBean.NewGoodsListBean list= (PageBean.DataBean.NewGoodsListBean) o;
        ImageView iv_qu = (ImageView) holder.getView(R.id.iv_qu);
        TextView tv_name = (TextView) holder.getView(R.id.tv_name);
        TextView tv_price = (TextView) holder.getView(R.id.tv_price);
        tv_name.setText(list.getName());
        tv_price.setText("￥"+list.getRetail_price());
        Glide.with(mContext).load(list.getList_pic_url()).into(iv_qu);
    }
}
