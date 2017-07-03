package com.app.elixir.brewz_rock_cafe.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.app.elixir.brewz_rock_cafe.Model.AddressModel;
import com.app.elixir.brewz_rock_cafe.Model.EntryItem;
import com.app.elixir.brewz_rock_cafe.Model.Item;
import com.app.elixir.brewz_rock_cafe.Model.OrderSummeryModel;
import com.app.elixir.brewz_rock_cafe.Model.SectionItem;
import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.adapter.ExpandableListAdapterOrderHistory;
import com.app.elixir.brewz_rock_cafe.adapter.adptOrderHistory;
import com.app.elixir.brewz_rock_cafe.interfac.ActionBarTitleSetter;
import com.app.elixir.brewz_rock_cafe.interfac.OnFragmentInteractionListener;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplLog;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplTextView;
import com.app.elixir.brewz_rock_cafe.pojo.OrderSummeryPojo;
import com.app.elixir.brewz_rock_cafe.pojo.OrderSummeryPojoArray;
import com.app.elixir.brewz_rock_cafe.pojo.OrderSummeryPojoArraySub;
import com.app.elixir.brewz_rock_cafe.pojo.OrderSummeryPojoArraySubSub;
import com.app.elixir.brewz_rock_cafe.pojo.PojoOrderHistory;
import com.app.elixir.brewz_rock_cafe.utils.CM;
import com.app.elixir.brewz_rock_cafe.volly.OnVolleyHandler;
import com.app.elixir.brewz_rock_cafe.volly.VolleyIntialization;
import com.app.elixir.brewz_rock_cafe.volly.WebService;
import com.app.elixir.brewz_rock_cafe.volly.WebServiceTag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Elixir on 09-Aug-2016.
 */
public class FragOrderHistory extends Fragment {

