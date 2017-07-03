package com.app.elixir.brewz_rock_cafe.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.interfac.ActionBarTitleSetter;
import com.app.elixir.brewz_rock_cafe.interfac.OnFragmentInteractionListener;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplButton;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplLog;
import com.app.elixir.brewz_rock_cafe.utils.CM;
import com.app.elixir.brewz_rock_cafe.utils.CV;
import com.app.elixir.brewz_rock_cafe.volly.OnVolleyHandler;
import com.app.elixir.brewz_rock_cafe.volly.VolleyIntialization;
import com.app.elixir.brewz_rock_cafe.volly.WebService;
import com.app.elixir.brewz_rock_cafe.volly.WebServiceTag;

import org.json.JSONException;
import org.json.JSONObject;


public class FragChangePassword extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;
    MtplButton login_btn;
    Activity thisActivity;
    String[] validation = {"Email ", "Old Password", "New Password"};
    private EditText edtemail, edtoldPass, edtNewPass;

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
        View rootView = inflater.inflate(R.layout.frag_changepassword, container, false);
        thisActivity = getActivity();
        ((ActionBarTitleSetter) thisActivity).setTitle(getString(R.string.changePassword));
        init(rootView);

        return rootView;
    }

    public void init(View view) {
        edtemail = (EditText) view.findViewById(R.id.edtemail);
        edtoldPass = (EditText) view.findViewById(R.id.edtoldPass);
        edtNewPass = (EditText) view.findViewById(R.id.edtNewPass);
        login_btn = (MtplButton) view.findViewById(R.id.changePassword_btn);
        login_btn.setOnClickListener(this);


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
        if (view == login_btn) {
            String valid = CM.Validation(validation, edtemail, edtoldPass, edtNewPass);
            if (!valid.equals(CV.Valid)) {
                CM.ShowDialogue(getActivity(), valid);
            } else {
                if (CM.isEmailValid(edtemail.getText().toString())) {
                    webcallChangePassword(edtemail.getText().toString(), edtoldPass.getText().toString(), edtNewPass.getText().toString(), CM.getSp(thisActivity, "customerId", "").toString());
                } else {
                    CM.showToast(thisActivity, getString(R.string.invalidEmail));
                }
            }
        }
    }


    public void webcallChangePassword(String email, String password, String newpass, String customerId) {
        try {
            VolleyIntialization v = new VolleyIntialization(thisActivity, true, true);

            WebService.GetChangePassword(v, email, password, newpass, customerId, new OnVolleyHandler() {
                @Override
                public void onVollySuccess(String response) {
                    if (thisActivity.isFinishing()) {
                        return;
                    }
                    MtplLog.i("WebCalls", response);
                    getResponseForChangePassword(response);

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

    private void getResponseForChangePassword(String response) {
        String strResponseStatus = CM.getValueFromJson(WebServiceTag.WEB_STATUS, response);
        if (strResponseStatus.equalsIgnoreCase(WebServiceTag.WEB_STATUSFAIL)) {
            CM.showPopupCommonValidation(thisActivity, CM.getValueFromJson(WebServiceTag.WEB_STATUS_ERRORTEXT, response), false);
            return;
        }
        try {

            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("ResponseCode").equals("203")) {
                CM.showToast(thisActivity, jsonObject.getString("ResponseObject"));
                edtemail.setText("");
                edtoldPass.setText("");
                edtNewPass.setText("");

            } else {
                CM.showToast(thisActivity, jsonObject.getString("ResponseObject"));
            }


        } catch (Exception e) {
            CM.showPopupCommonValidation(thisActivity, e.getMessage(), false);
        }
    }
}
