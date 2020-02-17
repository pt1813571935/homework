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
import com.umeng.jiaqizuoye1_26.bean.PageBean;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TopicFragment extends Fragment {

    private RecyclerView rec;
    private List<PageBean.DataBean.TopicListBean> topicList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.topic_layout, null);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
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

    private void initView(View view) {
        rec = view.findViewById(R.id.topic_rec);
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