    private static final String TAG = "FragOrderHistory";
    Activity thisActivity;
    private OnFragmentInteractionListener mListener;
    ArrayList<PojoOrderHistory> arrayList;
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private adptOrderHistory mAdapter;
    private LinearLayout noInternetLayout;
    private LinearLayout internetLayout;
    private LinearLayout rootLayout;
    private ProgressDialog mProgressDialog;
    private ArrayList<OrderSummeryPojoArray> orderSummeryPojoArrays;
    ArrayList<Item> items = new ArrayList<Item>();
    private ListView listView;
    private ExpandableListView expListView;
    private ExpandableListAdapterOrderHistory listAdapter;
    private ArrayList<ArrayList<OrderSummeryPojo>> listDataHeader;
    private HashMap<ArrayList<OrderSummeryPojo>, List<OrderSummeryPojoArray>> listDataChild;
    private ImageView imageViewMsg;
    private MtplTextView txtViewMsg;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragorderhistory, container, false);
        thisActivity = getActivity();
        ((ActionBarTitleSetter) thisActivity).setTitle("Order History");
        init(rootView);


        return rootView;
    }


    public void init(View rootView) {
        arrayList = new ArrayList<>();
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
        noInternetLayout = (LinearLayout) rootView.findViewById(R.id.noInternet);
        internetLayout = (LinearLayout) rootView.findViewById(R.id.Internet);
        rootLayout = (LinearLayout) rootView.findViewById(R.id.orderHistLayout);
        listView = (ListView) rootView.findViewById(R.id.listView);
        expListView = (ExpandableListView) rootView.findViewById(R.id.lvExp);
        imageViewMsg = (ImageView) rootView.findViewById(R.id.imageViewMessage);
        txtViewMsg = (MtplTextView) rootView.findViewById(R.id.textViewNoInterNet);
        if (CM.isInternetAvailable(getActivity())) {
            webCallOrderHistory(CM.getSp(thisActivity, "customerId", "").toString());
            noInternetLayout.setVisibility(View.GONE);
            internetLayout.setVisibility(View.VISIBLE);
        } else {
            noInternetLayout.setVisibility(View.VISIBLE);
            internetLayout.setVisibility(View.GONE);
        }


    }


    @Override
    public void onResume() {
        super.onResume();
        mListener.showDrawerToggle(false);
    }


    public void webCallOrderHistory(String cId) {
        try {
            VolleyIntialization v = new VolleyIntialization(thisActivity, true, true);
            WebService.getResponseForOrderHistoryCustomer(v, cId, new OnVolleyHandler() {
                @Override
                public void onVollySuccess(String response) {
                    if (thisActivity.isFinishing()) {
                        return;
                    }
                    MtplLog.i("WebCalls", response);
                    Log.e(TAG, response);
                    getResponseForContactUs(response);

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


    private void getResponseForContactUs(String response) {
        String strResponseStatus = CM.getValueFromJson(WebServiceTag.WEB_STATUS, response);
        if (strResponseStatus.equalsIgnoreCase(WebServiceTag.WEB_STATUSFAIL)) {
            CM.showPopupCommonValidation(thisActivity, CM.getValueFromJson(WebServiceTag.WEB_STATUS_ERRORTEXT, response), false);
            return;
        }
        try {
            JSONObject jsonObject = new JSONObject(response);
            String responseCode = jsonObject.getString("ResponseCode");
            if (responseCode.equals("202")) {
                OrderSummeryModel model_main = CM.JsonParse(new OrderSummeryModel(), jsonObject.getString("ResponseObject"));
                JSONObject jsonObject1 = new JSONObject(jsonObject.getString("ResponseObject"));
                JSONArray jsonArray = new JSONArray(jsonObject1.optString("data"));
                ArrayList<OrderSummeryPojo> orderSummeryPojos = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    OrderSummeryPojo orderSummeryPojo = new OrderSummeryPojo();
                    orderSummeryPojo.setdOrderDate(jsonArray.getJSONObject(i).optString("dOrderDate"));
                    orderSummeryPojo.setdOrderTime(jsonArray.getJSONObject(i).optString("dOrderTime"));
                    orderSummeryPojo.setnCartItemCount(jsonArray.getJSONObject(i).optString("nCartItemCount"));
                    orderSummeryPojo.setSubTotalAmount(jsonArray.getJSONObject(i).optString("SubTotalAmount"));
                    orderSummeryPojo.setTotalAmount(jsonArray.getJSONObject(i).optString("nTotalAmt"));
                    orderSummeryPojo.setcOrderNo(jsonArray.getJSONObject(i).optString("cOrderNo"));
                    orderSummeryPojo.setcOrderPaymentStatus(jsonArray.getJSONObject(i).optString("cOrderPaymentStatus"));
                    orderSummeryPojo.setcOrderPaymentMode(jsonArray.getJSONObject(i).optString("cOrderPaymentMode"));
                    orderSummeryPojo.setcOrderStatus(jsonArray.getJSONObject(i).optString("cOrderStatus"));
                    ArrayList<AddressModel> addressModels = new ArrayList<>();
                    AddressModel addressModel = new AddressModel();
                    addressModel.setcCustomerName(jsonArray.getJSONObject(i).optString("cShipCustomerName"));
                    addressModel.setcAddressLine1(jsonArray.getJSONObject(i).optString("cShipAddressLine1"));
                    addressModel.setcAddressLine2(jsonArray.getJSONObject(i).optString("cShipAddressLine2"));
                    addressModel.setcCity(jsonArray.getJSONObject(i).optString("cShipCity"));
                    addressModel.setcState(jsonArray.getJSONObject(i).optString("cShipState"));
                    addressModel.setcCountry(jsonArray.getJSONObject(i).optString("cShipCounty"));
                    addressModel.setcEmail(jsonArray.getJSONObject(i).optString("cShipEmail"));
                    addressModel.setcMobile(jsonArray.getJSONObject(i).optString("cShipMobile"));

                    addressModels.add(addressModel);
                    orderSummeryPojo.setAddressModels(addressModels);
                    JSONArray cartItemArray = new JSONArray(jsonArray.getJSONObject(i).optString("orderedproductdetails"));


                    orderSummeryPojoArrays = new ArrayList<>();
                    for (int j = 0; j < cartItemArray.length(); j++) {
                        OrderSummeryPojoArray orderSummeryPojoArray = new OrderSummeryPojoArray();
                        orderSummeryPojoArray.setQty(cartItemArray.getJSONObject(j).optString("Qty"));
                        orderSummeryPojoArray.setProductTaxAmt(cartItemArray.getJSONObject(j).optString("ProductTotalIncTax"));
                        orderSummeryPojoArray.setcProductImagePath(cartItemArray.getJSONObject(j).optString("cProductImagePath"));
                        orderSummeryPojoArray.setnCompanyProductID(cartItemArray.getJSONObject(j).optString("nCompanyProductID"));
                        orderSummeryPojoArray.setProductTaxAmt(cartItemArray.getJSONObject(j).optString("ProductTaxAmt"));
                        orderSummeryPojoArray.setnAddonTotalAmt(cartItemArray.getJSONObject(j).optString("nAddonTotalAmt"));


                        orderSummeryPojoArray.setnRate(cartItemArray.getJSONObject(j).optString("nRate"));
                        orderSummeryPojoArray.setProductTotalAmount(cartItemArray.getJSONObject(j).optString("nTotalIncTax"));
                        orderSummeryPojoArray.setnCartID(cartItemArray.getJSONObject(j).optString("nCartID"));
                        orderSummeryPojoArray.setcProductName(cartItemArray.getJSONObject(j).optString("cProductName"));
                        orderSummeryPojoArray.setnCartDetailID(cartItemArray.getJSONObject(j).optString("nCartDetailID"));
                        orderSummeryPojoArray.setnDiscountAmt(cartItemArray.getJSONObject(j).optString("nDiscountAmt"));
                        if (!cartItemArray.getJSONObject(j).optString("ProductAssignedAddonsJSON").toString().equals("null")) {
                            JSONArray jsonArray1 = new JSONArray(cartItemArray.getJSONObject(j).optString("ProductAssignedAddonsJSON"));
                            ArrayList<OrderSummeryPojoArraySub> orderSummeryPojoArraySubs = new ArrayList<>();
                            for (int k = 0; k < jsonArray1.length(); k++) {
                                OrderSummeryPojoArraySub orderSummeryPojoArraySub = new OrderSummeryPojoArraySub();
                                orderSummeryPojoArraySub.setnMapperID(jsonArray1.getJSONObject(k).optString("nMapperID"));
                                orderSummeryPojoArraySub.setnAttributeID(jsonArray1.getJSONObject(k).optString("nAttributeID"));
                                orderSummeryPojoArraySub.setcAttributeLabel(jsonArray1.getJSONObject(k).optString("cAttributeLabel"));
                                orderSummeryPojoArraySub.setIsMultiple(jsonArray1.getJSONObject(k).optString("isMultiple"));
                                orderSummeryPojoArraySub.setIsRequired(jsonArray1.getJSONObject(k).optString("isRequired"));

                                ArrayList<OrderSummeryPojoArraySubSub> orderSummeryPojoArraySubSubs = new ArrayList<>();
                                JSONArray jsonArray2 = new JSONArray(jsonArray1.getJSONObject(k).optString("ListProductAttributeMapperDetail"));
                                if (jsonArray1.getJSONObject(k).optString("ListProductAttributeMapperDetail") != null) {
                                    for (int l = 0; l < jsonArray2.length(); l++) {
                                        OrderSummeryPojoArraySubSub orderSummeryPojoArraySubSub = new OrderSummeryPojoArraySubSub();
                                        orderSummeryPojoArraySubSub.setcAttributeLabel(jsonArray2.getJSONObject(l).optString("cAttributeLabel"));
                                        orderSummeryPojoArraySubSub.setnMapperID(jsonArray2.getJSONObject(l).optString("nMapperID"));
                                        orderSummeryPojoArraySubSub.setcAttributeValueLabel(jsonArray2.getJSONObject(l).optString("cAttributeValueLabel"));
                                        orderSummeryPojoArraySubSub.setnAttributeID(jsonArray2.getJSONObject(l).optString("nAttributeID"));
                                        orderSummeryPojoArraySubSub.setnAttributeValueMasterID(jsonArray2.getJSONObject(l).optString("nAttributeValueMasterID"));
                                        orderSummeryPojoArraySubSub.setnMapperDetailID(jsonArray2.getJSONObject(l).optString("nMapperDetailID"));
                                        orderSummeryPojoArraySubSub.setnPrice(jsonArray2.getJSONObject(l).optString("nPrice"));

                                        orderSummeryPojoArraySubSubs.add(orderSummeryPojoArraySubSub);
                                    }
                                    orderSummeryPojoArraySub.setOrderSummeryPojoArraySubSubs(orderSummeryPojoArraySubSubs);
                                }
                                orderSummeryPojoArraySubs.add(orderSummeryPojoArraySub);
                            }
                            orderSummeryPojoArray.setOrderSummeryPojoArraySubs(orderSummeryPojoArraySubs);
                        }
                        orderSummeryPojoArrays.add(orderSummeryPojoArray);

                    }
                    orderSummeryPojo.setOrderSummeryPojoArrays(orderSummeryPojoArrays);

                    orderSummeryPojos.add(orderSummeryPojo);
                    orderSummeryPojos.size();


                }

                if (items != null) {
                    items.clear();
                }

                listDataHeader = new ArrayList<>();
                listDataChild = new HashMap<ArrayList<OrderSummeryPojo>, List<OrderSummeryPojoArray>>();
                for (int k = 0; k < orderSummeryPojos.size(); k++) {
                    ArrayList<OrderSummeryPojo> orderSummeryPojos1 = new ArrayList<>();
                    OrderSummeryPojo orderSummeryPojo = new OrderSummeryPojo();
                    orderSummeryPojo.setTotalAmount(orderSummeryPojos.get(k).getTotalAmount());
                    orderSummeryPojo.setdOrderDate(orderSummeryPojos.get(k).getdOrderDate());
                    orderSummeryPojo.setdOrderTime(orderSummeryPojos.get(k).getdOrderTime());
                    orderSummeryPojo.setSubTotalAmount(orderSummeryPojos.get(k).getSubTotalAmount());
                    orderSummeryPojo.setOrderSummeryPojoArrays(orderSummeryPojos.get(k).getOrderSummeryPojoArrays());
                    orderSummeryPojo.setcOrderPaymentMode(orderSummeryPojos.get(k).getcOrderPaymentMode());
                    orderSummeryPojo.setAddressModels(orderSummeryPojos.get(k).getAddressModels());
                    orderSummeryPojo.setcOrderNo(orderSummeryPojos.get(k).getcOrderNo());
                    orderSummeryPojo.setcOrderPaymentStatus(orderSummeryPojos.get(k).getcOrderPaymentStatus());
                    orderSummeryPojo.setcOrderStatus(orderSummeryPojos.get(k).getcOrderStatus());
                    orderSummeryPojos1.add(orderSummeryPojo);
                    listDataHeader.add(orderSummeryPojos1);
                    items.add(new SectionItem(orderSummeryPojos.get(k).nCartItemCount, orderSummeryPojos.get(k).SubTotalAmount, orderSummeryPojos.get(k).getTotalAmount(), orderSummeryPojos.get(k).dOrderDate, orderSummeryPojos.get(k).dOrderTime, orderSummeryPojos.get(k).dOrderDate, orderSummeryPojos.get(k).getAddressModels(), orderSummeryPojos.get(k).cOrderPaymentStatus, orderSummeryPojos.get(k).getcOrderPaymentMode(), orderSummeryPojos.get(k).getcOrderStatus()));
                    for (int j = 0; j < orderSummeryPojos.get(k).getOrderSummeryPojoArrays().size(); j++) {
                        items.add(new EntryItem(orderSummeryPojos.get(k).getOrderSummeryPojoArrays().get(j).cProductName, orderSummeryPojos.get(k).getOrderSummeryPojoArrays().get(j).nCartDetailID, orderSummeryPojos.get(k).getOrderSummeryPojoArrays().get(j).nCartID, orderSummeryPojos.get(k).getOrderSummeryPojoArrays().get(j).nCompanyProductID, orderSummeryPojos.get(k).getOrderSummeryPojoArrays().get(j).nProductCategoryID, orderSummeryPojos.get(k).getOrderSummeryPojoArrays().get(j).Qty, orderSummeryPojos.get(k).getOrderSummeryPojoArrays().get(j).nRate, orderSummeryPojos.get(k).getOrderSummeryPojoArrays().get(j).nAddonTotalAmt, orderSummeryPojos.get(k).getOrderSummeryPojoArrays().get(j).ProductTaxAmt, orderSummeryPojos.get(k).getOrderSummeryPojoArrays().get(j).ProductSubTotalAmt, orderSummeryPojos.get(k).getOrderSummeryPojoArrays().get(j).ProductTaxPer, orderSummeryPojos.get(k).getOrderSummeryPojoArrays().get(j).cProductImagePath, orderSummeryPojos.get(k).getOrderSummeryPojoArrays().get(j).ProductTotalAmount, orderSummeryPojos.get(k).getOrderSummeryPojoArrays().get(j).ProductTotalIncTax));
                    }

                    listDataChild.put(listDataHeader.get(k), orderSummeryPojos.get(k).getOrderSummeryPojoArrays());
                }
                listAdapter = new ExpandableListAdapterOrderHistory(thisActivity, listDataHeader, listDataChild);
                expListView.setAdapter(listAdapter);

                if (jsonArray.length() == 0) {
                    noInternetLayout.setVisibility(View.VISIBLE);
                    internetLayout.setVisibility(View.GONE);
                    txtViewMsg.setText(getString(R.string.noRecordFound));
                    imageViewMsg.setBackgroundResource(R.drawable.no_records);

                } else {
                    noInternetLayout.setVisibility(View.GONE);
                    internetLayout.setVisibility(View.VISIBLE);
                }

            }


        } catch (Exception e) {
            CM.showPopupCommonValidation(thisActivity, e.getMessage(), false);
        }
    }

    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.8f);
    }
}
