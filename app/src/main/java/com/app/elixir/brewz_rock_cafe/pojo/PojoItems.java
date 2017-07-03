package com.app.elixir.brewz_rock_cafe.pojo;

/**
 * Created by NetSupport on 26-08-2016.
 */
public class PojoItems {

    String nMapperID;
    String nAttributeID;
    String cAttributeLabel;
    String isMultiple;
    String hedaer;
    String IsRequired;

    public String getIsRequired() {
        return IsRequired;
    }

    public void setIsRequired(String isRequired) {
        IsRequired = isRequired;
    }

    public String getHedaer() {
        return hedaer;
    }

    public void setHedaer(String hedaer) {
        this.hedaer = hedaer;
    }

    public String getnMapperID() {
        return nMapperID;
    }

    public void setnMapperID(String nMapperID) {
        this.nMapperID = nMapperID;
    }

    public String getnAttributeID() {
        return nAttributeID;
    }

    public void setnAttributeID(String nAttributeID) {
        this.nAttributeID = nAttributeID;
    }

    public String getcAttributeLabel() {
        return cAttributeLabel;
    }

    public void setcAttributeLabel(String cAttributeLabel) {
        this.cAttributeLabel = cAttributeLabel;
    }

    public String getIsMultiple() {
        return isMultiple;
    }

    public void setIsMultiple(String isMultiple) {
        this.isMultiple = isMultiple;
    }
}
