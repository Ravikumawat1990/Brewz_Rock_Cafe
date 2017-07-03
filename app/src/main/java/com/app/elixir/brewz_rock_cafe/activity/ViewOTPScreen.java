package com.app.elixir.brewz_rock_cafe.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.elixir.brewz_rock_cafe.Model.LoginEmilModel;
import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplButton;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplLog;
import com.app.elixir.brewz_rock_cafe.utils.CM;
import com.app.elixir.brewz_rock_cafe.utils.messageListionerService;
import com.app.elixir.brewz_rock_cafe.volly.OnVolleyHandler;
import com.app.elixir.brewz_rock_cafe.volly.VolleyIntialization;
import com.app.elixir.brewz_rock_cafe.volly.WebService;
import com.app.elixir.brewz_rock_cafe.volly.WebServiceTag;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

public class ViewOTPScreen extends AppCompatActivity implements View.OnClickListener, messageListionerService.ServiceCallbacks {


    private static final String TAG = "ViewOTPScreen";
    private TextView _tv;
    private MtplButton btnSubmit;
    private EditText editTextOtp;
    private MtplButton btnResendOtop;
    private boolean mBounded;
    private static final String TAG1 = "logout";
    private LinearLayout rootlayout;
    private LoginEmilModel model_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpscreen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.titleColor));
        toolbar.setTitle(getString(R.string.otpscreen));
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
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
                CM.startActivity(ViewOTPScreen.this, ViewLoginActivity.class);
                finish();
            }
        });

        btnSubmit = (MtplButton) findViewById(R.id.btnSubmit);
        btnResendOtop = (MtplButton) findViewById(R.id.btnresendOtp);
        _tv = (TextView) findViewById(R.id.textViewTimeout);
        btnResendOtop.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        rootlayout = (LinearLayout) findViewById(R.id.mainOtp);
        editTextOtp = (EditText) findViewById(R.id.Otp);
        editTextOtp.setSelection(editTextOtp.getText().length());
        editTextOtp.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if (!TextUtils.isEmpty(editTextOtp.getText().toString().trim())) {
                        if (CM.isInternetAvailable(ViewOTPScreen.this)) {
                            if (CM.getSp(ViewOTPScreen.this, "otp", "").toString().equals(editTextOtp.getText().toString().trim())) {
                                webCallCreateCustomer(CM.getSp(ViewOTPScreen.this, "number", "").toString());
                            } else {
                                CM.showSnackBar(rootlayout, getString(R.string.otpVali));
                            }
                        } else {
                            CM.showSnackBar(rootlayout, getString(R.string.internetMsg));
                        }
                    } else {
                        CM.showSnackBar(rootlayout, getString(R.string.otpVali));
                    }
                    return true;
                }
                return false;
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CM.startActivity(ViewOTPScreen.this, ViewLoginActivity.class);
        finish();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnSubmit:
                if (!TextUtils.isEmpty(editTextOtp.getText().toString().trim())) {
                    if (CM.isInternetAvailable(ViewOTPScreen.this)) {
                        if (CM.getSp(ViewOTPScreen.this, "otp", "").toString().equals(editTextOtp.getText().toString().trim())) {
                            CM.setSp(ViewOTPScreen.this, "customerId", "");
                            webCallCreateCustomer(CM.getSp(ViewOTPScreen.this, "number", "").toString());
                        } else {
                            CM.showSnackBar(rootlayout, getString(R.string.otpVali));
                        }
                    } else {
                        CM.showSnackBar(rootlayout, getString(R.string.internetMsg));
                    }
                } else {
                    CM.showSnackBar(rootlayout, getString(R.string.otpVali));
                }
                break;
            case R.id.btnresendOtp:
                if (CM.isInternetAvailable(ViewOTPScreen.this)) {
                    webCallResendOtp(CM.getSp(ViewOTPScreen.this, "number", "").toString());
                } else {
                    CM.showSnackBar(rootlayout, getString(R.string.internetMsg));
                }
                break;

        }

    }

    private messageListionerService myService;
    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            messageListionerService.LocalBinder binder = (messageListionerService.LocalBinder) service;
            myService = binder.getService();
            mBounded = true;
            myService.setCallbacks(ViewOTPScreen.this); // register
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBounded = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        CM.setSp(ViewOTPScreen.this, "msg", "");
        Intent mIntent = new Intent(this, messageListionerService.class);
        bindService(mIntent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBounded) {
            unbindService(serviceConnection);
            mBounded = false;
            CM.setSp(ViewOTPScreen.this, "msg", "");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBounded) {
            unbindService(serviceConnection);
            mBounded = false;
            CM.setSp(ViewOTPScreen.this, "msg", "");
        }
    }

    @Override
    public void ShowConnectionPopup(String status) {
        try {
            Log.e("status", status);
            if (!status.equals("")) {
                CM.setSp(ViewOTPScreen.this, "msg", status);
                editTextOtp.setText(status);
                editTextOtp.setSelection(editTextOtp.getText().length());
            }
        } catch (Exception e) {

        }
    }

    public void webCallResendOtp(String no) {
        try {
            VolleyIntialization v = new VolleyIntialization(ViewOTPScreen.this, true, true);
            WebService.getOTP(v, no, new OnVolleyHandler() {
                @Override
                public void onVollySuccess(String response) {
                    if (isFinishing()) {
                        return;
                    }
                    MtplLog.i("WebCalls", response);
                    Log.e(TAG, response);
                    getResponseForResendOtp(response);

                }

                @Override
                public void onVollyError(String error) {
                    MtplLog.i("WebCalls", error);
                    if (CM.isInternetAvailable(ViewOTPScreen.this)) {
                        CM.showPopupCommonValidation(ViewOTPScreen.this, error, false);
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void getResponseForResendOtp(String response) {
        String strResponseStatus = CM.getValueFromJson(WebServiceTag.WEB_STATUS, response);
        if (strResponseStatus.equalsIgnoreCase(WebServiceTag.WEB_STATUSFAIL)) {
            CM.showPopupCommonValidation(ViewOTPScreen.this, CM.getValueFromJson(WebServiceTag.WEB_STATUS_ERRORTEXT, response), false);
            return;
        }
        try {

            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("ResponseCode").equals("201")) {
                CM.setSp(ViewOTPScreen.this, "otp", jsonObject.getString("ResponseObject"));
            } else if (jsonObject.getString("ResponseCode").equals("409")) {
                CM.showToast(this, "Number All ready registered");
            } else {
                CM.showToast(this, jsonObject.getString("ResponseObject").toString());
            }
        } catch (Exception e) {
            CM.showPopupCommonValidation(ViewOTPScreen.this, e.getMessage(), false);
        }
    }


    public void webCallCreateCustomer(String no) {
        try {
            VolleyIntialization v = new VolleyIntialization(ViewOTPScreen.this, true, true);
            WebService.getResponseForCreateCustomer(v, no, new OnVolleyHandler() {
                @Override
                public void onVollySuccess(String response) {
                    if (isFinishing()) {
                        return;
                    }
                    MtplLog.i("WebCalls", response);
                    Log.e(TAG, response);
                    getResponseForCreateCustomer(response);

                }

                @Override
                public void onVollyError(String error) {
                    MtplLog.i("WebCalls", error);
                    if (CM.isInternetAvailable(ViewOTPScreen.this)) {
                        CM.showPopupCommonValidation(ViewOTPScreen.this, error, false);
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void getResponseForCreateCustomer(String response) {
        String strResponseStatus = CM.getValueFromJson(WebServiceTag.WEB_STATUS, response);
        if (strResponseStatus.equalsIgnoreCase(WebServiceTag.WEB_STATUSFAIL)) {
            CM.showPopupCommonValidation(ViewOTPScreen.this, CM.getValueFromJson(WebServiceTag.WEB_STATUS_ERRORTEXT, response), false);
            return;
        }
        try {

            JSONObject jsonObject = new JSONObject(response);
            String responseObject = jsonObject.getString("ResponseObject");
            String responseCode = jsonObject.getString("ResponseCode");
            if (responseCode.equals("202")) {
                JSONObject jsonObject1 = new JSONObject(responseObject);
                model_main = CM.JsonParse(new LoginEmilModel(), jsonObject1.get("user").toString());
                CM.setSp(ViewOTPScreen.this, "number", model_main.cMobilePrimary);
                CM.setSp(ViewOTPScreen.this, "fname", model_main.cFirstname);
                CM.setSp(ViewOTPScreen.this, "lname", model_main.cLastname);
                CM.setSp(ViewOTPScreen.this, "email", model_main.cEmail);
                CM.setSp(ViewOTPScreen.this, "pNo", model_main.cMobilePrimary);

                JSONObject jsonObject2 = new JSONObject(jsonObject1.get("user").toString());
                JSONObject jsonArray = new JSONObject(jsonObject2.getString("tblusertype"));
                if (jsonArray.getString("cUserType").toString().equals("Customer")) {
                    Intent intent = new Intent("finish_activityCust");
                    sendBroadcast(intent);


                    if (model_main.nCustomerID != null && model_main.nCustomerID.toString().equals("0")) {
                        CM.showToast(ViewOTPScreen.this, getString(R.string.invcustId));
                    } else {
                        CM.setSp(ViewOTPScreen.this, "customerId", model_main.nCustomerID);
                        CM.setSp(ViewOTPScreen.this, TAG1, "0");
                        CM.setSp(this, "custLog", "1");
                        CM.startActivity(this, ViewDrawerActivty.class);
                        finish();
                    }


                } else if (jsonArray.getString("cUserType").toString().equals("Delivery Boy")) {

                    Intent intent = new Intent("finish_activityDeliv");
                    sendBroadcast(intent);
                    if (model_main.nUserID != null && model_main.nUserID.toString().equals("0")) {
                        CM.showToast(ViewOTPScreen.this, getString(R.string.invcustId));

                    } else {
                        CM.setSp(this, "custLog", "0");
                        CM.setSp(ViewOTPScreen.this, TAG1, "0");
                        CM.setSp(ViewOTPScreen.this, "customerId", model_main.nUserID);
                        CM.startActivity(this, ViewDeliveryBoy.class);
                        finish();
                    }

                }
            } else if (responseCode.equals("203")) {

                JSONObject jsonObject1 = new JSONObject(responseObject);
                model_main = CM.JsonParse(new LoginEmilModel(), jsonObject1.get("user").toString());
                CM.setSp(ViewOTPScreen.this, "number", model_main.cMobilePrimary);
                CM.setSp(ViewOTPScreen.this, "fname", model_main.cFirstname);
                CM.setSp(ViewOTPScreen.this, "lname", model_main.cLastname);
                CM.setSp(ViewOTPScreen.this, "email", model_main.cEmail);
                CM.setSp(ViewOTPScreen.this, "pNo", model_main.cMobilePrimary);

                JSONObject jsonObject2 = new JSONObject(jsonObject1.get("user").toString());
                JSONObject jsonArray = new JSONObject(jsonObject2.getString("tblusertype"));
                if (jsonArray.getString("cUserType").toString().equals("Customer")) {

                    if (model_main.nCustomerID != null && model_main.nCustomerID.toString().equals("0")) {
                        CM.showToast(ViewOTPScreen.this, getString(R.string.invcustId));

                    } else {
                        CM.setSp(this, "custLog", "1");
                        CM.setSp(ViewOTPScreen.this, TAG1, "0");
                        CM.setSp(ViewOTPScreen.this, "customerId", model_main.nCustomerID);
                        CM.startActivity(this, ViewDrawerActivty.class);
                        finish();
                    }

                } else if (jsonArray.getString("cUserType").toString().equals("Delivery Boy")) {


                    if (model_main.nUserID != null && model_main.nUserID.toString().equals("0")) {
                        CM.showToast(ViewOTPScreen.this, getString(R.string.invcustId));

                    } else {
                        CM.setSp(this, "custLog", "0");
                        CM.setSp(ViewOTPScreen.this, TAG1, "0");
                        CM.setSp(ViewOTPScreen.this, "customerId", model_main.nUserID);
                        CM.startActivity(this, ViewDeliveryBoy.class);
                        finish();
                    }

                }
            } else {
                CM.showToast(this, responseObject.toString());
            }
        } catch (Exception e) {
            CM.showPopupCommonValidation(ViewOTPScreen.this, e.getMessage(), false);
        }
    }
}
