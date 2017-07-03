package com.app.elixir.brewz_rock_cafe.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.utils.CM;


public class ViewSplash extends AppCompatActivity implements Animation.AnimationListener {

    private static final String TAG = "logout";
    private static int SPLASH_TIME_OUT = 3000;
    final String PREFS_NAME = "MyPrefsFile";
    private Intent intent, intent1;
    private Animation animFadein;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_splash);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        SharedPreferences settings = getSharedPreferences(getPackageName(), 0);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        /**
         * For Animation
         */
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);

        // set animation listener
        animFadein.setAnimationListener(this);
        imageView.startAnimation(animFadein);
        intent = getIntent();
        if (intent.getStringExtra("noti") != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (intent.getStringExtra("type").toString().equals("deli")) {
                        Log.e("1", "1");
                        if (CM.getSp(ViewSplash.this, "logout", "").toString().equals("1")) {
                            intent1 = new Intent(ViewSplash.this, ViewLoginActivity.class);
                        } else {
                            if (CM.getSp(ViewSplash.this, "custLog", "").equals("1")) {
                                Log.e("1", "-2");
                                intent1 = new Intent(ViewSplash.this, ViewLoginActivity.class);

                            } else {
                                Log.e("1", "2");
                                intent1 = new Intent(ViewSplash.this, ViewNotification.class);
                                intent1.putExtra("key", String.valueOf(intent.getStringExtra("requestID")));
                            }
                        }
                        Intent intent2 = new Intent("finish_activityDeliv");
                        sendBroadcast(intent2);
                        CM.startActivity(intent1, ViewSplash.this);
                        finish();

                    } else {
                        Log.e("2", "2");
                        if (CM.getSp(ViewSplash.this, "logout", "").toString().equals("1")) {
                            intent1 = new Intent(ViewSplash.this, ViewLoginActivity.class);
                        } else {
                            if (!CM.getSp(ViewSplash.this, "custLog", "").equals("1")) {
                                Log.e("2", "-3");
                                intent1 = new Intent(ViewSplash.this, ViewLoginActivity.class);
                            } else {
                                intent1 = new Intent(ViewSplash.this, ViewNotification.class);
                                intent1.putExtra("key", String.valueOf(intent.getStringExtra("requestID")));
                                Log.e("2", "3");
                            }
                        }
                        Intent intent2 = new Intent("finish_activityCust");
                        sendBroadcast(intent2);
                        CM.startActivity(intent1, ViewSplash.this);
                        finish();
                    }
                }
            }, SPLASH_TIME_OUT);

        } else {
            if (settings.getBoolean("my_first_time", true)) {
                settings.edit().putBoolean("my_first_time", false).commit();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        CM.setSp(ViewSplash.this, TAG, "1");
                        CM.startActivity(ViewSplash.this, ViewMobileNoVerification.class);
                        finish();
                    }
                }, SPLASH_TIME_OUT);

            } else {

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        if (CM.getSp(ViewSplash.this, TAG, "").toString().equals("1")) {
                            CM.startActivity(ViewSplash.this, ViewLoginActivity.class);
                            finish();
                        } else {
                            if (CM.getSp(ViewSplash.this, "custLog", "").equals("1")) {
                                CM.startActivity(ViewSplash.this, ViewDrawerActivty.class);
                                finish();
                            } else {
                                CM.startActivity(ViewSplash.this, ViewDeliveryBoy.class);
                                finish();
                            }
                        }
                    }
                }, SPLASH_TIME_OUT);

            }

        }


    }

    @Override
    protected void onNewIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        String tabNumber;

        if (extras != null) {
            tabNumber = extras.getString("screen");
            Log.d("TEMP", "Tab Number: " + tabNumber);

        } else {
            Log.d("TEMP", "Extras are NULL");

        }
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}