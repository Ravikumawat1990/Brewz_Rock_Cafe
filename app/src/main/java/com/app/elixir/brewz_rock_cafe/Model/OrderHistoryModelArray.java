package com.app.elixir.brewz_rock_cafe.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NetSupport on 14-11-2016.
 */
public class OrderHistoryModelArray {

    @SerializedName("nRowIndex")
    public String nRowIndex;
    @SerializedName("nCompanyProductID")
    public String nCompanyProductID;
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
    @SerializedName("nAddonTotalAmt")
    public String nAddonTotalAmt;
    @SerializedName("nSubTotalAmt")
    public String nSubTotalAmt;
    @SerializedName("ProductTaxAmt")
    public String ProductTaxAmt;
    @SerializedName("nTotalIncTax")
    public String nTotalIncTax;
    @SerializedName("ProductTaxPer")
    public String ProductTaxPer;


}
