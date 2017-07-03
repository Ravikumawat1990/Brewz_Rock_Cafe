package com.app.elixir.brewz_rock_cafe.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by NetSupport on 06-10-2016.
 */
public class CityModel {


    @SerializedName("Result")
    public ArrayList<CityModelArray> cityModelArrays = new ArrayList<CityModelArray>();
}
