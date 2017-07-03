package com.app.elixir.brewz_rock_cafe.Fragment;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.elixir.brewz_rock_cafe.Model.CategoryModel;
import com.app.elixir.brewz_rock_cafe.Model.CategoryModelArray;
import com.app.elixir.brewz_rock_cafe.Model.CompanyModel;
import com.app.elixir.brewz_rock_cafe.Model.CompanyModelArray;
import com.app.elixir.brewz_rock_cafe.Model.NotiModel;
import com.app.elixir.brewz_rock_cafe.Model.TimeModel;
import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.adapter.CustomSpinnerAdapter;
import com.app.elixir.brewz_rock_cafe.adapter.adptCategory;
import com.app.elixir.brewz_rock_cafe.database.tbl_notification;
import com.app.elixir.brewz_rock_cafe.interfac.ActionBarTitleSetter;
import com.app.elixir.brewz_rock_cafe.interfac.OnFragmentInteractionListener;
import com.app.elixir.brewz_rock_cafe.interfac.OnItemClickListener;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplLog;
import com.app.elixir.brewz_rock_cafe.utils.CM;
import com.app.elixir.brewz_rock_cafe.utils.Util;
import com.app.elixir.brewz_rock_cafe.volly.OnVolleyHandler;
import com.app.elixir.brewz_rock_cafe.volly.VolleyIntialization;
import com.app.elixir.brewz_rock_cafe.volly.WebService;
import com.app.elixir.brewz_rock_cafe.volly.WebServiceTag;
import com.sackcentury.shinebuttonlib.ShineButton;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Hours;
import org.joda.time.LocalDate;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;


/**
 * Created by Elixir on 02-Aug-2016.
 */
public class FragFoodCategory extends Fragment implements View.OnClickListener, View.OnTouchListener, DatePickerDialog.OnDateSetListener {

