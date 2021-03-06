package com.umeng.jiaqizuoye1_26;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.umeng.jiaqizuoye1_26.adapter.HomeAdapter;
import com.umeng.jiaqizuoye1_26.adapter.Page_TopicAdapter;
import com.umeng.jiaqizuoye1_26.adapter.MoodsAdapter;
import com.umeng.jiaqizuoye1_26.adapter.NewAdapter;
import com.umeng.jiaqizuoye1_26.adapter.BrandAdapter;
import com.umeng.jiaqizuoye1_26.base.BaseAdapter;
import com.umeng.jiaqizuoye1_26.base.BaseFragment;
import com.umeng.jiaqizuoye1_26.bean.PageBean;
import com.umeng.jiaqizuoye1_26.interfaces.fristpage.FirstPage;
import com.umeng.jiaqizuoye1_26.presenter.fristpagepresenter.PagePresenter;
import com.umeng.jiaqizuoye1_26.view.BrandDetailsActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class FirstPageFragment extends BaseFragment<FirstPage.View, FirstPage.Presenter> implements FirstPage.View, BaseAdapter.ItemClickHandler {


    private ArrayList<PageBean.DataBean.BannerBean> beans;
    private RecyclerView  rec_brand ;
    private RecyclerView rec_new;
    private TabLayout tab_first;
    private BrandAdapter adapter;
    private ArrayList<PageBean.DataBean.BrandListBean> list;
    private ArrayList<PageBean.DataBean.NewGoodsListBean> list1;
    private NewAdapter adapter1;
    private RecyclerView rec_moods;
    private RecyclerView rec_topic;
    private RecyclerView rec_home;
    private TextView tv_brand;
    private TextView tv_new;
    private TextView tv_moods;
    private TextView tv_topic;
    private TextView tv_home;
    private ArrayList<PageBean.DataBean.HotGoodsListBean> list2;
    private MoodsAdapter adapter2;
    private ArrayList<PageBean.DataBean.TopicListBean> list3;
    private Page_TopicAdapter adapter3;
    private ArrayList<PageBean.DataBean.CategoryListBean.GoodsListBean> list4;
    private HomeAdapter adapter4;
    private Banner banner;
    private List<PageBean.DataBean.BrandListBean> brandList;


    @Override
    protected int getLayout() {
        return R.layout.page_layout;
    }

    @Override
    protected void initView(View view) {
        rec_brand = view.findViewById(R.id.rec_brand);
        rec_new = view.findViewById(R.id.rec_new);
        rec_moods = view.findViewById(R.id.rec_moods);
        rec_topic = view.findViewById(R.id.rec_topic);
        rec_home = view.findViewById(R.id.rec_home);
        banner = view.findViewById(R.id.banner);
        tab_first = view.findViewById(R.id.tab_first);
        tv_brand = view.findViewById(R.id.tv_brand);
        tv_new = view.findViewById(R.id.tv_new);
        tv_moods = view.findViewById(R.id.tv_moods);
        tv_topic = view.findViewById(R.id.tv_topic);
        tv_home = view.findViewById(R.id.tv_home);
        //品牌制造商直供适配器
        list = new ArrayList<>();
        adapter = new BrandAdapter(list,getContext());
        rec_brand.setAdapter(adapter);
        rec_brand.setLayoutManager(new GridLayoutManager(getContext(), 2));
        //周一周四，新品首发适配器
        list1 = new ArrayList<>();
        adapter1 = new NewAdapter(list1,getContext());
        rec_new.setAdapter(adapter1);
        rec_new.setLayoutManager(new GridLayoutManager(getContext(), 2));
        //人气推荐适配器
        list2 = new ArrayList<>();
        adapter2 = new MoodsAdapter( list2,getContext());
        rec_moods.setAdapter(adapter2);
        rec_moods.setLayoutManager(new LinearLayoutManager(getContext()));
        rec_moods.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
       //专题精选适配器
        list3 = new ArrayList<>();
        adapter3 = new Page_TopicAdapter(list3,getContext());
        rec_topic.setAdapter(adapter3);
        rec_topic.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        //居家适配器
        list4 = new ArrayList<>();
        adapter4 = new HomeAdapter(list4,getContext());
        rec_home.setAdapter(adapter4);
        //给单独的RecyclerView设置禁止滑动
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
        presenter.getFirstPage();

    }

    @Override
    protected FirstPage.Presenter createPresenter() {
        return new PagePresenter();
    }


    @Override
    public void returnFirstPage(PageBean pageBean) {
        tv_brand.setText("品牌制造商直供");
        tv_brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BrandInfoActivity.class);
                startActivity(intent);
            }
        });
        brandList = pageBean.getData().getBrandList();
       adapter.updata(brandList);
       adapter.setOnItemClickHandler(this);

        tv_new.setText("周一周四.新品首发");
        tv_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NewActivity.class);
                startActivity(intent);
            }
        });
        List<PageBean.DataBean.NewGoodsListBean> goodsList = pageBean.getData().getNewGoodsList();
        adapter1.updata(goodsList);

        tv_moods.setText("人气推荐");
        tv_moods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MoodsActivity.class);
                startActivity(intent);
            }
        });
        List<PageBean.DataBean.HotGoodsListBean> hotGoodsList = pageBean.getData().getHotGoodsList();
        adapter2.updata(hotGoodsList);

        tv_topic.setText("专题精选");
        List<PageBean.DataBean.TopicListBean> topicList = pageBean.getData().getTopicList();
        adapter3.updata(topicList);

        tv_home.setText("居家");
        List<PageBean.DataBean.CategoryListBean> categoryList = pageBean.getData().getCategoryList();
        List<PageBean.DataBean.CategoryListBean.GoodsListBean> goodsList1 = categoryList.get(0).getGoodsList();
        adapter4.updata(goodsList1);

        List<PageBean.DataBean.BannerBean> banners = pageBean.getData().getBanner();
        ArrayList<String> imgs = new ArrayList<>();
        List<PageBean.DataBean.ChannelBean> channel = pageBean.getData().getChannel();
        for (int i = 0; i < channel.size(); i++) {
            tab_first.addTab(tab_first.newTab().setText(channel.get(i).getName()));
        }
        for (int i = 0; i < banners.size(); i++) {
            String image_url = banners.get(i).getImage_url();
            imgs.add(image_url);
        }
        banner.setImages(imgs).setImageLoader(new ImageLoader() {
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

    @Override
    public void itemClick(int position, BaseAdapter.BaseViewHolder holder) {
      startActivity(new Intent(context, BrandDetailsActivity.class).putExtra("id",brandList.get(position).getId()));
    }
}
