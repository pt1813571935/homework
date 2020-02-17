package com.umeng.jiaqizuoye1_26;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.umeng.jiaqizuoye1_26.adapter.HomeAdapter;
import com.umeng.jiaqizuoye1_26.adapter.Page_TopicAdapter;
import com.umeng.jiaqizuoye1_26.adapter.RenQiAdapter;
import com.umeng.jiaqizuoye1_26.adapter.TopicAdapter;
import com.umeng.jiaqizuoye1_26.adapter.YiSiAdapter;
import com.umeng.jiaqizuoye1_26.adapter.ZhiGongAdapter;
import com.umeng.jiaqizuoye1_26.base.BaseFragment;
import com.umeng.jiaqizuoye1_26.bean.PageBean;
import com.umeng.jiaqizuoye1_26.interfaces.fristpage.FristPage;
import com.umeng.jiaqizuoye1_26.presenter.MainPresenter;
import com.umeng.jiaqizuoye1_26.presenter.fristpagepresenter.PagePresenter;
import com.umeng.jiaqizuoye1_26.view.MainView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class FristPageFragment extends BaseFragment<FristPage.View, FristPage.Presenter> implements FristPage.View {


    private ArrayList<PageBean.DataBean.BannerBean> beans;
    private RecyclerView rec_p;
    private RecyclerView rec_yi;
    private Banner bann;
    private TabLayout tab_frist;
    private TextView tv_p;
    private TextView tv_y;
    private ZhiGongAdapter adapter;
    private ArrayList<PageBean.DataBean.BrandListBean> list;
    private ArrayList<PageBean.DataBean.NewGoodsListBean> list1;
    private YiSiAdapter adapter1;
    private RecyclerView rec_ren;
    private RecyclerView rec_topic;
    private RecyclerView rec_home;
    private TextView tv_r;
    private TextView tv_topic;
    private TextView tv_home;
    private ArrayList<PageBean.DataBean.HotGoodsListBean> list2;
    private RenQiAdapter adapter2;
    private ArrayList<PageBean.DataBean.TopicListBean> list3;
    private Page_TopicAdapter adapter3;
    private ArrayList<PageBean.DataBean.CategoryListBean.GoodsListBean> list4;
    private HomeAdapter adapter4;


    @Override
    protected int getLayout() {
        return R.layout.page_layout;
    }

    @Override
    protected void initView(View view) {
        rec_p = view.findViewById(R.id.rec_pinpai);
        rec_yi = view.findViewById(R.id.rec_yisi);
        rec_ren = view.findViewById(R.id.rec_renqi);
        rec_topic = view.findViewById(R.id.rec_topic);
        rec_home = view.findViewById(R.id.rec_home);
        bann = view.findViewById(R.id.banner);
        tab_frist = view.findViewById(R.id.tab_frist);
        tv_p = view.findViewById(R.id.tv_pinpai);
        tv_y = view.findViewById(R.id.tv_yisi);
        tv_r = view.findViewById(R.id.tv_renqi);
        tv_topic = view.findViewById(R.id.tv_topic);
        tv_home = view.findViewById(R.id.tv_home);
        //品牌制造商直供适配器
        list = new ArrayList<>();
        adapter = new ZhiGongAdapter(getContext(), list);
        rec_p.setAdapter(adapter);
        rec_p.setLayoutManager(new GridLayoutManager(getContext(), 2));
        //周一周四，新品首发适配器
        list1 = new ArrayList<>();
        adapter1 = new YiSiAdapter(getContext(), list1);
        rec_yi.setAdapter(adapter1);
        rec_yi.setLayoutManager(new GridLayoutManager(getContext(), 2));
        //人气推荐适配器
        list2 = new ArrayList<>();
        adapter2 = new RenQiAdapter(getContext(), list2);
        rec_ren.setAdapter(adapter2);
        rec_ren.setLayoutManager(new LinearLayoutManager(getContext()));
        rec_ren.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
       //专题精选适配器
        list3 = new ArrayList<>();
        adapter3 = new Page_TopicAdapter(getContext(), list3);
        rec_topic.setAdapter(adapter3);
        rec_topic.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        //居家适配器
        list4 = new ArrayList<>();
        adapter4 = new HomeAdapter(getContext(), list4);
        rec_home.setAdapter(adapter4);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rec_home.setLayoutManager(gridLayoutManager);

    }

    @Override
    protected void initData() {
        ((PagePresenter)persenter).getFristPage();

    }

    @Override
    protected FristPage.Presenter createPersenter() {
        return new PagePresenter();

    }


    @Override
    public void returnFristPage(PageBean pageBean) {
        tv_p.setText("品牌制造商直供");
        List<PageBean.DataBean.BrandListBean> brandList = pageBean.getData().getBrandList();
        list.addAll(brandList);
        adapter.notifyDataSetChanged();

        tv_y.setText("周一周四.新品首发");
        List<PageBean.DataBean.NewGoodsListBean> goodsList = pageBean.getData().getNewGoodsList();
        list1.addAll(goodsList);
        adapter1.notifyDataSetChanged();

        tv_r.setText("人气推荐");
        List<PageBean.DataBean.HotGoodsListBean> hotGoodsList = pageBean.getData().getHotGoodsList();
        list2.addAll(hotGoodsList);
        adapter2.notifyDataSetChanged();

        tv_topic.setText("专题精选");
        List<PageBean.DataBean.TopicListBean> topicList = pageBean.getData().getTopicList();
        list3.addAll(topicList);
        adapter3.notifyDataSetChanged();

        tv_home.setText("居家");
        List<PageBean.DataBean.CategoryListBean> categoryList = pageBean.getData().getCategoryList();
        List<PageBean.DataBean.CategoryListBean.GoodsListBean> goodsList1 = categoryList.get(0).getGoodsList();
        list4.addAll(goodsList1);
        adapter4.notifyDataSetChanged();

        List<PageBean.DataBean.BannerBean> banner = pageBean.getData().getBanner();
        ArrayList<String> imgs = new ArrayList<>();
        List<PageBean.DataBean.ChannelBean> channel = pageBean.getData().getChannel();
        for (int i = 0; i < channel.size(); i++) {
            tab_frist.addTab(tab_frist.newTab().setText(channel.get(i).getName()));
        }
        for (int i = 0; i < banner.size(); i++) {
            String image_url = banner.get(i).getImage_url();
            imgs.add(image_url);
        }
        bann.setImages(imgs).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(getActivity()).load((String) path).into(imageView);
            }
        }).start();

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {

    }
}
