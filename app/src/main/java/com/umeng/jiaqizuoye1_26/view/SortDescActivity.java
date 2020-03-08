package com.umeng.jiaqizuoye1_26.view;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.umeng.jiaqizuoye1_26.R;
import com.umeng.jiaqizuoye1_26.adapter.Rec_sortItemAdapter;
import com.umeng.jiaqizuoye1_26.base.BaseActivity;
import com.umeng.jiaqizuoye1_26.base.BaseAdapter;
import com.umeng.jiaqizuoye1_26.bean.ClassityBean;
import com.umeng.jiaqizuoye1_26.bean.SortItemListBean;
import com.umeng.jiaqizuoye1_26.interfaces.sort.SortContract;
import com.umeng.jiaqizuoye1_26.presenter.SortListPercenter;

import java.util.ArrayList;
import java.util.List;


//由 分类 列表item点击 跳转至此
public class SortDescActivity extends BaseActivity<SortContract.View, SortContract.Percenter> implements
        TabLayout.BaseOnTabSelectedListener, SortContract.View , BaseAdapter.ItemClickHandler{

    private TextView mTvtitle;
    private TextView mTvdesc;
    private RecyclerView mRecGoods;
    private TabLayout mTabdesc;
    private ArrayList<ClassityBean> lists;
    private Rec_sortItemAdapter rec_sortItemAdapter;
    private int posi;
    private ArrayList<SortItemListBean.DataBeanX.GoodsListBean> sortItemListBeans;

    @Override
    protected int getLayout() {
        return R.layout.activity_goods_desc;
    }

    @Override
    protected void initView() {
        mTvtitle = (TextView) findViewById(R.id.tv_goods_title);
        mTvdesc = (TextView) findViewById(R.id.tv_goods_desc);
        mTabdesc = (TabLayout) findViewById(R.id.tab_desc);
//你的自定义类  是我自己写的那个类？是的
        lists = (ArrayList<ClassityBean>) getIntent().getSerializableExtra("data");
        posi = getIntent().getIntExtra("posi", -1);
        setTitle("");

        mTvtitle.setText(lists.get(posi).name);
        mTvdesc.setText(lists.get(posi).desc);
        mRecGoods = (RecyclerView) findViewById(R.id.rec_goods);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        sortItemListBeans = new ArrayList<>();
        mRecGoods.setLayoutManager(gridLayoutManager);
        rec_sortItemAdapter = new Rec_sortItemAdapter(sortItemListBeans,this);
        mRecGoods.setAdapter(rec_sortItemAdapter);
        rec_sortItemAdapter.setOnItemClickHandler(this);

        //实现tab的动态添加文字
        for (int i = 0; i < lists.size(); i++) {
            mTabdesc.addTab(mTabdesc.newTab().setText(lists.get(i).name));
        }
        //实现点击位置跳转到tab指定位置
        mTabdesc.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTabdesc.getTabAt(posi).select();
            }
        },0);

        //tab的点击监听
        mTabdesc.addOnTabSelectedListener(this);
    }


    @Override
    protected void initData() {
        presenter.getSortListData(lists.get(posi).id,1,100);
    }

    @Override
    protected SortContract.Percenter createPersenter() {
        return new SortListPercenter();
    }

    //tab的点击监听
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mTvtitle.setText(lists.get(tab.getPosition()).name);
        mTvdesc.setText(lists.get(tab.getPosition()).desc);
        presenter.getSortListData(lists.get(tab.getPosition()).id,1,100);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void SortListDataReturn(SortItemListBean sortItemListBean) {
        List<SortItemListBean.DataBeanX.GoodsListBean> goodsList = sortItemListBean.getData().getGoodsList();
        rec_sortItemAdapter.updata(goodsList);
    }

    //item的点击监听
    @Override
    public void itemClick(int position, BaseAdapter.BaseViewHolder holder) {
        Intent intent = new Intent(this, GoodsShoppingActivity.class);
        intent.putExtra("id",sortItemListBeans.get(position).getId());
        startActivity(intent);

    }
}
