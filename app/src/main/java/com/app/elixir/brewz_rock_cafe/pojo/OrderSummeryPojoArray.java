package com.app.elixir.brewz_rock_cafe.pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by NetSupport on 18-10-2016.
 */


public class OrderSummeryPojoArray implements Serializable {


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
    public String ProductCatName;
    public String nDiscountAmt;
    public String cOrderPaymentMode;

    public String getcOrderPaymentMode() {
        return cOrderPaymentMode;
    }

    public void setcOrderPaymentMode(String cOrderPaymentMode) {
        this.cOrderPaymentMode = cOrderPaymentMode;
    }

    public String getnDiscountAmt() {
        return nDiscountAmt;
    }

    public void setnDiscountAmt(String nDiscountAmt) {
        this.nDiscountAmt = nDiscountAmt;
    }

    public String getProductCatName() {
        return ProductCatName;
    }

    public void setProductCatName(String productCatName) {
        ProductCatName = productCatName;
    }

    ArrayList<OrderSummeryPojoArraySub> orderSummeryPojoArraySubs;

    public ArrayList<OrderSummeryPojoArraySub> getOrderSummeryPojoArraySubs() {
        return orderSummeryPojoArraySubs;
    }

    public void setOrderSummeryPojoArraySubs(ArrayList<OrderSummeryPojoArraySub> orderSummeryPojoArraySubs) {
        this.orderSummeryPojoArraySubs = orderSummeryPojoArraySubs;
    }

    public String getcProductName() {
        return cProductName;
    }

    public void setcProductName(String cProductName) {
        this.cProductName = cProductName;
    }

    public String getnCartDetailID() {
        return nCartDetailID;
    }

    public void setnCartDetailID(String nCartDetailID) {
        this.nCartDetailID = nCartDetailID;
    }

    public String getnCartID() {
        return nCartID;
    }

    public void setnCartID(String nCartID) {
        this.nCartID = nCartID;
    }

    public String getnCompanyProductID() {
        return nCompanyProductID;
    }

    public void setnCompanyProductID(String nCompanyProductID) {
        this.nCompanyProductID = nCompanyProductID;
    }

    public String getnProductCategoryID() {
        return nProductCategoryID;
    }

    public void setnProductCategoryID(String nProductCategoryID) {
        this.nProductCategoryID = nProductCategoryID;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getnRate() {
        return nRate;
    }

    public void setnRate(String nRate) {
        this.nRate = nRate;
    }

    public String getnAddonTotalAmt() {
        return nAddonTotalAmt;
    }

    public void setnAddonTotalAmt(String nAddonTotalAmt) {
        this.nAddonTotalAmt = nAddonTotalAmt;
    }

    public String getProductTaxAmt() {
        return ProductTaxAmt;
    }

    public void setProductTaxAmt(String productTaxAmt) {
        ProductTaxAmt = productTaxAmt;
    }

    public String getProductSubTotalAmt() {
        return ProductSubTotalAmt;
    }

    public void setProductSubTotalAmt(String productSubTotalAmt) {
        ProductSubTotalAmt = productSubTotalAmt;
    }

    public String getProductTaxPer() {
        return ProductTaxPer;
    }

    public void setProductTaxPer(String productTaxPer) {
        ProductTaxPer = productTaxPer;
    }

    public String getcProductImagePath() {
        return cProductImagePath;
    }

    public void setcProductImagePath(String cProductImagePath) {
        this.cProductImagePath = cProductImagePath;
    }

    public String getProductTotalAmount() {
        return ProductTotalAmount;
    }

    public void setProductTotalAmount(String productTotalAmount) {
        ProductTotalAmount = productTotalAmount;
    }

    public String getProductTotalIncTax() {
        return ProductTotalIncTax;
    }

    public void setProductTotalIncTax(String productTotalIncTax) {
        ProductTotalIncTax = productTotalIncTax;
    }
}
