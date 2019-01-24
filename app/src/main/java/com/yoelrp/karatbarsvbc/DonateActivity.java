package com.yoelrp.karatbarsvbc;

/**
 * Created by YoelRP on 04/12/2018.
 */



import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.yoelrp.karatbarsvbc.Dialogs.CuadroDialogsDonateBTC;
import com.yoelrp.karatbarsvbc.Dialogs.CuadroDialogsDonateETH;
import com.yoelrp.karatbarsvbc.Dialogs.CuadroDialogsDonateKBC;
import com.yoelrp.karatbarsvbc.Dialogs.CuadroDialogsDonateKCB;
import com.yoelrp.karatbarsvbc.QRScanner.QrCodeActivity;


public class DonateActivity extends AppCompatActivity{

    private Button button;
    private static final int REQUEST_CODE_QR_SCAN = 101;
    private final String LOGTAG = "QRCScanner-MainActivity";


    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_donate);
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }


        Button btn_donate_kbc=(Button) findViewById(R.id.btn_donate_kbc);
        Button btn_donate_kcb=(Button) findViewById(R.id.btn_donate_kcb);
        Button btn_donate_btc=(Button) findViewById(R.id.btn_donate_btc);
        Button btn_donate_eth=(Button) findViewById(R.id.btn_donate_eth);

        FloatingActionButton fbtn=(FloatingActionButton) findViewById(R.id.fab_donate);
        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DonateActivity.this,QrCodeActivity.class);
                startActivityForResult( i,REQUEST_CODE_QR_SCAN);
            }
        });




        contexto=this;

        btn_donate_kbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CuadroDialogsDonateKBC(contexto);
            }
        });


        btn_donate_kcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CuadroDialogsDonateKCB(contexto);
            }
        });

        btn_donate_btc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CuadroDialogsDonateBTC(contexto);
            }
        });

        btn_donate_eth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CuadroDialogsDonateETH(contexto);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode != Activity.RESULT_OK)
        {
            Log.d(LOGTAG,"COULD NOT GET A GOOD RESULT.");
            if(data==null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if( result!=null)
            {
                AlertDialog alertDialog = new AlertDialog.Builder(DonateActivity.this).create();
                alertDialog.setTitle("Scan Error");
                alertDialog.setMessage("QR Code could not be scanned");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            return;

        }
        if(requestCode == REQUEST_CODE_QR_SCAN)
        {
            if(data==null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
            Log.d(LOGTAG,"Have scan result in your app activity :"+ result);
            AlertDialog alertDialog = new AlertDialog.Builder(DonateActivity.this).create();
            alertDialog.setTitle("Scan result");
            alertDialog.setMessage(result);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

        }
    }

}

