package com.yoelrp.karatbarsvbc.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.yoelrp.karatbarsvbc.R;

/**
 * Created by YoelRP on 19/12/2018.
 */

public class CuadroDialogsDonateBTC {
    public CuadroDialogsDonateBTC (Context contexto){
        final Dialog dialog= new Dialog(contexto);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialogs_donate_btc);

        ImageView btnAceptar=(ImageView) dialog.findViewById(R.id.donate_btc_aceptar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
