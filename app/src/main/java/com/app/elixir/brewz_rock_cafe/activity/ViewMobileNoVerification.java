package com.app.elixir.brewz_rock_cafe.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplButton;
import com.app.elixir.brewz_rock_cafe.utils.CM;
import com.app.elixir.brewz_rock_cafe.volly.OnVolleyHandler;
import com.app.elixir.brewz_rock_cafe.volly.VolleyIntialization;
import com.app.elixir.brewz_rock_cafe.volly.WebService;
import com.app.elixir.brewz_rock_cafe.volly.WebServiceTag;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

public class ViewMobileNoVerification extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "Verification Screen";
    private MtplButton buttonVerify;
    private EditText editTextMoNo;
    private RelativeLayout rootlayout;
    private static final int PERMISSION_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_mobile_no_verification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        try {
            CM.setSp(ViewMobileNoVerification.this, "regId", FirebaseInstanceId.getInstance().getToken().toString());
            Log.e("FCM_ID", FirebaseInstanceId.getInstance().getToken().toString());
        } catch (Exception e) {
            e.getMessage();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        }
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.titleColor));
        toolbar.setTitle(getString(R.string.moblieNoVerifaction));
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

        buttonVerify = (MtplButton) findViewById(R.id.mobileVerifybtn);
        editTextMoNo = (EditText) findViewById(R.id.moblieNoVerNo);
        editTextMoNo.setSelection(editTextMoNo.getText().length());
        rootlayout = (RelativeLayout) findViewById(R.id.mainmobveri);
        buttonVerify.setOnClickListener(this);
        /**
         *  Done Using KeyBord
         */
        editTextMoNo.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if (CM.isInternetAvailable(ViewMobileNoVerification.this)) {
                        if (CM.isValidPhoneNumber(editTextMoNo.getText().toString().trim())) {
//                            webCallVerifyNo(editTextMoNo.getText().toString().trim());
                            send();
                        } else {
                            CM.showSnackBar(rootlayout, getString(R.string.invalidNo));
                        }
                    } else {
                        CM.showSnackBar(rootlayout, getString(R.string.internetMsg));
                    }
                    return true;
                } else {

                }
                return false;
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mobileVerifybtn:

                if (CM.isInternetAvailable(ViewMobileNoVerification.this)) {
                    if (CM.isValidPhoneNumber(editTextMoNo.getText().toString().trim())) {
//                        webCallVerifyNo(editTextMoNo.getText().toString().trim());
                        send();
                    } else {
                        CM.showSnackBar(rootlayout, getString(R.string.invalidNo));
                    }
                } else {
                    CM.showSnackBar(rootlayout, getString(R.string.internetMsg));
                }
                break;
        }
    }

    public void webCallVerifyNo(String no) {
        try {
            VolleyIntialization v = new VolleyIntialization(ViewMobileNoVerification.this, true, true);
            WebService.getOTP(v, no, new OnVolleyHandler() {
                @Override
                public void onVollySuccess(String response) {
                    if (isFinishing()) {
                        return;
                    }
                    Log.e(TAG, response);
                    getResponseForVerifyNo(response);

                }

                @Override
                public void onVollyError(String error) {
                    Log.e(TAG, error);
                    if (CM.isInternetAvailable(ViewMobileNoVerification.this)) {
                        CM.showPopupCommonValidation(ViewMobileNoVerification.this, error, false);
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void getResponseForVerifyNo(String response) {
        String strResponseStatus = CM.getValueFromJson(WebServiceTag.WEB_STATUS, response);
        if (strResponseStatus.equalsIgnoreCase(WebServiceTag.WEB_STATUSFAIL)) {
            CM.showPopupCommonValidation(ViewMobileNoVerification.this, CM.getValueFromJson(WebServiceTag.WEB_STATUS_ERRORTEXT, response), false);
            return;
        }
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("ResponseCode").equals("201")) {
                CM.setSp(ViewMobileNoVerification.this, "otp", jsonObject.getString("ResponseObject"));
                CM.setSp(ViewMobileNoVerification.this, "number", editTextMoNo.getText().toString().trim());
                CM.startActivity(ViewMobileNoVerification.this, ViewOTPScreen.class);
                finish();
            } else if (jsonObject.getString("ResponseCode").equals("409")) {
                CM.showToast(this, "Number All ready registered");
                CM.startActivity(ViewMobileNoVerification.this, ViewDrawerActivty.class);
                finish();
            } else {
                CM.showToast(this, jsonObject.getString("ResponseObject").toString());
            }
        } catch (Exception e) {
            CM.showPopupCommonValidation(ViewMobileNoVerification.this, e.getMessage(), false);
        }
    }


    public void send() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)) {

                    //  CM.showSnackBar(rootlayout, "You need to grant SEND SMS permission to send sms");

                    //
                    Snackbar.make(rootlayout, "You need to grant SEND SMS permission to send sms",
                            Snackbar.LENGTH_LONG).setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST);
                            }
                        }
                    }).show();
                } else {
                    requestPermissions(new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST);
                }
            } else {
                //sendSMS();
                //  webCallVerifyNo(editTextMoblieNO.getText().toString().trim());
                webCallVerifyNo(editTextMoNo.getText().toString().trim());
            }
        } else {
            //   sendSMS();
            // webCallVerifyNo(editTextMoblieNO.getText().toString().trim());
            webCallVerifyNo(editTextMoNo.getText().toString().trim());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            Snackbar.make(rootlayout, "Permission Granted",
                    Snackbar.LENGTH_LONG).show();
            //   CM.showSnackBar(rootlayout, "Permission Granted");
            // sendSMS();
            // webCallVerifyNo(editTextMoblieNO.getText().toString().trim());
            webCallVerifyNo(editTextMoNo.getText().toString().trim());

        } else {

            // CM.showSnackBar(rootlayout, "Permission denied");
            Snackbar.make(rootlayout, "Permission denied",
                    Snackbar.LENGTH_LONG).show();
            webCallVerifyNo(editTextMoNo.getText().toString().trim());
            //   webCallVerifyNo(editTextMoblieNO.getText().toString().trim());

        }
    }
}


