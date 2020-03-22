package com.umeng.jiaqizuoye1_26.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.umeng.jiaqizuoye1_26.R;
import com.umeng.jiaqizuoye1_26.base.BaseAdapter;
import com.umeng.jiaqizuoye1_26.bean.CartListsBean;
import com.umeng.jiaqizuoye1_26.bean.CatalogItem;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class CartAdapter extends BaseAdapter {

    private boolean b = true;

    public CartAdapter(List data, Context context) {
        super(data, context);
    }
    // true 说明是正常状态
    // false 说明是编辑状态


    public void selectAll(boolean b){
        this.b = b;
        List<CartListsBean.DataBean.CartListBean> list = mDatas;
        if (b){
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setIsselect(true);
            }
        }else {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setIsselect(true);
            }
        }
    }
    //管理正常界面 和 编辑界面的 显示隐藏 并初始化编辑下的单选按钮为false未选中
    public void showAndHind(boolean b){
        this.b = b;
        List<CartListsBean.DataBean.CartListBean> list = mDatas;
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setDelect(false);
        }
        notifyDataSetChanged();
    }






    @Override
    public int getLayout() {
        return R.layout.item_cart;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {
        CartListsBean.DataBean.CartListBean bean = (CartListsBean.DataBean.CartListBean)o;

        //正常下控件
        ConstraintLayout view = (ConstraintLayout) holder.getView(R.id.item_cart_ConstraintLayout);
        ImageView img = (ImageView) holder.getView(R.id.item_cart_img);
        TextView name = (TextView) holder.getView(R.id.item_cart_name);
        TextView num = (TextView) holder.getView(R.id.item_cart_number);
        TextView price = (TextView) holder.getView(R.id.item_cart_price);
        //编辑下控件
        ConstraintLayout viewEdit = (ConstraintLayout) holder.getView(R.id.item_cart_edit_ConstraintLayout);
        TextView add = (TextView) holder.getView(R.id.item_cart_edit_add);
        TextView numEdit = (TextView) holder.getView(R.id.item_cart_edit_num);
        TextView subtrect = (TextView) holder.getView(R.id.item_cart_edit_subtrect);

        Glide.with(mContext).load(bean.getList_pic_url()).into(img);
        name.setText(bean.getGoods_name());
        num.setText(bean.getNumber() + "");
        price.setText("￥" + bean.getNumber() * bean.getRetail_price());

        CheckBox select = (CheckBox) holder.getView(R.id.item_cart_select);
        // true 说明是正常状态
        if (b) {
            //判断正常界面是否隐藏
            if (view.getVisibility() == View.GONE) view.setVisibility(View.VISIBLE);
            //隐藏 编辑界面
            viewEdit.setVisibility(View.GONE);

            //单击标记
            select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (buttonView.isPressed())
                        bean.setIsselect(isChecked);
                }
            });
            select.setChecked(bean.isIsselect());
            //false 说明是编辑状态
        } else {
            //隐藏 正常界面，显示 编辑界面
            view.setVisibility(View.GONE);
            viewEdit.setVisibility(View.VISIBLE);

            numEdit.setText(bean.getNumber() + "");

            //用于修改商品的网络请求
            int product_id = bean.getProduct_id();
            int goods_id = bean.getGoods_id();
            int id = bean.getId();
            CatalogItem catalogItem = new CatalogItem();
            catalogItem.productId = product_id + "";
            catalogItem.goodsId = goods_id + "";
            catalogItem.id = id;
//加
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = numEdit.getText().toString();
                    int i = Integer.parseInt(s) + 1;
                    numEdit.setText(i + "");
                    catalogItem.number = i + "";
                    EventBus.getDefault().post(catalogItem);
                }
            });
//减
            subtrect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = numEdit.getText().toString();
                    int i = Integer.parseInt(s);
                    if (i == 1) {
                        i = 1;
                    } else {
                        i -= 1;
                    }
                    numEdit.setText(i + "");
                    catalogItem.number = i + "";
                    EventBus.getDefault().post(catalogItem);
                }
            });

            //单击标记
            select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (buttonView.isPressed()) {
                        bean.setDelect(isChecked);
                    }
                }
            });
        }
    }

}
