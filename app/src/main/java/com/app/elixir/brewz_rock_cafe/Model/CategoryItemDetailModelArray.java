package com.app.elixir.brewz_rock_cafe.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by NetSupport on 31-08-2016.
 */
public class CategoryItemDetailModelArray {

    @SerializedName("nMapperID")
    public String nMapperID;
    @SerializedName("nAttributeID")
    public String nAttributeID;
    @SerializedName("cAttributeLabel")
    public String cAttributeLabel;
    @SerializedName("isMultiple")
    public String isMultiple;
    @SerializedName("isRequired")
    public String isRequired;
    @SerializedName("ListProductAttributeMapperDetail")
    public ArrayList<CategoryItemDetailModelSubArray> categoryItemDetailModelSubArrays = new ArrayList<CategoryItemDetailModelSubArray>();


}
