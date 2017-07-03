package com.app.elixir.brewz_rock_cafe.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by NetSupport on 08-10-2016.
 */
public class AddOnModelArray {

    @SerializedName("nMapperID")
    public String nMapperID;
    @SerializedName("cAttributeLabel")
    public String cAttributeLabel;
    @SerializedName("isMultiple")
    public String isMultiple;
    @SerializedName("isRequired")
    public String isRequired;
      @SerializedName("ListProductAttributeMapperDetail")
      public ArrayList<ListProductAttributeMapperDetail> listProductAttributeMapperDetails = new ArrayList<ListProductAttributeMapperDetail>();

}
