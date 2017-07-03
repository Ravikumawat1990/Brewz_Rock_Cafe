package com.app.elixir.brewz_rock_cafe.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplTextView;
import com.app.elixir.brewz_rock_cafe.pojo.PojoCategoryListing;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

/**
 * Created by NetSupport on 12-10-2016.
 */
public class CustomGridViewAdapter extends BaseAdapter {

    ArrayList<PojoCategoryListing> pojoCategoryListings;
    Context context;

    public CustomGridViewAdapter(Context context, ArrayList<PojoCategoryListing> pojoCategoryListings) {
        // TODO Auto-generated constructor stub
        this.pojoCategoryListings = pojoCategoryListings;
        this.context = context;

        // inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return pojoCategoryListings.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }


    public class Holder {
        MtplTextView txtGridHeader;
        ImageView imageView;
        ProgressBar progressBar;
        CardView cardView;
        LinearLayout addtocart;
        LinearLayout linearLayout;
        ImageView imageViewPlace;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder = new Holder();
        View grid;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        grid = inflater.inflate(R.layout.grid_single, null);
        holder.txtGridHeader = (MtplTextView) grid.findViewById(R.id.grid_text);
        holder.txtGridHeader.setSelected(true);
        holder.imageView = (ImageView) grid.findViewById(R.id.grid_image);
        holder.cardView = (CardView) grid.findViewById(R.id.gridMain);
        holder.addtocart = (LinearLayout) grid.findViewById(R.id.gridAddtoCart);
        holder.txtGridHeader.setText(pojoCategoryListings.get(position).getName());
        holder.progressBar = (ProgressBar) grid.findViewById(R.id.progressbar);
        holder.linearLayout = (LinearLayout) grid.findViewById(R.id.mainHolder);
        holder.imageViewPlace = (ImageView) grid.findViewById(R.id.placeholder);

        Animation scale = AnimationUtils.loadAnimation(context, R.anim.zoom);
        holder.imageViewPlace.startAnimation(scale);


        if (pojoCategoryListings.get(position).getImagePath() != null) {
            Glide.with(context)
                    .load(pojoCategoryListings.get(position).getImagePath())
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            holder.imageViewPlace.setVisibility(View.VISIBLE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            holder.imageViewPlace.setVisibility(View.GONE);
                            return false;
                        }
                    }).error(R.drawable.shortplace)
                    .into(holder.imageView);

        }
        return grid;
    }

}

