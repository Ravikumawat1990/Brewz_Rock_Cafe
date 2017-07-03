package com.app.elixir.brewz_rock_cafe.pojo;

import com.app.elixir.brewz_rock_cafe.Model.AddressModel;

import java.util.ArrayList;

/**
 * Created by NetSupport on 18-10-2016.
 */
public class OrderSummeryPojo {


    public String nCartItemCount;
    public String SubTotalAmount;
    public String TotalAmount;
    public String dOrderDate;
    public String dOrderTime;

    public String cOrderNo;
    public String bIsDiscountApplied;
    public String ProductTotalIncTax;
    public String Tax;
    public String nDeliveryCharge;
    public String cOrderPaymentStatus;
    public String cOrderPaymentMode;
    public String cOrderStatus;

    public String getcOrderStatus() {
        return cOrderStatus;
    }

    public void setcOrderStatus(String cOrderStatus) {
        this.cOrderStatus = cOrderStatus;
    }

    public String getcOrderPaymentMode() {
        return cOrderPaymentMode;
    }

    public void setcOrderPaymentMode(String cOrderPaymentMode) {
        this.cOrderPaymentMode = cOrderPaymentMode;
    }

    public String getcOrderPaymentStatus() {
        return cOrderPaymentStatus;
    }

    public void setcOrderPaymentStatus(String cOrderPaymentStatus) {
        this.cOrderPaymentStatus = cOrderPaymentStatus;
    }

    public String getnDeliveryCharge() {
        return nDeliveryCharge;
    }

    public void setnDeliveryCharge(String nDeliveryCharge) {
        this.nDeliveryCharge = nDeliveryCharge;
    }

    public String getTax() {
        return Tax;
    }

    public void setTax(String tax) {
        Tax = tax;
    }

    public String getProductTotalIncTax() {
        return ProductTotalIncTax;
    }

    public void setProductTotalIncTax(String productTotalIncTax) {
        ProductTotalIncTax = productTotalIncTax;
    }

    public String getbIsDiscountApplied() {
        return bIsDiscountApplied;
    }

    public void setbIsDiscountApplied(String bIsDiscountApplied) {
        this.bIsDiscountApplied = bIsDiscountApplied;
    }

    ArrayList<AddressModel> addressModels;

    public ArrayList<AddressModel> getAddressModels() {
        return addressModels;
    }

    public void setAddressModels(ArrayList<AddressModel> addressModels) {
        this.addressModels = addressModels;
    }

    public String getcOrderNo() {
        return cOrderNo;
    }

    public void setcOrderNo(String cOrderNo) {
        this.cOrderNo = cOrderNo;
    }

    public ArrayList<OrderSummeryPojoArray> getOrderSummeryPojoArrays() {
        return orderSummeryPojoArrays;
    }

    public void setOrderSummeryPojoArrays(ArrayList<OrderSummeryPojoArray> orderSummeryPojoArrays) {
        this.orderSummeryPojoArrays = orderSummeryPojoArrays;
    }

    ArrayList<OrderSummeryPojoArray> orderSummeryPojoArrays;

    public String getnCartItemCount() {
        return nCartItemCount;
    }

    public void setnCartItemCount(String nCartItemCount) {
        this.nCartItemCount = nCartItemCount;
    }

    public String getSubTotalAmount() {
        return SubTotalAmount;
    }

    public void setSubTotalAmount(String subTotalAmount) {
        SubTotalAmount = subTotalAmount;
    }

    public String getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        TotalAmount = totalAmount;
    }

    public String getdOrderDate() {
        return dOrderDate;
    }

    public void setdOrderDate(String dOrderDate) {
        this.dOrderDate = dOrderDate;
    }

    public String getdOrderTime() {
        return dOrderTime;
    }

    public void setdOrderTime(String dOrderTime) {
        this.dOrderTime = dOrderTime;
    }
}


