package com.app.elixir.brewz_rock_cafe.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.interfac.OnItemClickListenerFoodItem;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplTextView;
import com.app.elixir.brewz_rock_cafe.pojo.PojoCategoryListing;
import com.app.elixir.brewz_rock_cafe.utils.CM;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

/**
 * Created by Elixir on 09-Aug-2016.
 */
public class adptCustomGrid extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    ArrayList<PojoCategoryListing> pojoCategoryListings;
    public OnItemClickListenerFoodItem listener;


    public adptCustomGrid(Context c, ArrayList<PojoCategoryListing> pojoCategoryListings) {
        mContext = c;
        this.pojoCategoryListings = pojoCategoryListings;

    }

    public void SetOnItemClickListener(OnItemClickListenerFoodItem mItemClickListener) {

        this.listener = mItemClickListener;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return pojoCategoryListings.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            holder = new ViewHolder();

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_single, null);
            holder.txtGridHeader = (MtplTextView) grid.findViewById(R.id.grid_text);
            holder.txtGridHeader.setSelected(true);
            holder.imageView = (ImageView) grid.findViewById(R.id.grid_image);
            holder.cardView = (CardView) grid.findViewById(R.id.gridMain);
            holder.addtocart = (LinearLayout) grid.findViewById(R.id.gridAddtoCart);
            holder.txtGridHeader.setText(pojoCategoryListings.get(position).getName());
            holder.progressBar = (ProgressBar) grid.findViewById(R.id.progressbar);


            if (pojoCategoryListings.get(position).getIsActive() != null) {
                if (pojoCategoryListings.get(position).getIsActive().equals("true")) {
                    holder.cardView.setVisibility(View.VISIBLE);
                } else {
                    holder.cardView.setVisibility(View.GONE);
                }
            }

            final ViewHolder finalHolder = holder;


            if (pojoCategoryListings.get(position).getImagePath() != null) {
                Glide.with(mContext).load(pojoCategoryListings.get(position).getImagePath())
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                finalHolder.progressBar.setVisibility(View.VISIBLE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                finalHolder.progressBar.setVisibility(View.GONE);
                                return false;
                            }
                        }).error(R.drawable.placeholder)
                        .into(holder.imageView);
            }


            holder.addtocart.setOnClickListener(this);
            finalHolder.progressBar.setOnClickListener(this);
            holder.addtocart.setTag(position);
            finalHolder.progressBar.setTag(position);

        } else {
            grid = (View) convertView;
            holder = (ViewHolder) convertView.getTag();

        }

        return grid;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.gridAddtoCart) {

            try {
                Integer taggedPosition = (Integer) view.getTag();
                CM.showToast(mContext, String.valueOf(taggedPosition));
                listener.onItemClick("add", taggedPosition);

            } catch (Exception e) {

            }
        } else {

            try {
                Integer taggedPosition = (Integer) view.getTag();
                listener.onItemClick("detail", taggedPosition);
            } catch (Exception e) {

            }


        }
    }

    public class ViewHolder {
        MtplTextView txtGridHeader;
        ImageView imageView;
        ProgressBar progressBar;
        CardView cardView;
        LinearLayout addtocart;
    }
}