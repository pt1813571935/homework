package com.umeng.jiaqizuoye1_26;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.umeng.jiaqizuoye1_26.adapter.TopicAdapter;
import com.umeng.jiaqizuoye1_26.base.BaseFragment;
import com.umeng.jiaqizuoye1_26.bean.PageBean;
import com.umeng.jiaqizuoye1_26.interfaces.topic.Topic;
import com.umeng.jiaqizuoye1_26.presenter.fristpagepresenter.TopicPresenter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TopicFragment extends BaseFragment<Topic.View, Topic.Presenter>implements Topic.View {

    private RecyclerView rec;
    private List<PageBean.DataBean.TopicListBean> topicList;


    @Override
    protected int getLayout() {
        return R.layout.topic_layout;
    }

    protected void initData() {
      ( (TopicPresenter)presenter).getTopic();

    }

    @Override
    protected Topic.Presenter createPresenter() {
        return new TopicPresenter();
    }

    protected void initView(View view) {
        rec = view.findViewById(R.id.topic_rec);
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void returnTopic(PageBean pageBean) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .get()
                .url("https://cdwan.cn/api/index/index")
                .build();
        client.newCall(request)
                .enqueue(new Callback() {



                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String s = response.body().string();
                        PageBean pageBean = new Gson().fromJson(s, PageBean.class);
                        topicList = pageBean.getData().getTopicList();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TopicAdapter adapter = new TopicAdapter(getContext(), topicList);
                                rec.setAdapter(adapter);
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
