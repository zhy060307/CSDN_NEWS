package com.zhy.csdnnews.view;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.zhy.csdnnews.R;

/**
 * Created by lenovo on 2015/8/24.
 */
public class NewsDetailActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail);
        webView = (WebView) findViewById(R.id.wv_news_detail);

    }



}
