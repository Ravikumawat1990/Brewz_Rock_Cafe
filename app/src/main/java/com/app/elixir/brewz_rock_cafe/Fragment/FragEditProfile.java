package com.app.elixir.brewz_rock_cafe.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.interfac.ActionBarTitleSetter;
import com.app.elixir.brewz_rock_cafe.interfac.OnFragmentInteractionListener;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplTextView;
import com.app.elixir.brewz_rock_cafe.utils.CM;
import com.app.elixir.brewz_rock_cafe.utils.CONSTANT;
import com.app.elixir.brewz_rock_cafe.utils.CV;
import com.app.elixir.brewz_rock_cafe.utils.URLS;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FragEditProfile extends Fragment implements View.OnClickListener {

    private static final String TAG = "FragEditProfile";
    private static ProgressDialog mProgressDialog;
    private OnFragmentInteractionListener mListener;
    EditText edtFirstName, email_tv;
    Button profileupdatedetails;
    Activity thisActivity;
    LinearLayout register_layout;
    String[] validation = {"First Name", "Last Name", "Password"};
    String[] validation1 = {"First Name", "Last Name", "Email", "Password"};
    private HttpURLConnection connection;
    private EditText phoneNo;

    private EditText editTextLastName;
    private EditText editTextPrimaryNo;
    private EditText edtSecPhoneNo;
    private EditText edtPassword;

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
        setHasOptionsMenu(true);
        init(rootView);
        return rootView;
    }

    public void init(View view) {
        edtFirstName = (EditText) view.findViewById(R.id.username_tv);
        editTextLastName = (EditText) view.findViewById(R.id.usernam_lastName);
        editTextPrimaryNo = (EditText) view.findViewById(R.id.edtPrimaryPno);
        edtSecPhoneNo = (EditText) view.findViewById(R.id.edtSecPhoneNo);
        email_tv = (EditText) view.findViewById(R.id.email_tv);
        edtPassword = (EditText) view.findViewById(R.id.edtPassword);
        MtplTextView mtplTextView = (MtplTextView) view.findViewById(R.id.password);
        try {
            edtFirstName.setText(CM.getSp(thisActivity, "fname", "").toString());
            editTextLastName.setText(CM.getSp(thisActivity, "lname", "").toString());
            email_tv.setText(CM.getSp(thisActivity, "email", "").toString());
            editTextPrimaryNo.setText(CM.getSp(thisActivity, "pNo", "").toString());
            edtSecPhoneNo.setText(CM.getSp(thisActivity, "sno", "").toString());
        } catch (Exception e) {

        }

        if (!CM.getSp(thisActivity, "email", "").toString().equals("")) {
            edtPassword.setVisibility(View.GONE);
            email_tv.setEnabled(false);
            mtplTextView.setVisibility(View.GONE);
        } else {
            edtPassword.setVisibility(View.VISIBLE);
            mtplTextView.setVisibility(View.VISIBLE);
        }

        profileupdatedetails = (Button) view.findViewById(R.id.profileupdatedetails);
        register_layout = (LinearLayout) view.findViewById(R.id.register_layout);
        profileupdatedetails.setOnClickListener(this);
        register_layout.setOnClickListener(this);


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
            String valid = "";
            if (email_tv.getText().toString().equals("")) {
                valid = CM.Validation(validation1, edtFirstName, editTextLastName, email_tv, edtPassword);
            } else {
                valid = CM.Validation(validation, edtFirstName, editTextLastName, email_tv);
            }


            if (!valid.equals(CV.Valid)) {
                CM.ShowDialogue(thisActivity, valid);
            } else {
                Pattern pattern = Pattern.compile("^[00][0-9]{10,13}$|^[0-9]{10,13}$");
                Matcher matcher = pattern.matcher(edtSecPhoneNo.getText().toString());
                if (emailValidator(email_tv.getText().toString())) {
                    if (edtSecPhoneNo.getText().toString().length() == 0) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode);
                            jsonObject.put(CONSTANT.CAPIKEY, CONSTANT.cAPIKey);
                            jsonObject.put(CONSTANT.CAPIPASS, CONSTANT.cAPIPass);
                            jsonObject.put(CONSTANT.NCUSTOMERID, CM.getSp(thisActivity, "customerId", "").toString());
                            jsonObject.put(CONSTANT.CFIRSTNAME, edtFirstName.getText().toString());
                            jsonObject.put(CONSTANT.CLASTNAME, editTextLastName.getText().toString());
                            jsonObject.put(CONSTANT.CMOBILESECONDARY, edtSecPhoneNo.getText().toString());
                            if (CM.getSp(thisActivity, "email", "").toString().equals("")) {
                                jsonObject.put(CONSTANT.CEMAIL, email_tv.getText().toString());
                                jsonObject.put(CONSTANT.CEMAILPASSWORD, edtPassword.getText().toString());
                            }
                            String url = URLS.EDITCUSTOMERPROFILE;
                            POST(url, jsonObject);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    } else if (edtSecPhoneNo.getText().toString().length() >= 1 && matcher.matches()) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode);
                            jsonObject.put(CONSTANT.CAPIKEY, CONSTANT.cAPIKey);
                            jsonObject.put(CONSTANT.CAPIPASS, CONSTANT.cAPIPass);
                            jsonObject.put(CONSTANT.NCUSTOMERID, CM.getSp(thisActivity, "customerId", "").toString());
                            jsonObject.put(CONSTANT.CFIRSTNAME, edtFirstName.getText().toString());
                            jsonObject.put(CONSTANT.CLASTNAME, editTextLastName.getText().toString());
                            jsonObject.put(CONSTANT.CMOBILESECONDARY, edtSecPhoneNo.getText().toString());

                            if (CM.getSp(thisActivity, "email", "").toString().equals("")) {
                                jsonObject.put(CONSTANT.CEMAIL, email_tv.getText().toString());
                                jsonObject.put(CONSTANT.CEMAILPASSWORD, edtPassword.getText().toString());
                            }
                            String url = URLS.EDITCUSTOMERPROFILE;
                            POST(url, jsonObject);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.profile, menu);
        menu.findItem(R.id.profile).setVisible(false);
        if (menu.findItem(R.id.notiCount) != null) {
            menu.findItem(R.id.notiCount).setVisible(false);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                return true;

        }
        return false;
    }


    public String POST(String url, JSONObject jsonObject) {
        mProgressDialog = new ProgressDialog(thisActivity);
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
        InputStream inputStream = null;
        String result = "";
        try {
            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);
            String json = "";
            json = jsonObject.toString();
            StringEntity se = new StringEntity(json);
            // 6. set httpPost Entity
            httpPost.setEntity(se);
            // 7. Set some headers to inform server about the type of the content
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json;charset=utf-8");
            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);
            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
            // 10. convert inputstream to string
            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        mProgressDialog.dismiss();

        try {
            JSONObject jsonObject1 = new JSONObject(result.toString());
            String responseObj = "";
            String responseCode = jsonObject1.getString("ResponseCode");
            if (responseCode.equals("203")) {
                responseObj = jsonObject1.getString("ResponseObject");
                edtFirstName.setText("");
                editTextLastName.setText("");
                editTextPrimaryNo.setText("");
                edtSecPhoneNo.setText("");
                email_tv.setText("");
                edtPassword.setText("");
                CM.showToast(thisActivity, responseObj);

            } else if (responseCode.equals("409")) {
                CM.showToast(thisActivity, jsonObject1.getString("ResponseObject"));
            } else {
                CM.showToast(thisActivity, jsonObject1.getString("ResponseObject"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;
        inputStream.close();
        return result;

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
