package com.app.elixir.brewz_rock_cafe.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NetSupport on 08-10-2016.
 */
public class PastOrderModelArray {
    @SerializedName("nOrderID")
    public String nOrderID;
    @SerializedName("cOrderNo")
    public String cOrderNo;
    @SerializedName("cCustomerName")
    public String cCustomerName;

    @SerializedName("dOrderDate")
    public String dOrderDate;
    @SerializedName("dOrderTime")
    public String dOrderTime;


    @SerializedName("nAmount")
    public String nAmount;
    @SerializedName("nShippingAddressID")
    public String nShippingAddressID;
    @SerializedName("cAddress")
    public String cAddress;
    @SerializedName("cMobile")
    public String cMobile;
    @SerializedName("cOrderStatus")
    public String cOrderStatus;


}


