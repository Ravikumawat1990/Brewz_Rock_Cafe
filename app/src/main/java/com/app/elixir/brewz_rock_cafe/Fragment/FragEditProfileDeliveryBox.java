package com.app.elixir.brewz_rock_cafe.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.interfac.ActionBarTitleSetter;
import com.app.elixir.brewz_rock_cafe.interfac.OnFragmentInteractionListener;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplTextView;
import com.app.elixir.brewz_rock_cafe.utils.CV;
import com.app.elixir.brewz_rock_cafe.volly.WebServiceTag;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.app.elixir.brewz_rock_cafe.utils.CM;


public class FragEditProfileDeliveryBox extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;
    EditText username_tv, email_tv;
    Button profileupdatedetails;
    Activity thisActivity;
    LinearLayout register_layout;
    String[] validation = {"User Name", "Email", "Phone No."};

    private EditText phoneNo;
    TextView inputUserNme, inputEmail, inputPhone;

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_updatedetail, container, false);
        thisActivity = getActivity();
        ((ActionBarTitleSetter) thisActivity).setTitle(getString(R.string.editProfile));
        setHasOptionsMenu(false);
        init(rootView);
        return rootView;
    }

    public void init(View view) {
        username_tv = (EditText) view.findViewById(R.id.username_tv);
        email_tv = (EditText) view.findViewById(R.id.email_tv);
        profileupdatedetails = (Button) view.findViewById(R.id.profileupdatedetails);
        register_layout = (LinearLayout) view.findViewById(R.id.register_layout);
        phoneNo = (EditText) view.findViewById(R.id.phoneNo);
        profileupdatedetails.setOnClickListener(this);
        register_layout.setOnClickListener(this);
        inputUserNme = (MtplTextView) view.findViewById(R.id.inputUserNme);
        inputEmail = (MtplTextView) view.findViewById(R.id.inputEmail);
        inputPhone = (MtplTextView) view.findViewById(R.id.inputPhone);


    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnFragmentInteractionListener) activity;
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
        if (view == profileupdatedetails) {
            String valid = CM.Validation(validation, username_tv, email_tv, phoneNo);
            if (!valid.equals(CV.Valid)) {
                CM.ShowDialogue(thisActivity, valid);
            } else {
                Pattern pattern = Pattern
                        .compile("^[00][0-9]{10,13}$|^[0-9]{10,13}$");
                Matcher matcher = pattern.matcher(phoneNo.getText()
                        .toString());
                if (emailValidator(email_tv.getText().toString())) {
                    if (matcher.matches()) {
                        //webcallUpdateProfile(CM.getSp(thisActivity, "UserId", "").toString(), username_tv.getText().toString().trim(), email_tv.getText().toString(), phoneNo.getText().toString(), CM.getSp(thisActivity, "AccessToken", "").toString());
                    } else {
                        CM.ShowDialogue(thisActivity, "Please enter a 10 digit mobile number.");
                    }
                } else {
                    CM.ShowDialogue(thisActivity, "Enter Valid Email");

                }


            }
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
                return true;

        }
        return false;
    }


//    public void webcallUpdateProfile(String userId, String userName, String email, String mobile, String accessToken) {
//        try {
//            VolleyIntialization v = new VolleyIntialization(thisActivity, true, true);
//
//            WebService.GetUpdateProfile(v, userId, userName, email, mobile, accessToken, new OnVolleyHandler() {
//                @Override
//                public void onVollySuccess(String response) {
//                    if (thisActivity.isFinishing()) {
//                        return;
//                    }
//                    MtplLog.i("WebCalls", response);
//                    getResponseForUpdateProfile(response);
//
//                }
//
//                @Override
//                public void onVollyError(String error) {
//                    MtplLog.i("WebCalls", error);
//                    if (CM.isInternetAvailable(thisActivity)) {
//                        CM.showPopupCommonValidation(thisActivity, error, false);
//                    }
//                }
//            });
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    //** get response **////
    private void getResponseForUpdateProfile(String response) {
        String strResponseStatus = CM.getValueFromJson(WebServiceTag.WEB_STATUS, response);
        if (strResponseStatus.equalsIgnoreCase(WebServiceTag.WEB_STATUSFAIL)) {
            CM.showPopupCommonValidation(thisActivity, CM.getValueFromJson(WebServiceTag.WEB_STATUS_ERRORTEXT, response), false);
            return;
        }
        try {
            String fromJson = CM.getValueFromJson(WebServiceTag.WEB_STATUS_ERRORTEXT, response);
            if (strResponseStatus.equals("False")) {
                CM.ShowDialogue(thisActivity, fromJson);
            } else {
//                RegistrationModel model_main = CM.JsonParse(new RegistrationModel(), response.toString());
//                modelArrays = model_main.modelArrays;
//                for (int i = 0; i < modelArrays.size(); i++) {
//                    CM.setSp(thisActivity, "UserName", model_main.modelArrays.get(i).UserName);
//                    CM.setSp(thisActivity, "Mobile", model_main.modelArrays.get(i).Mobile);
//                    CM.setSp(thisActivity, "Email", model_main.modelArrays.get(i).Email);
//                    username_tv.setText("");
//                    email_tv.setText("");
//                    phoneNo.setText("");
//                }
//                CM.ShowDialogue(thisActivity, fromJson);
            }

        } catch (Exception e) {
            CM.showPopupCommonValidation(thisActivity, e.getMessage(), false);
        }
    }

    public static boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
