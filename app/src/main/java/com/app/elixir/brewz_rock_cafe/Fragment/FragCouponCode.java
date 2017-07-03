package com.app.elixir.brewz_rock_cafe.Fragment;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.elixir.brewz_rock_cafe.Model.CouponCodeModel;
import com.app.elixir.brewz_rock_cafe.Model.CouponCodeModelArray;
import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.adapter.adptCouponCode;
import com.app.elixir.brewz_rock_cafe.interfac.ActionBarTitleSetter;
import com.app.elixir.brewz_rock_cafe.interfac.OnFragmentInteractionListener;
import com.app.elixir.brewz_rock_cafe.interfac.OnItemClickListener;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplLog;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplTextView;
import com.app.elixir.brewz_rock_cafe.pojo.PojoCouponCode;
import com.app.elixir.brewz_rock_cafe.utils.CM;
import com.app.elixir.brewz_rock_cafe.volly.OnVolleyHandler;
import com.app.elixir.brewz_rock_cafe.volly.VolleyIntialization;
import com.app.elixir.brewz_rock_cafe.volly.WebService;
import com.app.elixir.brewz_rock_cafe.volly.WebServiceTag;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Elixir on 09-Aug-2016.
 */
public class FragCouponCode extends Fragment {

    private static final String TAG = "FragCouponCode";
    Activity thisActivity;
    private OnFragmentInteractionListener mListener;

    ArrayList<PojoCouponCode> arrayList;
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private adptCouponCode mAdapter;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    private ArrayList<CouponCodeModelArray> couponCodeModelArrays;
    private LinearLayout noInternetLayout;
    private LinearLayout internetLayout;
    public static final String KEY_STATUS = "status";
    private ImageView imageViewMsg;
    private MtplTextView txtViewMsg;

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragcouponcode, container, false);
        thisActivity = getActivity();
        ((ActionBarTitleSetter) thisActivity).setTitle(getString(R.string.coupones));
        init(rootView);
        setHasOptionsMenu(true);
        return rootView;
    }


    public void init(View rootView) {
        arrayList = new ArrayList<>();
        myClipboard = (ClipboardManager) thisActivity.getSystemService(thisActivity.CLIPBOARD_SERVICE);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
        noInternetLayout = (LinearLayout) rootView.findViewById(R.id.noInternet);
        internetLayout = (LinearLayout) rootView.findViewById(R.id.Internet);
        imageViewMsg = (ImageView) rootView.findViewById(R.id.imageViewMessage);
        txtViewMsg = (MtplTextView) rootView.findViewById(R.id.textViewNoInterNet);
        if (CM.isInternetAvailable(getActivity())) {
            webCallCouponCode();
            noInternetLayout.setVisibility(View.GONE);
            internetLayout.setVisibility(View.VISIBLE);
        } else {
            noInternetLayout.setVisibility(View.VISIBLE);
            internetLayout.setVisibility(View.GONE);
            txtViewMsg.setText(getString(R.string.internetMsg));
            imageViewMsg.setBackgroundResource(R.drawable.group);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        mListener.showDrawerToggle(false);
    }

    public void webCallCouponCode() {
        try {
            VolleyIntialization v = new VolleyIntialization(thisActivity, true, true);
            WebService.getResponseForCouPonCode(v, new OnVolleyHandler() {
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
            JSONObject jsonObject1 = new JSONObject(jsonObject.getString("ResponseObject"));

            if (jsonObject.getString("ResponseCode").equals("202")) {
                if (jsonObject1.optString("lblNoDiscountCoupons") != null && !jsonObject1.optString("lblNoDiscountCoupons").equals("")) {
                    noInternetLayout.setVisibility(View.VISIBLE);
                    internetLayout.setVisibility(View.GONE);
                    txtViewMsg.setText(getString(R.string.noCoupons));
                    imageViewMsg.setBackgroundResource(R.drawable.no_records);

                } else {
                    CouponCodeModel model_main = CM.JsonParse(new CouponCodeModel(), jsonObject.getString("ResponseObject"));
                    couponCodeModelArrays = model_main.couponCodeModelArrays;
                    couponCodeModelArrays.size();
                    mAdapter = new adptCouponCode(thisActivity, couponCodeModelArrays);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.SetOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(String value) {
                            CM.setClipboard(thisActivity, value);
                            CM.showToast(thisActivity, "Text Copied");
                        }
                    });
                }
            } else {
                CM.showToast(thisActivity, jsonObject.getString("ResponseObject").toString());
            }
        } catch (Exception e) {
            CM.showPopupCommonValidation(thisActivity, e.getMessage(), false);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        if (menu.findItem(R.id.notiCount) != null) {
            menu.findItem(R.id.notiCount).setVisible(false);
        }
        if (menu.findItem(R.id.profile) != null) {
            menu.findItem(R.id.profile).setVisible(false);
        }
    }
}
