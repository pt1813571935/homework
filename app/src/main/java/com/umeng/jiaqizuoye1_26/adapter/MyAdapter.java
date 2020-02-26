package com.umeng.jiaqizuoye1_26.adapter;


import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.umeng.jiaqizuoye1_26.R;
import com.umeng.jiaqizuoye1_26.base.BaseAdapter;
import com.umeng.jiaqizuoye1_26.bean.MeBean;
import java.util.List;

public class MyAdapter extends BaseAdapter {


    public MyAdapter(List data, Context context) {
        super(data, context);
    }


    @Override
    public int getLayout() {
        return R.layout.item_me;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {
        MeBean list= (MeBean) o;
        ImageView img = (ImageView) holder.getView(R.id.iv_me_item);
        TextView tv = (TextView) holder.getView(R.id.tv_me_item_text);
        tv.setText(list.getText());
        Glide.with(mContext).load(list.getImgpath()).into(img);
    }

}
