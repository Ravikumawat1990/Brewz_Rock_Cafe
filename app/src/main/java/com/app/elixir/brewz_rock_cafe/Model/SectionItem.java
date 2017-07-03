package com.app.elixir.brewz_rock_cafe.Model;

import java.util.ArrayList;

public class SectionItem implements Item {


    public String nCartItemCount;
    public String SubTotalAmount;
    public String TotalAmount;
    public String dOrderDate;
    public String dOrderTime;
    public String cOrderPaymentStatus;
    ArrayList<AddressModel> addressModels;
    public String cOrderPaymentMode;
    public String cOrderStatus;


    public SectionItem(String cartItemCount, String nCartItemCount, String SubTotalAmount, String TotalAmount, String dOrderDate, String dOrderTime, ArrayList<AddressModel> addressModels, String cOrderPaymentStatus, String cOrderPaymentMode, String cOrderStatus) {
        this.nCartItemCount = nCartItemCount;
        this.SubTotalAmount = SubTotalAmount;
        this.TotalAmount = TotalAmount;
        this.dOrderDate = dOrderDate;
        this.dOrderTime = dOrderTime;
        this.addressModels = addressModels;
        this.cOrderPaymentStatus = cOrderPaymentStatus;
        this.cOrderPaymentMode = cOrderPaymentMode;
        this.cOrderStatus = cOrderStatus;
    }


    @Override
    public boolean isSection() {
        return true;
    }


}
