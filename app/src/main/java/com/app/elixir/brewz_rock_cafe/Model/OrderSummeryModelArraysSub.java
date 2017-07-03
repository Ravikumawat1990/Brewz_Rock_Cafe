package com.app.elixir.brewz_rock_cafe.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NetSupport on 08-10-2016.
 */
public class OrderSummeryModelArraysSub {


    @SerializedName("cProductName")
    public String cProductName;
    @SerializedName("nCartDetailID")
    public String nCartDetailID;
    @SerializedName("nCartID")
    public String nCartID;
    @SerializedName("nCompanyProductID")
    public String nCompanyProductID;
    @SerializedName("nProductCategoryID")
    public String nProductCategoryID;
    @SerializedName("Qty")
    public String Qty;
    @SerializedName("nRate")
    public String nRate;
    @SerializedName("nAddonTotalAmt")
    public String nAddonTotalAmt;
    @SerializedName("ProductTaxAmt")
    public String ProductTaxAmt;
    @SerializedName("ProductSubTotalAmt")
    public String ProductSubTotalAmt;
    @SerializedName("ProductTaxPer")
    public String ProductTaxPer;
    @SerializedName("cProductImagePath")
    public String cProductImagePath;
    @SerializedName("ProductTotalAmount")
    public String ProductTotalAmount;
    @SerializedName("ProductTotalIncTax")
    public String ProductTotalIncTax;
    //@SerializedName("ProductAssignedAddonsJSON")
    //public ArrayList<AddOnModelArray> addOnModelArrays = new ArrayList<AddOnModelArray>();
}
