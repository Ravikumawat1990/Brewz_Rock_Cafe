package com.app.elixir.brewz_rock_cafe.pojo;

/**
 * Created by Elixir on 10-Aug-2016.
 */
public class PojoCouponCode {

    String description;
    String code;
    String title;
    String validity;
    String categoryApply;

    public String getCategoryApply() {
        return categoryApply;
    }

    public void setCategoryApply(String categoryApply) {
        this.categoryApply = categoryApply;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
