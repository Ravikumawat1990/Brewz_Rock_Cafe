package com.app.elixir.brewz_rock_cafe.volly;


import com.android.volley.Request;
import com.app.elixir.brewz_rock_cafe.utils.CONSTANT;
import com.app.elixir.brewz_rock_cafe.utils.URLS;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WebService {


    //    public static void getMobileNoVerification(VolleyIntialization vollyInit, String moNo, OnVolleyHandler vollyHanlder) throws JSONException {
//
//        String url = URLS.GET_ALL_MAKE;
//        Map<String, String> params = new HashMap<>();
//        params.put(WebServiceTag.TAG_STR_APIKEY, CV.API_KEY);
//        params.put(WebServiceTag.TAG__MOBILE, moNo);
//        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
//    }
//
//    public static void getEmail(VolleyIntialization vollyInit, String email, OnVolleyHandler vollyHanlder) throws JSONException {
//
//        String url = URLS.GET_ALL_MAKE;
//        Map<String, String> params = new HashMap<>();
//        params.put(WebServiceTag.TAG_STR_APIKEY, CV.API_KEY);
//        params.put(WebServiceTag.TAG__MOBILE, email);
//        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
//    }
//
//
    public static void getOTP(VolleyIntialization vollyInit, String phoneNo, OnVolleyHandler vollyHanlder) throws JSONException {

        String url = URLS.GETOTP;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        params.add(new BasicNameValuePair(CONSTANT.CMOBILE, phoneNo));
        vollyInit.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }

    public static void getSignIn(VolleyIntialization vollyInit, String email, String pass, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.GetLoginViaEmail;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.CEMAIL, email));
        params.add(new BasicNameValuePair(CONSTANT.CEMAILPASSWORD, pass));
        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
    }
//
//    public static void getResendOTP(VolleyIntialization vollyInit, String otp, OnVolleyHandler vollyHanlder) throws JSONException {
//
//        String url = URLS.GET_ALL_MAKE;
//        Map<String, String> params = new HashMap<>();
//        params.put(WebServiceTag.TAG_STR_APIKEY, CV.API_KEY);
//        params.put(WebServiceTag.TAG__OTP, otp);
//        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
//    }
//
//

//
//
//    public static void getResendForSetDate(VolleyIntialization vollyInit, String phoneNo, OnVolleyHandler vollyHanlder) throws JSONException {
//        String url = URLS.URL;
//        Map<String, String> params = new HashMap<>();
//        params.put("cAppStoreCode", "r-1");
//        params.put("cAPIKey", "a");
//        params.put("cAPIPass", "a");
//        params.put("nCompanyID", "1");
//        vollyInit.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
//    }
//


    public static void getResendForCategories(VolleyIntialization volleyIntialization, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.GETPRODUCTCATEGORIES;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        volleyIntialization.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }

    public static void getResendForCategoryItem(VolleyIntialization vollyInit, String id, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.GETCOMPANYPRODUCTS;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        params.add(new BasicNameValuePair(CONSTANT.NPRODUCTCATEGORYID, id));
        vollyInit.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }

    //
    public static void getAddToCart(VolleyIntialization vollyInit, String id, String spicyType, String itemQuantity, String price, String array, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.POSTCART;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));


        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
    }

    //
    public static void getResendForCategoryItemDetail(VolleyIntialization vollyInit, String catId, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.PRODUCTCUSTOMIZATION;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYPRODUCTID, catId));
        vollyInit.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }

    //
