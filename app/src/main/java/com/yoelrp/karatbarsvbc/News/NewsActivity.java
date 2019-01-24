package com.yoelrp.karatbarsvbc.News;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.GoogleApiClient;
import com.yoelrp.karatbarsvbc.Constant;
import com.yoelrp.karatbarsvbc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by YoelRP on 08/01/2019.
 */

public class NewsActivity extends AppCompatActivity implements ExampleAdapter.OnItemClickListener {

    public static final String EXTRA_URL = "imageUrl";
    public static final String CREATOR_NAME = "creatorName";
    public static final String EXTRA_LIKES = "likeCount";

    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue mRequestQueue;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton fab;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_news);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        fab = (FloatingActionButton) findViewById(R.id.fab_news);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, Constant.SHARE_CONTENT);
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, getString(R.string.share_with)));
            }
        });


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_recycler_view);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        parseJSON();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);

            }
        });
    }

    private void parseJSON() {


        String url = "https://raw.githubusercontent.com/yoel1993/KaratbarsVBC/master/server.json";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        save(response.toString());


                        try {
                            mExampleList.clear();

                            JSONArray jsonArray = response.getJSONArray("item");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);


                                String creatorName = hit.getString("title");
                                String imageUrl = hit.getString("link");
                                String likeCount = hit.getString("pubDate");


                                mExampleList.add(new ExampleItem(imageUrl, creatorName, likeCount));
                            }

                            mExampleAdapter = new ExampleAdapter(NewsActivity.this, mExampleList);
                            mRecyclerView.setAdapter(mExampleAdapter);
                            mExampleAdapter.setOnItemClickListener(NewsActivity.this);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(NewsActivity.this, R.string.internet_problem, Toast.LENGTH_SHORT).show();
                volleyError.printStackTrace();

            }
        });

        MySingleton.getsInstance(getApplicationContext()).addToRequestque(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        ExampleItem clickedItem = mExampleList.get(position);
        detailIntent.putExtra(EXTRA_URL, clickedItem.getmImageUrl());
        detailIntent.putExtra(EXTRA_LIKES, clickedItem.getmLikes());
        detailIntent.putExtra(CREATOR_NAME, clickedItem.getmCreator());
        startActivity(detailIntent);
    }

    private void save(String json) {

        try {
            OutputStreamWriter fout =
                    new OutputStreamWriter(
                            openFileOutput("datos.txt", Context.MODE_PRIVATE));

            fout.write(json);
            fout.close();
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al escribir fichero a memoria interna");
        }

    }


}
