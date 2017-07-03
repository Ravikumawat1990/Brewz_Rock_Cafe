package com.app.elixir.brewz_rock_cafe.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by NetSupport on 10-10-2016.
 */
public class OrderDeliveryDetailArraySub {

    @SerializedName("nCustomerID")
    public String nCustomerID;
    @SerializedName("cCustomerName")
    public String cCustomerName;
    @SerializedName("cMobile")
    public String cMobile;
    @SerializedName("cEmail")
    public String cEmail;
    @SerializedName("nSubTotalAmt")
    public String nSubTotalAmt;
    @SerializedName("nShippingAddressID")
    public String nShippingAddressID;

    @SerializedName("tblorderedproductdetail")
    public ArrayList<OrderDeliveryDetailArraySubSub> orderDeliveryDetailArraySubSubs = new ArrayList<OrderDeliveryDetailArraySubSub>();


}
