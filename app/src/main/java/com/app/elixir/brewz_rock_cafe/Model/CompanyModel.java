package com.app.elixir.brewz_rock_cafe.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by NetSupport on 03-10-2016.
 */
public class CompanyModel {

    @SerializedName("comapny")
    public ArrayList<CompanyModelArray> companyModelArrays = new ArrayList<CompanyModelArray>();
}
