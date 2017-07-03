package com.app.elixir.brewz_rock_cafe.volly;

import android.app.Activity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.app.elixir.brewz_rock_cafe.FoodOrdringApplication;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;


import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.utils.CM;


public class VolleyIntialization {
    private Activity mActivity;

    private boolean mIsShowPopup;
    private boolean mIsDismissPopup;
    private MtplProgressDialog mtplDialog;

    public VolleyIntialization(Activity activity, boolean showpopup, boolean dismisspopup) {

        mActivity = activity;
        mIsShowPopup = showpopup;
        mIsDismissPopup = dismisspopup;

    }

    public Activity getActivity() {
        return mActivity;
    }

    public boolean getShowPopup() {
        return mIsShowPopup;
    }

    public boolean getDismissPopup() {
        return mIsDismissPopup;
    }

//Volly Webservice Related Methods //

    /**
     * Webservice call with Map Key pair value and after response not
     * any ws call use this method(single boolean) for dialog dismiss
     *
     * @param url
     * @param requestMethod //     * @param json
     * @param params
     * @param vollyHandler
     */
    public void vollyStringRequestCall(String url, final int requestMethod, final List<NameValuePair> params, final OnVolleyHandler vollyHandler, boolean showPopup) throws JSONException {
        //AS we have to pass Security key in ever webservice we have
//        if (json != null) {
//            json.put("strSecurityKey", CV.SECURITY_KEY);
//        }
        if (!CM.isInternetAvailable(mActivity)) {
            vollyHandler.onVollyError(mActivity.getResources().getString(R.string.msg_internet_unavailable_msg));
            return;
        }
        if (mIsShowPopup) {
            showMtplDialog(mActivity);
        }
       /* if (showPopup) {
            showMtplDialog(mActivity);
        }*/
        Log.i("WebCalls", url);
        String paramString = URLEncodedUtils.format(params, "utf-8");
        url += "?" + paramString;


        StringRequest stringRequest = new StringRequest(requestMethod,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String strResponseStatus = CM.getValueFromJson(WebServiceTag.WEB_STATUS, response);
                        if (strResponseStatus.equalsIgnoreCase(WebServiceTag.WEB_STATUSFAIL)) {
                            dismissMtplDialog(mActivity);
                        } else {
                            if (mIsDismissPopup) {
                                dismissMtplDialog(mActivity);
                            }
                        }
                        dismissMtplDialog(mActivity);
                        vollyHandler.onVollySuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Webcalls", "error=" + error.getMessage());


                dismissMtplDialog(mActivity);
                String errorSet = getActivity().getResources().getString(R.string.msg_networkerror);
                vollyHandler.onVollyError(errorSet);
            }
        }) {
            @Override
            public String getBodyContentType() {
                // TODO Auto-generated method stub
                return "application/json";
            }


//            @Override
//            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                try {
//                    Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
//                    if (cacheEntry == null) {
//                        cacheEntry = new Cache.Entry();
//                    }
//                    final long cacheHitButRefreshed = 3 * 60 * 1000; // in 3 minutes cache will be hit, but also refreshed on background
//                    final long cacheExpired = 24 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely
//                    long now = System.currentTimeMillis();
//                    final long softExpire = now + cacheHitButRefreshed;
//                    final long ttl = now + cacheExpired;
//                    cacheEntry.data = response.data;
//                    cacheEntry.softTtl = softExpire;
//                    cacheEntry.ttl = ttl;
//                    String headerValue;
//                    headerValue = response.headers.get("Date");
//                    if (headerValue != null) {
//                        cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
//                    }
//                    headerValue = response.headers.get("Last-Modified");
//                    if (headerValue != null) {
//                        cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
//                    }
//                    cacheEntry.responseHeaders = response.headers;
//                    final String jsonString = new String(response.data,
//                            HttpHeaderParser.parseCharset(response.headers));
//
//                    vollyHandler.onVollySuccess(jsonString);
//                    return Response.success(jsonString, cacheEntry);
//                } catch (UnsupportedEncodingException e) {
//                    return Response.error(new ParseError(e));
//                }
//
//            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };

        ((FoodOrdringApplication) mActivity.getApplicationContext()).volley.addToRequestQueue(stringRequest);


    }

    /****
     * multipe images upload
     ***////
    public void vollyStringRequestCallwithFile(String url, final String f, final String f1, final String f2, final int requestMethod, final Map<String, String> params, final OnVolleyHandler vollyHandler) throws JSONException {
        if (!CM.isInternetAvailable(mActivity)) {
            vollyHandler.onVollyError(mActivity.getResources().getString(R.string.msg_internet_unavailable_msg));
            return;
        }
        if (mIsShowPopup) {
            showMtplDialog(mActivity);
        }
        MultipartRequest mr = new MultipartRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response", response);
                String strResponseStatus = CM.getValueFromJson(WebServiceTag.WEB_STATUS, response);
                if (strResponseStatus.equalsIgnoreCase(WebServiceTag.WEB_STATUSFAIL)) {
                    dismissMtplDialog(mActivity);
                } else {
                    if (mIsDismissPopup) {
                        dismissMtplDialog(mActivity);
                    }
                }
                try {
                    vollyHandler.onVollySuccess(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Webcalls", "error=" + error.getMessage());
                dismissMtplDialog(mActivity);
                String errorSet = error.getMessage();
                vollyHandler.onVollyError(errorSet);
            }
        }, f, f1, f2, params);
        Log.e("Webcalls", "params=" + params.toString());
        ((FoodOrdringApplication) mActivity.getApplicationContext()).volley.addToRequestQueue(mr);
    }

    //Volly Webservice Related Methods End //
    public void showMtplDialog(Activity mActivity) {
        if (mActivity.isFinishing()) {
            return;
        }
        if (mtplDialog == null)
            mtplDialog = new MtplProgressDialog(mActivity, "", false);
        if (!mtplDialog.isShowing())
            mtplDialog.show();
    }

    private void dismissMtplDialog(Activity activity) {

        if (mtplDialog != null && mtplDialog.isShowing())
            mtplDialog.dismiss();
    }


    public void vollyStringJasonObjectCall(String url, final int requestMethod, JSONObject params, final OnVolleyHandler vollyHandler, boolean showPopup) throws JSONException {
        //AS we have to pass Security key in ever webservice we have
//        if (json != null) {
//            json.put("strSecurityKey", CV.SECURITY_KEY);
//        }
        if (!CM.isInternetAvailable(mActivity)) {
            vollyHandler.onVollyError(mActivity.getResources().getString(R.string.msg_internet_unavailable_msg));
            return;
        }
        if (mIsShowPopup) {
            showMtplDialog(mActivity);
        }

        //    JSONObject jsonObject = new JSONObject(params);
        JsonObjectRequest req = new JsonObjectRequest(url, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            Log.e("response", response.toString());
                            //VolleyLog.v("Response:%n %s", response.toString(0));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return String.format("application/json; charset=utf-8");
            }

        };


        ((FoodOrdringApplication) mActivity.getApplicationContext()).volley.addToRequestQueue(req);


    }


}
