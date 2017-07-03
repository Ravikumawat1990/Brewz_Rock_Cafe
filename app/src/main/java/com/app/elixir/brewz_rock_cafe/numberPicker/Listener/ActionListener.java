package com.app.elixir.brewz_rock_cafe.numberPicker.Listener;

import android.view.View;

import com.app.elixir.brewz_rock_cafe.numberPicker.NumberPicker;
import com.app.elixir.brewz_rock_cafe.numberPicker.Enums.ActionEnum;

/**
 * Created by travijuu on 26/05/16.
 */
public class ActionListener implements View.OnClickListener {

    NumberPicker layout;
    ActionEnum action;


    public ActionListener(NumberPicker layout, ActionEnum action) {
        this.layout = layout;
        this.action = action;
    }



    @Override
    public void onClick(View v) {
        if (this.action == ActionEnum.INCREMENT) {
            this.layout.increment();

        } else {
            this.layout.decrement();

        }
    }


}