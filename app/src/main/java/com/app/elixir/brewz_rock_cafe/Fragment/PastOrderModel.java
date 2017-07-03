package com.app.elixir.brewz_rock_cafe.Fragment;

import com.app.elixir.brewz_rock_cafe.Model.PastOrderModelArray;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by NetSupport on 08-10-2016.
 */
public class PastOrderModel {

    @SerializedName("PastOrderList")
    public ArrayList<PastOrderModelArray> pastOrderModelArrays = new ArrayList<PastOrderModelArray>();
}
