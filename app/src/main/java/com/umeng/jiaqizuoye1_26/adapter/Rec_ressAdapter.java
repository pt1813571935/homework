package com.umeng.jiaqizuoye1_26.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


import com.umeng.jiaqizuoye1_26.R;
import com.umeng.jiaqizuoye1_26.base.BaseAdapter;
import com.umeng.jiaqizuoye1_26.bean.RessBean;

import java.util.List;

public class Rec_ressAdapter extends BaseAdapter {


    public Rec_ressAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        return R.layout.item_ress;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {
        RessBean.DataBean bean = (RessBean.DataBean) o;
        TextView txtName = (TextView) holder.getView(R.id.txt_name);
        TextView txtDefault = (TextView) holder.getView(R.id.txt_default);
        TextView txtPhone = (TextView) holder.getView(R.id.txt_phone);
        TextView txtAdress = (TextView) holder.getView(R.id.txt_adress);
        CheckBox imgEditor = (CheckBox) holder.getView(R.id.img_editor);
        txtName.setText(bean.getName());
        txtDefault.setVisibility(bean.getIs_default() == 1 ? View.VISIBLE : View.GONE);
        txtPhone.setText(bean.getMobile());
        txtAdress.setText(bean.getCity_name()+bean.getDistrict_name()+bean.getFull_region());

        imgEditor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isPressed()){
                    imgEditor.setOnClickListener(clickListener);
                }
            }
        });
    }
}