//
//    public static void getResendForOrderSummery(VolleyIntialization vollyInit, String id, OnVolleyHandler vollyHanlder) throws JSONException {
//        String url = URLS.GET_ALL_MAKE;
//        Map<String, String> params = new HashMap<>();
//        params.put(WebServiceTag.TAG_STR_APIKEY, CV.API_KEY);
//        params.put(WebServiceTag.TAG_CAT_ID, id);
//        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
//    }
//
//    public static void getResendRemoveItem(VolleyIntialization vollyInit, String id, OnVolleyHandler vollyHanlder) throws JSONException {
//        String url = URLS.GET_ALL_MAKE;
//        Map<String, String> params = new HashMap<>();
//        params.put(WebServiceTag.TAG_STR_APIKEY, CV.API_KEY);
//        params.put(WebServiceTag.TAG_CAT_ID, id);
//        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
//    }
//
//
//    public static void getResendContinuPayment(VolleyIntialization vollyInit, String id, OnVolleyHandler vollyHanlder) throws JSONException {
//        String url = URLS.GET_ALL_MAKE;
//        Map<String, String> params = new HashMap<>();
//        params.put(WebServiceTag.TAG_STR_APIKEY, CV.API_KEY);
//        params.put(WebServiceTag.TAG_CAT_ID, id);
//        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
//    }
//
//
//    public static void getCustomerDetail(VolleyIntialization vollyInit, String name, OnVolleyHandler vollyHanlder) throws JSONException {
//        String url = URLS.GET_ALL_MAKE;
//        Map<String, String> params = new HashMap<>();
//        params.put(WebServiceTag.TAG_STR_APIKEY, CV.API_KEY);
//        params.put(WebServiceTag.TAG_CAT_ID, name);
//        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
//    }
//
//
//    public static void getPlaceOder(VolleyIntialization vollyInit, String name, OnVolleyHandler vollyHanlder) throws JSONException {
//        String url = URLS.GET_ALL_MAKE;
//        Map<String, String> params = new HashMap<>();
//        params.put(WebServiceTag.TAG_STR_APIKEY, CV.API_KEY);
//        params.put(WebServiceTag.TAG_CAT_ID, name);
//        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
//    }
//
//    public static void GetUpdateProfile(VolleyIntialization vollyInit, String userId, String userName, String email, String mobile, String accessToken, OnVolleyHandler vollyHanlder) throws JSONException {
//        String url = URLS.PART_USER_UPDATE_PROFILE;
//        Map<String, String> params = new HashMap<>();
//        params.put(WebServiceTag.TAG_STR_APIKEY, CV.API_KEY);
//        params.put(WebServiceTag.TAG_STR_ACCESSTOKEN, accessToken);
//        params.put(WebServiceTag.TAG_USERID, userId);
//        params.put(WebServiceTag.TAG_USER_NAME, userName);
//        params.put(WebServiceTag.TAG__MOBILE, mobile);
//        params.put(WebServiceTag.TAG__EMAIL, email);
//        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
//    }
//
//
    public static void GetChangePassword(VolleyIntialization vollyInit, String email, String pass, String newpass, String customerId, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.POSTCHANGEPASSWORD;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.USERTYPEID, CONSTANT.UserTypeID));
        params.add(new BasicNameValuePair(CONSTANT.CEMAIL, email));
        params.add(new BasicNameValuePair(CONSTANT.CEMAILPASSWORD, pass));
        params.add(new BasicNameValuePair(CONSTANT.CEMAILPASSWORDNEW, newpass));
        params.add(new BasicNameValuePair(CONSTANT.NCUSTOMERID, customerId));
        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
    }
//
//    // Forgot Password //
//    public static void ForgotPassword(VolleyIntialization vollyInit, String email, OnVolleyHandler vollyHanlder) throws JSONException {
//        String url = URLS.FORGOT_PASSWORD;
//        Map<String, String> params = new HashMap<>();
//        params.put(WebServiceTag.TAG_STR_APIKEY, CV.API_KEY);
//        params.put(WebServiceTag.TAG_FORGOT_EMAIL
//                , email);
//        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
//    }
//
//
//    public static void getResendForOderHistoryDetail(VolleyIntialization vollyInit, String id, OnVolleyHandler vollyHanlder) throws JSONException {
//        String url = URLS.GET_ALL_MAKE;
//        Map<String, String> params = new HashMap<>();
//        params.put(WebServiceTag.TAG_STR_APIKEY, CV.API_KEY);
//        params.put(WebServiceTag.TAG_CAT_ID, id);
//        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
//    }
//
//
//    public static void getResendForgetOderHistory(VolleyIntialization vollyInit, String phoneNo, OnVolleyHandler vollyHanlder) throws JSONException {
//        String url = URLS.GET_ALL_MAKE;
//        Map<String, String> params = new HashMap<>();
//        params.put(WebServiceTag.TAG_STR_APIKEY, CV.API_KEY);
//        params.put(WebServiceTag.TAG_MOBILE, phoneNo);
//        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
//    }


