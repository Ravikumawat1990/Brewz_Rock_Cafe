package com.app.elixir.brewz_rock_cafe.pojo;

import java.util.ArrayList;

/**
 * Created by NetSupport on 18-10-2016.
 */
public class OrderSummeryPojoArraySub {


    public String nMapperID;
    public String cAttributeLabel;
    public String isMultiple;
    public String isRequired;
    public String nAttributeID;

    ArrayList<OrderSummeryPojoArraySubSub> orderSummeryPojoArraySubSubs;


    public ArrayList<OrderSummeryPojoArraySubSub> getOrderSummeryPojoArraySubSubs() {
        return orderSummeryPojoArraySubSubs;
    }

    public void setOrderSummeryPojoArraySubSubs(ArrayList<OrderSummeryPojoArraySubSub> orderSummeryPojoArraySubSubs) {
        this.orderSummeryPojoArraySubSubs = orderSummeryPojoArraySubSubs;
    }

    public String getnAttributeID() {
        return nAttributeID;
    }

    public void setnAttributeID(String nAttributeID) {
        this.nAttributeID = nAttributeID;
    }

    public String getnMapperID() {
        return nMapperID;
    }

    public void setnMapperID(String nMapperID) {
        this.nMapperID = nMapperID;
    }

    public String getcAttributeLabel() {
        return cAttributeLabel;
    }

    public void setcAttributeLabel(String cAttributeLabel) {
        this.cAttributeLabel = cAttributeLabel;
    }

    public String getIsMultiple() {
        return isMultiple;
    }

    public void setIsMultiple(String isMultiple) {
        this.isMultiple = isMultiple;
    }

    public String getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }
}
