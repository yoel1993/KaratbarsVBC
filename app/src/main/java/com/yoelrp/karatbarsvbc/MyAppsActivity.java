package com.yoelrp.karatbarsvbc;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;


import com.yoelrp.karatbarsvbc.adapter.MyAppsAdapter;
import com.yoelrp.karatbarsvbc.model.MyAppsModel;

import java.util.ArrayList;
import java.util.List;

public class MyAppsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_apps);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_my_apps);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView recycler_my_apps = (RecyclerView) findViewById(R.id.recycler_my_apps);
        MyAppsAdapter adapter = new MyAppsAdapter(this, initData());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_my_apps.setLayoutManager(linearLayoutManager);
        recycler_my_apps.setAdapter(adapter);
    }

    private List<MyAppsModel> initData() {
        List<MyAppsModel> lists = new ArrayList();

        MyAppsModel model0 = new MyAppsModel();
        model0.setName(Constant.KBC_WALLET);
        model0.setDescription(Constant.KBC_WALLET_DESCRIPTION);
        model0.setPackageName(Constant.KBC_WALLET_PACKAGE);
        model0.setGooglePlayUrl(Constant.KBC_WALLET_URL);
        model0.setImageUrl("kbc_wallet");
        lists.add(model0);


        MyAppsModel model1 = new MyAppsModel();
        model1.setName(Constant.KARATPAY);
        model1.setDescription(Constant.KARATPAY_DESCRIPTION);
        model1.setPackageName(Constant.KARATPAY_PACKAGE);
        model1.setGooglePlayUrl(Constant.KARATPAY_URL);
        model1.setImageUrl("karatpay");
        lists.add(model1);

        MyAppsModel model2 = new MyAppsModel();
        model2.setName(Constant.KARAT_BUSINESS);
        model2.setDescription(Constant.KARAT_BUSINESS_DESCRIPTION);
        model2.setPackageName(Constant.KARAT_BUSINESS_PACKAGE);
        model2.setGooglePlayUrl(Constant.KARAT_BUSINESS_URL);
        model2.setImageUrl("karat_business");
        lists.add(model2);

        MyAppsModel model3 = new MyAppsModel();
        model3.setName(Constant.K_TEAM);
        model3.setDescription(Constant.K_TEAM_DESCRIPTION);
        model3.setPackageName(Constant.K_TEAM_PACKAGE);
        model3.setGooglePlayUrl(Constant.K_TEAM_URL);
        model3.setImageUrl("k_team_manager");
        lists.add(model3);

        return lists;
    }
}