//    cAppStoreCode=r-1&cAPIKey=a&cAPIPass=a&nCompanyID=1

    public static void getResendForConpanyDate(VolleyIntialization vollyInit, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.GETCOMPANYDATA;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        vollyInit.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }

    //
//    public static void getResponseForFeedBack(VolleyIntialization vollyInit, String id, OnVolleyHandler vollyHanlder) throws JSONException {
//        String url = URLS.GET_ALL_MAKE;
//        Map<String, String> params = new HashMap<>();
//        params.put(WebServiceTag.TAG_STR_APIKEY, CV.API_KEY);
//        params.put(WebServiceTag.TAG_CAT_ID, id);
//        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
//    }
//
//
//    public static void getResponseForOrderList(VolleyIntialization vollyInit, String id, OnVolleyHandler vollyHanlder) throws JSONException {
//        String url = URLS.GET_ALL_MAKE;
//        Map<String, String> params = new HashMap<>();
//        params.put(WebServiceTag.TAG_STR_APIKEY, CV.API_KEY);
//        params.put(WebServiceTag.TAG_CAT_ID, id);
//        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
//    }
//
//    public static void getResponseForPastDelivery(VolleyIntialization vollyInit, String id, OnVolleyHandler vollyHanlder) throws JSONException {
//        String url = URLS.GET_ALL_MAKE;
//        Map<String, String> params = new HashMap<>();
//        params.put(WebServiceTag.TAG_STR_APIKEY, CV.API_KEY);
//        params.put(WebServiceTag.TAG_CAT_ID, id);
//        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
//    }
//
    public static void getResponseForContactUs(VolleyIntialization vollyInit, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.GETCOMPANYDATA;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        vollyInit.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }
