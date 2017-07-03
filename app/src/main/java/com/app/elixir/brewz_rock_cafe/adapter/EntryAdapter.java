package com.app.elixir.brewz_rock_cafe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.elixir.brewz_rock_cafe.Model.EntryItem;
import com.app.elixir.brewz_rock_cafe.Model.Item;
import com.app.elixir.brewz_rock_cafe.Model.SectionItem;
import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.utils.CM;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class EntryAdapter extends ArrayAdapter<Item> {

    private Context context;
    private ArrayList<Item> items;
    private LayoutInflater vi;

    public EntryAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
        vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final Item i = items.get(position);
        if (i != null) {
            if (i.isSection()) {
                SectionItem si = (SectionItem) i;
                v = vi.inflate(R.layout.list_item_section, null);
                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);
                TextView sectionView = (TextView) v.findViewById(R.id.textViewHeader);
                DateFormat readFormat = new SimpleDateFormat("hh:mm:ss");
                DateFormat dateFormat = new SimpleDateFormat("hh:mm aaa");
                try {
                    if (si.dOrderDate != null) {
                        sectionView.setText(CM.converDateFormate("yyyy-MM-dd'T'HH:mm:ss", "dd/MM/yyyy", si.dOrderDate) + " " + dateFormat.format(readFormat.parse(si.dOrderTime)) + " Total " + CM.getSp(context, "currency", "").toString() + "" + si.TotalAmount);
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                EntryItem ei = (EntryItem) i;
                v = vi.inflate(R.layout.list_item_entry, null);
                final TextView title = (TextView) v.findViewById(R.id.list_item_entry_title);
                final TextView subtitle = (TextView) v.findViewById(R.id.list_item_entry_summary);
                final ImageView imageView = (ImageView) v.findViewById(R.id.list_item_entry_drawable);
                final TextView textViewQan = (TextView) v.findViewById(R.id.itemQuantity);

                if (ei.cProductImagePath != null) {
                    Glide.with(context)
                            .load(ei.cProductImagePath)
                            .listener(new RequestListener<String, GlideDrawable>() {
                                @Override
                                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                    //imageViewPlace.setVisibility(View.VISIBLE);
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                    //imageViewPlace.setVisibility(View.GONE);
                                    return false;
                                }
                            }).error(R.drawable.placeholder)
                            .into(imageView);
                }

                if (title != null)
                    title.setText(ei.cProductName);
                if (subtitle != null)
                    subtitle.setText("Rate" + " " + CM.getSp(context, "currency", "").toString() + "" + ei.nRate);
                textViewQan.setText("Quantity : " + ei.Qty);

            }
        }
        return v;
    }

}
