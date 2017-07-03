package com.app.elixir.brewz_rock_cafe.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.elixir.brewz_rock_cafe.Model.CartItem;
import com.app.elixir.brewz_rock_cafe.Model.CategoryItemDetailModel;
import com.app.elixir.brewz_rock_cafe.Model.CategoryItemDetailModelArray;
import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.adapter.ExpandableListAdapter;
import com.app.elixir.brewz_rock_cafe.adapter.ExpandableListAdapterNot;
import com.app.elixir.brewz_rock_cafe.database.ItemDetail;
import com.app.elixir.brewz_rock_cafe.database.Items;
import com.app.elixir.brewz_rock_cafe.interfac.OnItemClickListener;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplButton;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplLog;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplTextView;
import com.app.elixir.brewz_rock_cafe.numberPicker.Interface.LimitExceededListener;
import com.app.elixir.brewz_rock_cafe.numberPicker.NumberPicker;
import com.app.elixir.brewz_rock_cafe.pojo.PojoArray;
import com.app.elixir.brewz_rock_cafe.pojo.PojoItemDetail;
import com.app.elixir.brewz_rock_cafe.pojo.PojoItems;
import com.app.elixir.brewz_rock_cafe.utils.CM;
import com.app.elixir.brewz_rock_cafe.utils.CONSTANT;
import com.app.elixir.brewz_rock_cafe.utils.URLS;
import com.app.elixir.brewz_rock_cafe.volly.OnVolleyHandler;
import com.app.elixir.brewz_rock_cafe.volly.VolleyIntialization;
import com.app.elixir.brewz_rock_cafe.volly.WebService;
import com.app.elixir.brewz_rock_cafe.volly.WebServiceTag;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class viewActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "viewActivity";
    Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private MtplButton buttonAddToCart, buttonAddOn;
    public static MtplTextView textViewItemDis, textViewItemPrice;
    private ImageView imgCoverImage, imageViewPlaceHolder;
    private NumberPicker numberPicker;
    public static ExpandableListView expListView, expListView1;
    Spinner spinner;
    private LinearLayout linearLayoutExpandList, addOnHeaderLayout;
    private CategoryItemDetailModel model_main;
    private String itemQuantity = "1";
    private String catId = "";
    private String tot = "";
    private String isRequired = "";
    private String header = "";
    private String itemPrice = "";
    private ArrayList<CategoryItemDetailModelArray> categoryItemDetailModelArrayArrayList;
    private ArrayList<PojoItems> pojoCustomizes;
    private ArrayList<PojoItemDetail> pojoItemDetails;
    public ArrayList<PojoItems> pojoItemses;
    public ArrayList<PojoItemDetail> pojoItemDetails1;
    public ArrayList<PojoArray> pojoArrays;
    public ArrayList<String> listDataHeader;
    public HashMap<String, ArrayList<PojoArray>> listDataChild1;
    public static ExpandableListAdapter listAdapter;
    public static ExpandableListAdapterNot expandableListAdapterNot;
    private TextView item;
    private StringBuilder stringBuilder;
    private ProgressDialog mProgressDialog;
    Button btnaddons;
    private LinearLayout linearLayoutAddons, btnAddonsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_scrolling);
        toolbar = (Toolbar) findViewById(R.id.mToolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        Intent intent = getIntent();
        if (intent.getStringExtra("catId") != null) {
            catId = intent.getStringExtra("catId");
        } else {

        }
        toolbar.setTitle("");
        TextView titleTextView = null;
        try {
            Field f = toolbar.getClass().getDeclaredField("mTitleTextView");
            f.setAccessible(true);
            titleTextView = (TextView) f.get(toolbar);
            Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), getString(R.string.fontface_robotolight_0));
            titleTextView.setTypeface(font);
            titleTextView.setTextSize(16);
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CM.finishActivity(viewActivity.this);
                Items.deleteAll();
                ItemDetail.deleteAll();
                CM.setSp(viewActivity.this, "quantity", "");
            }
        });

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.personal_collapsed_title);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.personal_expanded_title);
        final Typeface tf = Typeface.createFromAsset(getAssets(), getString(R.string.fontface_robotolight_0));
        collapsingToolbarLayout.setCollapsedTitleTypeface(tf);
        collapsingToolbarLayout.setExpandedTitleTypeface(tf);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        btnaddons = (Button) findViewById(R.id.btnaddons);
        linearLayoutAddons = (LinearLayout) findViewById(R.id.addonsLayout);
        btnAddonsLayout = (LinearLayout) findViewById(R.id.btnAddonsLayout);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

        buttonAddToCart = (MtplButton) findViewById(R.id.addtoCartbtn);
        buttonAddToCart.setOnClickListener(this);
        btnaddons.setOnClickListener(this);


        initView();


    }


    public void initView() {
        imgCoverImage = (ImageView) findViewById(R.id.cover_pic);
        imgCoverImage.setOnClickListener(this);
        textViewItemDis = (MtplTextView) findViewById(R.id.textViewItemDis);
        numberPicker = (NumberPicker) findViewById(R.id.number_picker);
        numberPicker.setLimitExceededListener(new viewActivity.DefaultLimitExceededListener());
        numberPicker.setMax(Integer.parseInt(CM.getSp(viewActivity.this, "Quant", "").toString()));
        textViewItemPrice = (MtplTextView) findViewById(R.id.textViewItemPrice);
        imageViewPlaceHolder = (ImageView) findViewById(R.id.placeholder);

        addOnHeaderLayout = (LinearLayout) findViewById(R.id.addOnHeader);

        Animation scale = AnimationUtils.loadAnimation(viewActivity.this, R.anim.zoom);
        imageViewPlaceHolder.startAnimation(scale);

        expListView = (ExpandableListView) findViewById(R.id.expListView);
        linearLayoutExpandList = (LinearLayout) findViewById(R.id.layoutexpandList);
        expListView1 = (ExpandableListView) findViewById(R.id.expListView1);

        if (CM.isInternetAvailable(viewActivity.this)) {
            webCallFoodItemDetail(catId);
        } else {
            CM.showToast(viewActivity.this, getString(R.string.internetMsg));
        }

        numberPicker.SetOnItemClickListener(new OnItemClickListener() {
                                                @Override
                                                public void onItemClick(String value) {
                                                    if (model_main != null) {
                                                        itemQuantity = value;
                                                        tot = ItemDetail.getAllCheckeditemRecord();
                                                        if (!CM.getSp(viewActivity.this, "itemPrice", "").toString().equals("")) {
                                                            Double aFloat = (Double.parseDouble(CM.getSp(viewActivity.this, "itemPrice", "").toString()) + Double.parseDouble(tot)) * Double.parseDouble(itemQuantity);
                                                            DecimalFormat form = new DecimalFormat("0.00");
                                                            textViewItemPrice.setText(CM.getSp(viewActivity.this, "currency", "").toString() + String.valueOf(form.format(aFloat)));
                                                            CM.setSp(viewActivity.this, "quantity", value);
                                                        } else {

                                                        }
                                                    }


                                                }
                                            }

        );

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addtoCartbtn:
                ArrayList<PojoItems> pojoItems = ItemDetail.getAlldataNotReruirdwithAddOnRequird();
                if (pojoItems != null && pojoItems.size() > 0) {
                    String addOnPrice = ItemDetail.getAllCheckeditemRecord();
                    ArrayList<PojoItemDetail> allCheckedRecord = ItemDetail.getAllCheckedRecord();
                    ArrayList<PojoItems> pojoItemses = new ArrayList<>();
                    for (int i = 0; i < allCheckedRecord.size(); i++) {
                        ArrayList<PojoItems> data = Items.getAlldataUsingMapperId(allCheckedRecord.get(i).getnMapperID().toString());
                        for (int j = 0; j < data.size(); j++) {
                            PojoItems pojoItemDetail = new PojoItems();
                            pojoItemDetail.setnMapperID(data.get(j).getnMapperID().toString());
                            pojoItemDetail.setnAttributeID(data.get(j).getnAttributeID().toString());
                            pojoItemDetail.setcAttributeLabel(data.get(j).getcAttributeLabel().toString());
                            pojoItemDetail.setIsMultiple(data.get(j).getIsMultiple().toString());
                            pojoItemDetail.setIsRequired(data.get(j).getIsRequired().toString());
                            pojoItemses.add(pojoItemDetail);
                        }
                    }

                    // String sub = "Full (? 125.00)";
                    // String name = "Toppings";


                    // stringBuilder.append("<li id=4>" + sub + " </li>");


                    JSONArray jsonArrayMain = new JSONArray();
                    JSONObject jsonObjectHeader = new JSONObject();
                    stringBuilder = new StringBuilder();
                    for (int k = 0; k < pojoItemses.size(); k++) {
                        try {
                            jsonObjectHeader.put("nMapperID", pojoItemses.get(k).getnMapperID());
                            jsonObjectHeader.put("nAttributeID", pojoItemses.get(k).getnAttributeID());
                            jsonObjectHeader.put("cAttributeLabel", pojoItemses.get(k).getcAttributeLabel());
                            jsonObjectHeader.put("isMultiple", pojoItemses.get(k).getIsMultiple());
                            jsonObjectHeader.put("isRequired", pojoItemses.get(k).getIsRequired());
                            stringBuilder.append("<div id=" + "'StrAddon_" + pojoItemses.get(k).getnAttributeID() + "'" + "style='font-weight: normal !important;'><strong>" + pojoItemses.get(k).getcAttributeLabel() + ":</strong>");
                            ArrayList<PojoItemDetail> data = ItemDetail.getAlldataUsingMapperId(pojoItemses.get(k).getnMapperID().toString());
                            JSONObject jsonObject = new JSONObject();
                            JSONArray jsonArray = new JSONArray();
                            for (int j = 0; j < data.size(); j++) {
                                stringBuilder.append("<ul style='list-style: none; padding: 0px; margin: 5px;'>");
                                if (data.get(j).getnPrice().toString().equals("0")) {
                                    stringBuilder.append("<li id='" + data.get(j).getnMapperDetailID() + "'" + ">" + "+ " + data.get(j).getcAttributeValueLabel().toString() + "</li>");
                                } else {
                                    stringBuilder.append("<li id='" + data.get(j).getnMapperDetailID() + "'" + ">" + "+ " + data.get(j).getcAttributeValueLabel().toString() + "(" + CM.getSp(viewActivity.this, "currency", "").toString() + " " + data.get(j).getnPrice().toString() + ")" + "</li>");
                                }
                                stringBuilder.append("</ul></div>");
                                jsonObject.put("nAttributeID", data.get(j).getnAttributeID().toString());
                                jsonObject.put("cAttributeLabel", "");
                                jsonObject.put("nMapperDetailID", data.get(j).getnMapperDetailID().toString());
                                jsonObject.put("nMapperID", data.get(j).getnMapperID().toString());
                                jsonObject.put("nAttributeValueMasterID", data.get(j).getnAttributeValueMasterID().toString());
                                jsonObject.put("cAttributeValueLabel", data.get(j).getcAttributeValueLabel().toString());
                                if (data.get(j).getnPrice().toString().equals("0")) {
                                    jsonObject.put("nPrice", "");
                                } else {
                                    jsonObject.put("nPrice", data.get(j).getnPrice().toString());
                                }
                                jsonArray.put(jsonObject);
                            }
                            jsonObjectHeader.put("ListProductAttributeMapperDetail", jsonArray);
                            jsonArrayMain.put(jsonObjectHeader);

                        } catch (Exception e) {
                            e.getMessage();
                        }


                    }
                    jsonObjectHeader.length();
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode);
                        jsonObject.put(CONSTANT.CAPIPASS, CONSTANT.cAPIPass);
                        jsonObject.put(CONSTANT.CAPIKEY, CONSTANT.cAPIKey);
                        jsonObject.put(CONSTANT.NROWINDEX, model_main.nRowIndex);
                        if (!CM.getSp(viewActivity.this, "cartId", "").equals("")) {
                            jsonObject.put(CONSTANT.NCARTID, CM.getSp(viewActivity.this, "cartId", "").toString());
                        } else {
                            jsonObject.put(CONSTANT.NCARTID, "0");
                        }
                        jsonObject.put(CONSTANT.NCARTDETAILID, "");
                        jsonObject.put(CONSTANT.NCOMPANYPRODUCTID, model_main.nCompanyProductID);
                        jsonObject.put(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID);
                        jsonObject.put(CONSTANT.NCUSTOMERID, CM.getSp(viewActivity.this, "customerId", "").toString());
                        jsonObject.put(CONSTANT.NSERIALNO, "0");

                        if (CM.getSp(viewActivity.this, "date", "").toString().equals("")) {
                            Date date = Calendar.getInstance().getTime();
                            String currentDate = CM.converDateFormate("EEE MMM dd HH:mm:ss Z yyyy", "yyyy-MM-dd", date.toString());
                            jsonObject.put(CONSTANT.ORDERDATE, currentDate);
                        } else {
                            String currentDate = CM.converDateFormate("dd-MM-yyyy", "yyyy-MM-dd", CM.getSp(viewActivity.this, "date", "").toString());
                            jsonObject.put(CONSTANT.ORDERDATE, currentDate);
                        }


                        if (CM.getSp(viewActivity.this, "time", "").toString().equals("")) {
                            try {
                                Date time = new Date();
                                DateFormat readFormat = new SimpleDateFormat("hh:mm aaa");
                                DateFormat dateFormat = new SimpleDateFormat("hh:mm aaa");
                                DateFormat writeFormat = new SimpleDateFormat("HH:mm:ss");
                                Date currentTime = readFormat.parse(String.valueOf(dateFormat.format(time)));
                                String formattedDate = "";
                                if (currentTime != null) {
                                    formattedDate = writeFormat.format(currentTime);
                                }

                                jsonObject.put(CONSTANT.ORDERTIME, formattedDate);
                            } catch (Exception e) {
                                e.getMessage();
                            }

                        } else {
                            try {
                                DateFormat readFormat = new SimpleDateFormat("hh:mm aaa");
                                DateFormat writeFormat = new SimpleDateFormat("HH:mm:ss");
                                Date date = readFormat.parse(CM.getSp(viewActivity.this, "time", "").toString());
                                String formattedDate = "";
                                if (date != null) {
                                    formattedDate = writeFormat.format(date);
                                }
                                jsonObject.put(CONSTANT.ORDERTIME, formattedDate);

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        jsonObject.put(CONSTANT.CADDONS, stringBuilder.toString());
                        jsonObject.put(CONSTANT.NPRODUCTID, model_main.nProductID);
                        jsonObject.put(CONSTANT.CPRODUCTNAME, model_main.cProductName);
                        jsonObject.put(CONSTANT.CPRODUCTDESC, model_main.cProductDesc);
                        jsonObject.put(CONSTANT.CPRODUCTIMAGEPATH, model_main.cProductImagePath);
                        jsonObject.put(CONSTANT.CCATEGORYNAME, model_main.cCategoryName);
                        jsonObject.put(CONSTANT.NPIECES, model_main.nPieces);
                        jsonObject.put(CONSTANT.NPRODUCTCATEGORYID, model_main.nProductCategoryID);
                        if (!CM.getSp(viewActivity.this, "quantity", "").toString().equals("")) {
                            jsonObject.put(CONSTANT.QTY, CM.getSp(viewActivity.this, "quantity", "").toString());
                        } else {
                            jsonObject.put(CONSTANT.QTY, "1");
                        }
                        jsonObject.put(CONSTANT.NRATE, model_main.nRate);

                        tot = ItemDetail.getAllCheckeditemRecord();
                        if (tot != null && !tot.equals("")) {
                            jsonObject.put(CONSTANT.NADDONTOTALAMT, tot);
                        } else {
                            jsonObject.put(CONSTANT.NADDONTOTALAMT, "");
                        }
                        jsonObject.put(CONSTANT.TOTALAMOUNT, textViewItemPrice.getText().toString().trim());
                        jsonObject.put(CONSTANT.PRODUCTSUBTOTALAMT, model_main.ProductSubTotalAmt);
                        //   jsonObject.put(CONSTANT.CADDONS, model_main.cAddons);
                        jsonObject.put(CONSTANT.CADDONSPRICE, addOnPrice);


                        if (!CM.getSp(viewActivity.this, "DefPmode", "").toString().equals("null") && !CM.getSp(viewActivity.this, "DefPmode", "").toString().equals("")) {
                            jsonObject.put(CONSTANT.NORDERFILLMODE, CM.getSp(viewActivity.this, "DefPmode", "").toString());
                        } else {
                            jsonObject.put(CONSTANT.NORDERFILLMODE, "2");
                        }

                        jsonObject.put("ListProductAttributeMapper", jsonArrayMain);
                        new TestAsync().execute(URLS.POSTCART, jsonObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // webCallAddToCart(pojoItemDetails.get(0).getnAttributeID().toString(), "", spinner.getSelectedItem().toString(), itemQuantity, textViewItemPrice.getText().toString(), jsonObject.toString());
                } else {


                    if (categoryItemDetailModelArrayArrayList != null && categoryItemDetailModelArrayArrayList.size() > 0) {
                        CM.showToast(this, "Please Select Required AddOn");
                    } else {

                        JSONObject jsonObject = new JSONObject();
                        try {

                            if (model_main != null) {
                                jsonObject.put(CONSTANT.CAPPSTORECODE, CONSTANT.cAppStoreCode);
                                jsonObject.put(CONSTANT.CAPIPASS, CONSTANT.cAPIPass);
                                jsonObject.put(CONSTANT.CAPIKEY, CONSTANT.cAPIKey);
                                jsonObject.put(CONSTANT.NROWINDEX, model_main.nRowIndex);
                                if (!CM.getSp(viewActivity.this, "cartId", "").equals("")) {
                                    jsonObject.put(CONSTANT.NCARTID, CM.getSp(viewActivity.this, "cartId", "").toString());
                                } else {
                                    jsonObject.put(CONSTANT.NCARTID, "0");
                                }
                                jsonObject.put(CONSTANT.NCARTDETAILID, "");
                                jsonObject.put(CONSTANT.NCOMPANYPRODUCTID, model_main.nCompanyProductID);
                                jsonObject.put(CONSTANT.NCOMPANYID, CONSTANT.nCompanyID);
                                jsonObject.put(CONSTANT.NCUSTOMERID, CM.getSp(viewActivity.this, "customerId", "").toString());
                                jsonObject.put(CONSTANT.NSERIALNO, "0");
                                if (CM.getSp(viewActivity.this, "date", "").toString().equals("")) {
                                    Date date = Calendar.getInstance().getTime();
                                    String currentDate = CM.converDateFormate("EEE MMM dd HH:mm:ss Z yyyy", "yyyy-MM-dd", date.toString());
                                    jsonObject.put(CONSTANT.ORDERDATE, currentDate);
                                } else {
                                    String currentDate = CM.converDateFormate("dd-MM-yyyy", "yyyy-MM-dd", CM.getSp(viewActivity.this, "date", "").toString());
                                    jsonObject.put(CONSTANT.ORDERDATE, currentDate);
                                }


                                if (CM.getSp(viewActivity.this, "time", "").toString().equals("")) {
                                    try {
                                        Date time = new Date();
                                        DateFormat readFormat = new SimpleDateFormat("hh:mm aaa");
                                        DateFormat dateFormat = new SimpleDateFormat("hh:mm aaa");
                                        DateFormat writeFormat = new SimpleDateFormat("HH:mm:ss");
                                        Date currentTime = readFormat.parse(String.valueOf(dateFormat.format(time)));
                                        String formattedDate = "";
                                        if (currentTime != null) {
                                            formattedDate = writeFormat.format(currentTime);
                                        }

                                        jsonObject.put(CONSTANT.ORDERTIME, formattedDate);
                                    } catch (Exception e) {
                                    }

                                } else {
                                    try {
                                        DateFormat readFormat = new SimpleDateFormat("hh:mm aaa");
                                        DateFormat writeFormat = new SimpleDateFormat("HH:mm:ss");
                                        Date date = readFormat.parse(CM.getSp(viewActivity.this, "time", "").toString());
                                        String formattedDate = "";
                                        if (date != null) {
                                            formattedDate = writeFormat.format(date);
                                        }

                                        jsonObject.put(CONSTANT.ORDERTIME, formattedDate);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                }
                                jsonObject.put(CONSTANT.CADDONS, "");
                                jsonObject.put(CONSTANT.NPRODUCTID, model_main.nProductID);
                                jsonObject.put(CONSTANT.CPRODUCTNAME, model_main.cProductName);
                                jsonObject.put(CONSTANT.CPRODUCTDESC, model_main.cProductDesc);
                                jsonObject.put(CONSTANT.CPRODUCTIMAGEPATH, model_main.cProductImagePath);
                                jsonObject.put(CONSTANT.CCATEGORYNAME, model_main.cCategoryName);
                                jsonObject.put(CONSTANT.NPIECES, model_main.nPieces);
                                jsonObject.put(CONSTANT.NPRODUCTCATEGORYID, model_main.nProductCategoryID);

                                if (!CM.getSp(viewActivity.this, "quantity", "").toString().equals("")) {
                                    jsonObject.put(CONSTANT.QTY, CM.getSp(viewActivity.this, "quantity", "").toString());
                                } else {
                                    jsonObject.put(CONSTANT.QTY, "1");
                                }
                                jsonObject.put(CONSTANT.NRATE, model_main.nRate);

                                tot = ItemDetail.getAllCheckeditemRecord();
                                if (tot != null && !tot.equals("")) {
                                    jsonObject.put(CONSTANT.NADDONTOTALAMT, tot);
                                } else {
                                    jsonObject.put(CONSTANT.NADDONTOTALAMT, "");
                                }
                                jsonObject.put(CONSTANT.TOTALAMOUNT, textViewItemPrice.getText().toString().trim());
                                jsonObject.put(CONSTANT.PRODUCTSUBTOTALAMT, model_main.ProductSubTotalAmt);
                                jsonObject.put(CONSTANT.CADDONS, model_main.cAddons);
                                jsonObject.put(CONSTANT.CADDONSPRICE, "0");

                                if (!CM.getSp(viewActivity.this, "DefPmode", "").toString().equals("null") && !CM.getSp(viewActivity.this, "DefPmode", "").toString().equals("")) {
                                    jsonObject.put(CONSTANT.NORDERFILLMODE, CM.getSp(viewActivity.this, "DefPmode", "").toString());
                                } else {
                                    jsonObject.put(CONSTANT.NORDERFILLMODE, "2");
                                }

                                JSONArray jsonArray = new JSONArray();
                                jsonObject.put("ListProductAttributeMapper", jsonArray);
                                new TestAsync().execute(URLS.POSTCART, jsonObject.toString());

                            } else {
                                CM.showToast(viewActivity.this, getString(R.string.cna));

                            }
                        } catch (Exception e) {

                        }
                    }
                }
                break;
            case R.id.btnaddons:
                Intent i = new Intent(viewActivity.this, ViewCustomize.class);
                CM.startActivityForResult(i, 100, viewActivity.this);
                break;
        }
    }


    public void webCallFoodItemDetail(String catId) {
        try {
            VolleyIntialization v = new VolleyIntialization(viewActivity.this, true, true);
            WebService.getResendForCategoryItemDetail(v, catId, new OnVolleyHandler() {
                @Override
                public void onVollySuccess(String response) {
                    if (isFinishing()) {
                        return;
                    }
                    MtplLog.i("WebCalls", response);
                    Log.e(TAG, response);
                    getResponseForCouponCode(response);

                }

                @Override
                public void onVollyError(String error) {
                    MtplLog.i("WebCalls", error);
                    if (CM.isInternetAvailable(viewActivity.this)) {
                        CM.showPopupCommonValidation(viewActivity.this, error, false);
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void getResponseForCouponCode(String response) {
        String strResponseStatus = CM.getValueFromJson(WebServiceTag.WEB_STATUS, response);
        if (strResponseStatus.equalsIgnoreCase(WebServiceTag.WEB_STATUSFAIL)) {
            CM.showPopupCommonValidation(viewActivity.this, CM.getValueFromJson(WebServiceTag.WEB_STATUS_ERRORTEXT, response), false);
            return;
        }
        try {

            JSONObject jsonObject = new JSONObject(response);


            if (jsonObject.getString("ResponseCode") != null && jsonObject.getString("ResponseCode").equals("202")) {
                JSONObject jsonObject1 = new JSONObject(jsonObject.getString("ResponseObject"));
                if (!jsonObject1.getString("product").toString().equals("null")) {

                    model_main = CM.JsonParse(new CategoryItemDetailModel(), jsonObject1.getString("product"));
                    if (categoryItemDetailModelArrayArrayList != null) {
                        categoryItemDetailModelArrayArrayList.clear();
                    }
                    categoryItemDetailModelArrayArrayList = model_main.categoryItemDetailModelArrays;
                    categoryItemDetailModelArrayArrayList.size();
                    try {

                        if (model_main.nRate != null) {
                            CM.setSp(viewActivity.this, "itemPrice", model_main.nRate);
                        } else {
                            CM.setSp(viewActivity.this, "itemPrice", "0.00");
                        }

                        toolbar.setTitle(model_main.cProductName);
                        collapsingToolbarLayout.setTitle(model_main.cProductName);
                        textViewItemDis.setText(model_main.cProductDesc.toString());
                        DecimalFormat form = new DecimalFormat("0.00");
                        textViewItemPrice.setText(CM.getSp(viewActivity.this, "currency", "").toString() + form.format(Double.parseDouble(model_main.nRate)));
                        Glide.with(viewActivity.this)
                                .load(model_main.cProductImagePath)
                                .listener(new RequestListener<String, GlideDrawable>() {
                                    @Override
                                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                        imageViewPlaceHolder.setVisibility(View.VISIBLE);
                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                        imageViewPlaceHolder.setVisibility(View.GONE);
                                        return false;
                                    }
                                }).error(R.drawable.shortplace).into(imgCoverImage);
                        pojoCustomizes = new ArrayList<>();
                        pojoCustomizes.clear();
                        pojoItemDetails = new ArrayList<>();
                        pojoItemDetails.clear();
                        String isMulti;
                        for (int i = 0; i < categoryItemDetailModelArrayArrayList.size(); i++) {
                            PojoItems pojoCustomize = new PojoItems();
                            pojoCustomize.setnMapperID(categoryItemDetailModelArrayArrayList.get(i).nMapperID);
                            pojoCustomize.setnAttributeID(categoryItemDetailModelArrayArrayList.get(i).nAttributeID);
                            pojoCustomize.setcAttributeLabel(categoryItemDetailModelArrayArrayList.get(i).cAttributeLabel);
                            pojoCustomize.setIsMultiple(categoryItemDetailModelArrayArrayList.get(i).isMultiple);
                            pojoCustomize.setIsRequired(categoryItemDetailModelArrayArrayList.get(i).isRequired);
                            isMulti = categoryItemDetailModelArrayArrayList.get(i).isMultiple;
                            isRequired = categoryItemDetailModelArrayArrayList.get(i).isRequired;
                            header = categoryItemDetailModelArrayArrayList.get(i).cAttributeLabel;
                            pojoCustomizes.add(pojoCustomize);
                            for (int j = 0; j < categoryItemDetailModelArrayArrayList.get(i).categoryItemDetailModelSubArrays.size(); j++) {
                                PojoItemDetail pojoItemDetail = new PojoItemDetail();
                                pojoItemDetail.setnAttributeID(categoryItemDetailModelArrayArrayList.get(i).categoryItemDetailModelSubArrays.get(j).nAttributeID);
                                pojoItemDetail.setcAttributeLabel(categoryItemDetailModelArrayArrayList.get(i).categoryItemDetailModelSubArrays.get(j).cAttributeLabel);
                                pojoItemDetail.setnMapperDetailID(categoryItemDetailModelArrayArrayList.get(i).categoryItemDetailModelSubArrays.get(j).nMapperDetailID);
                                pojoItemDetail.setnMapperID(categoryItemDetailModelArrayArrayList.get(i).categoryItemDetailModelSubArrays.get(j).nMapperID);
                                pojoItemDetail.setnAttributeValueMasterID(categoryItemDetailModelArrayArrayList.get(i).categoryItemDetailModelSubArrays.get(j).nAttributeValueMasterID);
                                pojoItemDetail.setcAttributeValueLabel(categoryItemDetailModelArrayArrayList.get(i).categoryItemDetailModelSubArrays.get(j).cAttributeValueLabel);
                                pojoItemDetail.setnPrice(categoryItemDetailModelArrayArrayList.get(i).categoryItemDetailModelSubArrays.get(j).nPrice);
                                pojoItemDetail.setIsChecked("0");
                                pojoItemDetail.setIsMulti(isMulti);
                                pojoItemDetail.setIsRequired(isRequired);
                                pojoItemDetail.setHeader(header);
                                pojoItemDetails.add(pojoItemDetail);
                            }

                        }

                        if (categoryItemDetailModelArrayArrayList.size() > 1) {
                            itemPrice = pojoItemDetails.get(0).getnPrice();
                            addOnHeaderLayout.setVisibility(View.VISIBLE);


                        } else {

                            if (categoryItemDetailModelArrayArrayList.size() >= 2) {
                                linearLayoutAddons.setVisibility(View.VISIBLE);
                                btnAddonsLayout.setVisibility(View.GONE);
                                //  LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linearLayoutExpandList.getLayoutParams();
                                // int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250, getResources().getDisplayMetrics());
                                //params.height = height;
                                //    linearLayoutExpandList.setLayoutParams(params);
                            } else if (categoryItemDetailModelArrayArrayList.size() == 0) {
                                linearLayoutAddons.setVisibility(View.GONE);
                                btnAddonsLayout.setVisibility(View.GONE);
                            }
//                            // addOnHeaderLayout.setVisibility(View.GONE);
                            itemPrice = "0.0";
                        }
//                        CustomSpinnerAdapterAddOn mySpinnerArrayAdapter = new CustomSpinnerAdapterAddOn(viewActivity.this, pojoItemDetails);
//                        spinner.setAdapter(mySpinnerArrayAdapter);
//                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                            @Override
//                            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                                itemPrice = pojoItemDetails.get(position).getnPrice();
//                                Double aFloat = Double.parseDouble(itemPrice) * Double.parseDouble(itemQuantity);
//                                DecimalFormat form = new DecimalFormat("0.00");
//                                textViewItemPrice.setText("Rs. " + String.valueOf(form.format(aFloat)));
//                            }
//
//                            @Override
//                            public void onNothingSelected(AdapterView<?> parentView) {
//                                // your code here
//                            }
//
//                        });
                        try {
                            Items.Insert(pojoCustomizes);
                            ItemDetail.Insert(pojoItemDetails);
                        } catch (Exception e) {
                            Log.e("dbError", String.valueOf(e.getMessage()));
                        }
                        prepareListDataForRequiredAddOn();
                        prepareListDataForNotRequiredAddOn();

                    } catch (Exception e) {
                        CM.showPopupCommonValidation(viewActivity.this, e.getMessage(), false);
                    }
                } else {
                    addOnHeaderLayout.setVisibility(View.GONE);
                    CM.showToast(viewActivity.this, "No Date Found");
                }
            } else {
                if (jsonObject.getString("ResponseObject").equals("null")) {
                    CM.showToast(viewActivity.this, "No Data Found");
                } else {
                    CM.showToast(viewActivity.this, jsonObject.getString("ResponseObject"));
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public class DefaultLimitExceededListener implements LimitExceededListener {

        public void limitExceeded(int limit, int exceededValue) {

            String message = String.format("Value cannot be setted to %d because the limit is %d.", limit, exceededValue);
            Log.v(this.getClass().getSimpleName(), message);
        }

    }

    private void prepareListDataForRequiredAddOn() {
        listDataHeader = new ArrayList<String>();
        listDataChild1 = new HashMap<>();
        listDataHeader.clear();
        listDataChild1.clear();

        if (pojoItemses != null && pojoItemses.size() > 0) {
            pojoItemses.clear();
        }
        pojoItemses = Items.getAlldata();
        if (pojoItemses != null) {
            for (int j = 0; j < pojoItemses.size(); j++) {
                listDataHeader.add(pojoItemses.get(j).getcAttributeLabel());
                pojoItemDetails1 = ItemDetail.getSelectedIdRecord(pojoItemses.get(j).getnMapperID());
                pojoArrays = new ArrayList<>();
                pojoArrays.clear();
                for (int i = 0; i < pojoItemDetails1.size(); i++) {
                    PojoArray pojoArray = new PojoArray();
                    pojoArray.setnAttributeID(pojoItemDetails1.get(i).getnAttributeID());
                    pojoArray.setcAttributeLabel(pojoItemDetails1.get(i).getcAttributeLabel());
                    pojoArray.setnMapperDetailID(pojoItemDetails1.get(i).getnMapperDetailID());
                    pojoArray.setnMapperID(pojoItemDetails1.get(i).getnMapperID());
                    pojoArray.setcAttributeValueLabel(pojoItemDetails1.get(i).getcAttributeValueLabel());
                    pojoArray.setnAttributeValueMasterID(pojoItemDetails1.get(i).getnAttributeValueMasterID());
                    pojoArray.setnPrice(pojoItemDetails1.get(i).getnPrice());
                    pojoArray.setIsChecked(pojoItemDetails1.get(i).getIsChecked());
                    pojoArray.setIsMulti(pojoItemDetails1.get(i).getIsMulti());
                    pojoArray.setHeader(pojoItemDetails1.get(i).getHeader());
                    pojoArrays.add(pojoArray);
                }
                listDataChild1.put(pojoItemses.get(j).getcAttributeLabel(), pojoArrays);
            }
            if (listAdapter != null) {
                listAdapter = null;
            }

            listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild1);
            expListView.setAdapter(listAdapter);
            expListView.expandGroup(0);
            expListView.invalidateViews();
            listAdapter.notifyDataSetChanged();
            item = (TextView) findViewById(R.id.textViewGrandTotal);
            String tot = ItemDetail.getAllCheckeditemRecord();
            try {
                double d = (Double.parseDouble(CM.getSp(viewActivity.this, "itemPrice", "").toString()) + (Double.parseDouble(tot)));
                item.setText("Total " + CM.getSp(viewActivity.this, "currency", "").toString() + " " + String.valueOf(d));
            } catch (Exception e) {

            }
        }
    }

    private void prepareListDataForNotRequiredAddOn() {
        listDataHeader = new ArrayList<String>();
        listDataChild1 = new HashMap<>();
        listDataHeader.clear();
        listDataChild1.clear();

        if (pojoItemses != null && pojoItemses.size() > 0) {
            pojoItemses.clear();
        }
        pojoItemses = Items.getAlldataNotReruird();
        if (pojoItemses != null) {
            for (int j = 0; j < pojoItemses.size(); j++) {
                listDataHeader.add(pojoItemses.get(j).getcAttributeLabel());
                pojoItemDetails1 = ItemDetail.getSelectedIdRecord(pojoItemses.get(j).getnMapperID());
                pojoArrays = new ArrayList<>();
                pojoArrays.clear();
                for (int i = 0; i < pojoItemDetails1.size(); i++) {
                    PojoArray pojoArray = new PojoArray();
                    pojoArray.setnAttributeID(pojoItemDetails1.get(i).getnAttributeID());
                    pojoArray.setcAttributeLabel(pojoItemDetails1.get(i).getcAttributeLabel());
                    pojoArray.setnMapperDetailID(pojoItemDetails1.get(i).getnMapperDetailID());
                    pojoArray.setnMapperID(pojoItemDetails1.get(i).getnMapperID());
                    pojoArray.setcAttributeValueLabel(pojoItemDetails1.get(i).getcAttributeValueLabel());
                    pojoArray.setnAttributeValueMasterID(pojoItemDetails1.get(i).getnAttributeValueMasterID());
                    pojoArray.setnPrice(pojoItemDetails1.get(i).getnPrice());
                    pojoArray.setIsChecked(pojoItemDetails1.get(i).getIsChecked());
                    pojoArray.setIsMulti(pojoItemDetails1.get(i).getIsMulti());
                    pojoArray.setHeader(pojoItemDetails1.get(i).getHeader());
                    pojoArrays.add(pojoArray);
                }
                listDataChild1.put(pojoItemses.get(j).getcAttributeLabel(), pojoArrays);
            }
            expandableListAdapterNot = new ExpandableListAdapterNot(this, listDataHeader, listDataChild1);
            expListView1.setAdapter(expandableListAdapterNot);
            expListView1.expandGroup(0);

            item = (TextView) findViewById(R.id.textViewGrandTotal);
            String tot = ItemDetail.getAllCheckeditemRecord();
            try {
                double d = (double) Float.parseFloat(tot);
                item.setText("Total " + CM.getSp(viewActivity.this, "currency", "").toString() + " " + String.valueOf(d));

            } catch (Exception e) {

            }
        }
    }

    class TestAsync extends AsyncTask<String, Integer, String> {
        String TAG = getClass().getSimpleName();
        private String result;

        protected void onPreExecute() {
            mProgressDialog = new ProgressDialog(viewActivity.this);
            mProgressDialog.setMessage("Please wait...");
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();
        }

        protected String doInBackground(String... strings) {
            InputStream inputStream = null;


            try {
                // 1. create HttpClient
                HttpClient httpclient = new DefaultHttpClient();
                // 2. make POST request to the given URL
                HttpPost httpPost = new HttpPost(strings[0]);
                String json = "";

                JSONObject jsonObject1 = new JSONObject(strings[1]);
                json = jsonObject1.toString();
                String jsons = json.toString().replace("\\", "");
                StringEntity se = new StringEntity(json);
                // 6. set httpPost Entity
                httpPost.setEntity(se);
                // 7. Set some headers to inform server about the type of the content
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
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


            return result;
        }


        protected void onPostExecute(String result) {
            mProgressDialog.dismiss();
            if (result != null) {
                try {
                    JSONObject jsonObject1 = new JSONObject(result.toString());
                    String responseCode = jsonObject1.getString("ResponseCode");
                    if (responseCode.equals("202")) {

                        CM.setSp(viewActivity.this, "quantity", "");
                        CartItem model_main = CM.JsonParse(new CartItem(), jsonObject1.getString("ResponseObject").toString());
                        if (CM.getSp(viewActivity.this, "cartId", "").equals("")) {
                            CM.setSp(viewActivity.this, "cartId", model_main.nCartID.toString());
                        }
                        CM.setSp(viewActivity.this, "cartItem", model_main.nCartItemCount.toString());
                        CM.showToast(viewActivity.this, "Item Added to cart");
                        Intent intent = viewActivity.this.getIntent();
                        intent.putExtra("count", model_main.nCartItemCount.toString());
                        viewActivity.this.setResult(RESULT_OK, intent);
                        finish();
                        Items.deleteAll();
                        ItemDetail.deleteAll();
                    } else {
                        CM.showToast(viewActivity.this, jsonObject1.getString("ResponseObject").toString());
                        Log.e("error", jsonObject1.getString("ResponseObject").toString());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                CM.showToast(viewActivity.this, getString(R.string.serverNotWork));
            }

        }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Items.deleteAll();
        ItemDetail.deleteAll();
        CM.setSp(viewActivity.this, "quantity", "");
        CM.finishActivity(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK) {
                tot = ItemDetail.getAllCheckeditemRecord();
                if (!CM.getSp(viewActivity.this, "itemPrice", "").toString().equals("")) {
                    Double aFloat = (Double.parseDouble(CM.getSp(viewActivity.this, "itemPrice", "").toString()) + Double.parseDouble(tot)) * Double.parseDouble(itemQuantity);
                    DecimalFormat form = new DecimalFormat("0.00");
                    textViewItemPrice.setText(CM.getSp(viewActivity.this, "currency", "").toString() + String.valueOf(form.format(aFloat)));
                    CM.setSp(viewActivity.this, "quantity", itemQuantity);
                } else {

                }

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult
}
