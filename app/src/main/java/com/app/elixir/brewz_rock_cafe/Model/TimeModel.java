package com.app.elixir.brewz_rock_cafe.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NetSupport on 07-10-2016.
 */
public class TimeModel {
    @SerializedName("bIsClosed")
    public String bIsClosed;
    @SerializedName("isPopupOpen")
    public String isPopupOpen;
    @SerializedName("tOpeningTime")
    public String tOpeningTime;
    @SerializedName("tClosingTime")
    public String tClosingTime;
    @SerializedName("tDisplayHoursInterval")
    public String tDisplayHoursInterval;
}
