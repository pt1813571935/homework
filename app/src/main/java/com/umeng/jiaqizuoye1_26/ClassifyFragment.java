package com.umeng.jiaqizuoye1_26;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.umeng.jiaqizuoye1_26.adapter.ClassifyAdapter;
import com.umeng.jiaqizuoye1_26.base.BaseFragment;
import com.umeng.jiaqizuoye1_26.bean.ClassifyListBean;
import com.umeng.jiaqizuoye1_26.bean.ClassifyTabBean;
import com.umeng.jiaqizuoye1_26.bean.ClassityBean;
import com.umeng.jiaqizuoye1_26.interfaces.classify.ClassifyTab;
import com.umeng.jiaqizuoye1_26.presenter.ClassifyTabPresenter;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class ClassifyFragment extends BaseFragment<ClassifyTab.View,ClassifyTab.Presenter>implements ClassifyTab.View {
    private VerticalTabLayout tabFenlei;
    private ConstraintLayout cl;
    private ImageView cImg;
    private TextView cTvHome;
    private TextView cc_desc;
    private RecyclerView cl_rec;
    private ClassifyAdapter adapter;
    private ArrayList<ClassityBean> beans;


    @Override
    protected int getLayout() {
        return R.layout.classify_layout;
    }


    @Override
    protected void initData() {
       presenter.getClassifyTab();
    }




    @Override
    protected ClassifyTab.Presenter createPresenter() {
        return new ClassifyTabPresenter();
    }

    @Override
    protected void initView(View view) {
        tabFenlei = (VerticalTabLayout) view.findViewById(R.id.tab_fenlei);
        cl = (ConstraintLayout) view.findViewById(R.id.cl);
        cImg = (ImageView) view.findViewById(R.id.c_img);
        cTvHome = (TextView) view.findViewById(R.id.c_tv_home);
        cc_desc = (TextView) view.findViewById(R.id.cc_desc);
        cl_rec = (RecyclerView) view.findViewById(R.id.cl_rec);
        cl_rec.setLayoutManager(new GridLayoutManager(getContext(),3));
    }

    @Override
    public void returnClassifyTab(ClassifyTabBean classifyTabBean) {
        final List<ClassifyTabBean.DataBean.CategoryListBean> categoryList = classifyTabBean.getData().getCategoryList();
        final ArrayList<String> tab = new ArrayList<>();
        for (int i = 0; i < categoryList.size(); i++) {
            String name = categoryList.get(i).getName();
            tab.add(name);
        }
        //分类的默认名字和图片（居家分类）
        cc_desc.setText(categoryList.get(0).getFront_name());
        cTvHome.setText(categoryList.get(0).getName()+"分类");
        Glide.with(getContext()).load(categoryList.get(0).getWap_banner_url()).into(cImg);

        //tab的适配器
        tabFenlei.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return tab.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                ITabView.TabTitle title = new ITabView.TabTitle.Builder()
                        .setContent(tab.get(position))//从集合中获取标题
                        .setTextColor(Color.RED, Color.BLACK)
                        .build();
                return title;
            }
            @Override
            public int getBackground(int position) {
                return 0;
            }});

        beans = new ArrayList<>();
        List<ClassifyTabBean.DataBean.CategoryListBean.SubCategoryListBeanX> subCategoryList = categoryList.get(0).getSubCategoryList();
        //将获取的list数据由自定义的类替换掉
            for (int i = 0; i < subCategoryList.size(); i++) {
                ClassityBean classityBean = new ClassityBean();
                classityBean.image = subCategoryList.get(i).getWap_banner_url();
                classityBean.name = subCategoryList.get(i).getName();
                beans.add(classityBean);
        }
            adapter = new ClassifyAdapter(beans,getContext());
            cl_rec.setAdapter(adapter);
            tabFenlei.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabView tab, int position) {
                    cTvHome.setText(categoryList.get(position).getName()+"分类");
                    presenter.getClassifyList(categoryList.get(position).getId());
                }

                @Override
                public void onTabReselected(TabView tab, int position) {

                }
            });

    }

    @Override
    public void returnClassifyList(ClassifyListBean classifyListBean) {
        ClassifyListBean.DataBean.CurrentCategoryBean listBean = classifyListBean.getData().getCurrentCategory();
        cc_desc.setText(listBean.getFront_name());
        cTvHome.setText(listBean.getName()+"分类");
        Glide.with(getContext()).load(listBean.getWap_banner_url()).into(cImg);
        beans.clear();
        for (int i = 0; i <listBean.getSubCategoryList().size() ; i++) {
            ClassityBean classityBean = new ClassityBean();
            classityBean.image=listBean.getSubCategoryList().get(i).getWap_banner_url();
            classityBean.name=listBean.getSubCategoryList().get(i).getName();
            beans.add(classityBean);
        }
        adapter.notifyDataSetChanged();

    }



    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {

    }
}
