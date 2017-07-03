package com.app.elixir.brewz_rock_cafe.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by NetSupport on 10-10-2016.
 */
public class OrderDeliveryDetail {

    @SerializedName("OrderDetail")
    public ArrayList<OrderDeliveryDetailArray> orderDeliveryDetailArrays = new ArrayList<OrderDeliveryDetailArray>();
}
