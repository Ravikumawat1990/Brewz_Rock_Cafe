package com.app.elixir.brewz_rock_cafe.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by NetSupport on 10-10-2016.
 */
public class OrderDeliveryDetailArray {

    @SerializedName("nOrderID")
    public String nOrderID;
    @SerializedName("cOrderNo")
    public String cOrderNo;
    @SerializedName("dOrderDate")
    public String dOrderDate;
    @SerializedName("dOrderTime")
    public String dOrderTime;
    @SerializedName("nAmount")
    public String nAmount;
    @SerializedName("nOrderStatusID")
    public String nOrderStatusID;
    @SerializedName("cOrderStatus")
    public String cOrderStatus;
    @SerializedName("tblOrderdetail")
    public ArrayList<OrderDeliveryDetailArraySub> orderDeliveryDetailArraySubs = new ArrayList<OrderDeliveryDetailArraySub>();


}
