package com.app.elixir.brewz_rock_cafe.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by NetSupport on 02-09-2016.
 */
public class OrderSummeryModelArray {

    @SerializedName("nCartItemCount")
    public String nCartItemCount;
    @SerializedName("SubTotalAmount")
    public String SubTotalAmount;
    @SerializedName("TotalAmount")
    public String TotalAmount;
    @SerializedName("dOrderDate")
    public String dOrderDate;
    @SerializedName("dOrderTime")
    public String dOrderTime;
    @SerializedName("nDiscountValue")
    public String nDiscountValue;
    @SerializedName("nDiscountAmt")
    public String nDiscountAmt;
    @SerializedName("cCategoryName")
    public String cCategoryName;
    @SerializedName("bIsDiscountApplied")
    public String bIsDiscountApplied;
    @SerializedName("ProductTotalIncTax")
    public String ProductTotalIncTax;
    @SerializedName("Tax")
    public String Tax;
    @SerializedName("nDeliveryCharge")
    public String nDeliveryCharge;
    @SerializedName("cOrderPaymentStatus")
    public String cOrderPaymentStatus;
    @SerializedName("ProductTotalAmount")
    public String ProductTotalAmount;
    @SerializedName("cartItems")
    public ArrayList<OrderSummeryModelArraysSub> orderSummeryModelArraysSubs = new ArrayList<OrderSummeryModelArraysSub>();


}