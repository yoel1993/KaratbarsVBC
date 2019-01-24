package com.yoelrp.karatbarsvbc.News;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.yoelrp.karatbarsvbc.R;

import java.util.Objects;

import static com.yoelrp.karatbarsvbc.News.NewsActivity.CREATOR_NAME;
import static com.yoelrp.karatbarsvbc.News.NewsActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private WebView webView;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Intent intent=getIntent();
        String url=intent.getStringExtra(EXTRA_URL);
        String title=intent.getStringExtra(CREATOR_NAME);

        toolbar=(Toolbar) findViewById(R.id.toollbar_news);
        webView=(WebView) findViewById(R.id.webViewNews);
        progressBar=(ProgressBar) findViewById(R.id.progress_bar_news);
        swipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.webViewRefresh);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setSubtitle(url);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });

        initWebView();
        webView.loadUrl(url);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                webView.reload();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView(){


        webView.getSettings().setSupportZoom(true);

        webView.getSettings().setBuiltInZoomControls(true);

        webView.setWebChromeClient(new MyWebChromeClient(this));

        webView.setWebViewClient(new WebViewClient(){

            Intent intent=getIntent();
            String url=intent.getStringExtra(EXTRA_URL);

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String newUrl) {

                webView.loadUrl(newUrl);
                url=newUrl;
                Objects.requireNonNull(getSupportActionBar()).setSubtitle(newUrl);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(DetailActivity.this,"Error Loading"+url,Toast.LENGTH_SHORT).show();
            }
        });
        webView.clearCache(true);
        webView.clearHistory();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setHorizontalScrollBarEnabled(true);

    }

    private class MyWebChromeClient extends WebChromeClient{
        Context context;
        public MyWebChromeClient(Context context){
            super();
            this.context=context;
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }
}
