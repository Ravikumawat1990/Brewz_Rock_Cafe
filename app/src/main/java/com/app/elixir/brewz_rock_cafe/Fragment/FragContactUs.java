package com.app.elixir.brewz_rock_cafe.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.interfac.ActionBarTitleSetter;
import com.app.elixir.brewz_rock_cafe.interfac.OnFragmentInteractionListener;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplLog;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplTextView;
import com.app.elixir.brewz_rock_cafe.utils.CM;
import com.app.elixir.brewz_rock_cafe.volly.OnVolleyHandler;
import com.app.elixir.brewz_rock_cafe.volly.VolleyIntialization;
import com.app.elixir.brewz_rock_cafe.volly.WebService;
import com.app.elixir.brewz_rock_cafe.volly.WebServiceTag;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class FragContactUs extends Fragment implements View.OnClickListener {

    private static final String TAG = "FragContactUs";
    Activity thisActivity;
    private OnFragmentInteractionListener mListener;
    private ProgressDialog mProgressDialog;
    private LinearLayout rootlayout;
    private MtplTextView mtplTextView;
    private MtplTextView txtViewCName, txtViewCEmail, txtViewCPno, txtViewOfficeNo, txtViewAdress, txtViewWebSite;
    private ImageView imageView;
    private ProgressBar progressBar;
    private LinearLayout noInternetLayout;
    private LinearLayout internetLayout;


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

        View view = inflater.inflate(R.layout.contact_us, container, false);
        thisActivity = getActivity();
        setHasOptionsMenu(true);
        ((ActionBarTitleSetter) thisActivity).setTitle("Contact Us");
        inIt(view);

        return view;
    }

    public void inIt(View view) {
        rootlayout = (LinearLayout) view.findViewById(R.id.mainScroll);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        txtViewCName = (MtplTextView) view.findViewById(R.id.textViewCompName);
        txtViewCEmail = (MtplTextView) view.findViewById(R.id.textViewCompEmail);
        txtViewCPno = (MtplTextView) view.findViewById(R.id.textViewComPhoneNo);
        txtViewOfficeNo = (MtplTextView) view.findViewById(R.id.textViewCompOfficeNo);
        txtViewAdress = (MtplTextView) view.findViewById(R.id.textViewAddress);
        txtViewWebSite = (MtplTextView) view.findViewById(R.id.textViewebsite);
        progressBar = (ProgressBar) view.findViewById(R.id.pgnar);
        noInternetLayout = (LinearLayout) view.findViewById(R.id.noInternet);
        internetLayout = (LinearLayout) view.findViewById(R.id.Internet);
        if (CM.isInternetAvailable(getActivity())) {
            //   new AsyncTaskRunner().execute();

            webCallContactUs();
            noInternetLayout.setVisibility(View.GONE);
            internetLayout.setVisibility(View.VISIBLE);
        } else {
            noInternetLayout.setVisibility(View.VISIBLE);
            internetLayout.setVisibility(View.GONE);
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }


    }

    @Override
    public void onResume() {
        super.onResume();
        mListener.showDrawerToggle(false);
    }


    public void webCallContactUs() {
        try {
            VolleyIntialization v = new VolleyIntialization(thisActivity, true, true);
            WebService.getResponseForContactUs(v, new OnVolleyHandler() {
                @Override
                public void onVollySuccess(String response) {
                    if (thisActivity.isFinishing()) {
                        return;
                    }
                    MtplLog.i("WebCalls", response);
                    Log.e(TAG, response);
                    getResponseForContactUs(response);

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


    private void getResponseForContactUs(String response) {
        String strResponseStatus = CM.getValueFromJson(WebServiceTag.WEB_STATUS, response);
        if (strResponseStatus.equalsIgnoreCase(WebServiceTag.WEB_STATUSFAIL)) {
            CM.showPopupCommonValidation(thisActivity, CM.getValueFromJson(WebServiceTag.WEB_STATUS_ERRORTEXT, response), false);
            return;
        }
        try {

            JSONObject jsonObject = new JSONObject(response);
            String responseCode = jsonObject.getString("ResponseCode");

            if (responseCode.equals("202")) {
                String responseObj = jsonObject.getString("ResponseObject");
                try {
                    JSONObject jsonObj = new JSONObject(responseObj);
                    JSONArray jsonArray1 = jsonObj.getJSONArray("comapny");
                    for (int j = 0; j < jsonArray1.length(); j++) {
                        JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                        txtViewCName.setText(jsonObject1.optString("cName"));
                        Spanned next = Html.fromHtml("<font color=\"black\">Email:</font>");

                        String phone = "Phone: ";
                        String officephone = "OfficePhone: ";
                        String address = "Address: ";
                        String website = "WebSite: ";


                        txtViewCEmail.setText(setText("Email: " + jsonObject1.optString("cEmail"), 0, 5));
                        txtViewCPno.setText(setText(phone + jsonObject1.optString("cPhoneMobile"), 0, 5));
                        txtViewOfficeNo.setText(setText(officephone + jsonObject1.optString("cPhoneOffice"), 0, 12));
                        txtViewAdress.setText(setText(address + jsonObject1.optString("cAddress"), 0, 8));
                        txtViewWebSite.setText(setText(website + jsonObject1.optString("cWebsite"), 0, 8));

                        try {

                            Glide.with(thisActivity).load(jsonObject1.optString("cBannerImagePath"))
                                    .listener(new RequestListener<String, GlideDrawable>() {
                                        @Override
                                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                            progressBar.setVisibility(View.VISIBLE);
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                            progressBar.setVisibility(View.GONE);
                                            return false;
                                        }
                                    }).error(R.drawable.placeholder)
                                    .into(imageView);

                        } catch (Exception e) {
                            e.getMessage();
                        }
                    }
                } catch (Exception e) {
                }
            }


        } catch (Exception e) {
            CM.showPopupCommonValidation(thisActivity, e.getMessage(), false);
        }
    }

    public SpannableString setText(String text, int pos, int pos1) {
        SpannableString ss1 = new SpannableString(text);
        ss1.setSpan(new RelativeSizeSpan(1f), pos, pos1, 0); // set size
        ss1.setSpan(new ForegroundColorSpan(Color.BLACK), pos, pos1, 0);// set color
        return ss1;
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
