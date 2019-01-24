package com.yoelrp.karatbarsvbc;

/**
 * Created by YoelRP on 04/12/2018.
 */

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;



import com.yoelrp.karatbarsvbc.util.AppUtils;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_about);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));

        initView();
    }

    public void initView() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show);
        ScrollView scroll_about = (ScrollView) findViewById(R.id.scroll_about);
        scroll_about.startAnimation(animation);

        LinearLayout ll_card_about_2_shop =(LinearLayout) findViewById(R.id.ll_card_about_2_shop);
        LinearLayout ll_card_about_2_email = (LinearLayout)findViewById(R.id.ll_card_about_2_email);
        LinearLayout ll_card_about_2_facebook = (LinearLayout)findViewById(R.id.ll_card_about_2_facebook);
        LinearLayout ll_card_about_2_twitter = (LinearLayout)findViewById(R.id.ll_card_about_2_twitter);
        LinearLayout ll_card_about_2_instagram = (LinearLayout)findViewById(R.id.ll_card_about_2_instagram);
        LinearLayout ll_card_about_2_website = (LinearLayout)findViewById(R.id.ll_card_about_2_website);
        LinearLayout ll_card_about_2_github=(LinearLayout) findViewById(R.id.ll_card_about_2_github);
        LinearLayout ll_card_about_source_licenses =(LinearLayout) findViewById(R.id.ll_card_about_source_licenses);
        ll_card_about_2_shop.setOnClickListener(this);
        ll_card_about_2_email.setOnClickListener(this);
        ll_card_about_2_facebook.setOnClickListener(this);
        ll_card_about_2_twitter.setOnClickListener(this);
        ll_card_about_2_instagram.setOnClickListener(this);
        ll_card_about_2_website.setOnClickListener(this);
        ll_card_about_2_github.setOnClickListener(this);
        ll_card_about_source_licenses.setOnClickListener(this);

        FloatingActionButton fab =(FloatingActionButton) findViewById(R.id.fab_about_share);
        fab.setOnClickListener(this);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setStartOffset(600);

        TextView tv_about_version =(TextView) findViewById(R.id.tv_about_version);
        tv_about_version.setText(AppUtils.getVersionName(this));
        tv_about_version.startAnimation(alphaAnimation);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.ll_card_about_2_shop:
                intent.setData(Uri.parse(Constant.APP_URL));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                break;

            case R.id.ll_card_about_2_email:
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse(Constant.EMAIL));
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.about_email_intent));
                //intent.putExtra(Intent.EXTRA_TEXT, "Hi,");
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(AboutActivity.this, getString(R.string.about_not_found_email), Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.ll_card_about_source_licenses:
                final Dialog dialog = new Dialog(this, R.style.DialogFullscreenWithTitle);
                dialog.setTitle(getString(R.string.about_source_licenses));
                dialog.setContentView(R.layout.dialog_source_licenses);

                final WebView webView = (WebView) dialog.findViewById(R.id.web_source_licenses);
                webView.loadUrl("file:///android_asset/open_source_license.html");

                Button btn_source_licenses_close = (Button) dialog.findViewById(R.id.btn_source_licenses_close);
                btn_source_licenses_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
                break;

            case R.id.ll_card_about_2_facebook:
                intent.setData(Uri.parse(Constant.FACEBOOK));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                break;
            case R.id.ll_card_about_2_twitter:
                intent.setData(Uri.parse(Constant.TWITTER));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                break;
            case R.id.ll_card_about_2_instagram:
                intent.setData(Uri.parse(Constant.INSTAGRAM));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                break;

            case R.id.ll_card_about_2_website:
                intent.setData(Uri.parse(Constant.MY_WEBSITE));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                break;
            case R.id.ll_card_about_2_github:
                intent.setData(Uri.parse(Constant.GITHUB));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                break;

            case R.id.fab_about_share:
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, Constant.SHARE_CONTENT);
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, getString(R.string.share_with)));
                break;
        }
    }

}