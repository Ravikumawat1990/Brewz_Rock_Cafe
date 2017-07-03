package com.app.elixir.brewz_rock_cafe.payu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.utils.CM;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by NetSupport on 02-11-2016.
 */
public class PayMentGateWay extends Activity {

    private ArrayList<String> post_val = new ArrayList<String>();
    private String post_Data = "";
    WebView webView;
    final Activity activity = this;
    private String tag = "PayMentGateWay";
    private String hash, hashSequence;
    ProgressDialog progressDialog;

    // String merchant_key = "zBxSQi"; // live
    //String salt = "ZhraT96O"; // live


    // Merchant ID	5711839 Live
    //Merchant Key	n9Aa6Yfe Live
    // Merchant Salt	4QUSTd6zt8 LIVE


    //Merchant Key	0n35wS Demo
    // Salt ddKr3Wsb Demo


    String merchant_key = "fB7m8s";
    String salt = "eRis5Chv";
    String action1 = "";
    String base_url = "https://test.payu.in/_payment";
    //https://secure.payu.in
    //  String base_url = "https://secure.payu.in";//
    int error = 0;
    String hashString = "";
    Map<String, String> params;
    String txnid = "";
    private String mTXNId; // This will create below randomly
    String SUCCESS_URL = "https://www.payumoney.com/mobileapp/payumoney/success.php"; // failed
    String FAILED_URL = "https://www.payumoney.com/mobileapp/payumoney/failure.php";

    Handler mHandler = new Handler();


    static String getFirstName, getNumber, getEmailAddress, Amt;


    ProgressDialog pDialog;
    private String traId;
    private String errorMsg = "";
    private ArrayList<String> oldData;
    private ArrayList<String> newData;
    Map<String, String> oldDataMap;
    Map<String, String> newDataMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(activity);


        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        webView = new WebView(this);
        setContentView(webView);


        oldData = new ArrayList<String>();
        oldData.clear();
        newData = new ArrayList<String>();
        newData.clear();

        oldDataMap = new HashMap<>();
        newDataMap = new HashMap<>();
        newDataMap.clear();
        oldDataMap.clear();
        Intent oIntent = getIntent();

        getFirstName = oIntent.getExtras().getString("FIRST_NAME");
        getNumber = oIntent.getExtras().getString("PHONE_NUMBER");
        getEmailAddress = oIntent.getExtras().getString("EMAIL_ADDRESS");
        Amt = oIntent.getExtras().getString("AMT");

        params = new HashMap<String, String>();
        params.put("key", merchant_key);
        params.put("amount", Amt);
        params.put("firstname", getFirstName);
        params.put("email", getEmailAddress);
        params.put("phone", getNumber);
        params.put("productinfo", "brewzrock");
        params.put("surl", SUCCESS_URL);
        params.put("furl", FAILED_URL);
        params.put("service_provider", "payu_paisa");
        params.put("lastname", "");
        params.put("address1", "");
        params.put("address2", "");
        params.put("city", "");
        params.put("state", "");
        params.put("country", "");
        params.put("zipcode", "");
        params.put("udf1", "");
        params.put("udf2", "");
        params.put("udf3", "");
        params.put("udf4", "");
        params.put("udf5", "");
        params.put("pg", "");


        if (empty(params.get("txnid"))) {
            Random rand = new Random();
            String rndm = Integer.toString(rand.nextInt()) + (System.currentTimeMillis() / 1000L);
            txnid = hashCal("SHA-256", rndm).substring(0, 20);
            params.put("txnid", txnid);

        } else
            txnid = params.get("txnid");

