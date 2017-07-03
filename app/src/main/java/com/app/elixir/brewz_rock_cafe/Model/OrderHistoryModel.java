package com.app.elixir.brewz_rock_cafe.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by NetSupport on 14-11-2016.
 */
public class OrderHistoryModel {

    @SerializedName("nAmount")
    public String nAmount;
    @SerializedName("dOrderDate")
    public String dOrderDate;
    @SerializedName("dOrderTime")
    public String dOrderTime;
    @SerializedName("cartItems")
    public ArrayList<OrderHistoryModelArray> orderHistoryModelArrays = new ArrayList<OrderHistoryModelArray>();

}
