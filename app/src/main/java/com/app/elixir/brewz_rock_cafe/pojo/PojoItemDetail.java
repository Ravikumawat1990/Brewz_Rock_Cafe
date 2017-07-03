package com.app.elixir.brewz_rock_cafe.pojo;

/**
 * Created by NetSupport on 26-08-2016.
 */
public class PojoItemDetail {

    String nAttributeID;
    String cAttributeLabel;
    String nMapperDetailID;
    String nMapperID;
    String nAttributeValueMasterID;
    String cAttributeValueLabel;
    String nPrice;
    String isChecked;
    String isMulti;
    String header;
    String IsRequired;

    public String getIsRequired() {
        return IsRequired;
    }

    public void setIsRequired(String isRequired) {
        IsRequired = isRequired;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getIsMulti() {
        return isMulti;
    }

    public void setIsMulti(String isMulti) {
        this.isMulti = isMulti;
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

    public String getnMapperDetailID() {
        return nMapperDetailID;
    }

    public void setnMapperDetailID(String nMapperDetailID) {
        this.nMapperDetailID = nMapperDetailID;
    }

    public String getnMapperID() {
        return nMapperID;
    }

    public void setnMapperID(String nMapperID) {
        this.nMapperID = nMapperID;
    }

    public String getcAttributeValueLabel() {
        return cAttributeValueLabel;
    }

    public void setcAttributeValueLabel(String cAttributeValueLabel) {
        this.cAttributeValueLabel = cAttributeValueLabel;
    }

    public String getnAttributeValueMasterID() {
        return nAttributeValueMasterID;
    }

    public void setnAttributeValueMasterID(String nAttributeValueMasterID) {
        this.nAttributeValueMasterID = nAttributeValueMasterID;
    }

    public String getnPrice() {
        return nPrice;
    }

    public void setnPrice(String nPrice) {
        this.nPrice = nPrice;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }
}
