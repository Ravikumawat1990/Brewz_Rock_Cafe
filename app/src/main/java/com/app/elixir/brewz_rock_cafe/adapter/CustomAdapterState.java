package com.app.elixir.brewz_rock_cafe.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.elixir.brewz_rock_cafe.Model.StateModelArray;
import com.app.elixir.brewz_rock_cafe.R;

import java.util.List;

/**
 * Created by NetSupport on 06-10-2016.
 */
public class CustomAdapterState extends ArrayAdapter<StateModelArray> {

    LayoutInflater flater;

    public CustomAdapterState(Activity context, int resouceId, List<StateModelArray> list) {

        super(context, resouceId, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return rowview(convertView, position);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return rowview(convertView, position);
    }

    private View rowview(View convertView, int position) {

        StateModelArray rowItem = getItem(position);

        viewHolder holder;
        View rowview = convertView;
        if (rowview == null) {
            holder = new viewHolder();
            flater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowview = flater.inflate(R.layout.adapterstate, null, false);
            holder.txtTitle = (TextView) rowview.findViewById(R.id.title);
            rowview.setTag(holder);
        } else {
            holder = (viewHolder) rowview.getTag();
        }

        if (rowItem.cStateName != null) {
            holder.txtTitle.setText(rowItem.cStateName);
        } else {
            holder.txtTitle.setText("--");
        }

        return rowview;
    }

    private class viewHolder {
        TextView txtTitle;

    }
}
