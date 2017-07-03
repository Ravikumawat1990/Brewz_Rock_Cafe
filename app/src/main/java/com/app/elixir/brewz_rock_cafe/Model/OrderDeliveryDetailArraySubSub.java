package com.app.elixir.brewz_rock_cafe.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NetSupport on 10-10-2016.
 */
public class OrderDeliveryDetailArraySubSub {
    @SerializedName("cProductName")
    public String cProductName;
    @SerializedName("cProductDesc")
    public String cProductDesc;
    @SerializedName("cProductImagePath")
    public String cProductImagePath;
    @SerializedName("Qty")
    public String Qty;

    @SerializedName("nRate")
    public String nRate;
    @SerializedName("cAddons")
    public String cAddons;
    @SerializedName("nAddonTotalAmt")
    public String nAddonTotalAmt;
    @SerializedName("nSubTotalAmt")
    public String nSubTotalAmt;
    @SerializedName("nTotalIncTax")
    public String nTotalIncTax;
    @SerializedName("bIsDiscountAfterTax")
    public String bIsDiscountAfterTax;
    @SerializedName("bIsDiscountApplied")
    public String bIsDiscountApplied;
    @SerializedName("nDiscountType")
    public String nDiscountType;
    @SerializedName("nDiscountValue")
    public String nDiscountValue;
    @SerializedName("nDiscountAmt")
    public String nDiscountAmt;
    @SerializedName("ProductTaxPer")
    public String ProductTaxPer;
    @SerializedName("ProductTaxAmt")
    public String ProductTaxAmt;
//    public String cProductCategoryName;
//    public ArrayList<catModel> catModels = new ArrayList<catModel>();

}
