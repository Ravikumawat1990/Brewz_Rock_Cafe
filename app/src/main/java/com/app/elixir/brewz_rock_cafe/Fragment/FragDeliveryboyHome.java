package com.app.elixir.brewz_rock_cafe.Fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.elixir.brewz_rock_cafe.Model.LoginEmilModel;
import com.app.elixir.brewz_rock_cafe.Model.NotiModel;
import com.app.elixir.brewz_rock_cafe.Model.OrderList;
import com.app.elixir.brewz_rock_cafe.Model.OrderListArray;
import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.adapter.adptDeliveryBox;
import com.app.elixir.brewz_rock_cafe.database.tbl_notificationNew;
import com.app.elixir.brewz_rock_cafe.interfac.ActionBarTitleSetter;
import com.app.elixir.brewz_rock_cafe.interfac.OnFragmentInteractionListenerDelivery;
import com.app.elixir.brewz_rock_cafe.interfac.OnItemClickListenerDeliverd;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplLog;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplTextView;
import com.app.elixir.brewz_rock_cafe.pojo.PojoDeliveryBoy;
import com.app.elixir.brewz_rock_cafe.utils.BadgeDrawable;
import com.app.elixir.brewz_rock_cafe.utils.CM;
import com.app.elixir.brewz_rock_cafe.volly.OnVolleyHandler;
import com.app.elixir.brewz_rock_cafe.volly.VolleyIntialization;
import com.app.elixir.brewz_rock_cafe.volly.WebService;
import com.app.elixir.brewz_rock_cafe.volly.WebServiceTag;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;


public class FragDeliveryboyHome extends Fragment implements View.OnClickListener {

