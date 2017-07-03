package com.app.elixir.brewz_rock_cafe.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.elixir.brewz_rock_cafe.Model.PastOrderModelArray;
import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.adapter.adptPastDelivery;
import com.app.elixir.brewz_rock_cafe.interfac.ActionBarTitleSetter;
import com.app.elixir.brewz_rock_cafe.interfac.OnFragmentInteractionListenerDelivery;
import com.app.elixir.brewz_rock_cafe.interfac.OnItemClickListener;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplLog;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplTextView;
import com.app.elixir.brewz_rock_cafe.pojo.PojoPastDelivery;
import com.app.elixir.brewz_rock_cafe.utils.CM;
import com.app.elixir.brewz_rock_cafe.volly.OnVolleyHandler;
import com.app.elixir.brewz_rock_cafe.volly.VolleyIntialization;
import com.app.elixir.brewz_rock_cafe.volly.WebService;
import com.app.elixir.brewz_rock_cafe.volly.WebServiceTag;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by Elixir on 09-Aug-2016.
 */
public class FragPastDelivery extends Fragment {

    private static final String TAG = "FragPastDelivery";
    Activity thisActivity;
    private OnFragmentInteractionListenerDelivery mListener;

    ArrayList<PojoPastDelivery> arrayList;
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private adptPastDelivery mAdapter;
    private ArrayList<PastOrderModelArray> pastOrderModelArrays;
    private ImageView imageViewMsg;
    private MtplTextView txtViewMsg;
    private LinearLayout noInternetLayout;
    private LinearLayout internetLayout;
    private SwipeRefreshLayout swipeRefreshLayout;


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
        View rootView = inflater.inflate(R.layout.fragpastdelivery, container, false);
        thisActivity = getActivity();
        ((ActionBarTitleSetter) thisActivity).setTitle("Past Delivery");
        init(rootView);


        return rootView;
    }


    public void init(View rootView) {
        arrayList = new ArrayList<>();
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        noInternetLayout = (LinearLayout) rootView.findViewById(R.id.noInternet);
        internetLayout = (LinearLayout) rootView.findViewById(R.id.Internet);
        imageViewMsg = (ImageView) rootView.findViewById(R.id.imageViewMessage);
        txtViewMsg = (MtplTextView) rootView.findViewById(R.id.textViewNoInterNet);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        if (CM.isInternetAvailable(thisActivity)) {
            webCallPastDelivery(CM.getSp(thisActivity, "customerId", "").toString());
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
                // Refresh items
                webCallPastDelivery(CM.getSp(thisActivity, "customerId", "").toString());
            }
        });


    }


    @Override
    public void onResume() {
        super.onResume();
        mListener.showDrawerToggle(false);
    }


    public void webCallPastDelivery(String custId) {
        try {
            VolleyIntialization v = new VolleyIntialization(thisActivity, true, true);

            WebService.getResponseForOrderPast(v, custId, new OnVolleyHandler() {
                @Override
                public void onVollySuccess(String response) {
                    if (thisActivity.isFinishing()) {
                        return;
                    }
                    MtplLog.i("WebCalls", response);
                    Log.e(TAG, response);
                    getResponsePastDelivery(response);

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

    private void getResponsePastDelivery(String response) {
        String strResponseStatus = CM.getValueFromJson(WebServiceTag.WEB_STATUS, response);
        if (strResponseStatus.equalsIgnoreCase(WebServiceTag.WEB_STATUSFAIL)) {
            CM.showPopupCommonValidation(thisActivity, CM.getValueFromJson(WebServiceTag.WEB_STATUS_ERRORTEXT, response), false);
            return;
        }

        swipeRefreshLayout.setRefreshing(false);
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("ResponseCode").equals("202")) {
                PastOrderModel model_main = CM.JsonParse(new PastOrderModel(), jsonObject.getString("ResponseObject"));
                if (pastOrderModelArrays != null) {
                    pastOrderModelArrays.clear();
                }
                pastOrderModelArrays = model_main.pastOrderModelArrays;
                pastOrderModelArrays.size();

                if (pastOrderModelArrays.size() >= 1) {
                    noInternetLayout.setVisibility(View.GONE);
                    internetLayout.setVisibility(View.VISIBLE);
                    mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                    mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
                    
					Collections.reverse(pastOrderModelArrays);
					mAdapter = new adptPastDelivery(thisActivity, pastOrderModelArrays);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.SetOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(String value) {

                        }
                    });
                } else {
                    noInternetLayout.setVisibility(View.VISIBLE);
                    internetLayout.setVisibility(View.GONE);
                    txtViewMsg.setText(getString(R.string.noRecordFound));
                    imageViewMsg.setBackgroundResource(R.drawable.no_records);
                }
            } else {
                CM.showToast(thisActivity, jsonObject.getString("ResponseObject"));

            }

        } catch (Exception e) {
            CM.showPopupCommonValidation(thisActivity, e.getMessage(), false);
        }
    }


}
