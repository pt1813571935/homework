package com.umeng.jiaqizuoye1_26;


import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.umeng.jiaqizuoye1_26.adapter.MoodsInfoAdapter;
import com.umeng.jiaqizuoye1_26.base.BaseActivity;
import com.umeng.jiaqizuoye1_26.bean.MoodsBean;
import com.umeng.jiaqizuoye1_26.bean.MoodsBeanPhoto;
import com.umeng.jiaqizuoye1_26.interfaces.Moods.MoodsInfo;
import com.umeng.jiaqizuoye1_26.presenter.MoodsPresenter;

import java.util.ArrayList;
import java.util.List;

public class MoodsActivity extends BaseActivity<MoodsInfo.View, MoodsInfo.Presenter> implements MoodsInfo.View,TabLayout.BaseOnTabSelectedListener {

    private ImageView mMoodImg;
    /**
     * 123
     */
    private TextView mMoodName;
    private TabLayout mMoodTab;
    private RecyclerView mMoodRec;
    private int hot=1;
    private int page=1;
    private int size=1000;
    private String order="asc";
    private String de="default";
    private int id=0;
    private ArrayList<MoodsBean.DataBeanX.DataBean> dataBeans;
    private MoodsInfoAdapter moodsInfoAdapter;


    @Override
    protected int getLayout() {
        return R.layout.activity_moods;
    }

    @Override
    protected void initView() {

        mMoodImg = (ImageView) findViewById(R.id.mood_img);
        mMoodName = (TextView) findViewById(R.id.mood_name);
        mMoodTab = (TabLayout) findViewById(R.id.mood_tab);
        mMoodTab.addTab(mMoodTab.newTab().setText("综合"));
        mMoodTab.addTab(mMoodTab.newTab().setText("价格"));
        mMoodTab.addTab(mMoodTab.newTab().setText("分类"));

        mMoodTab.addOnTabSelectedListener( this);

        mMoodRec = (RecyclerView) findViewById(R.id.mood_rec);
        mMoodRec.setLayoutManager(new GridLayoutManager(context,2));
        dataBeans = new ArrayList<>();
        moodsInfoAdapter = new MoodsInfoAdapter(dataBeans, context);
        mMoodRec.setAdapter(moodsInfoAdapter);
    }

    @Override
    protected void initData() {
        presenter.getMoodsInfo( hot, page,size,order,de, id);
        presenter.getMoodsInfoPhoto();
    }

    @Override
    protected MoodsInfo.Presenter createPersenter() {
        return new MoodsPresenter();
    }

    @Override
    public void returnMoodsInfo(MoodsBean moodsBean) {
        List<MoodsBean.DataBeanX.DataBean> data = moodsBean.getData().getData();
        moodsInfoAdapter.updata(data);
    }

    @Override
    public void returnMoodsInfoPhoto(MoodsBeanPhoto moodsBeanPhoto) {
        MoodsBeanPhoto.DataBean.BannerInfoBean info = moodsBeanPhoto.getData().getBannerInfo();
        Glide.with(context).load(info.getImg_url()).into(mMoodImg);
        mMoodName.setText(info.getName());
    }
            //选中状态
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getPosition()==0){
            de="default";
            presenter.getMoodsInfo( hot, page,size,order,de, id);
        }else if(tab.getPosition()==1){
            if (de.equals("default")){
                de="price";
                if (order.equals("asc")){
                    presenter.getMoodsInfo( hot, page,size,order,de, id);
                    order="desc";
                }else {
                    presenter.getMoodsInfo( hot, page,size,order,de, id);
                    order="asc";
                }

            }



        }else {
            presenter.getMoodsInfo( hot, page,size,order,de, id);
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
                presenter.getMoodsInfo( hot, page,size,order,de, id);
                order="desc";
            }else {
                presenter.getMoodsInfo( hot, page,size,order,de, id);
                order="asc";
            }
        }
    }
}