        String txn = "abcd";
        hash = "";
        String hashSequence = "key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|udf10";
        if (empty(params.get("hash")) && params.size() > 0) {
            if (empty(params.get("key"))
                    || empty(params.get("txnid"))
                    || empty(params.get("amount"))
                    || empty(params.get("firstname"))
                    || empty(params.get("email"))
                    || empty(params.get("phone"))
                    || empty(params.get("productinfo"))
                    || empty(params.get("surl"))
                    || empty(params.get("furl"))
                    || empty(params.get("service_provider"))

                    ) {
                error = 1;
            } else {
                String[] hashVarSeq = hashSequence.split("\\|");

                for (String part : hashVarSeq) {
                    hashString = (empty(params.get(part))) ? hashString.concat("") : hashString.concat(params.get(part));
                    hashString = hashString.concat("|");
                }
                hashString = hashString.concat(salt);


                hash = hashCal("SHA-512", hashString);
                action1 = base_url.concat("/_payment");
            }
        } else if (!empty(params.get("hash"))) {
            hash = params.get("hash");
            action1 = base_url.concat("/_payment");
        }


        webView.setWebViewClient(new MyWebViewClient() {

                                     public void onPageFinished(WebView view, final String url) {


                                         try {

                                             String htmlPage = "";
                                             newDataMap.clear();
                                             if (url.contains("data:text/html")) {
                                                 htmlPage = url.replace("data:text/html,", "");
                                                 Document doc = Jsoup.parse(htmlPage);
                                                 Elements hidden = doc.select("input[type=hidden]");
                                                 for (Element el1 : hidden) {
                                                     Log.i("TAG", "Tag Name: " + el1.attr("name") + " Tag Value   " + el1.attr("value"));
                                                     newDataMap.put(el1.attr("name"), el1.attr("value"));
                                                 }

                                                 try {
                                                     Set<String> values1 = new HashSet<>(oldDataMap.values());
                                                     Set<String> values2 = new HashSet<>(newDataMap.values());
                                                     boolean equal = values1.equals(values2);
                                                     if (equal) {
                                                         Log.i("TAG", "onPageFinished: Equal");
                                                         // showDiloag();
                                                     } else {
                                                         showDiloag();
                                                     }
                                                 } catch (Exception e) {

                                                 }


                                             }


                                             Log.i("pagefinish", "onPageFinished: " + url);
                                         } catch (Exception e) {
                                             e.getMessage();
                                         }


                                         progressDialog.dismiss();
                                     }

                                     public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                         //make sure dialog is showing
                                         if (!progressDialog.isShowing()) {
                                             progressDialog.show();
                                         }

                                     }

                                     @Override
                                     public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                                         Toast.makeText(activity, "error! " + error, Toast.LENGTH_SHORT).show();
                                         super.onReceivedError(view, request, error);
                                     }

