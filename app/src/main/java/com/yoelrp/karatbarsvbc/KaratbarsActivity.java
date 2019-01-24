package com.yoelrp.karatbarsvbc;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.yoelrp.karatbarsvbc.YouTubeVideo.VideoKaratbars1Activity;
import com.yoelrp.karatbarsvbc.YouTubeVideo.VideoKaratbars2Activity;
import com.yoelrp.karatbarsvbc.YouTubeVideo.VideoKaratbars3Activity;


/**
 * Created by YoelRP on 05/12/2018.
 */

public class KaratbarsActivity extends AppCompatActivity{

    ViewFlipper vflipper_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karatbars);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        Button btn1=(Button) findViewById(R.id.boton_karatbars_video_0);
        Button btn2=(Button) findViewById(R.id.boton_karatbars_video_2);
        Button btn3=(Button) findViewById(R.id.boton_karatbars_video_3);
        Button btnRegistrarse=(Button) findViewById(R.id.boton_karatbars_registrarse);
        ImageView imageRegistrarse=(ImageView) findViewById(R.id.image_button_karatbars_registrarse);


        imageRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setData(Uri.parse(Constant.REGISTRARSE));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
            }
        });

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setData(Uri.parse(Constant.REGISTRARSE));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),VideoKaratbars1Activity.class);
                startActivity(intent);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),VideoKaratbars2Activity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),VideoKaratbars3Activity.class);
                startActivity(intent);
            }
        });



        int images[]={R.drawable.karatbars_0, R.drawable.karatbars_1, R.drawable.karatbars_2, R.drawable.karatbars_3, R.drawable.karatbars_4};

        vflipper_1 = (ViewFlipper) findViewById(R.id.v_flipper1);
        /*for (int i=0; i<images.length; i++){
            flipperImages(images[i]);
        }*/

        for (int image: images){
            flipperImages(image);
        }



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_scrolling);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_scrolling);
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

        //ImageView image_scrolling_top = (ImageView) findViewById(R.id.image_scrolling_top);
        //Glide.with(this).load(R.drawable.material_design_3).apply(new RequestOptions().fitCenter()).into(image_scrolling_top);
    }

    public void flipperImages (int image){
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);
        vflipper_1.addView(imageView);
        vflipper_1.setFlipInterval(3000);
        vflipper_1.setAutoStart(true);
        vflipper_1.setInAnimation(this, android.R.anim.slide_in_left);
        vflipper_1.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Configuration configuration = getResources().getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            CollapsingToolbarLayout collapsing_toolbar_layout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
            collapsing_toolbar_layout.setExpandedTitleTextColor(ColorStateList.valueOf(Color.TRANSPARENT));
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

}
