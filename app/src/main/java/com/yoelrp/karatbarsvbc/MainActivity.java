package com.yoelrp.karatbarsvbc;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.yoelrp.karatbarsvbc.News.NewsActivity;

import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ViewFlipper v_flipper_card_1;
    ViewFlipper v_flipper_card_2;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int images_card_1[]={R.drawable.karatbars_0, R.drawable.karatbars_1, R.drawable.karatbars_2, R.drawable.karatbars_3, R.drawable.karatbars_4};

        int images_card_2[]={R.drawable.cripto_1, R.drawable.cripto_2, R.drawable.cripto_3, R.drawable.cripto_4, R.drawable.cripto_5};



        v_flipper_card_1 = (ViewFlipper) findViewById(R.id.v_flipper_card_1);
        v_flipper_card_2 = (ViewFlipper) findViewById(R.id.v_flipper_card_2);


        requestMultiPermission();






        for (int image: images_card_1){
            flipperImagesCard1(image);
        }


        for (int image: images_card_2){
            flipperImagesCard2(image);
        }






        Button btn_card0 = (Button) findViewById(R.id.btn_card_main0_action);
        btn_card0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),BienvenidoActivity.class);
                startActivity(intent);
            }
        });

        Button btn_card1 = (Button) findViewById(R.id.btn_card_main1_action);
        btn_card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),KaratbarsActivity.class);
                startActivity(intent);
            }
        });

        Button btn_card3 = (Button) findViewById(R.id.btn_card_main3_action);
        btn_card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),FaqsActivity.class);
                startActivity(intent);
            }
        });
        Button btn_card2 = (Button) findViewById(R.id.btn_card_main2_action);
        btn_card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),CryptoActivity.class);
                startActivity(intent);
            }
        });

        Button btn_card4 = (Button) findViewById(R.id.btn_card_main4_action);
        btn_card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), NewsActivity.class);
                startActivity(intent);
            }
        });
        Button btn_card5 = (Button) findViewById(R.id.btn2_card_main4_action);
        btn_card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),MyAppsActivity.class);
                startActivity(intent);
            }
        });

        ImageView imageshare =(ImageView)findViewById(R.id.img_main_card2_share);
        imageshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, Constant.SHARE_CONTENT);
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, getString(R.string.share_with)));
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_main);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);


    }

    private void requestMultiPermission() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            showSettingsDialog();
                        }
                    }
                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), R.string.permiso_error, Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.permiso_necesario_title);
        builder.setMessage(R.string.permiso_necesario_subtitle);
        builder.setPositiveButton(R.string.permiso_configuracion, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton(R.string.permiso_cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }
    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 100);
    }


    public void flipperImagesCard1 (int image){
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);
        v_flipper_card_1.addView(imageView);
        v_flipper_card_1.setFlipInterval(3000);
        v_flipper_card_1.setAutoStart(true);
        v_flipper_card_1.setInAnimation(this, R.anim.anim_fade_in);
        v_flipper_card_1.setOutAnimation(this, R.anim.anim_fade_out);

    }
    public void flipperImagesCard2 (int image){
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);
        v_flipper_card_2.addView(imageView);
        v_flipper_card_2.setFlipInterval(3000);
        v_flipper_card_2.setAutoStart(true);
        v_flipper_card_2.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper_card_2.setOutAnimation(this, android.R.anim.slide_out_right);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_main_1:
                Intent intent1 = new Intent(this, AboutActivity.class);
                startActivity(intent1);
                break;
            case R.id.action_menu_main_2:
                Intent intent2 = new Intent(this, DonateActivity.class);
                startActivity(intent2);
                break;
            case R.id.action_menu_main_3:
                Intent intent3 = new Intent();
                intent3.setData(Uri.parse(Constant.APP_URL));
                intent3.setAction(Intent.ACTION_VIEW);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = new Intent();

        switch (item.getItemId()) {


            case R.id.nav_karatbars:
                intent.setClass(this, KaratbarsActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_crypto:
                intent.setClass(this, CryptoActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_news:
                intent.setClass(this, NewsActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_faq:
                intent.setClass(this, FaqsActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_about:
                intent.setClass(this, AboutActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_donate:
                intent.setClass(this, DonateActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_my_apps:
                intent.setClass(this, MyAppsActivity.class);
                startActivity(intent);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