                                     @Override
                                     public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
                                         final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                                         builder.setMessage(R.string.notification_error_ssl_cert_invalid);
                                         builder.setPositiveButton("continue", new DialogInterface.OnClickListener() {
                                             @Override
                                             public void onClick(DialogInterface dialog, int which) {
                                                 handler.proceed();
                                             }
                                         });
                                         builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                             @Override
                                             public void onClick(DialogInterface dialog, int which) {
                                                 handler.cancel();
                                             }
                                         });
                                         final AlertDialog dialog = builder.create();
                                         dialog.show();
                                     }

                                     public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                         // TODO Auto-generated method stub
                                         //Toast.makeText(activity, "Page Started! " + url, Toast.LENGTH_SHORT).show();


                                         if (url.startsWith(SUCCESS_URL)) {
                                             //Toast.makeText(activity, "Payment Successful! " + url, Toast.LENGTH_SHORT).show();
                                             return false;
                                         } else if (url.startsWith(FAILED_URL)) {
                                             // Toast.makeText(activity, "Payment Failed! " + url, Toast.LENGTH_SHORT).show();
                                             return false;
                                         } else if (url.contains("paymentId")) {
                                             String[] split = url.split("paymentId=");
                                             traId = split[1];
                                             Log.i("paid", traId);
                                             //Toast.makeText(activity, "Payment ID! " + split[1], Toast.LENGTH_SHORT).show();
                                             return false;

                                         } else if (url.contains("response?")) {
                                             errorMsg = url;
//                                             new response().execute(url);
                                         }
                                         //return super.shouldOverrideUrlLoading(view, url);
                                         return false;
                                     }


                                 }


        );


        webView.setVisibility(View.VISIBLE);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NORMAL);
        webView.getSettings().setDomStorageEnabled(true);
        webView.clearHistory();
        webView.clearCache(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setUseWideViewPort(false);
        webView.getSettings().setLoadWithOverviewMode(false);


        webView.addJavascriptInterface(new PayUJavaScriptInterface(), "PayUMoney");
        Map<String, String> mapParams = new HashMap<String, String>();

        mapParams.put("key", merchant_key);
        mapParams.put("hash", PayMentGateWay.this.hash);
        mapParams.put("txnid", (empty(PayMentGateWay.this.params.get("txnid"))) ? "" : PayMentGateWay.this.params.get("txnid"));
        Log.d(tag, "txnid: " + PayMentGateWay.this.params.get("txnid"));
        mapParams.put("service_provider", "payu_paisa");
        mapParams.put("amount", (empty(PayMentGateWay.this.params.get("amount"))) ? "" : PayMentGateWay.this.params.get("amount"));
        mapParams.put("firstname", (empty(PayMentGateWay.this.params.get("firstname"))) ? "" : PayMentGateWay.this.params.get("firstname"));
        mapParams.put("email", (empty(PayMentGateWay.this.params.get("email"))) ? "" : PayMentGateWay.this.params.get("email"));
        mapParams.put("phone", (empty(PayMentGateWay.this.params.get("phone"))) ? "" : PayMentGateWay.this.params.get("phone"));
        mapParams.put("productinfo", (empty(PayMentGateWay.this.params.get("productinfo"))) ? "" : PayMentGateWay.this.params.get("productinfo"));
        mapParams.put("surl", (empty(PayMentGateWay.this.params.get("surl"))) ? "" : PayMentGateWay.this.params.get("surl"));
        mapParams.put("furl", (empty(PayMentGateWay.this.params.get("furl"))) ? "" : PayMentGateWay.this.params.get("furl"));
        mapParams.put("lastname", (empty(PayMentGateWay.this.params.get("lastname"))) ? "" : PayMentGateWay.this.params.get("lastname"));
        mapParams.put("address1", (empty(PayMentGateWay.this.params.get("address1"))) ? "" : PayMentGateWay.this.params.get("address1"));
        mapParams.put("address2", (empty(PayMentGateWay.this.params.get("address2"))) ? "" : PayMentGateWay.this.params.get("address2"));
        mapParams.put("city", (empty(PayMentGateWay.this.params.get("city"))) ? "" : PayMentGateWay.this.params.get("city"));
        mapParams.put("state", (empty(PayMentGateWay.this.params.get("state"))) ? "" : PayMentGateWay.this.params.get("state"));
        mapParams.put("country", (empty(PayMentGateWay.this.params.get("country"))) ? "" : PayMentGateWay.this.params.get("country"));
        mapParams.put("zipcode", (empty(PayMentGateWay.this.params.get("zipcode"))) ? "" : PayMentGateWay.this.params.get("zipcode"));
        mapParams.put("udf1", (empty(PayMentGateWay.this.params.get("udf1"))) ? "" : PayMentGateWay.this.params.get("udf1"));
        mapParams.put("udf2", (empty(PayMentGateWay.this.params.get("udf2"))) ? "" : PayMentGateWay.this.params.get("udf2"));
        mapParams.put("udf3", (empty(PayMentGateWay.this.params.get("udf3"))) ? "" : PayMentGateWay.this.params.get("udf3"));
        mapParams.put("udf4", (empty(PayMentGateWay.this.params.get("udf4"))) ? "" : PayMentGateWay.this.params.get("udf4"));
        mapParams.put("udf5", (empty(PayMentGateWay.this.params.get("udf5"))) ? "" : PayMentGateWay.this.params.get("udf5"));
        mapParams.put("pg", (empty(PayMentGateWay.this.params.get("pg"))) ? "" : PayMentGateWay.this.params.get("pg"));


        oldDataMap.put("key", merchant_key);
        oldDataMap.put("hash", PayMentGateWay.this.hash);
        oldDataMap.put("txnid", (empty(PayMentGateWay.this.params.get("txnid"))) ? "" : PayMentGateWay.this.params.get("txnid"));
        Log.d(tag, "txnid: " + PayMentGateWay.this.params.get("txnid"));
        oldDataMap.put("service_provider", "payu_paisa");
        oldDataMap.put("amount", (empty(PayMentGateWay.this.params.get("amount"))) ? "" : PayMentGateWay.this.params.get("amount"));
        oldDataMap.put("firstname", (empty(PayMentGateWay.this.params.get("firstname"))) ? "" : PayMentGateWay.this.params.get("firstname"));
        oldDataMap.put("email", (empty(PayMentGateWay.this.params.get("email"))) ? "" : PayMentGateWay.this.params.get("email"));
        oldDataMap.put("phone", (empty(PayMentGateWay.this.params.get("phone"))) ? "" : PayMentGateWay.this.params.get("phone"));
        oldDataMap.put("productinfo", (empty(PayMentGateWay.this.params.get("productinfo"))) ? "" : PayMentGateWay.this.params.get("productinfo"));
        oldDataMap.put("surl", (empty(PayMentGateWay.this.params.get("surl"))) ? "" : PayMentGateWay.this.params.get("surl"));
        oldDataMap.put("furl", (empty(PayMentGateWay.this.params.get("furl"))) ? "" : PayMentGateWay.this.params.get("furl"));
        oldDataMap.put("lastname", (empty(PayMentGateWay.this.params.get("lastname"))) ? "" : PayMentGateWay.this.params.get("lastname"));
        oldDataMap.put("address1", (empty(PayMentGateWay.this.params.get("address1"))) ? "" : PayMentGateWay.this.params.get("address1"));
        oldDataMap.put("address2", (empty(PayMentGateWay.this.params.get("address2"))) ? "" : PayMentGateWay.this.params.get("address2"));
        oldDataMap.put("city", (empty(PayMentGateWay.this.params.get("city"))) ? "" : PayMentGateWay.this.params.get("city"));
        oldDataMap.put("state", (empty(PayMentGateWay.this.params.get("state"))) ? "" : PayMentGateWay.this.params.get("state"));
        oldDataMap.put("country", (empty(PayMentGateWay.this.params.get("country"))) ? "" : PayMentGateWay.this.params.get("country"));
        oldDataMap.put("zipcode", (empty(PayMentGateWay.this.params.get("zipcode"))) ? "" : PayMentGateWay.this.params.get("zipcode"));
        oldDataMap.put("udf1", (empty(PayMentGateWay.this.params.get("udf1"))) ? "" : PayMentGateWay.this.params.get("udf1"));
        oldDataMap.put("udf2", (empty(PayMentGateWay.this.params.get("udf2"))) ? "" : PayMentGateWay.this.params.get("udf2"));
        oldDataMap.put("udf3", (empty(PayMentGateWay.this.params.get("udf3"))) ? "" : PayMentGateWay.this.params.get("udf3"));
        oldDataMap.put("udf4", (empty(PayMentGateWay.this.params.get("udf4"))) ? "" : PayMentGateWay.this.params.get("udf4"));
        oldDataMap.put("udf5", (empty(PayMentGateWay.this.params.get("udf5"))) ? "" : PayMentGateWay.this.params.get("udf5"));
        oldDataMap.put("pg", (empty(PayMentGateWay.this.params.get("pg"))) ? "" : PayMentGateWay.this.params.get("pg"));


        webview_ClientPost(webView, action1, mapParams.entrySet()

        );

    }

  /*public class PayUJavaScriptInterface {

   @JavascriptInterface
        public void success(long id, final String paymentId) {
            mHandler.post(new Runnable() {
                public void run() {
                    mHandler = null;


              Toast.makeText(getApplicationContext(),"Successfully payment\n redirect from PayUJavaScriptInterface" ,Toast.LENGTH_LONG).show();

                }
            });
        }
 }*/


    private final class PayUJavaScriptInterface {

        PayUJavaScriptInterface() {
        }

        @JavascriptInterface
        public void showHTML(String html) {
            new AlertDialog.Builder(activity).setTitle("HTML").setMessage(html)
                    .setPositiveButton(android.R.string.ok, null).setCancelable(false).create().show();
        }

        /**
         * This is not called on the UI thread. Post a runnable to invoke
         * loadUrl on the UI thread.
         */
        @JavascriptInterface
        public void success(long id, final String paymentId) {
            mHandler.post(new Runnable() {
                public void run() {
                    mHandler = null;


//                    Intent returnIntent = new Intent();
//                    returnIntent.putExtra("result", "success");
//                    returnIntent.putExtra("transId", paymentId);
//                    setResult(Activity.RESULT_OK, returnIntent);
//                    finish();
//                    Toast.makeText(getApplicationContext(), "Successfully payment Received", Toast.LENGTH_LONG).show();
                    new responseSuccess().execute(errorMsg, paymentId);

                }
            });
        }

        @JavascriptInterface
        public void failure(String id, final String payID) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
//                    Intent returnIntent = new Intent();
//                    returnIntent.putExtra("result", "failure");
//                    returnIntent.putExtra("transId", error);
//                    returnIntent.putExtra("msg", "payment cancel");
//                    setResult(Activity.RESULT_OK, returnIntent);
//                    finish();
                    new responseFail().execute(errorMsg, payID);
                }
            });
        }

        @JavascriptInterface
        public void failure() {
            failure("");
        }

        @JavascriptInterface
        public void failure(final String payId) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    new responseFail().execute(errorMsg, payId);

                }
            });
        }

    }


    public void webview_ClientPost(WebView webView, String url, Collection<Map.Entry<String, String>> postData) {
        StringBuilder sb = new StringBuilder();

        sb.append("<html><head></head>");
        sb.append("<body onload='form1.submit()'>");
        sb.append(String.format("<form id='form1' action='%s' method='%s'>", url, "post"));
        for (Map.Entry<String, String> item : postData) {
            sb.append(String.format("<input name='%s' type='hidden' value='%s' />", item.getKey(), item.getValue()));
        }
        sb.append("</form></body></html>");
        Log.d(tag, "webview_ClientPost called");

        //setup and load the progress bar
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading. Please wait...");
        webView.loadData(sb.toString(), "text/html", "utf-8");


    }


    public void success(long id, final String paymentId) {

        mHandler.post(new Runnable() {
            public void run() {
                mHandler = null;
                new responseSuccess().execute(errorMsg, paymentId);
            }
        });
    }


    public boolean empty(String s) {
        if (s == null || s.trim().equals(""))
            return true;
        else
            return false;
    }

    public String hashCal(String type, String str) {
        byte[] hashseq = str.getBytes();
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest algorithm = MessageDigest.getInstance(type);
            algorithm.reset();
            algorithm.update(hashseq);
            byte messageDigest[] = algorithm.digest();


            for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                if (hex.length() == 1) hexString.append("0");
                hexString.append(hex);
            }

        } catch (NoSuchAlgorithmException nsae) {
        }

        return hexString.toString();


    }

    //String SUCCESS_URL = "https://pay.in/sccussful" ; // failed
