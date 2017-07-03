package com.app.elixir.brewz_rock_cafe.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NetSupport on 05-10-2016.
 */
public class LoginEmilModel {
    @SerializedName("nCustomerID")
    public String nCustomerID;
    @SerializedName("cFirstname")
    public String cFirstname;
    @SerializedName("cLastname")
    public String cLastname;
    @SerializedName("cMobilePrimary")
    public String cMobilePrimary;
    @SerializedName("cMobileSecondary")
    public String cMobileSecondary;
    @SerializedName("cEmail")
    public String cEmail;
    @SerializedName("cEmailPassword")
    public String cEmailPassword;
    @SerializedName("nUserID")
    public String nUserID;


}