//String cAppStoreCode, String cAPIKey, String cAPIPass, Int32 nCompanyID
//    public static void getResponseForDisclaminer(VolleyIntialization vollyInit, String id, OnVolleyHandler vollyHanlder) throws JSONException {
//        String url = URLS.GET_ALL_MAKE;
//        Map<String, String> params = new HashMap<>();
//        params.put(WebServiceTag.TAG_STR_APIKEY, CV.API_KEY);
//        params.put(WebServiceTag.TAG_CAT_ID, id);
//        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
//    }


    public static void getResponseForCouPonCode(VolleyIntialization vollyInit, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.DISCOUNTCOUPONS;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        vollyInit.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }

    public static void getResponseForUserProfile(VolleyIntialization vollyInit, String customerId, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.CUSTOMERPROFILE;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCUSTOMERID, customerId));
        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
    }

    public static void getResponseForEditProfile(VolleyIntialization vollyInit, JSONObject data, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.EDITCUSTOMERPROFILE;
        vollyInit.vollyStringJasonObjectCall(url, Request.Method.POST, data, vollyHanlder, false);
    }

    public static void getResponseForCreateCustomer(VolleyIntialization vollyInit, String number, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.CREATECUSTOMER;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.CMOBILE, number));
        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
    }


    public static void getResendForForgetPassword(VolleyIntialization volleyIntialization, String email, String customerId, String usertype, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.FORGETPASSWORD;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        params.add(new BasicNameValuePair(CONSTANT.CEMAIL, email));
        params.add(new BasicNameValuePair(CONSTANT.NCUSTOMERID, customerId));
        params.add(new BasicNameValuePair(CONSTANT.USERTYPEID, usertype));
        volleyIntialization.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
    }

    public static void getResponseForFeedBack(VolleyIntialization volleyIntialization, String comment, String rating, String customerId, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.FEEDBACK;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        params.add(new BasicNameValuePair(CONSTANT.NCUSTOMERID, customerId));
        params.add(new BasicNameValuePair(CONSTANT.NRATING, rating));
        params.add(new BasicNameValuePair(CONSTANT.CCOMMENT, comment));
        volleyIntialization.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
    }

    public static void getResponseForState(VolleyIntialization vollyInit, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.GETSTATE;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        vollyInit.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }

    public static void getResponseForCity(VolleyIntialization vollyInit, String id, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.GETCITIES;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NSTATEID, id));
        vollyInit.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }

    public static void getResponseForSetTime(VolleyIntialization vollyInit, String orderDate, String currentDate, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.GETSTORETIMINGS;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.DORDERDATE, orderDate));
        params.add(new BasicNameValuePair(CONSTANT.DCURRENTDATE, currentDate));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        vollyInit.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }


    public static void getResponseForPlaceOrder(VolleyIntialization vollyInit, String id, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.PLACEORDER;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NSTATEID, id));
        vollyInit.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }

    public static void getResponseForCustomerDetail(VolleyIntialization volleyIntialization, String customerId, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.CUSTOMERPROFILE;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        params.add(new BasicNameValuePair(CONSTANT.NCUSTOMERID, customerId));
        volleyIntialization.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
    }

    public static void getResponseForOrderSummer(VolleyIntialization volleyIntialization, String customerId, String cartId, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.GETCARTITEMS;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        params.add(new BasicNameValuePair(CONSTANT.NCUSTOMERID, customerId));
        params.add(new BasicNameValuePair(CONSTANT.NCARTID, cartId));
        volleyIntialization.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }


    public static void getResponseForStoreTime(VolleyIntialization volleyIntialization, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.GETSTORETIMINGS;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        volleyIntialization.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }

    public static void getResponseForLandMark(VolleyIntialization volleyIntialization, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.GETLANDMARKS;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        volleyIntialization.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }

    public static void getResponseForOrderHistory(VolleyIntialization volleyIntialization, String customerId, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.GETTODAYSORDER;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        params.add(new BasicNameValuePair(CONSTANT.NUSERID, customerId));
        volleyIntialization.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }

    public static void getResponseForOrderPast(VolleyIntialization volleyIntialization, String custId, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.GETPASTORDER;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        params.add(new BasicNameValuePair(CONSTANT.NUSERID, custId));
        volleyIntialization.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }

    public static void getResponseForOrderDetail(VolleyIntialization volleyIntialization, String custId, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.GETORDERDETAIL;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NORDERID, custId));

        volleyIntialization.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }


    public static void getResponseForDeliveryUserProfile(VolleyIntialization vollyInit, String customerId, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.GETDELIVERYBOYPROFILE;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NUSERID, customerId));
        vollyInit.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }

    public static void getResponseForDeliveryStatus(VolleyIntialization vollyInit, String orderId, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.POSTORDERSTATUSDELIVER;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NORDERID, orderId));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
    }

    public static void getResponseForRemoveCART(VolleyIntialization vollyInit, String cartID, String cartIdDetail, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.REMOVEFROMCART;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYPRODUCTID, CONSTANT.nCompanyProductID));
        params.add(new BasicNameValuePair(CONSTANT.NCARTID, cartID));
        params.add(new BasicNameValuePair(CONSTANT.NCARTDETAILID, cartIdDetail));

        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
    }

    public static void GETCARTDATA(VolleyIntialization vollyInit, String cusId, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.GETCARTDATA;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        params.add(new BasicNameValuePair(CONSTANT.NCUSTOMERID, cusId));
        vollyInit.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }

    public static void GetUserId(VolleyIntialization vollyInit, String email, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.GETUSERID;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        params.add(new BasicNameValuePair(CONSTANT.CEMAIL, email));
        vollyInit.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }


    public static void getResponseForOrderHistoryCustomer(VolleyIntialization volleyIntialization, String customerId, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.GETCUSTOMERORDERHISTORY;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        params.add(new BasicNameValuePair(CONSTANT.NCUSTOMERID, customerId));
        volleyIntialization.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }

    public static void getResponseForApplayCoupon(VolleyIntialization volleyIntialization, String customerId, String carId, String coupon, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.VALIDATEDISCOUNTCOUPON;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.COUPONCODE1, coupon));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        params.add(new BasicNameValuePair(CONSTANT.NCUSTOMERID, customerId));
        params.add(new BasicNameValuePair(CONSTANT.NCARTID, carId));
        volleyIntialization.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }

    // http://192.168.3.105:1007/api/AppApi/RemoveDiscountCoupon?cAppStoreCode=r-1&cAPIKey=a&cAPIPass=a&nCompanyID=1&nCartID=29

    public static void getResponseForRemoveCoupon(VolleyIntialization volleyIntialization, String carId, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.REMOVEDISCOUNTCOUPON;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        //    params.add(new BasicNameValuePair(CONSTANT.COUPONCODE1, coupon));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        //  params.add(new BasicNameValuePair(CONSTANT.NCUSTOMERID, customerId));
        params.add(new BasicNameValuePair(CONSTANT.NCARTID, carId));
        volleyIntialization.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }

    public static void GetSuccessPayment(VolleyIntialization vollyInit, String custId, String payId, String msg, String orderId, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.PAYUSUCCESS;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        params.add(new BasicNameValuePair(CONSTANT.NCUSTOMERID, custId));
        params.add(new BasicNameValuePair(CONSTANT.TXNID, payId));
        params.add(new BasicNameValuePair(CONSTANT.STATUS, msg));
        params.add(new BasicNameValuePair(CONSTANT.NORDERID, orderId));


        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
    }

    public static void GetFailPayment(VolleyIntialization vollyInit, String custId, String payId, String msg, String errorMsg, String orderId, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.PAYUFAILURE;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        params.add(new BasicNameValuePair(CONSTANT.NCUSTOMERID, custId));
        params.add(new BasicNameValuePair(CONSTANT.TXNID, payId));
        params.add(new BasicNameValuePair(CONSTANT.STATUS, msg));
        params.add(new BasicNameValuePair(CONSTANT.ERROR_MESSAGE, errorMsg));
        params.add(new BasicNameValuePair(CONSTANT.NORDERID, orderId));
        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
    }

    public static void getResponsePayMode(VolleyIntialization vollyInit, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.GETPAYMENTMODE;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        vollyInit.vollyStringRequestCall(url, Request.Method.GET, params, vollyHanlder, false);
    }

    public static void getResponseChoosePayMode(VolleyIntialization vollyInit, String mode, String custId, String cardId, OnVolleyHandler vollyHanlder) throws JSONException {
        String url = URLS.CHANGEORDERFILLMODE;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode));
        params.add(new BasicNameValuePair(CONSTANT.CAPIKEY, CONSTANT.cAPIKey));
        params.add(new BasicNameValuePair(CONSTANT.CAPIPASS, CONSTANT.cAPIPass));
        params.add(new BasicNameValuePair(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID));
        params.add(new BasicNameValuePair(CONSTANT.NCUSTOMERID, custId));
        params.add(new BasicNameValuePair(CONSTANT.NCARTID, cardId));
        params.add(new BasicNameValuePair(CONSTANT.NORDERFILLMODE, mode));

        vollyInit.vollyStringRequestCall(url, Request.Method.POST, params, vollyHanlder, false);
    }
}

