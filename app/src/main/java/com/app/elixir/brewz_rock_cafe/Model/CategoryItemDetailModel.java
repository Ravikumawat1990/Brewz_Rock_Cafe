package com.app.elixir.brewz_rock_cafe.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by NetSupport on 31-08-2016.
 */
public class CategoryItemDetailModel {

    @SerializedName("nRowIndex")
    public String nRowIndex;
    @SerializedName("nCompanyProductID")
    public String nCompanyProductID;
    @SerializedName("nProductID")
    public String nProductID;
    @SerializedName("cProductName")
    public String cProductName;
    @SerializedName("cProductDesc")
    public String cProductDesc;
    @SerializedName("cProductImagePath")
    public String cProductImagePath;
    @SerializedName("cCategoryName")
    public String cCategoryName;
    @SerializedName("nPieces")
    public String nPieces;
    @SerializedName("nProductCategoryID")
    public String nProductCategoryID;
    @SerializedName("Qty")
    public String Qty;
    @SerializedName("nRate")
    public String nRate;
    @SerializedName("nAddonTotalAmt")
    public String nAddonTotalAmt;
    @SerializedName("TotalAmount")
    public String TotalAmount;
    @SerializedName("ProductSubTotalAmt")
    public String ProductSubTotalAmt;
    @SerializedName("cAddons")
    public String cAddons;
    @SerializedName("ProductAssignedAddonsJSON")
    public String ProductAssignedAddonsJSON;
    @SerializedName("cSpecialComments")
    public String cSpecialComments;
    @SerializedName("ListProductAttributeMapper")
    public ArrayList<CategoryItemDetailModelArray> categoryItemDetailModelArrays = new ArrayList<CategoryItemDetailModelArray>();


}
