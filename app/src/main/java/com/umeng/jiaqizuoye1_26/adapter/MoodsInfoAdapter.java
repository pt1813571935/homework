package com.umeng.jiaqizuoye1_26.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.umeng.jiaqizuoye1_26.R;
import com.umeng.jiaqizuoye1_26.base.BaseAdapter;
import com.umeng.jiaqizuoye1_26.bean.MoodsBean;

import java.util.List;

public class MoodsInfoAdapter extends BaseAdapter {
    public MoodsInfoAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        return R.layout.moodsinfo_layout;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {
        MoodsBean.DataBeanX.DataBean list= (MoodsBean.DataBeanX.DataBean) o;
        ImageView moodsInfo_img = (ImageView) holder.getView(R.id.moodsInfo_img);
        TextView moodsInfo_name = (TextView) holder.getView(R.id.moodsInfo_name);
        TextView moodsInfo_price = (TextView) holder.getView(R.id.moodsInfo_price);
        Glide.with(mContext).load(list.getList_pic_url()).into(moodsInfo_img);
        moodsInfo_name.setText(list.getName());
        moodsInfo_price.setText(list.getRetail_price()+"元起");
    }
}
