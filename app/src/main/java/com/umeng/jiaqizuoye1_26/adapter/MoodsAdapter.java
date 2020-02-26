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

public class MoodsAdapter extends BaseAdapter {


    public MoodsAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        return R.layout.moods_layout;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {
        PageBean.DataBean.HotGoodsListBean list= (PageBean.DataBean.HotGoodsListBean) o;
        ImageView renqi_img = (ImageView) holder.getView(R.id.renqi_img);
        TextView renqi_tv_name = (TextView) holder.getView(R.id.renqi_tv_name);
        TextView renqi_tv_title = (TextView) holder.getView(R.id.renqi_tv_title);
        TextView renqi_tv_price = (TextView) holder.getView(R.id.renqi_tv_price);
        renqi_tv_name.setText(list.getName());
        renqi_tv_title.setText(list.getGoods_brief());
        renqi_tv_price.setText("￥"+list.getRetail_price());
        Glide.with(mContext).load(list.getList_pic_url()).into( renqi_img);
    }
}