    private static final String TAG = "FragDeliveryboyHome";
    Activity thisActivity;
    private OnFragmentInteractionListenerDelivery mListener;
    public RecyclerView recyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private ArrayList<PojoDeliveryBoy> pojoDeliveryBoys;
    public adptDeliveryBox mAdapter;
    private LoginEmilModel model_main;
    private ArrayList<OrderListArray> orderListArrays;
    private LinearLayout noInternetLayout;
    private LinearLayout internetLayout;
    private ImageView imageViewMsg;
    private MtplTextView txtViewMsg;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int count = 0;

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.mListener = (OnFragmentInteractionListenerDelivery) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragdeliveryoxhome, container, false);
        thisActivity = getActivity();
        ((ActionBarTitleSetter) thisActivity).setTitle(getString(R.string.app_name));
        setHasOptionsMenu(true);
        inIt(view);
        return view;
    }

    public void inIt(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleViewDeliveryBox);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(new LinearLayoutManager(thisActivity));
        noInternetLayout = (LinearLayout) view.findViewById(R.id.noInternet);
        internetLayout = (LinearLayout) view.findViewById(R.id.Internet);
        imageViewMsg = (ImageView) view.findViewById(R.id.imageViewMessage);
        txtViewMsg = (MtplTextView) view.findViewById(R.id.textViewNoInterNet);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);

        if (CM.isInternetAvailable(thisActivity)) {
            webCallOrderListForDelivery(CM.getSp(thisActivity, "customerId", "").toString());
            noInternetLayout.setVisibility(View.GONE);
            internetLayout.setVisibility(View.VISIBLE);

        } else {
            noInternetLayout.setVisibility(View.VISIBLE);
            internetLayout.setVisibility(View.GONE);
            txtViewMsg.setText(getString(R.string.internetMsg));
            imageViewMsg.setBackgroundResource(R.drawable.group);
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (CM.isInternetAvailable(thisActivity)) {
                    webCallOrderListForDelivery(CM.getSp(thisActivity, "customerId", "").toString());
                } else {

                }

            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {


        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mListener.showDrawerToggle(true);
        thisActivity.registerReceiver(mMessageReceiver, new IntentFilter("Delivery"));
        try {
            ArrayList<NotiModel> notiModels = tbl_notificationNew.getAllData();
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

        }


    }


    //
    public void webCallOrderListForDelivery(String cusId) {
        try {
            VolleyIntialization v = new VolleyIntialization(thisActivity, true, true);

            WebService.getResponseForOrderHistory(v, cusId, new OnVolleyHandler() {
                @Override
                public void onVollySuccess(String response) {
                    if (thisActivity.isFinishing()) {
                        return;
                    }
                    MtplLog.i("WebCalls", response);
                    Log.e(TAG, response);
                    getResponseOrderListForDelivery(response);

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

    private void getResponseOrderListForDelivery(String response) {
        String strResponseStatus = CM.getValueFromJson(WebServiceTag.WEB_STATUS, response);
        if (strResponseStatus.equalsIgnoreCase(WebServiceTag.WEB_STATUSFAIL)) {
            CM.showPopupCommonValidation(thisActivity, CM.getValueFromJson(WebServiceTag.WEB_STATUS_ERRORTEXT, response), false);
            return;
        }
        try {
            JSONObject jsonObject = new JSONObject(response);
            swipeRefreshLayout.setRefreshing(false);
            if (jsonObject.getString("ResponseCode").equals("202")) {
                if (orderListArrays != null) {
                    orderListArrays.clear();
                }
                OrderList model_main = CM.JsonParse(new OrderList(), jsonObject.getString("ResponseObject"));

                if (orderListArrays != null) {
                    orderListArrays.clear();
                }
                orderListArrays = model_main.orderListArrays;
                orderListArrays.size();

                if (orderListArrays.size() >= 1) {
                    noInternetLayout.setVisibility(View.GONE);
                    internetLayout.setVisibility(View.VISIBLE);
                    if (mAdapter != null) {
                        mAdapter = null;
                    }
                    Collections.reverse(orderListArrays);
                    mAdapter = new adptDeliveryBox(thisActivity, orderListArrays);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.SetOnItemClickListener(new OnItemClickListenerDeliverd() {
                        @Override
                        public void onItemClick(String id, String data, String txt) {
                            if (data.equals("map")) {
                                if (!txt.equals("")) {
                                    String uri = "http://maps.google.co.in/maps?q=" + txt;
                                    startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));
                                } else {
                                    CM.showToast(thisActivity, "Address is required");
                                }
                            } else if (data.equals("view")) {
                                showPopupForLogOut(thisActivity, id);
                            } else if (data.equals("detail")) {
                                FragmentManager fm = getFragmentManager();
                                fm.popBackStack("FragDeliveryboyHome", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                                FragmentTransaction ft = fm.beginTransaction();
                                ft.setCustomAnimations(0, R.anim.fadeout);
                                Bundle bundle = new Bundle();
                                bundle.putString("id", id);
                                Fragment fragment = new FragOrderDetailView();
                                fragment.setArguments(bundle);
                                ft.replace(R.id.fragcontainer, fragment).addToBackStack("FragDeliveryboyHome");
                                ft.commit();
                            }
                        }
                    });
                } else {
                    noInternetLayout.setVisibility(View.VISIBLE);
                    internetLayout.setVisibility(View.GONE);
                    txtViewMsg.setText(getString(R.string.noRecordFound));
                    imageViewMsg.setBackgroundResource(R.drawable.no_records);
                }

            } else if (jsonObject.getString("ResponseCode").equals("402")) {
                CM.showToast(thisActivity, jsonObject.getString("ResponseObject"));
            }
        } catch (Exception e) {
            CM.showPopupCommonValidation(thisActivity, e.getMessage(), false);
        }
    }

    public void showPopupForLogOut(Context context, final String id) {
        new AlertDialog.Builder(context)
                .setTitle(getString(R.string.app_name))
                .setMessage("Do you want to update status?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            webCallDeliverd(CM.getSp(thisActivity, "customerId", "").toString(), id);
                        } catch (Exception e) {
                            e.getMessage();
                        }


                    }
                }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setIcon(R.drawable.applogo).show();
    }


    public void webCallDeliverd(String customerId, String orderId) {
        try {
            VolleyIntialization v = new VolleyIntialization(thisActivity, true, true);
            WebService.getResponseForDeliveryStatus(v, orderId, new OnVolleyHandler() {
                @Override
                public void onVollySuccess(String response) {
                    if (thisActivity.isFinishing()) {
                        return;
                    }
                    MtplLog.i("WebCalls", response);
                    Log.e(TAG, response);
                    getResponseForDeliverd(response);

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

    private void getResponseForDeliverd(String response) {
        String strResponseStatus = CM.getValueFromJson(WebServiceTag.WEB_STATUS, response);
        if (strResponseStatus.equalsIgnoreCase(WebServiceTag.WEB_STATUSFAIL)) {
            CM.showPopupCommonValidation(thisActivity, CM.getValueFromJson(WebServiceTag.WEB_STATUS_ERRORTEXT, response), false);
            return;
        }
        try {

            JSONObject jsonObject = new JSONObject(response);
            try {
                if (jsonObject.getString("ResponseCode").toString().equals("203")) {
                    CM.showToast(thisActivity, jsonObject.getString("ResponseObject"));
                    webCallOrderListForDelivery(CM.getSp(thisActivity, "customerId", "").toString());
                } else {
                    CM.showToast(thisActivity, jsonObject.getString(jsonObject.getString("ResponseObject")));
                }

            } catch (Exception e) {

            }
        } catch (Exception e) {
            CM.showPopupCommonValidation(thisActivity, e.getMessage(), false);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater();
        //.inflate(R.menu.deliveryhome, menu);
//        MenuItem menuItem = menu.findItem(R.id.notiCount);
//        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
//        if (currentapiVersion == 15) {
//            LayerDrawable icon = null;
//            try {
//                menuItem.setIcon(thisActivity.getResources().getDrawable(R.drawable.ic_add_shopping_cart_white_24dp));
//                BitmapDrawable iconBitmap = (BitmapDrawable) menuItem.getIcon();
//                icon = new LayerDrawable(new Drawable[]{iconBitmap});
//            } catch (Exception e) {
//                e.getMessage();
//            }
//            setBadgeCount(thisActivity, icon, String.valueOf(count));
//        } else {
//            LayerDrawable icon = null;
//            try {
//                icon = (LayerDrawable) menuItem.getIcon();
//            } catch (Exception e) {
//                e.getMessage();
//            }
//            setBadgeCount(thisActivity, icon, String.valueOf(count));
//        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refereshList:
                if (CM.isInternetAvailable(thisActivity)) {
                    webCallOrderListForDelivery(CM.getSp(thisActivity, "customerId", "").toString());
                } else {

                }
                break;
            case R.id.notiCount:
                FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
                FragNotificationDeliveryBoy fragNotification = new FragNotificationDeliveryBoy();
                t.setCustomAnimations(0, R.anim.fadeout);
                t.replace(R.id.fragcontainer, fragNotification).addToBackStack("FragDeliveryboyHome");
                t.commit();
                break;

        }
        return false;
    }

    //This is the handler that will manager to process the broadcast intent
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            // Extract data included in the Intent
            String message = intent.getStringExtra("message");
            try {
                ArrayList<NotiModel> notiModels = tbl_notificationNew.getAllData();
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

            }
        }
    };

    public static void setBadgeCount(Context context, LayerDrawable icon, String count) {

        BadgeDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.notCount);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.notCount, badge);
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

}
