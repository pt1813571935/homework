package com.umeng.jiaqizuoye1_26.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.jiaqizuoye1_26.R;
import com.umeng.jiaqizuoye1_26.base.BaseAdapter;
import com.umeng.jiaqizuoye1_26.bean.SortItemListBean;

import java.util.List;

public class Rec_sortItemAdapter extends BaseAdapter {


    public Rec_sortItemAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        //换你新品首发的布局
        return R.layout.new_layout;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {
        SortItemListBean.DataBeanX.GoodsListBean list= (SortItemListBean.DataBeanX.GoodsListBean) o;

        ImageView img = (ImageView) holder.getView(R.id.iv_qu);
        TextView name = (TextView) holder.getView(R.id.tv_name);
        TextView price = (TextView) holder.getView(R.id.tv_price);

        name.setText(list.getName());
        price.setText(list.getRetail_price()+"元起");
        Glide.with(mContext).load(list.getList_pic_url()).into(img);
    }
}
