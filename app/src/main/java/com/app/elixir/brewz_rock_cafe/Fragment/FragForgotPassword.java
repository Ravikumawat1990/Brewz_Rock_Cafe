package com.app.elixir.brewz_rock_cafe.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.volly.WebServiceTag;


import com.app.elixir.brewz_rock_cafe.utils.CM;

/**
 * Created by Admin on 14-07-2016.
 */
public class FragForgotPassword extends Fragment implements View.OnClickListener {

    //   private OnFragmentInteractionListener mListener;
    EditText email_edtTxt;
    Button reset_btn, cancel_btn;
    Activity thisActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_forgot_password, container, false);
        thisActivity = getActivity();

        init(rootView);
        return rootView;
    }

    public void init(View view) {
        email_edtTxt = (EditText) view.findViewById(R.id.email_edtTxt);
        reset_btn = (Button) view.findViewById(R.id.reset_btn);
        cancel_btn = (Button) view.findViewById(R.id.cancel_btn);

        reset_btn.setOnClickListener(this);
        cancel_btn.setOnClickListener(this);

    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View view) {
        if (view == reset_btn) {
            if (email_edtTxt.length() > 0 && CM.isEmailValid(email_edtTxt.getText().toString())) {
                //  forgotpassword(email_edtTxt.getText().toString());
            } else {
                Toast.makeText(thisActivity, "Valid email address is required.", Toast.LENGTH_SHORT).show();
            }
        } else if (view == cancel_btn) {

        }
    }

//    public void forgotpassword(String email) {
//        try {
//            VolleyIntialization v = new VolleyIntialization(thisActivity, true, true);
//            WebService.ForgotPassword(v, email, new OnVolleyHandler() {
//                @Override
//                public void onVollySuccess(String response) {
//                    if (thisActivity.isFinishing()) {
//                        return;
//                    }
//                    MtplLog.i("WebCalls", response);
//                    getResponseForforgotpassword(response);
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
    private void getResponseForforgotpassword(String response) {
        String strResponseStatus = CM.getValueFromJson(WebServiceTag.WEB_STATUS, response);
        String msg = CM.getValueFromJson(WebServiceTag.WEB_STATUS_ERRORTEXT, response);
        if (strResponseStatus.equalsIgnoreCase(WebServiceTag.WEB_STATUSFAIL)) {
            CM.showPopupCommonValidation(thisActivity, CM.getValueFromJson(WebServiceTag.WEB_STATUS_ERRORTEXT, response), false);
            return;
        }
        try {
            if (strResponseStatus.equals("True")) {
                CM.ShowDialogue(getActivity(), msg);
//                FragmentManager fm = getFragmentManager();
//                fm.popBackStack("LogIn", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                FragmentTransaction ft = fm.beginTransaction();
//                Fragment fragment = new FragLogIn();
//                ft.replace(R.id.container, fragment).addToBackStack("Register");
//                ft.commit();
            } else {
                CM.ShowDialogue(getActivity(), msg);
            }
        } catch (Exception e) {
            CM.showPopupCommonValidation(thisActivity, e.getMessage(), false);
        }
    }
}
