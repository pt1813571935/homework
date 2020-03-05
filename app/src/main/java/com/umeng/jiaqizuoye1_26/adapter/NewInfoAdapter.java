package com.umeng.jiaqizuoye1_26.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.jiaqizuoye1_26.R;
import com.umeng.jiaqizuoye1_26.base.BaseAdapter;
import com.umeng.jiaqizuoye1_26.bean.MoodsBean;
import com.umeng.jiaqizuoye1_26.bean.NewBean;

import java.util.List;

public class NewInfoAdapter extends BaseAdapter {
    public NewInfoAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        return R.layout.newinfo_layout;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {
        NewBean.DataBeanX.DataBean list= (NewBean.DataBeanX.DataBean) o;
        ImageView newInfo_img = (ImageView) holder.getView(R.id.newInfo_img);
        TextView newInfo_name = (TextView) holder.getView(R.id.newInfo_name);
        TextView newInfo_price = (TextView) holder.getView(R.id.newInfo_price);
        Glide.with(mContext).load(list.getList_pic_url()).into(newInfo_img);
        newInfo_name.setText(list.getName());
        newInfo_price.setText(list.getRetail_price()+"元起");
    }
}
