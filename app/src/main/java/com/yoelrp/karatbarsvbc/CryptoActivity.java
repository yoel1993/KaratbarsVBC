package com.yoelrp.karatbarsvbc;

/**
 * Created by YoelRP on 04/12/2018.
 */



import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.yoelrp.karatbarsvbc.YouTubeVideo.VideoCripto1Activity;
import com.yoelrp.karatbarsvbc.YouTubeVideo.VideoCripto2Activity;
import com.yoelrp.karatbarsvbc.YouTubeVideo.VideoCriptoEntrevista;


public class CryptoActivity extends AppCompatActivity {
    ViewFlipper vflipper_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        int images[]={R.drawable.cripto_1, R.drawable.cripto_2, R.drawable.cripto_3, R.drawable.cripto_4, R.drawable.cripto_5};


        vflipper_2 = (ViewFlipper) findViewById(R.id.v_flipper2);
        /*for (int i=0; i<images.length; i++){
            flipperImages(images[i]);
        }*/

        for (int image: images){
            flipperImages(image);
        }


        ImageView imageYobit=(ImageView) findViewById(R.id.image_button_yobit);
        imageYobit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setData(Uri.parse(Constant.YOBIT));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
            }
        });


        ImageView imageHitbtc=(ImageView) findViewById(R.id.image_button_hitbtc);
        imageHitbtc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setData(Uri.parse(Constant.HITBTC));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
            }
        });

        Button btnkbc=(Button) findViewById(R.id.boton_cripto_video_kbc);
        btnkbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), VideoCripto1Activity.class);
                startActivity(intent);
            }
        });

        Button btnentrevista=(Button) findViewById(R.id.boton_cripto_video_entrevista);
        btnentrevista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), VideoCriptoEntrevista.class);
                startActivity(intent);
            }
        });


        ImageButton imabtnbuy=(ImageButton) findViewById(R.id.buy_kcb_button);
        imabtnbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setData(Uri.parse(Constant.BUY_KCB));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
            }
        });
        Button btnkcb=(Button) findViewById(R.id.boton_cripto_video_kcb);
        btnkcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), VideoCripto2Activity.class);
                startActivity(intent);
            }
        });




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_scrolling_cripto);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_scrolling_cripto);
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
    }
    public void flipperImages (int image){
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);
        vflipper_2.addView(imageView);
        vflipper_2.setFlipInterval(3000);
        vflipper_2.setAutoStart(true);
        vflipper_2.setInAnimation(this, android.R.anim.slide_in_left);
        vflipper_2.setOutAnimation(this, android.R.anim.slide_out_right);
    }
    @Override
    protected void onResume() {
        super.onResume();

        Configuration configuration = getResources().getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            CollapsingToolbarLayout collapsing_toolbar_layout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_cripto);
            collapsing_toolbar_layout.setExpandedTitleTextColor(ColorStateList.valueOf(Color.TRANSPARENT));
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

}
