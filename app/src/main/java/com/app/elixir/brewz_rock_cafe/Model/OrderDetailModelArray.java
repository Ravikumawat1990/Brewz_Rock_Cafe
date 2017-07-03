package com.app.elixir.brewz_rock_cafe.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by NetSupport on 08-10-2016.
 */
public class OrderDetailModelArray {

    @SerializedName("cOrderPaymentMode")
    public String cOrderPaymentMode;
    @SerializedName("dOrderTime")
    public String dOrderTime;
    @SerializedName("nAmount")
    public String nAmount;
    @SerializedName("cOrderStatus")
    public String cOrderStatus;
    @SerializedName("cOrderPaymentStatus")
    public String cOrderPaymentStatus;
    @SerializedName("tblorderdetails")
    public ArrayList<OrderDetailModelArraySub> orderDetailModelArraySubs = new ArrayList<OrderDetailModelArraySub>();

}