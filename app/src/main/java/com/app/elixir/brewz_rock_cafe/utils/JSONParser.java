package com.app.elixir.brewz_rock_cafe.utils;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class JSONParser {

    static InputStream is = null;
    static JSONObject jObj = null;

    int timeoutSocket = 60 * 1000;
    String TAG = "JSONParser";

    // constructor
    public JSONParser() {

    }

    // function get json from url
    // by making HTTP POST or GET mehtod
    public JSONObject getJSONFromUrl(String url, List<NameValuePair> params) {
        // Making HTTP request
        try {
            String method = "GET";

            // check for request method

            if (method == "POST") {
                // request method is POST
                // defaultHttpClient
                is = null;
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

                HttpConnectionParams.setConnectionTimeout(
                        httpClient.getParams(), timeoutSocket);
                HttpConnectionParams.setSoTimeout(httpClient.getParams(),
                        timeoutSocket);

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            } else if (method == "GET") {
                // request method is GET
                is = null;
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (is != null) {

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                String json = "";
                json = sb.toString();

                Log.e(TAG, json.toString());

                // try parse the string to a JSON object
                try {

                    jObj = new JSONObject(json);
                    return jObj;
                } catch (JSONException e) {
                    // Log.e(TAG, "getJSONFromUrl" , "Error parsing data " ,
                    // e.toString());
                    jObj = null;
                    return jObj;
                }
            } else {
                jObj = null;
                return jObj;
            }

        } catch (Exception e) {
            // Log.e(TAG,"getJSONFromUrl",
            // "int the Exception","Buffer Error  Error converting result " +
            // e.toString());
            // / return JSON String
            jObj = null;
            return jObj;
        }
    }

    public JSONObject getJSONFromUrl2(String url, List<NameValuePair> params) {
        // Making HTTP request
        try {
            String method = "POST";

            // check for request method

            if (method == "POST") {
                // request method is POST
                // defaultHttpClient
                is = null;
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

                HttpConnectionParams.setConnectionTimeout(
                        httpClient.getParams(), timeoutSocket);
                HttpConnectionParams.setSoTimeout(httpClient.getParams(),
                        timeoutSocket);

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            } else if (method == "GET") {
                // request method is GET
                is = null;
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (is != null) {

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                String json = "";
                json = sb.toString();

                // Log.e(TAG, "getJSONFromUrl","JSON",json.toString());

                // try parse the string to a JSON object
                try {

                    jObj = new JSONObject(json);
                    return jObj;
                } catch (JSONException e) {
                    // Log.e(TAG, "getJSONFromUrl" , "Error parsing data " ,
                    // e.toString());
                    jObj = null;
                    return jObj;
                }
            } else {
                jObj = null;
                return jObj;
            }

        } catch (Exception e) {
            // Log.e(TAG,"getJSONFromUrl",
            // "int the Exception","Buffer Error  Error converting result " +
            // e.toString());
            // return JSON String
            jObj = null;
            return jObj;
        }
    }

    /**
     * @param url
     * @return json
     * @author MAYUR KAPADIA
     * @description GLB-595 calling api for user info GLB-593 otp request
     * -24/11/14 5:42
     */
    public JSONObject getJSONFromUrlImeiOtp(String url) {
        // Making HTTP request
        try {
            String method = "POST";
            // check for request method
            if (method == "POST") {
                // request method is POST
                // defaultHttpClient

                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                // httpPost.setEntity(new UrlEncodedFormEntity(params));
                HttpConnectionParams.setConnectionTimeout(
                        httpClient.getParams(), 60000);
                int timeoutSocket = 300 * 1000;
                HttpConnectionParams.setSoTimeout(httpClient.getParams(),
                        timeoutSocket);

                HttpResponse httpResponse = httpClient.execute(httpPost);
                // Log.print("JSONObject Url ", "" +
                // httpResponse.getStatusLine().getStatusCode());
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            } else if (method == "GET") {
                // request method is GET
                DefaultHttpClient httpClient = new DefaultHttpClient();
                // String paramString = URLEncodedUtils.format(params, "utf-8");
                // url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (is != null) {

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                String json = "";
                json = sb.toString();

                // Log.e(TAG, "getJSONFromUrlImeiOtp","JSON",json.toString());

                // try parse the string to a JSON object
                try {

                    jObj = new JSONObject(json);
                    return jObj;
                } catch (JSONException e) {
                    // Log.e(TAG, "getJSONFromUrlImeiOtp" ,
                    // "Error parsing data " , e.toString());
                    jObj = null;
                    return jObj;
                }
            } else {
                jObj = null;
                return jObj;
            }

        } catch (Exception e) {
            // Log.e(TAG,"getJSONFromUrlImeiOtp",
            // "int the Exception","Buffer Error  Error converting result " +
            // e.toString());
            // return JSON String
            jObj = null;
            return jObj;
        }
    }

    /**
     * nimit raja glb 213 @ for device switch
     *
     * @return jsonobject
     */
    public JSONObject getJSONFromUrlSwitch(String url) {
        // Making HTTP request
        try {
            String method = "POST";
            // check for request method
            if (method == "POST") {
                // request method is POST
                // defaultHttpClient

                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                // httpPost.setEntity(new UrlEncodedFormEntity(params));
                HttpConnectionParams.setConnectionTimeout(
                        httpClient.getParams(), 60000);
                int timeoutSocket = 300 * 1000;
                HttpConnectionParams.setSoTimeout(httpClient.getParams(),
                        timeoutSocket);

                HttpResponse httpResponse = httpClient.execute(httpPost);
                // Log.print("JSONObject Url ", "" +
                // httpResponse.getStatusLine().getStatusCode());
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            } else if (method == "GET") {
                // request method is GET
                DefaultHttpClient httpClient = new DefaultHttpClient();
                // String paramString = URLEncodedUtils.format(params, "utf-8");
                // url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (is != null) {

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                String json = "";
                json = sb.toString();

                // Log.e(TAG, "getJSONFromUrlImeiOtp", "JSON", json.toString());

                // try parse the string to a JSON object
                try {

                    jObj = new JSONObject(json);
                    return jObj;
                } catch (JSONException e) {
                    // Log.e(TAG, "getJSONFromUrlImeiOtp",
                    // "Error parsing data ", e.toString());
                    jObj = null;
                    return jObj;
                }
            } else {
                jObj = null;
                return jObj;
            }

        } catch (Exception e) {
            // Log.e(TAG, "getJSONFromUrlImeiOtp", "int the Exception",
            // "Buffer Error  Error converting result " + e.toString());
            // return JSON String
            jObj = null;
            return jObj;
        }
    }

    public JSONObject getMediaResult(String url, MultipartEntity entity) {
        // Making HTTP request
        try {
            String method = "POST";

            // check for request method

            if (method == "POST") {
                // request method is POST
                // defaultHttpClient
                is = null;
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(entity);

                HttpConnectionParams.setConnectionTimeout(
                        httpClient.getParams(), timeoutSocket);
                HttpConnectionParams.setSoTimeout(httpClient.getParams(),
                        timeoutSocket);

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (is != null) {

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                String json = "";
                json = sb.toString();

                Log.e(TAG, json.toString());

                // try parse the string to a JSON object
                try {

                    jObj = new JSONObject(json);
                    return jObj;
                } catch (JSONException e) {
                    // Log.e(TAG, "getJSONFromUrl" , "Error parsing data " ,
                    // e.toString());
                    jObj = null;
                    return jObj;
                }
            } else {
                jObj = null;
                return jObj;
            }

        } catch (Exception e) {
            // Log.e(TAG,"getJSONFromUrl",
            // "int the Exception","Buffer Error  Error converting result " +
            // e.toString());
            // / return JSON String
            jObj = null;
            return jObj;
        }
    }

}