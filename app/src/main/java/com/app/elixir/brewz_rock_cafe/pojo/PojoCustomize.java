package com.app.elixir.brewz_rock_cafe.pojo;

import java.util.ArrayList;

/**
 * Created by Elixir on 19-Aug-2016.
 */
public class PojoCustomize {

    String itemName;
    String itemPrice;
    String isMulti;
    ArrayList<PojoArray> pojoArrays;
    ArrayList<PojoCustomizeSubPart> pojoCustomizeSubParts;


    public ArrayList<PojoArray> getPojoArrays() {
        return pojoArrays;
    }

    public void setPojoArrays(ArrayList<PojoArray> pojoArrays) {
        this.pojoArrays = pojoArrays;
    }

    public ArrayList<PojoCustomizeSubPart> getPojoCustomizeSubParts() {
        return pojoCustomizeSubParts;
    }

    public void setPojoCustomizeSubParts(ArrayList<PojoCustomizeSubPart> pojoCustomizeSubParts) {
        this.pojoCustomizeSubParts = pojoCustomizeSubParts;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getIsMulti() {
        return isMulti;
    }

    public void setIsMulti(String isMulti) {
        this.isMulti = isMulti;
    }
}
