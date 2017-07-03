package com.app.elixir.brewz_rock_cafe.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.elixir.brewz_rock_cafe.Model.LoginEmilModel;
import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.database.tbl_notification;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplLog;
import com.app.elixir.brewz_rock_cafe.utils.CM;
import com.app.elixir.brewz_rock_cafe.volly.OnVolleyHandler;
import com.app.elixir.brewz_rock_cafe.volly.VolleyIntialization;
import com.app.elixir.brewz_rock_cafe.volly.WebService;
import com.app.elixir.brewz_rock_cafe.volly.WebServiceTag;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;


public class ViewLoginActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "logout";

    private Button btnVerfy, btnLogin;
    private EditText editTextMoblieNO, editTextEmial;
    private LinearLayout rootLayout;
    private EditText editTextPass;
    private Button btnForgetPass;
    private LoginEmilModel model_main;
    private LinearLayout rootView;
    private static final int PERMISSION_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        CM.setSp(this, "Notikey", "");
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.titleColor));
        toolbar.setTitle(getString(R.string.signIn));
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

        initView();
    }


    public void initView() {

        rootLayout = (LinearLayout) findViewById(R.id.main);
        editTextMoblieNO = (EditText) findViewById(R.id.edtMobileNo);
        editTextEmial = (EditText) findViewById(R.id.edtEmail);
        editTextPass = (EditText) findViewById(R.id.loginpass);
        btnVerfy = (Button) findViewById(R.id.btnLoginVerifyNo);
        btnLogin = (Button) findViewById(R.id.btnLoginSignin);
        btnForgetPass = (Button) findViewById(R.id.btnLoginForgot);
        rootView = (LinearLayout) findViewById(R.id.main);
        btnVerfy.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnForgetPass.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CM.finishActivity(ViewLoginActivity.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoginVerifyNo:
                if (CM.isValidPhoneNumber(editTextMoblieNO.getText().toString().trim())) {
                    if (CM.isInternetAvailable(ViewLoginActivity.this)) {
                     //   webCallVerifyNo(editTextMoblieNO.getText().toString().trim());
                        send();
                    } else {
                        CM.showSnackBar(rootLayout, getString(R.string.internetMsg));
                    }
                } else {
                    CM.showSnackBar(rootLayout, getString(R.string.invalidNo));
                }
                break;
            case R.id.btnLoginSignin:
                if (CM.isInternetAvailable(ViewLoginActivity.this)) {
                    if (!editTextEmial.getText().toString().trim().equals("") && !editTextPass.getText().toString().trim().equals("")) {
                        if (CM.isEmailValid(editTextEmial.getText().toString())) {
                            CM.setSp(ViewLoginActivity.this, "customerId", "");
                            webCallSignIn(editTextEmial.getText().toString(), editTextPass.getText().toString());
                        } else {
                            CM.showSnackBar(rootLayout, getString(R.string.invalidEmail));
                        }
                    } else {
                        CM.showSnackBar(rootLayout, getString(R.string.requireddetail));
                    }
                } else {
                    CM.showSnackBar(rootLayout, getString(R.string.internetMsg));
                }
                break;
            case R.id.btnLoginForgot:
                if (CM.isInternetAvailable(ViewLoginActivity.this)) {
                    CM.startActivity(this, ViewForgotPassword.class);
                } else {
                    CM.showSnackBar(rootLayout, getString(R.string.internetMsg));
                }
                break;
        }
    }


    public void webCallSignIn(String email, String pass) {
        try {
            VolleyIntialization v = new VolleyIntialization(ViewLoginActivity.this, true, true);
            WebService.getSignIn(v, email, pass, new OnVolleyHandler() {
                @Override
                public void onVollySuccess(String response) {
                    if (isFinishing()) {
                        return;
                    }
                    MtplLog.i("WebCalls", response);
                    Log.e(TAG, response);
                    getResponseForSignIn(response);

                }

                @Override
                public void onVollyError(String error) {
                    MtplLog.i("WebCalls", error);
                    if (CM.isInternetAvailable(ViewLoginActivity.this)) {
                        CM.showPopupCommonValidation(ViewLoginActivity.this, error, false);
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void getResponseForSignIn(String response) {
        String strResponseStatus = CM.getValueFromJson(WebServiceTag.WEB_STATUS, response);
        if (strResponseStatus.equalsIgnoreCase(WebServiceTag.WEB_STATUSFAIL)) {
            CM.showPopupCommonValidation(ViewLoginActivity.this, CM.getValueFromJson(WebServiceTag.WEB_STATUS_ERRORTEXT, response), false);
            return;
        }
        try {
            JSONObject jsonObject = new JSONObject(response);
            String responseObject = jsonObject.getString("ResponseObject");
            String responseCode = jsonObject.getString("ResponseCode");
            if (responseCode.equals("202")) {
                JSONObject jsonObject1 = new JSONObject(responseObject);
                model_main = CM.JsonParse(new LoginEmilModel(), jsonObject1.get("user").toString());
                CM.setSp(ViewLoginActivity.this, "number", model_main.cMobilePrimary);
                CM.setSp(ViewLoginActivity.this, "fname", model_main.cFirstname);
                CM.setSp(ViewLoginActivity.this, "lname", model_main.cLastname);
                CM.setSp(ViewLoginActivity.this, "email", model_main.cEmail);
                CM.setSp(ViewLoginActivity.this, "pNo", model_main.cMobilePrimary);

                JSONObject jsonObject2 = new JSONObject(jsonObject1.get("user").toString());
                JSONObject jsonArray = new JSONObject(jsonObject2.getString("tblusertype"));
                if (jsonArray.getString("cUserType").toString().equals("Customer")) {
                    tbl_notification.deleteAll();
                    Intent intent = new Intent("finish_activityCust");
                    sendBroadcast(intent);

                    if (model_main.nCustomerID != null && model_main.nCustomerID.toString().equals("0")) {
                        CM.showToast(ViewLoginActivity.this, getString(R.string.invcustId));
                    } else {
                        CM.setSp(ViewLoginActivity.this, TAG, "0");
                        CM.setSp(this, "custLog", "1");
                        CM.setSp(ViewLoginActivity.this, "customerId", model_main.nCustomerID);
                        CM.startActivity(this, ViewDrawerActivty.class);
                        finish();
                    }
                } else if (jsonArray.getString("cUserType").toString().equals("Delivery Boy")) {
                    tbl_notification.deleteAll();
                    Intent intent = new Intent("finish_activityDeliv");
                    sendBroadcast(intent);

                    if (model_main.nUserID != null && model_main.nUserID.toString().equals("0")) {
                        CM.showToast(ViewLoginActivity.this, getString(R.string.invcustId));
                    } else {
                        CM.setSp(ViewLoginActivity.this, TAG, "0");
                        CM.setSp(this, "custLog", "0");
                        CM.setSp(ViewLoginActivity.this, "customerId", model_main.nUserID);
                        CM.startActivity(this, ViewDeliveryBoy.class);
                        finish();
                    }


                }
            } else if (responseCode.equals("404")) {
                if (responseObject.toString().equals("Not Found")) {
                    CM.showToast(this, getString(R.string.validateLogin));
                } else {
                    CM.showSnackBar(rootView, responseObject.toString());
                }

            } else {
                CM.showSnackBar(rootView, responseObject.toString());
            }


        } catch (Exception e) {
            CM.showPopupCommonValidation(ViewLoginActivity.this, e.getMessage(), false);
        }
    }

    public void webCallVerifyNo(String no) {
        try {
            VolleyIntialization v = new VolleyIntialization(ViewLoginActivity.this, true, true);
            WebService.getOTP(v, no, new OnVolleyHandler() {
                @Override
                public void onVollySuccess(String response) {
                    if (isFinishing()) {
                        return;
                    }
                    MtplLog.i("WebCalls", response);
                    Log.e(TAG, response);
                    getResponseForVerifyNo(response);

                }

                @Override
                public void onVollyError(String error) {
                    MtplLog.i("WebCalls", error);
                    if (CM.isInternetAvailable(ViewLoginActivity.this)) {
                        CM.showPopupCommonValidation(ViewLoginActivity.this, error, false);
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
            CM.showPopupCommonValidation(ViewLoginActivity.this, CM.getValueFromJson(WebServiceTag.WEB_STATUS_ERRORTEXT, response), false);
            return;
        }
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("ResponseCode").equals("201")) {
                CM.setSp(ViewLoginActivity.this, "otp", jsonObject.getString("ResponseObject"));
                CM.setSp(ViewLoginActivity.this, "number", editTextMoblieNO.getText().toString().trim());
                CM.setSp(this, "custLog", "1");
                CM.startActivity(ViewLoginActivity.this, ViewOTPScreen.class);
                finish();
            } else if (jsonObject.getString("ResponseCode").equals("409")) {
                CM.showToast(this, "Number All ready registered");
                CM.startActivity(ViewLoginActivity.this, ViewDrawerActivty.class);
                finish();
            } else {
                CM.showToast(this, jsonObject.getString("ResponseObject").toString());
            }
        } catch (Exception e) {
            CM.showPopupCommonValidation(ViewLoginActivity.this, e.getMessage(), false);
        }
    }

    public void send() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)) {

                    //CM.showSnackBar(rootLayout, "You need to grant SEND SMS permission to send sms");

                    //
                    Snackbar.make(rootLayout, "You need to grant SEND SMS permission to send sms",
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
                webCallVerifyNo(editTextMoblieNO.getText().toString().trim());
            }
        } else {
            //   sendSMS();
            webCallVerifyNo(editTextMoblieNO.getText().toString().trim());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            Snackbar.make(rootView, "Permission Granted",
                    Snackbar.LENGTH_LONG).show();
            //CM.showSnackBar(rootView, "Permission Granted");
            // sendSMS();
            webCallVerifyNo(editTextMoblieNO.getText().toString().trim());

        } else {

            //  CM.showSnackBar(rootView, "Permission denied");
            Snackbar.make(rootView, "Permission denied",
                    Snackbar.LENGTH_LONG).show();
            webCallVerifyNo(editTextMoblieNO.getText().toString().trim());

        }
    }
}