//String FAILED_URL = "https://pay.in/failed" ;
//override the override loading method for the webview client
    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {






         /*if(url.contains("response.php") || url.equalsIgnoreCase(SUCCESS_URL)){

          new PostRechargeData().execute();

          Toast.makeText(getApplicationContext(),"Successfully payment\n redirect from webview" ,Toast.LENGTH_LONG).show();

                return false;
         }else  */
            if (url.startsWith("http")) {
                //Toast.makeText(getApplicationContext(),url ,Toast.LENGTH_LONG).show();
                progressDialog.show();
                view.loadUrl(url);
                System.out.println("myresult " + url);
                //return true;
            } else {
                return false;
            }

            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage(R.string.notification_error_ssl_cert_invalid);
            builder.setPositiveButton("continue", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    handler.proceed();
                }
            });
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    handler.cancel();
                }
            });
            final AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

    /*******************************************
     * send record to back end
     ******************************************/


    /*******************************************
     * closed send record to back end
     ************************************/

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(PayMentGateWay.this);
                        alertDialog.setTitle("Warning");
                        alertDialog.setMessage("Do you cancel this transaction?");
                        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent returnIntent = new Intent();
                                setResult(Activity.RESULT_CANCELED, returnIntent);
                                finish();
                            }
                        });
                        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertDialog.show();

                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    class responseSuccess extends AsyncTask<String, String, String> {
        private String resString = "";
        String payId;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try {
                pDialog = new ProgressDialog(activity);
                pDialog.setMessage("Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();
            } catch (Exception e) {

            }
        }

        protected String doInBackground(String... args) {
            try {
                try {
                    payId = args[1];
                } catch (Exception e) {

                }
                if (args[0] != null && !args[0].toString().equals("")) {
                    Document doc = Jsoup.connect(args[0]).timeout(60 * 1000).get();
                    Element loginform = doc.getElementById("frmPostBack");
                    Elements inputElements = loginform.getElementsByTag("input");
                    for (Element inputElement : inputElements) {


                        if (inputElement.attr("name").equals("error_Message")) {
                            String value = inputElement.attr("value");
                            System.out.println("Param name: " + inputElement.attr("key") + " \nParam value: " + value);
                            resString = value;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return resString;
        }

        protected void onPostExecute(final String strStatus) {

            try {
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
            } catch (Exception e) {

            }

            Log.e("result", strStatus);
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", "success");
            returnIntent.putExtra("transId", payId);
            returnIntent.putExtra("msg", strStatus);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
            CM.showToast(activity, "PAYMENT SUCCESSFULLY RECEIVED");
        }
    }

    class responseFail extends AsyncTask<String, String, String> {
        private String resString = "";
        String payId = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try {
                pDialog = new ProgressDialog(activity);
                pDialog.setMessage("Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();
            } catch (Exception e) {

            }
        }

        protected String doInBackground(String... args) {
            try {
                try {
                    payId = args[1];
                } catch (Exception e) {

                }
                if (args[0] != null && !args[0].toString().equals("")) {
                    Document doc = Jsoup.connect(args[0]).timeout(60 * 1000).get();
                    Element loginform = doc.getElementById("frmPostBack");
                    Elements inputElements = loginform.getElementsByTag("input");
                    for (Element inputElement : inputElements) {


                        if (inputElement.attr("name").equals("error_Message")) {
                            String value = inputElement.attr("value");
                            System.out.println("Param name: " + inputElement.attr("key") + " \nParam value: " + value);
                            resString = value;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return resString;
        }

        protected void onPostExecute(final String strStatus) {

            try {
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
            } catch (Exception e) {

            }

            Log.e("result", strStatus);
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", "failure");
            returnIntent.putExtra("transId", payId);
            returnIntent.putExtra("msg", strStatus);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
            CM.showToast(activity, "PAYMENT FAIL");
        }
    }


    public void showDiloag() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(PayMentGateWay.this);
        alertDialog.setTitle("Warning");
        alertDialog.setMessage("Your Information tempered. Some buddy try to hack your details ?");
        alertDialog.setCancelable(false);
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });
        alertDialog.show();

    }
}
