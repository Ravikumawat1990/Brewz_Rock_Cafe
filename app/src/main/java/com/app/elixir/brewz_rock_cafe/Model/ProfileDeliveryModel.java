package com.app.elixir.brewz_rock_cafe.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NetSupport on 08-10-2016.
 */
public class ProfileDeliveryModel {

    //@SerializedName("DeliveryBoyDetail")
    //public ArrayList<ProfileDeliveryModelArray> profileDeliveryModelArrays = new ArrayList<ProfileDeliveryModelArray>();
    @SerializedName("nUserID")
    public String nUserID;
    @SerializedName("Fname")
    public String Fname;
    @SerializedName("Lname")
    public String Lname;
    @SerializedName("Email")
    public String Email;
    @SerializedName("Mobile")
    public String Mobile;
}
