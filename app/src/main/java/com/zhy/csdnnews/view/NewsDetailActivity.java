package com.zhy.csdnnews.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zhy.bean.NewsItem;
import com.zhy.csdnnews.R;

/**
 * Created by lenovo on 2015/8/24.
 */
public class NewsDetailActivity extends Activity {

    private WebView webView;
    private NewsItem newsItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置窗口风格为进度条
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.news_detail);
        this.newsItem = (NewsItem) getIntent().getSerializableExtra(TabItemFragment.NEWS_ITEM_TAG);
        webView = (WebView) findViewById(R.id.wv_news_detail);

        WebSettings settings = webView.getSettings();
        settings.setSupportZoom(true);//支持缩放
        settings.setBuiltInZoomControls(true);//启动内置缩放装置
        settings.setJavaScriptEnabled(true);//启动JS脚本

        webView.setWebViewClient(new WebViewClient() {
            //当点击链接时，希望覆盖而不是打开新窗口
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.loadUrl(newsItem.getLink());
    }



}
