package com.app.elixir.brewz_rock_cafe.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.pojo.PojoItemDetail;

import java.util.ArrayList;


public class CustomSpinnerAdapterSpicy extends BaseAdapter implements SpinnerAdapter {

    private final Context activity;
    private ArrayList<PojoItemDetail> asr;

    public CustomSpinnerAdapterSpicy(Context context, ArrayList<PojoItemDetail> asr) {
        this.asr = asr;
        activity = context;
    }


    public int getCount() {
        return asr.size();
    }

    public Object getItem(int i) {
        return asr.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        Typeface face = Typeface.createFromAsset(activity.getAssets(), "Roboto-Black_0.ttf");
        TextView txt = new TextView(activity);
        txt.setPadding(32, 16, 32, 16);

        txt.setTextSize(18);
        txt.setTypeface(face);
        txt.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        txt.setText(asr.get(position).getHeader());

        return txt;
    }

    public View getView(int i, View view, ViewGroup viewgroup) {
        View view1 = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.textviewlaout, viewgroup, false);
        Typeface face = Typeface.createFromAsset(activity.getAssets(), "Roboto-Light_0.ttf");
        TextView txt = new TextView(activity);
        txt.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        txt.setTypeface(face);
        txt.setPadding(32, 16, 32, 16);

        // float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, activity.getResources().getDisplayMetrics());
        txt.setTextSize(15);
        txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_arrow_drop_down, 0);
        if (asr.get(i).getHeader() != null) {
            txt.setText(asr.get(i).getHeader());
        }
        return txt;
    }


}