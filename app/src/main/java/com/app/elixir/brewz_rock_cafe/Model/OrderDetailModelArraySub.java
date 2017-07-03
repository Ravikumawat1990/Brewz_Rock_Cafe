package com.app.elixir.brewz_rock_cafe.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NetSupport on 08-10-2016.
 */
public class OrderDetailModelArraySub {

    @SerializedName("cCustomerName")
    public String cCustomerName;
    @SerializedName("cMobile")
    public String cMobile;
    @SerializedName("cEmail")
    public String cEmail;
    @SerializedName("nShippingAddressID")
    public String nShippingAddressID;
    @SerializedName("nBillingAddressID")
    public String nBillingAddressID;
    @SerializedName("cAddressLine1")
    public String cAddressLine1;


}
