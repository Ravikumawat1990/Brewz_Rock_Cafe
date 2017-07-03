package com.app.elixir.brewz_rock_cafe.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.app.elixir.brewz_rock_cafe.Model.OrderDeliveryDetailArraySubSub;
import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.interfac.OnItemClickListenerOrderSuumnery;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplButton;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplTextView;
import com.app.elixir.brewz_rock_cafe.utils.CM;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

/**
 * Created by Elixir on 09-Aug-2016.
 */
public class adptorderDetail extends RecyclerView.Adapter<adptorderDetail.MyViewHolder> {


    private ArrayList<OrderDeliveryDetailArraySubSub> dataSet;
    Context context;
    public OnItemClickListenerOrderSuumnery listener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final MtplTextView mtplTextViewTotal;
        private final MtplTextView mtplTextViewDisAmt;
        public LinearLayout placeHolder;
        public LinearLayout placeNameHolder;
        CardView cardView;
        MtplTextView textViewItemName, textViewItemPrice, textViewItemQuanti, orderSummeryStrickItemPrice, mtplTextViewDicount, textViewAddOn;
        MtplButton orderSummeryRemoveButton, orderSummeryEditButton;
        private final ImageView imageView;
        private final ProgressBar progressBar;
        LinearLayout addonsLayout;
        ImageView imageViewPlace;
        MtplTextView orderSummeryProductCat;
        LinearLayout catLay;

        public MyViewHolder(View itemView) {
            super(itemView);
            placeHolder = (LinearLayout) itemView.findViewById(R.id.mainHolder);
            placeNameHolder = (LinearLayout) itemView.findViewById(R.id.placeNameHolder);
            textViewItemName = (MtplTextView) itemView.findViewById(R.id.orderSummeryItemName);
            textViewItemPrice = (MtplTextView) itemView.findViewById(R.id.orderSummeryItemPrice);
            textViewItemQuanti = (MtplTextView) itemView.findViewById(R.id.orderSummeryItemQuanti);
            orderSummeryRemoveButton = (MtplButton) itemView.findViewById(R.id.orderSummeryRemoveButton);
            mtplTextViewDisAmt = (MtplTextView) itemView.findViewById(R.id.discountAmt);
            addonsLayout = (LinearLayout) itemView.findViewById(R.id.addonsLayout);
            catLay = (LinearLayout) itemView.findViewById(R.id.catLay);


            textViewAddOn = (MtplTextView) itemView.findViewById(R.id.textViewAddOn);
            imageView = (ImageView) itemView.findViewById(R.id.list_image);
            mtplTextViewTotal = (MtplTextView) itemView.findViewById(R.id.totleItem);
            orderSummeryProductCat = (MtplTextView) itemView.findViewById(R.id.orderSummeryProductCat);


            orderSummeryRemoveButton.setOnClickListener(this);
            imageViewPlace = (ImageView) itemView.findViewById(R.id.placeholder1);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressnar);
            orderSummeryEditButton = (MtplButton) itemView.findViewById(R.id.orderSummeryEditButton);
            cardView = (CardView) itemView.findViewById(R.id.placeCard);
            orderSummeryRemoveButton.setOnClickListener(this);
            orderSummeryEditButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.orderSummeryEditButton:
                    listener.onItemClick("", "", "edit");
                    break;
                case R.id.orderSummeryRemoveButton:
                    //listener.onItemClick(dataSet.get(getAdapterPosition()).nOrderID, dataSet.get(getAdapterPosition()).nOrderID, "remove");
                    break;
            }
        }
    }


    public void SetOnItemClickListener(OnItemClickListenerOrderSuumnery mItemClickListener) {

        this.listener = mItemClickListener;
    }

    public adptorderDetail(Context context, ArrayList<OrderDeliveryDetailArraySubSub> data) {
        this.dataSet = data;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapterordersummary, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {


        MtplTextView itemName = holder.textViewItemName;
        MtplTextView itemPrice = holder.textViewItemPrice;
        MtplTextView itemQuanti = holder.textViewItemQuanti;
        MtplButton removeItem = holder.orderSummeryRemoveButton;
        MtplTextView discount = holder.mtplTextViewDicount;
        ImageView imageView = holder.imageView;
        final ImageView imageView1 = holder.imageViewPlace;
        MtplTextView mtplTextViewAddOn = holder.textViewAddOn;
        MtplTextView itemToal = holder.mtplTextViewTotal;
        mtplTextViewAddOn.setSelected(true);
        final ProgressBar progressBar = holder.progressBar;
        MtplTextView mtplTextViewStrikeOf = holder.orderSummeryStrickItemPrice;
        MtplTextView mtplTextViewDis = holder.mtplTextViewDisAmt;
        itemName.setText(Html.fromHtml(dataSet.get(listPosition).cProductName + ""));
        LinearLayout addonsLayout = holder.addonsLayout;
        MtplTextView orderSummeryProductCat = holder.orderSummeryProductCat;
        LinearLayout catLay = holder.catLay;
        catLay.setVisibility(View.GONE);

        if (dataSet.get(listPosition).Qty != null) {
            itemQuanti.setText(dataSet.get(listPosition).Qty);
        } else {
            itemQuanti.setText("0");
        }
        if (dataSet.get(listPosition).cAddons != null) {
            mtplTextViewAddOn.setText(Html.fromHtml(dataSet.get(listPosition).cAddons));
        } else {
            addonsLayout.setVisibility(View.GONE);
        }
        try {
            Double tot = Double.parseDouble(dataSet.get(listPosition).nSubTotalAmt) * Double.parseDouble(dataSet.get(listPosition).Qty);
            itemToal.setText(CM.getSp(context, "currency", "").toString() + String.valueOf(tot));
        } catch (Exception e) {

        }


        Double perPrice = 0.0;
        try {
            if (dataSet.get(listPosition).nAddonTotalAmt != null) {
                perPrice = Double.parseDouble(dataSet.get(listPosition).nRate) + Double.parseDouble(dataSet.get(listPosition).nAddonTotalAmt);
            } else {
                perPrice = Double.parseDouble(dataSet.get(listPosition).nRate);
            }
        } catch (Exception e) {

        }

        try {
            itemPrice.setText(CM.getSp(context, "currency", "").toString() + perPrice);
        } catch (Exception e) {

        }

        if (dataSet.get(listPosition).nDiscountAmt != null && !dataSet.get(listPosition).nDiscountAmt.toString().equals("null")) {
            mtplTextViewDis.setText(CM.getSp(context, "currency", "").toString() + dataSet.get(listPosition).nDiscountAmt);
        } else {
            mtplTextViewDis.setVisibility(View.GONE);
        }

        if (dataSet.get(listPosition).cProductImagePath != null) {
            Glide.with(context).load(dataSet.get(listPosition).cProductImagePath)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            imageView1.setVisibility(View.VISIBLE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            imageView1.setVisibility(View.GONE);
                            return false;
                        }
                    }).error(R.drawable.placeholder)
                    .into(imageView);
        }

        removeItem.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}