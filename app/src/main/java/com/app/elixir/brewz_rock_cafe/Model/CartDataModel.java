package com.app.elixir.brewz_rock_cafe.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by NetSupport on 10-10-2016.
 */
public class CartDataModel {

    @SerializedName("data")
    public ArrayList<CardDateArray> cardDateArrays = new ArrayList<CardDateArray>();


}
