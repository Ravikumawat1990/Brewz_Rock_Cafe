package com.app.elixir.brewz_rock_cafe.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NetSupport on 07-10-2016.
 */
public class CartItem {


    @SerializedName("nCartID")
    public String nCartID;
    @SerializedName("nCompanyID")
    public String nCompanyID;
    @SerializedName("nCustomerID")
    public String nCustomerID;
    @SerializedName("nCouponID")
    public String nCouponID;
    @SerializedName("nOrderFillMode")
    public String nOrderFillMode;
    @SerializedName("nCartItemCount")
    public String nCartItemCount;
    @SerializedName("SubTotalAmount")
    public String SubTotalAmount;
    @SerializedName("SubTotalAfterDiscount")
    public String SubTotalAfterDiscount;
    @SerializedName("nTaxPer")
    public String nTaxPer;
    @SerializedName("nDiscountValue")
    public String nDiscountValue;
    @SerializedName("nDiscountAmt")
    public String nDiscountAmt;
    @SerializedName("nMinOrderAmt")
    public String nMinOrderAmt;
    @SerializedName("nDeliveryCharge")
    public String nDeliveryCharge;
    @SerializedName("TotalAmount")
    public String TotalAmount;
    @SerializedName("bIsDiscountAfterTax")
    public String bIsDiscountAfterTax;
    @SerializedName("bIsDiscountApplied")
    public String bIsDiscountApplied;
    @SerializedName("bAllowFreeDelivery")
    public String bAllowFreeDelivery;
    @SerializedName("TaxType")
    public String TaxType;
    @SerializedName("nDiscountType")
    public String nDiscountType;
    @SerializedName("Tax")
    public String Tax;
    @SerializedName("dDateCreated")
    public String dDateCreated;
    @SerializedName("dDateModified")
    public String dDateModified;
    @SerializedName("cCouponCodeUsed")
    public String cCouponCodeUsed;
    //  @SerializedName("cart")
    // public ArrayList<CartItemArray> cartItemArrays = new ArrayList<CartItemArray>();
}
