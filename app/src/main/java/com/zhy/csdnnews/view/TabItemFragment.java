package com.zhy.csdnnews.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.zhy.bean.NetworkException;
import com.zhy.bean.NewsItem;
import com.zhy.csdn.Constaint;
import com.zhy.csdn.NewsParser;
import com.zhy.csdnnews.R;
import com.zhy.csdnnews.enums.LoadStatus;
import com.zhy.csdnnews.utils.CConfigKey;
import com.zhy.csdnnews.utils.MyPreference;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;

import static me.maxwin.view.XListView.IXListViewListener;

public class TabItemFragment extends Fragment implements IXListViewListener {

    public static final String NEWS_ITEM_TAG = "news_item";
    private int newsType = 1;
    private int curPage = Constaint.NEWS_TYPE_YEJIE;

    private NewsParser parser;

    private XListView xListView;
    private List<NewsItem> newsList = new ArrayList<>();

    private NewsItemAdapter newsItemAdapter;
    private MyPreference myPreference;

    public TabItemFragment() {
        parser = new NewsParser();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myPreference = MyPreference.getInstance(getActivity());
        View view = inflater.inflate(R.layout.tab_item_fragment_main, container, false);
        Bundle args = getArguments();
        if (null != args) {
            newsType = args.getInt(CConfigKey.NEWS_TYPE, Constaint.NEWS_TYPE_YEJIE);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        xListView = (XListView) getView().findViewById(R.id.id_xlistView);
        newsItemAdapter = new NewsItemAdapter(getActivity(), newsList);

        xListView.setAdapter(newsItemAdapter);
        xListView.setPullRefreshEnable(true);
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(this);
        xListView.startRefresh();

        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsItem newsItem = newsList.get(position - 1);
                Intent intent = new Intent();
                intent.putExtra(NEWS_ITEM_TAG,newsItem);
                intent.setClass(getActivity(),NewsDetailActivity.class);
                startActivity(intent);
            }
        });

    }


    //下拉刷新
    @Override
    public void onRefresh() {
        new LoadDataTask().execute(LoadStatus.ON_REFRESH);
    }

    //上拉加载更多
    @Override
    public void onLoadMore() {
        new LoadDataTask().execute(LoadStatus.LOAD_MORE);
    }

    private class LoadDataTask extends AsyncTask<LoadStatus, Void, Integer> {


        @Override
        protected Integer doInBackground(LoadStatus... params) {

            LoadStatus param = params[0];
            switch (param) {
                case ON_REFRESH://不需要加类名
                    refreshData();
                    break;
                case LOAD_MORE:
                    loadMore();
                    break;
                default:
                    break;
            }

            return 0;
        }

        @Override
        protected void onPostExecute(Integer result) {

            newsItemAdapter.notifyDataSetChanged();
            xListView.setRefreshTime(myPreference.getRefreshTime(newsType));
            xListView.stopRefresh();
            xListView.stopLoadMore();
        }
    }

    private void refreshData() {
        try {
            curPage = 1;
            newsList = parser.parserByPage(newsType, curPage);
            newsItemAdapter.setNewList(newsList);
            myPreference.setRefreshTime(newsType);
        } catch (NetworkException e) {
            e.printStackTrace();
        }
    }

    private void loadMore() {
        try {
            curPage += 1;
            newsList = parser.parserByPage(newsType, curPage);
            newsItemAdapter.addAll(newsList);
        } catch (NetworkException e) {
            e.printStackTrace();
        }
    }
}
