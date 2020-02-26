package com.umeng.jiaqizuoye1_26.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.jiaqizuoye1_26.R;
import com.umeng.jiaqizuoye1_26.base.BaseAdapter;
import com.umeng.jiaqizuoye1_26.bean.BrandManufacturer;

import java.util.List;

public class BrandInfoAdapter extends BaseAdapter {
    public BrandInfoAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        return R.layout.info_layout;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {
        BrandManufacturer.DataBeanX.DataBean list=( BrandManufacturer.DataBeanX.DataBean) o;
        ImageView brandinfo_img = (ImageView) holder.getView(R.id.brandinfo_img);
        TextView brandinfo_desc = (TextView) holder.getView(R.id.brandinfo_desc);
        Glide.with(mContext).load(list.getApp_list_pic_url()).into(brandinfo_img);
        brandinfo_desc.setText(list.getName());

    }
}
