package com.umeng.jiaqizuoye1_26;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.umeng.jiaqizuoye1_26.adapter.MoodsInfoAdapter;
import com.umeng.jiaqizuoye1_26.adapter.NewInfoAdapter;
import com.umeng.jiaqizuoye1_26.base.BaseActivity;
import com.umeng.jiaqizuoye1_26.base.BaseAdapter;
import com.umeng.jiaqizuoye1_26.bean.MoodsBean;
import com.umeng.jiaqizuoye1_26.bean.MoodsBeanPhoto;
import com.umeng.jiaqizuoye1_26.bean.NewBean;
import com.umeng.jiaqizuoye1_26.interfaces.Moods.MoodsInfo;
import com.umeng.jiaqizuoye1_26.interfaces.infonew.NewInfo;
import com.umeng.jiaqizuoye1_26.presenter.MoodsPresenter;
import com.umeng.jiaqizuoye1_26.presenter.NewInfoPresenter;
import com.umeng.jiaqizuoye1_26.view.GoodsShoppingActivity;

import java.util.ArrayList;
import java.util.List;

public class NewActivity extends BaseActivity<NewInfo.View, NewInfo.Presenter> implements NewInfo.View,TabLayout.BaseOnTabSelectedListener, BaseAdapter.ItemClickHandler {

    private ImageView new_img;
    /**
     * 123
     */
    private TextView new_name;
    private TabLayout new_tab;
    private RecyclerView new_rec;
    private int ne=1;
    private int page=1;
    private int size=1000;
    private String order="asc";
    private String sort="default";
    private int id=0;
    private NewInfoAdapter newInfoAdapter;
    private ArrayList<NewBean.DataBeanX.DataBean> beans;


    @Override
    protected int getLayout() {
        return R.layout.activity_new;
    }

    @Override
    protected void initView() {

        new_img = (ImageView) findViewById(R.id.new_img);
        new_name = (TextView) findViewById(R.id.new_name);
        new_tab = (TabLayout) findViewById(R.id.new_tab);
        new_tab.addTab(new_tab.newTab().setText("综合"));
        new_tab.addTab(new_tab.newTab().setText("价格"));
        new_tab.addTab(new_tab.newTab().setText("分类"));

        new_tab.addOnTabSelectedListener( this);

        new_rec = (RecyclerView) findViewById(R.id.new_rec);
        new_rec.setLayoutManager(new GridLayoutManager(context,2));
        beans = new ArrayList<>();
        newInfoAdapter = new NewInfoAdapter(beans, context);
        new_rec.setAdapter(newInfoAdapter);
        newInfoAdapter.setOnItemClickHandler(this);
    }

    @Override
    protected void initData() {
        presenter.getNewBean( ne, page,size,order,sort, id);
        presenter.getMoodsInfoPhoto();
    }

    @Override
    protected NewInfo.Presenter createPersenter() {
        return new NewInfoPresenter();
    }


    @Override
    public void returnNewBean(NewBean newBean) {
        List<NewBean.DataBeanX.DataBean> data = newBean.getData().getData();
        newInfoAdapter.updata(data);
    }

    @Override
    public void returnMoodsInfoPhoto(MoodsBeanPhoto moodsBeanPhoto) {
        MoodsBeanPhoto.DataBean.BannerInfoBean info = moodsBeanPhoto.getData().getBannerInfo();
        Glide.with(context).load(info.getImg_url()).into(new_img);
        new_name.setText(info.getName());
    }
            //选中状态
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getPosition()==0){
            sort="default";
            presenter.getNewBean( ne, page,size,order,sort, id);
        }else if(tab.getPosition()==1){
            if (sort.equals("default")){
                sort="price";
                if (order.equals("asc")){
                    presenter.getNewBean( ne, page,size,order,sort, id);
                    order="desc";
                }else {
                    presenter.getNewBean( ne, page,size,order,sort, id);
                    order="asc";
                }

            }



        }else {
            presenter.getNewBean( ne, page,size,order,sort, id);
        }
    }
            //未选中状态
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }
            //二次选中
    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        if (tab.getPosition()==1){
            if (order.equals("asc")){
                presenter.getNewBean( ne, page,size,order,sort, id);
                order="desc";
            }else {
                presenter.getNewBean( ne, page,size,order,sort, id);
                order="asc";
            }
        }
    }

    @Override
    public void itemClick(int position, BaseAdapter.BaseViewHolder holder) {
        startActivity(new Intent(this, GoodsShoppingActivity.class).putExtra("id",beans.get(position).getId()));
    }
}
