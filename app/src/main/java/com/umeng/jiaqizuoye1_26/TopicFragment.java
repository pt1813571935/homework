package com.umeng.jiaqizuoye1_26;


import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.umeng.jiaqizuoye1_26.adapter.TopicAdapter;
import com.umeng.jiaqizuoye1_26.base.BaseFragment;
import com.umeng.jiaqizuoye1_26.bean.PageBean;
import com.umeng.jiaqizuoye1_26.interfaces.topic.Topic;
import com.umeng.jiaqizuoye1_26.presenter.fristpagepresenter.TopicPresenter;

import java.util.List;

public class TopicFragment extends BaseFragment<Topic.View, Topic.Presenter>implements Topic.View {

    private RecyclerView rec;
    private List<PageBean.DataBean.TopicListBean> topicList;


    @Override
    protected int getLayout() {
        return R.layout.topic_layout;
    }

    protected void initData() {
      presenter.getTopic();

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
        topicList = pageBean.getData().getTopicList();
        TopicAdapter adapter = new TopicAdapter(topicList,getContext());
        rec.setAdapter(adapter);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {

    }
}
