package com.umeng.jiaqizuoye1_26;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.umeng.jiaqizuoye1_26.adapter.ClassifyAdapter;
import com.umeng.jiaqizuoye1_26.base.BaseFragment;
import com.umeng.jiaqizuoye1_26.bean.ClassifyTabBean;
import com.umeng.jiaqizuoye1_26.bean.ClassityBean;
import com.umeng.jiaqizuoye1_26.bean.ListBean;
import com.umeng.jiaqizuoye1_26.interfaces.classify.ClassifyTab;
import com.umeng.jiaqizuoye1_26.presenter.ClassifyTabPresenter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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



    @Override
    protected int getLayout() {
        return R.layout.classify_layout;
    }


    @Override
    protected void initData() {
       
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
    }

    @Override
    public void returnClassifyTab(ClassifyTabBean classifyTabBean) {

            //获取tab数据
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request=new Request.Builder()
                    .get()
                    .url("https://cdwan.cn/api/")
                    .build();
            okHttpClient.newCall(request)
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            String s = response.body().string();
                            ClassifyTabBean classifyTabBean = new Gson().fromJson(s, ClassifyTabBean.class);
                            final List<ClassifyTabBean.DataBean.CategoryListBean> categoryList = classifyTabBean.getData().getCategoryList();
                            final ArrayList<String> tab = new ArrayList<>();
                            for (int i = 0; i < categoryList.size(); i++) {
                                String name = categoryList.get(i).getName();
                                tab.add(name);
                            }
                            getActivity().runOnUiThread(new Runnable() {

                                private ClassifyAdapter adapter;

                                @Override
                                public void run() {
                                    cc_desc.setText(categoryList.get(0).getFront_name());
                                    cTvHome.setText(categoryList.get(0).getName()+"分类");
                                    Glide.with(getContext()).load(categoryList.get(0).getWap_banner_url()).into(cImg);

                                    //recyclerview适配器
                                    final ArrayList<ClassityBean> beans = new ArrayList<>();
                                    final List<ClassifyTabBean.DataBean.CategoryListBean.SubCategoryListBeanX> subCategoryList = categoryList.get(0).getSubCategoryList();
                                    for (int i = 0; i < subCategoryList.size(); i++) {
                                        ClassityBean classityBean = new ClassityBean();
                                        classityBean.image=subCategoryList.get(i).getWap_banner_url();
                                        classityBean.name=subCategoryList.get(i).getName();
                                        beans.add(classityBean);
                                    }
                                    adapter = new ClassifyAdapter(getContext(), beans);
                                    cl_rec.setAdapter(adapter);
                                    cl_rec.setLayoutManager(new GridLayoutManager(getContext(),3));

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
                                        }
                                    });

                                    //tab的点击监听
                                    tabFenlei.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
                                        @Override
                                        public void onTabSelected(TabView tab, int position) {
                                            cTvHome.setText(categoryList.get(position).getName()+"分类");

                                            //获取list数据
                                            OkHttpClient okHttpClient = new OkHttpClient();
                                            Request request=new Request.Builder()
                                                    .get()
                                                    .url("https://cdwan.cn/api/catalog/current?id="+categoryList.get(position).getId())
                                                    .build();
                                            okHttpClient.newCall(request)
                                                    .enqueue(new Callback() {
                                                        @Override
                                                        public void onFailure(@NotNull Call call, @NotNull IOException e) {

                                                        }

                                                        @Override
                                                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                                            String s1 = response.body().string();
                                                            final ListBean listBean = new Gson().fromJson(s1, ListBean.class);
                                                            final ListBean.DataBean.CurrentCategoryBean currentCategory = listBean.getData().getCurrentCategory();
                                                            getActivity().runOnUiThread(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    cc_desc.setText(listBean.getData().getCurrentCategory().getFront_name());
                                                                    cTvHome.setText(listBean.getData().getCurrentCategory().getName()+"分类");
                                                                    Glide.with(getContext()).load(listBean.getData().getCurrentCategory().getWap_banner_url()).into(cImg);
                                                                    beans.clear();
                                                                    for (int i = 0; i <listBean.getData().getCurrentCategory().getSubCategoryList().size() ; i++) {
                                                                        ClassityBean classityBean = new ClassityBean();
                                                                        classityBean.image=listBean.getData().getCurrentCategory().getSubCategoryList().get(i).getWap_banner_url();
                                                                        classityBean.name=listBean.getData().getCurrentCategory().getSubCategoryList().get(i).getName();
                                                                        beans.add(classityBean);
                                                                    }
                                                                    adapter.notifyDataSetChanged();

                                                                }
                                                            });
                                                        }
                                                    });
                                        }

                                        @Override
                                        public void onTabReselected(TabView tab, int position) {

                                        }

                                    });
                                }
                            });

                        }
                    });

        }


    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {

    }
}
