package com.app.elixir.brewz_rock_cafe.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplLog;
import com.app.elixir.brewz_rock_cafe.utils.CM;
import com.app.elixir.brewz_rock_cafe.volly.OnVolleyHandler;
import com.app.elixir.brewz_rock_cafe.volly.VolleyIntialization;
import com.app.elixir.brewz_rock_cafe.volly.WebService;
import com.app.elixir.brewz_rock_cafe.volly.WebServiceTag;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

public class ViewForgotPassword extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ViewForgotPassword";
    EditText email_edtTxt;
    Button reset_btn, cancel_btn;
    Activity thisActivity;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisActivity = this;
        setContentView(R.layout.activity_view_forgot_password);
        toolbar = (Toolbar) findViewById(R.id.mToolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        toolbar.setTitle(getString(R.string.forgmentPass));
        toolbar.setTitleTextColor(Color.WHITE);
        TextView titleTextView = null;
        try {
            Field f = toolbar.getClass().getDeclaredField("mTitleTextView");
            f.setAccessible(true);
            titleTextView = (TextView) f.get(toolbar);
            Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), getString(R.string.fontface_robotolight_0));
            titleTextView.setTypeface(font);
            titleTextView.setTextSize(20);
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CM.finishActivity(ViewForgotPassword.this);
            }
        });
        init();
    }

    public void init() {
        email_edtTxt = (EditText) findViewById(R.id.email_edtTxt);
        reset_btn = (Button) findViewById(R.id.reset_btn);
        cancel_btn = (Button) findViewById(R.id.cancel_btn);
        reset_btn.setOnClickListener(this);
        cancel_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.reset_btn:
                if (CM.isInternetAvailable(ViewForgotPassword.this)) {
                    if (email_edtTxt.length() > 0 && CM.isEmailValid(email_edtTxt.getText().toString())) {
                        webCallGetUserId(email_edtTxt.getText().toString());
                    } else {
                        CM.showToast(thisActivity, getString(R.string.validemail));
                    }
                } else {
                    CM.showToast(thisActivity, getString(R.string.internetMsg));
                }
                break;
            case R.id.cancel_btn:
                CM.finishActivity(this);
                break;
        }

    }


    public void webCallGetUserId(String email) {
        try {
            VolleyIntialization v = new VolleyIntialization(ViewForgotPassword.this, true, true);
            WebService.GetUserId(v, email, new OnVolleyHandler() {
                @Override
                public void onVollySuccess(String response) {
                    if (isFinishing()) {
                        return;
                    }
                    MtplLog.i("WebCalls", response);
                    Log.e(TAG, response);
                    getResponseForUserId(response);

                }

                @Override
                public void onVollyError(String error) {
                    MtplLog.i("WebCalls", error);
                    if (CM.isInternetAvailable(ViewForgotPassword.this)) {
                        CM.showPopupCommonValidation(ViewForgotPassword.this, error, false);
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getResponseForUserId(String response) {
        String strResponseStatus = CM.getValueFromJson(WebServiceTag.WEB_STATUS, response);
        if (strResponseStatus.equalsIgnoreCase(WebServiceTag.WEB_STATUSFAIL)) {
            CM.showPopupCommonValidation(ViewForgotPassword.this, CM.getValueFromJson(WebServiceTag.WEB_STATUS_ERRORTEXT, response), false);
            return;
        }
        try {

            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.optString("ResponseCode").toString().equals("202")) {
                JSONObject jsonObject1 = new JSONObject(jsonObject.getString("ResponseObject"));
                JSONObject jsonObject11 = new JSONObject(jsonObject1.getString("user"));
                webCallForgetPassword(email_edtTxt.getText().toString(), jsonObject11.getString("nUserID").toString(), jsonObject11.getString("UserTypeID").toString());
            } else if (jsonObject.optString("ResponseCode").toString().equals("404")) {
                CM.showToast(thisActivity, jsonObject.getString("ResponseObject").toString());
            }
        } catch (Exception e) {
            CM.showPopupCommonValidation(ViewForgotPassword.this, e.getMessage(), false);
        }
    }

    public void webCallForgetPassword(String email, String customerId, String userTypeId) {
        try {
            VolleyIntialization v = new VolleyIntialization(ViewForgotPassword.this, true, true);
            WebService.getResendForForgetPassword(v, email, customerId, userTypeId, new OnVolleyHandler() {
                @Override
                public void onVollySuccess(String response) {
                    if (isFinishing()) {
                        return;
                    }
                    MtplLog.i("WebCalls", response);
                    Log.e(TAG, response);
                    getResponseForForgetPassword(response);

                }

                @Override
                public void onVollyError(String error) {
                    MtplLog.i("WebCalls", error);
                    if (CM.isInternetAvailable(ViewForgotPassword.this)) {
                        CM.showPopupCommonValidation(ViewForgotPassword.this, error, false);
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getResponseForForgetPassword(String response) {
        String strResponseStatus = CM.getValueFromJson(WebServiceTag.WEB_STATUS, response);
        if (strResponseStatus.equalsIgnoreCase(WebServiceTag.WEB_STATUSFAIL)) {
            CM.showPopupCommonValidation(ViewForgotPassword.this, CM.getValueFromJson(WebServiceTag.WEB_STATUS_ERRORTEXT, response), false);
            return;
        }
        try {

            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.optString("ResponseCode").toString().equals("200")) {
                CM.showToast(ViewForgotPassword.this, "Your password has been send to your email address");
                email_edtTxt.setText("");
            } else if (jsonObject.optString("ResponseCode").toString().equals("402")) {
                CM.showToast(ViewForgotPassword.this, jsonObject.getString("ResponseObject"));
            } else {
                CM.showToast(ViewForgotPassword.this, jsonObject.getString("ResponseObject"));
            }
        } catch (Exception e) {
            CM.showPopupCommonValidation(ViewForgotPassword.this, e.getMessage(), false);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CM.finishActivity(thisActivity);
    }
}
