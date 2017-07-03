package com.app.elixir.brewz_rock_cafe.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.elixir.brewz_rock_cafe.Model.CategoryModelArray;
import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.interfac.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

/**
 * Created by Elixir on 08-Aug-2016.
 */
public class adptCategory extends RecyclerView.Adapter<adptCategory.MyViewHolder> {


    private ArrayList<CategoryModelArray> dataSet;
    Context context;
    public OnItemClickListener listener;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final CardView rootView;
        public LinearLayout placeHolder;
        public LinearLayout placeNameHolder;
        public TextView placeName;
        public ImageView placeImage;
        ProgressBar progressBar;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            rootView = (CardView) itemView.findViewById(R.id.placeCard);
            placeHolder = (LinearLayout) itemView.findViewById(R.id.mainHolder);
            placeName = (TextView) itemView.findViewById(R.id.placeName);
            placeNameHolder = (LinearLayout) itemView.findViewById(R.id.placeNameHolder);
            placeImage = (ImageView) itemView.findViewById(R.id.placeImage);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressbar);
            imageView = (ImageView) itemView.findViewById(R.id.placeholder1);
            placeHolder.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(dataSet.get(getAdapterPosition()).nProductCategoryID);
        }
    }

    public void SetOnItemClickListener(OnItemClickListener mItemClickListener) {

        this.listener = mItemClickListener;
    }

    public adptCategory(Context context, ArrayList<CategoryModelArray> data) {
        this.dataSet = data;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adaptercategory, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        TextView textViewTitle = holder.placeName;
        final ImageView imageView = holder.placeImage;
        CardView cardView = holder.rootView;
        final ImageView placeHolderView = holder.imageView;
        final ProgressBar progressBar = holder.progressBar;
        textViewTitle.setText(dataSet.get(listPosition).cCategoryName);

        if (dataSet.get(listPosition).bIsActive != null) {
            if (dataSet.get(listPosition).bIsActive.equals("true")) {
                cardView.setVisibility(View.VISIBLE);
            } else {
                cardView.setVisibility(View.GONE);
            }
        }


        if (dataSet.get(listPosition).cProductCatImagePath != null) {
            Glide.with(context).load(dataSet.get(listPosition).cProductCatImagePath)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            placeHolderView.setVisibility(View.VISIBLE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            placeHolderView.setVisibility(View.GONE);
                            return false;
                        }
                    }).error(R.drawable.longplace)
                    .into(imageView);
            Animation scale = AnimationUtils.loadAnimation(context, R.anim.zoom);
            placeHolderView.startAnimation(scale);
        }

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