    private static final String TAG = "FragFoodCategory";
    Activity thisActivity;
    private OnFragmentInteractionListener mListener;
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    public adptCategory mAdapter;
    private ArrayList<CategoryModelArray> categoryModelArrays;
    private EditText editTextDate;
    private Spinner editTextTime;
    private LinearLayout rootLayout;
    private LinearLayout noInternetLayout, internetLayout;
    private ArrayList<String> times;
    private TimeModel model_main;
    private AlertDialog helpDialog;
    private int count = 0;
    private ArrayList<CompanyModelArray> companyModelArrays;
    private ProgressDialog pDialog;

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.mListener = (OnFragmentInteractionListener) context;
            ((ActionBarTitleSetter) context).setTitle(getString(R.string.food_category));
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragcategory, container, false);


        thisActivity = getActivity();
        ((ActionBarTitleSetter) thisActivity).setTitle(getString(R.string.app_name));
        TextView titleTextView = null;
        try {
            Field f = ((ActionBarTitleSetter) thisActivity).getClass().getDeclaredField("mTitleTextView");
            f.setAccessible(true);
            titleTextView = (TextView) f.get(((ActionBarTitleSetter) thisActivity));
            Typeface font = Typeface.createFromAsset(thisActivity.getAssets(), getString(R.string.fontface_robotolight_0));
            titleTextView.setTypeface(font);
            titleTextView.setTextSize(20);
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }
//        try {
//            CM.setSp(thisActivity, "regId", FirebaseInstanceId.getInstance().getToken().toString());
//            Log.e("FCM_ID", FirebaseInstanceId.getInstance().getToken().toString());
//        } catch (Exception e) {
//            e.getMessage();
//        }
        // new PostRechargeData().execute();
        setHasOptionsMenu(false);
        init(rootView);

        getData();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        thisActivity.registerReceiver(mMessageReceiver, new IntentFilter("Customer"));
        mListener.showDrawerToggle(true);
        try {
            ArrayList<NotiModel> notiModels = tbl_notification.getAllData();
            if (notiModels != null) {
                if (notiModels.size() >= 1) {
                    int cou = Integer.parseInt(String.valueOf(notiModels.size()));
                    count = 0;
                    for (int i = 0; i < cou; i++) {
                        doIncrease();
                    }
                }

            } else {
                count = 0;
                thisActivity.invalidateOptionsMenu();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }


    public void init(View rootView) {
        rootLayout = (LinearLayout) rootView.findViewById(R.id.mainCAT);
        Button button = (Button) rootView.findViewById(R.id.btnbooktbl);
        button.setOnClickListener(this);
        ShineButton shineButton = (ShineButton) rootView.findViewById(R.id.po_image1);
        shineButton.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View view, boolean checked) {
                if (checked) {
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    FragBookOrder fragBookOrder = new FragBookOrder();
                    fragmentTransaction.setCustomAnimations(0, R.anim.fadeout);
                    fragmentTransaction.add(R.id.container, fragBookOrder).addToBackStack("FragFoodCategory");
                    fragmentTransaction.commit();
                } else {

                }
            }
        });


        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
        noInternetLayout = (LinearLayout) rootView.findViewById(R.id.noInternet);
        internetLayout = (LinearLayout) rootView.findViewById(R.id.Internet);

        if (CM.isInternetAvailable(getActivity())) {
            webCallCompanyPolicy();
        } else {
            noInternetLayout.setVisibility(View.VISIBLE);
            internetLayout.setVisibility(View.GONE);
        }

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.foodcat, menu);
        if (menu.findItem(R.id.profile) != null) {
            menu.findItem(R.id.profile).setVisible(false);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.notiCount:
                FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
                FragNotification fragNotification = new FragNotification();
                t.setCustomAnimations(0, R.anim.fadeout);
                t.replace(R.id.container, fragNotification).addToBackStack("FragFoodCategory");
                t.commit();
                return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.edtDate:
                break;
            case R.id.btnbooktbl:
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                FragBookOrder fragBookOrder = new FragBookOrder();
                fragBookOrder.setArguments(bundle);
                fragmentTransaction.setCustomAnimations(0, R.anim.fadeout);
                fragmentTransaction.add(R.id.container, fragBookOrder).addToBackStack("FragFoodCategory");
                fragmentTransaction.commit();
                break;


        }
    }


    private void showPopUp2() {
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(thisActivity);
        helpBuilder.setTitle(getString(R.string.orderForTime));
        helpBuilder.setMessage(getString(R.string.content));
        View child = thisActivity.getLayoutInflater().inflate(R.layout.popuplout, null);
        helpBuilder.setView(child);
        editTextDate = (EditText) child.findViewById(R.id.edtDate);
        editTextTime = (Spinner) child.findViewById(R.id.spinnerTime);
        editTextDate.setOnTouchListener(this);
        helpBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        webCallFoodCategory();
                    }
                });
        helpBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
            }
        });
        try {
            showdate(model_main.tOpeningTime, model_main.tClosingTime, model_main.tDisplayHoursInterval);
        } catch (Exception e) {
            e.getMessage();
        }
        if (times != null && times.size() > 0) {
            CustomSpinnerAdapter mySpinnerArrayAdapter = new CustomSpinnerAdapter(thisActivity, times);
            editTextTime.setAdapter(mySpinnerArrayAdapter);
        }
        helpDialog = helpBuilder.create();
        helpDialog.show();

        helpDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextDate.getText().toString().equals("")) {
                    helpDialog.dismiss();
                    String strings = (String) editTextTime.getSelectedItem();
                    CM.setSp(thisActivity, "time", strings);
                    webCallFoodCategory();
                } else {
                    CM.showToast(thisActivity, getString(R.string.validateCate));
                }
            }
        });
        editTextTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String strings = (String) adapterView.getSelectedItem();
                CM.setSp(thisActivity, "time", strings);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        final int DRAWABLE_LEFT = 0;
        final int DRAWABLE_TOP = 1;
        final int DRAWABLE_RIGHT = 2;
        final int DRAWABLE_BOTTOM = 3;
        switch (view.getId()) {
            case R.id.edtDate:
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (motionEvent.getRawX() >= (editTextDate.getRight() - editTextDate.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        Calendar now = Calendar.getInstance();

                        if (!CM.getSp(thisActivity, "date", "").equals("")) {
                            try {
                                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                                Date date = format.parse(CM.getSp(thisActivity, "serverDate", "").toString());
                                now.setTime(date);
                            } catch (Exception e) {
                                e.getMessage();
                            }
                        }

                        DatePickerDialog dpd = DatePickerDialog.newInstance(this,
                                now.get(Calendar.YEAR),
                                now.get(Calendar.MONTH),
                                now.get(Calendar.DAY_OF_MONTH));
                        try {
                            Calendar now1 = Calendar.getInstance();
                            int day = now1.get(Calendar.DATE);
                            int days = now1.getActualMaximum(Calendar.DAY_OF_MONTH);
                            ArrayList<Calendar> calendars = new ArrayList<>();


                            int month = now1.get(Calendar.MONTH) + 1;
                            String yearMonth = now1.get(Calendar.YEAR) + "/" + month + "/";
                            for (int i = day + 3; i <= days; i++) {
                                calendars.add(DateToCalendar(new Date(yearMonth + i)));
                            }


                            Calendar tArray[] = calendars.toArray(new Calendar[calendars.size()]);
                            dpd.setDisabledDays(tArray);
                            dpd.setMinDate(DateToCalendar(new Date(System.currentTimeMillis() - 1000)));
                            dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");


                            LocalDate monthEnd = new LocalDate().plusMonths(1).withDayOfMonth(1).minusDays(1);
                            // 2016-12-31
                            if ((day == (monthEnd.getDayOfMonth() - 2)) || (day == (monthEnd.getDayOfMonth() - 1)) || (day == (monthEnd.getDayOfMonth()))) {

                            } else {
                                Calendar c = Calendar.getInstance();
                                c.set(monthEnd.getYear(), monthEnd.getMonthOfYear() - 1, monthEnd.getDayOfMonth());//Year,Mounth -1,Day
                                dpd.setMaxDate(c);
                            }

                        } catch (Exception e) {
                            e.getMessage();

                        }
                        return true;
                    }
                }
                break;
        }

        return false;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        int month = monthOfYear + 1;

        editTextDate.setText(dayOfMonth + "-" + month + "-" + year);
        String currentDate = "";
        Date cuurentTime;
        String formattedDate = "";
        DateFormat readFormat = new SimpleDateFormat("hh:mm aaa");
        DateFormat dateFormat = new SimpleDateFormat("hh:mm aaa");
        DateFormat writeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = Calendar.getInstance().getTime();
        currentDate = CM.converDateFormate("EEE MMM dd HH:mm:ss Z yyyy", "yyyy-MM-dd", date.toString());
        Date time = new Date();
        try {
            cuurentTime = readFormat.parse(dateFormat.format(time));
            if (cuurentTime != null) {
                formattedDate = writeFormat.format(cuurentTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CM.setSp(thisActivity, "serverDate", dayOfMonth + "-" + month + "-" + year);

    }


    public int getDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return maxDay;
    }


    public void webCallStoreTime(String currentDate, String orderDateTime) {
        try {
            VolleyIntialization v = new VolleyIntialization(thisActivity, false, false);
            WebService.getResponseForSetTime(v, currentDate, orderDateTime, new OnVolleyHandler() {
                @Override
                public void onVollySuccess(String response) {
                    if (thisActivity.isFinishing()) {
                        return;
                    }
                    MtplLog.i("WebCalls", response);
                    Log.e(TAG, response);
                    getResponseForStoreTime(response);

                }

                @Override
                public void onVollyError(String error) {
                    MtplLog.i("WebCalls", error);
                    if (CM.isInternetAvailable(thisActivity)) {
                        CM.showPopupCommonValidation(thisActivity, error, false);
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getResponseForStoreTime(String response) {
        String strResponseStatus = CM.getValueFromJson(WebServiceTag.WEB_STATUS, response);
        if (strResponseStatus.equalsIgnoreCase(WebServiceTag.WEB_STATUSFAIL)) {
            CM.showPopupCommonValidation(thisActivity, CM.getValueFromJson(WebServiceTag.WEB_STATUS_ERRORTEXT, response), false);
            return;
        }
        try {

            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("ResponseCode").equals("202")) {

                JSONObject jsonObject1 = new JSONObject(jsonObject.getString("ResponseObject"));
                if (jsonObject1.has("lblStoreClosed")) {
                    if (helpDialog != null) {
                        helpDialog.dismiss();
                    }
                    showPopUp2();
                } else {
                    model_main = CM.JsonParse(new TimeModel(), jsonObject.getString("ResponseObject"));
                    CM.setSp(thisActivity, "openingTime", model_main.tOpeningTime);
                    CM.setSp(thisActivity, "closingTime", model_main.tClosingTime);
                    CM.setSp(thisActivity, "timeInterval", model_main.tDisplayHoursInterval);
                    if (model_main.isPopupOpen.equals("false")) {
                        if (helpDialog != null) {
                            helpDialog.dismiss();
                        }
                        webCallFoodCategory();
                    } else if (model_main.isPopupOpen.equals("true")) {
                        if (helpDialog != null) {
                            helpDialog.dismiss();
                        }
                        showPopUp2();
                    } else {
                        webCallFoodCategory();
                    }
                }


            } else {

            }

        } catch (Exception e) {
            CM.showPopupCommonValidation(thisActivity, e.getMessage(), false);
        }
    }


    public void webCallFoodCategory() {
        try {
            VolleyIntialization v = new VolleyIntialization(thisActivity, false, false);
            WebService.getResendForCategories(v, new OnVolleyHandler() {
                @Override
                public void onVollySuccess(String response) {
                    if (thisActivity.isFinishing()) {
                        return;
                    }
                    MtplLog.i("WebCalls", response);
                    Log.e(TAG, response);
                    getResponseForCouponCode(response);

                }

                @Override
                public void onVollyError(String error) {
                    MtplLog.i("WebCalls", error);
                    if (CM.isInternetAvailable(thisActivity)) {
                        CM.showPopupCommonValidation(thisActivity, error, false);
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void getResponseForCouponCode(String response) {
        String strResponseStatus = CM.getValueFromJson(WebServiceTag.WEB_STATUS, response);
        if (strResponseStatus.equalsIgnoreCase(WebServiceTag.WEB_STATUSFAIL)) {
            CM.showPopupCommonValidation(thisActivity, CM.getValueFromJson(WebServiceTag.WEB_STATUS_ERRORTEXT, response), false);
            return;
        }
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("ResponseCode") != null && jsonObject.getString("ResponseCode").toString().equals("202")) {
                CategoryModel model_main = CM.JsonParse(new CategoryModel(), jsonObject.getString("ResponseObject"));
                categoryModelArrays = model_main.categoryModels;
                categoryModelArrays.size();
                mAdapter = new adptCategory(thisActivity, categoryModelArrays);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.SetOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(String value) {
                        FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
                        FragFoodItem categoryListing = new FragFoodItem();
                        Bundle bundle = new Bundle();
                        bundle.putString("itemId", value);
                        t.setCustomAnimations(0, R.anim.fadeout);
                        categoryListing.setArguments(bundle);
                        t.add(R.id.container, categoryListing).addToBackStack("FragFoodCategory");
                        t.commit();
                    }
                });
            }


        } catch (Exception e) {
            CM.showPopupCommonValidation(thisActivity, e.getMessage(), false);
        }
    }

    public static Calendar DateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }


    public void showdate(String tOpeningTime, String tClosingTime, String intervel) {
        Date time = null;
        Date time1 = null;
        DateTimeFormatter FORMAT = DateTimeFormat.forPattern("hh:mm aa");
        ArrayList<String> strings = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
        try {
            time = dateFormat.parse(tOpeningTime);
            time1 = dateFormat.parse(tClosingTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        times = new ArrayList<String>();
        String hour = intervel.substring(0, 2);
        String min = intervel.length() > 2 ? intervel.substring(intervel.length() - 2) : intervel;
        Duration duration = null;
        try {
            if (hour != null && !hour.equals("00")) {
                duration = Hours.hours(Integer.parseInt(hour)).toStandardDuration();
            } else if (min != null && !min.equals("00")) {
                duration = Minutes.minutes(Integer.parseInt(min)).toStandardDuration();
            } else {
                duration = Minutes.minutes(Integer.parseInt(min)).toStandardDuration();
            }
        } catch (Exception e) {

        }
        final Set<DateTime> set = new LinkedHashSet<DateTime>();
        DateTime d = new DateTime(time);
        DateTime e = new DateTime(time1);
        do {
            set.add(d);
            d = d.plus(duration);
        } while (d.compareTo(e) <= 0);
        for (final DateTime dt : set) {
            System.out.println(FORMAT.print(dt));
            strings.add(FORMAT.print(dt));
            times.add(FORMAT.print(dt));
        }
        times.size();

    }


    @Override
    public void onPause() {
        super.onPause();
        thisActivity.unregisterReceiver(mMessageReceiver);
    }

    private void doIncrease() {
        count++;
        thisActivity.invalidateOptionsMenu();
    }

    //This is the handler that will manager to process the broadcast intent
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            // Extract data included in the Intent
            String message = intent.getStringExtra("message");

            try {
                ArrayList<NotiModel> notiModels = tbl_notification.getAllData();
                if (notiModels != null) {
                    if (notiModels.size() >= 1) {
                        int cou = Integer.parseInt(String.valueOf(notiModels.size()));
                        count = 0;
                        for (int i = 0; i < cou; i++) {
                            doIncrease();
                        }
                    }
                } else {
                    count = 0;
                    thisActivity.invalidateOptionsMenu();
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }
    };

    public void webCallCompanyPolicy() {
        try {
            VolleyIntialization v = new VolleyIntialization(thisActivity, false, false);
            WebService.getResendForConpanyDate(v, new OnVolleyHandler() {
                @Override
                public void onVollySuccess(String response) {
                    if (thisActivity.isFinishing()) {
                        return;
                    }
                    MtplLog.i("WebCalls", response);
                    Log.e(TAG, response);
                    getResponseForCompanyPolicy(response);

                }

                @Override
                public void onVollyError(String error) {
                    MtplLog.i("WebCalls", error);
                    if (CM.isInternetAvailable(thisActivity)) {
                        CM.showPopupCommonValidation(thisActivity, error, false);
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void getResponseForCompanyPolicy(String response) {
        String strResponseStatus = CM.getValueFromJson(WebServiceTag.WEB_STATUS, response);
        if (strResponseStatus.equalsIgnoreCase(WebServiceTag.WEB_STATUSFAIL)) {
            CM.showPopupCommonValidation(thisActivity, CM.getValueFromJson(WebServiceTag.WEB_STATUS_ERRORTEXT, response), false);
            return;
        }
        try {
            JSONObject jsonObject = new JSONObject(response);

            if (jsonObject.getString("ResponseCode") != null && jsonObject.getString("ResponseCode").toString().equals("202")) {
                CompanyModel model_main = CM.JsonParse(new CompanyModel(), jsonObject.getString("ResponseObject"));
                companyModelArrays = model_main.companyModelArrays;
                if (companyModelArrays != null && companyModelArrays.size() > 0) {

                    if (companyModelArrays.get(0).cCurrencyCode != null) {
                        CM.setSp(thisActivity, "currency", Util.getCurrencySymbol(companyModelArrays.get(0).cCurrencyCode.toString()));

                    }
                    if (companyModelArrays.get(0).nMaxOrderQty != null) {
                        CM.setSp(thisActivity, "Quant", companyModelArrays.get(0).nMaxOrderQty.toString());
                    } else {
                        CM.setSp(thisActivity, "Quant", "10");
                    }
                    if (companyModelArrays.get(0).bImagesVisible != null) {
                        if (companyModelArrays.get(0).bImagesVisible.toString().equals("false")) {
                            CM.setSp(thisActivity, "isImage", "1");
                        } else {
                            CM.setSp(thisActivity, "isImage", "0");
                        }
                    } else {
                        CM.setSp(thisActivity, "isImage", "0");
                    }
                    if (companyModelArrays.get(0).nMinOrderPlacingAmt != null) {
                        CM.setSp(thisActivity, "minOrder", companyModelArrays.get(0).nMinOrderPlacingAmt);
                    }

                    if (companyModelArrays.get(0).bIsDiscountAfterTax != null) {
                        CM.setSp(thisActivity, "bIsDiscountAfterTax", companyModelArrays.get(0).bIsDiscountAfterTax.toString());
                    }

                    if (companyModelArrays.get(0).nOrderFillMode != null) {
                        CM.setSp(thisActivity, "payMode", companyModelArrays.get(0).nOrderFillMode.toString());
                    }

                    if (companyModelArrays.get(0).nOrderFillModeDefault != null) {
                        CM.setSp(thisActivity, "DefPmode", companyModelArrays.get(0).nOrderFillModeDefault.toString());
                    }


                    DateFormat readFormat = new SimpleDateFormat("hh:mm aaa");
                    DateFormat dateFormat = new SimpleDateFormat("hh:mm aaa");
                    DateFormat writeFormat = new SimpleDateFormat("HH:mm:ss");
                    Date date = Calendar.getInstance().getTime();
                    Date time = new Date();
                    Date orderTime = null, cuurentTime = null;
                    String currentDate = "";
                    String formattedDate = "";
                    try {
                        cuurentTime = readFormat.parse(dateFormat.format(time));
                        if (cuurentTime != null) {
                            formattedDate = writeFormat.format(cuurentTime);
                        }
                        currentDate = CM.converDateFormate("EEE MMM dd HH:mm:ss Z yyyy", "yyyy-MM-dd", date.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();

                    }
                    String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                    webCallStoreTime(currentDate + " " + timeStamp, currentDate + " " + formattedDate);
                    noInternetLayout.setVisibility(View.GONE);
                    internetLayout.setVisibility(View.VISIBLE);
                }
            }
        } catch (Exception e) {
            CM.showPopupCommonValidation(thisActivity, e.getMessage(), false);
        }
    }


    public void getData() {

        try {


            ArrayList<String> oldData = new ArrayList<String>();

            oldData.add("1");
            oldData.add("2");
            oldData.add("3");
            oldData.add("4");

            ArrayList<String> newData = new ArrayList<String>();

            newData.add("1");
            newData.add("2");
            newData.add("3");
            newData.add("4");

            String[] stockArr = new String[oldData.size()];
            String[] order11 = oldData.toArray(stockArr);
            String[] stockArr1 = new String[newData.size()];
            String[] order12 = newData.toArray(stockArr1);

            boolean result = Arrays.equals(order11, order12);
            if (result) {
                Log.i("TAG", "onPageFinished: equal");
            }

            Document doc = Jsoup.parse("<html><head></head><body onload='form1.submit()'><form id='form1' action='https://test.payu.in/_payment/_payment' method='post'><input name='udf2' type='hidden' value='' /><input name='address2' type='hidden' value='' /><input name='productinfo' type='hidden' value='brewzrock' /><input name='service_provider' type='hidden' value='payu_paisa' /><input name='hash' type='hidden' value='ee8a5a648d8b9bfd4db0862e6328f0e3d1f3aa696b69336d419afa3fd6de13b05e5f0591181c5c1e857e0a8cf28ce457c16c08bd585eec052e9d217997f1c2b2' /><input name='zipcode' type='hidden' value='' /><input name='udf5' type='hidden' value='' /><input name='city' type='hidden' value='' /><input name='address1' type='hidden' value='' /><input name='phone' type='hidden' value='7728877982' /><input name='email' type='hidden' value='kravi@elixirinfo.com' /><input name='state' type='hidden' value='' /><input name='firstname' type='hidden' value='Ravi kumawat' /><input name='udf4' type='hidden' value='' /><input name='pg' type='hidden' value='' /><input name='amount' type='hidden' value='904.55' /><input name='txnid' type='hidden' value='bceaae2ef21372586faf' /><input name='udf3' type='hidden' value='' /><input name='country' type='hidden' value='' /><input name='udf1' type='hidden' value='' /><input name='key' type='hidden' value='fB7m8s' /><input name='lastname' type='hidden' value='' /><input name='furl' type='hidden' value='https://www.payumoney.com/mobileapp/payumoney/failure.php' /><input name='surl' type='hidden' value='https://www.payumoney.com/mobileapp/payumoney/success.php' /></form></body></html>");
            Elements hidden = doc.select("input[type=hidden]");
            for (Element el1 : hidden) {
                Log.i(TAG, "Tag Name: " + el1.attr("name") + " Tag Value   " + el1.attr("value"));
            }


        } catch (Exception e) {
            e.getMessage();
        }

    }
//    class PostRechargeData extends AsyncTask<String, String, String> {
//        private String resString;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            pDialog = new ProgressDialog(thisActivity);
//            pDialog.setMessage("Please wait...");
//            pDialog.setIndeterminate(false);
//            pDialog.setCancelable(false);
//            pDialog.show();
//
//        }
//
//        protected String doInBackground(String... args) {
//            try {
//                Document doc = Jsoup.connect("https://secure.payu.in/response?mihpayid=2d67fc217242ae10156880ace5bc5fb1e9b53e6b40ad780b04caf125ced5ed4f").timeout(10 * 1000).get();
//                Element loginform = doc.getElementById("frmPostBack");
//                Elements inputElements = loginform.getElementsByTag("input");
//                for (Element inputElement : inputElements) {
//                    if (inputElement.attr("name").equals("error_Message")) {
//                        String value = inputElement.attr("value");
//                        resString = inputElement.attr("key");
//                        System.out.println("Param name: " + inputElement.attr("key") + " \nParam value: " + value);
//                    }
//                }
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return resString;
//        }
//
//        protected void onPostExecute(final String strStatus) {
//
//            //   Spanned kk = Html.fromHtml(strStatus);
//
//
//            pDialog.dismiss();
//        }
//    }

}