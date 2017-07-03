package com.app.elixir.brewz_rock_cafe.volly;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.elixir.brewz_rock_cafe.R;


public class MtplProgressDialog extends Dialog {


    public MtplProgressDialog(Context context, String Message,
                              boolean isCancelable) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mtpl_progress_dialog);
        setCancelable(isCancelable);
        ProgressBar pBar = (ProgressBar) findViewById(R.id.dialogProgressBar);

        TextView txtMsg = (TextView) findViewById(R.id.mtpl_customdialog_txtMessage);

        txtMsg.setText(Message);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

    }
}