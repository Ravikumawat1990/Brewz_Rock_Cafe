package com.app.elixir.brewz_rock_cafe.pojo;

import java.util.ArrayList;

/**
 * Created by NetSupport on 22/08/2016.
 */
public class MainCustomize {

    String itemName;
    String itemPrice;
    String isMulti;

    ArrayList<PojoCustomize> pojoCustomizeSubParts;

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

    public ArrayList<PojoCustomize> getPojoCustomizeSubParts() {
        return pojoCustomizeSubParts;
    }

    public void setPojoCustomizeSubParts(ArrayList<PojoCustomize> pojoCustomizeSubParts) {
        this.pojoCustomizeSubParts = pojoCustomizeSubParts;
    }
}
