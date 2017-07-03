package com.app.elixir.brewz_rock_cafe.Model;


public class EntryItem implements Item {


    public String cProductName;
    public String nCartDetailID;
    public String nCartID;
    public String nCompanyProductID;
    public String nProductCategoryID;
    public String Qty;
    public String nRate;
    public String nAddonTotalAmt;
    public String ProductTaxAmt;
    public String ProductSubTotalAmt;
    public String ProductTaxPer;
    public String cProductImagePath;
    public String ProductTotalAmount;
    public String ProductTotalIncTax;

    public EntryItem(String cProductName, String nCartDetailID, String nCartID, String nCompanyProductID, String nProductCategoryID, String Qty, String nRate, String nAddonTotalAmt, String ProductTaxAmt, String ProductSubTotalAmt, String ProductTaxPer, String cProductImagePath, String ProductTotalAmount, String ProductTotalIncTax) {
        this.cProductName = cProductName;
        this.nCartDetailID = nCartDetailID;
        this.nCartID = nCartID;
        this.nCompanyProductID = nCompanyProductID;
        this.nProductCategoryID = nProductCategoryID;
        this.Qty = Qty;
        this.nRate = nRate;
        this.nAddonTotalAmt = nAddonTotalAmt;
        this.ProductSubTotalAmt = ProductSubTotalAmt;
        this.ProductTaxPer = ProductTaxPer;
        this.cProductImagePath = cProductImagePath;
        this.ProductTotalAmount = ProductTotalAmount;
        this.ProductTaxAmt = ProductTaxAmt;
        this.ProductTotalIncTax = ProductTotalIncTax;
    }

    @Override
    public boolean isSection() {
        return false;
    }

}
