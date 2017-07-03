package com.app.elixir.brewz_rock_cafe.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.elixir.brewz_rock_cafe.Model.ProfileDeliveryModel;
import com.app.elixir.brewz_rock_cafe.Model.ProfileDeliveryModelArray;
import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.interfac.ActionBarTitleSetter;
import com.app.elixir.brewz_rock_cafe.interfac.OnFragmentInteractionListenerDelivery;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplLog;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplTextView;


import com.app.elixir.brewz_rock_cafe.utils.CM;
import com.app.elixir.brewz_rock_cafe.volly.OnVolleyHandler;
import com.app.elixir.brewz_rock_cafe.volly.VolleyIntialization;
import com.app.elixir.brewz_rock_cafe.volly.WebService;
import com.app.elixir.brewz_rock_cafe.volly.WebServiceTag;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class FragMyProfileDeliveryBox extends Fragment implements View.OnClickListener {

    private static final String TAG = "FragMyProfile";
    private OnFragmentInteractionListenerDelivery mListener;
    TextView forgot_pswrd_tv;
    EditText username_tv, email_tv;
    Button buttonChangePassword, profileupdatedetails;
    Activity thisActivity;
    LinearLayout register_layout;
    private EditText phoneNo;


    TextView inputUserNme, inputEmail, inputPhone;
    private ArrayList<ProfileDeliveryModelArray> userProfileModelArrays;

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_profile, container, false);
        thisActivity = getActivity();
        ((ActionBarTitleSetter) thisActivity).setTitle(getString(R.string.myProfile));
        // setHasOptionsMenu(true);
        init(rootView);
        return rootView;
    }

    public void init(View view) {
        forgot_pswrd_tv = (TextView) view.findViewById(R.id.forgot_pswrd_tv);
        username_tv = (EditText) view.findViewById(R.id.username_tv);
        email_tv = (EditText) view.findViewById(R.id.email_tv);
        profileupdatedetails = (Button) view.findViewById(R.id.profileupdatedetails);
        register_layout = (LinearLayout) view.findViewById(R.id.register_layout);
        phoneNo = (EditText) view.findViewById(R.id.phoneNo);
        buttonChangePassword = (Button) view.findViewById(R.id.buttonchangePassword);
        buttonChangePassword.setVisibility(View.GONE);
        buttonChangePassword.setOnClickListener(this);
        profileupdatedetails.setOnClickListener(this);
        forgot_pswrd_tv.setOnClickListener(this);
        register_layout.setOnClickListener(this);
        inputUserNme = (MtplTextView) view.findViewById(R.id.inputUserNme);
        inputEmail = (MtplTextView) view.findViewById(R.id.inputEmail);
        inputPhone = (MtplTextView) view.findViewById(R.id.inputPhone);

        try {
            webCallUserProfile(CM.getSp(thisActivity, "customerId", "").toString());

        } catch (Exception e) {
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnFragmentInteractionListenerDelivery) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mListener.showDrawerToggle(false);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonChangePassword) {
            Fragment fr = new FragChangePasswordDeliveryBoy();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.popBackStack("FragMyProfileDeliveryBox", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(0, R.anim.fadeout);
            fragmentTransaction.replace(R.id.fragcontainer, fr).addToBackStack("FragMyProfileDeliveryBox");
            fragmentTransaction.commit();

        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.profile, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                Fragment fr = new FragEditProfileDeliveryBox();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack("FragMyProfileDeliveryBox", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(0, R.anim.fadeout);
                fragmentTransaction.replace(R.id.fragcontainer, fr).addToBackStack("FragMyProfileDeliveryBox");
                fragmentTransaction.commit();
                return true;

        }
        return false;
    }

    public void webCallUserProfile(String customerId) {
        try {
            VolleyIntialization v = new VolleyIntialization(thisActivity, true, true);
            WebService.getResponseForDeliveryUserProfile(v, customerId, new OnVolleyHandler() {
                @Override
                public void onVollySuccess(String response) {
                    if (thisActivity.isFinishing()) {
                        return;
                    }
                    MtplLog.i("WebCalls", response);
                    Log.e(TAG, response);
                    getResponseForUserProfile(response);

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


    private void getResponseForUserProfile(String response) {
        String strResponseStatus = CM.getValueFromJson(WebServiceTag.WEB_STATUS, response);
        if (strResponseStatus.equalsIgnoreCase(WebServiceTag.WEB_STATUSFAIL)) {
            CM.showPopupCommonValidation(thisActivity, CM.getValueFromJson(WebServiceTag.WEB_STATUS_ERRORTEXT, response), false);
            return;
        }
        try {

            JSONObject jsonObject = new JSONObject(response);
            JSONObject jsonObject1 = new JSONObject(jsonObject.getString("ResponseObject"));
            ProfileDeliveryModel model_main = CM.JsonParse(new ProfileDeliveryModel(), jsonObject1.getString("DeliveryBoyDetail"));
            try {
                CM.setSp(thisActivity, "fname", model_main.Fname);
                CM.setSp(thisActivity, "lname", model_main.Lname);
                CM.setSp(thisActivity, "email", model_main.Email);
                CM.setSp(thisActivity, "pNo", model_main.Mobile);
                inputUserNme.setText(CM.getSp(thisActivity, "fname", "").toString() + " " + CM.getSp(thisActivity, "lname", ""));
                inputEmail.setText(CM.getSp(thisActivity, "email", "").toString());
                inputPhone.setText(CM.getSp(thisActivity, "pNo", "").toString());


            } catch (Exception e) {

            }
        } catch (Exception e) {
            CM.showPopupCommonValidation(thisActivity, e.getMessage(), false);
        }
    }
}
