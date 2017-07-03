package com.app.elixir.brewz_rock_cafe.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.interfac.OnItemClickListenerOrderSuumnery;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplButton;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplTextView;
import com.app.elixir.brewz_rock_cafe.pojo.OrderSummeryPojoArray;
import com.app.elixir.brewz_rock_cafe.utils.CM;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * Created by Elixir on 09-Aug-2016.
 */
public class adptordersummery extends RecyclerView.Adapter<adptordersummery.MyViewHolder> {


    private ArrayList<OrderSummeryPojoArray> dataSet;
    Context context;
    public OnItemClickListenerOrderSuumnery listener;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final MtplTextView mtplTextViewTotal;
        private final LinearLayout proTaxLay;
        private final MtplTextView txtproTax;
        private final MtplTextView totletxt;
        public MtplTextView mtplTextViewProductCat;
        public LinearLayout placeHolder;
        public LinearLayout placeNameHolder;
        CardView cardView;
        MtplTextView textViewItemName, textViewItemPrice, textViewItemQuanti, orderSummeryStrickItemPrice, mtplTextViewDicount, txtViewAddOn, itemTotal;
        MtplButton orderSummeryRemoveButton, orderSummeryEditButton;
        private final ImageView imageViewPlace;
        private final ProgressBar progressBar;
        ImageView imageView;
        LinearLayout dicLayout, dicLayoutAfter, addonsLayout;
        MtplTextView mtplTextViewDisAmt, mtplTextViewDisAmtAfter;

        public MyViewHolder(View itemView) {
            super(itemView);
            placeHolder = (LinearLayout) itemView.findViewById(R.id.mainHolder);
            placeNameHolder = (LinearLayout) itemView.findViewById(R.id.placeNameHolder);
            textViewItemName = (MtplTextView) itemView.findViewById(R.id.orderSummeryItemName);
            textViewItemName.setSelected(true);
            textViewItemPrice = (MtplTextView) itemView.findViewById(R.id.orderSummeryItemPrice);
            textViewItemQuanti = (MtplTextView) itemView.findViewById(R.id.orderSummeryItemQuanti);
            orderSummeryRemoveButton = (MtplButton) itemView.findViewById(R.id.orderSummeryRemoveButton);
            mtplTextViewProductCat = (MtplTextView) itemView.findViewById(R.id.orderSummeryProductCat);
            mtplTextViewProductCat.setSelected(true);
            mtplTextViewDisAmt = (MtplTextView) itemView.findViewById(R.id.discountAmt);
            mtplTextViewDisAmtAfter = (MtplTextView) itemView.findViewById(R.id.discountAmtAfter);
            itemTotal = (MtplTextView) itemView.findViewById(R.id.itemTotal);
            totletxt = (MtplTextView) itemView.findViewById(R.id.totletxt);


            dicLayout = (LinearLayout) itemView.findViewById(R.id.discLayout);
            dicLayoutAfter = (LinearLayout) itemView.findViewById(R.id.discLayoutAfter);

            addonsLayout = (LinearLayout) itemView.findViewById(R.id.addonsLayout);
            proTaxLay = (LinearLayout) itemView.findViewById(R.id.proTaxLay);
            txtproTax = (MtplTextView) itemView.findViewById(R.id.proTax);

            mtplTextViewTotal = (MtplTextView) itemView.findViewById(R.id.totleItem);


            txtViewAddOn = (MtplTextView) itemView.findViewById(R.id.textViewAddOn);
            imageView = (ImageView) itemView.findViewById(R.id.list_image);
            orderSummeryRemoveButton.setOnClickListener(this);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressnar);
            //     mtplTextViewDicount = (MtplTextView) itemView.findViewById(R.id.orderSummerydiccount);
            // mtplTextViewDicount.setVisibility(View.VISIBLE);
            orderSummeryEditButton = (MtplButton) itemView.findViewById(R.id.orderSummeryEditButton);
            //orderSummeryStrickItemPrice = (MtplTextView) itemView.findViewById(R.id.orderSummeryStrickItemPrice);
            cardView = (CardView) itemView.findViewById(R.id.placeCard);
            imageViewPlace = (ImageView) itemView.findViewById(R.id.placeholder1);
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
                    listener.onItemClick(dataSet.get(getAdapterPosition()).nCartID, dataSet.get(getAdapterPosition()).nCartDetailID, "remove");
                    break;
            }
        }
    }


    public void SetOnItemClickListener(OnItemClickListenerOrderSuumnery mItemClickListener) {

        this.listener = mItemClickListener;
    }

    public adptordersummery(Context context, ArrayList<OrderSummeryPojoArray> data) {
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


        DecimalFormat form = new DecimalFormat("0.00");
        MtplTextView itemName = holder.textViewItemName;
        MtplTextView itemPrice = holder.textViewItemPrice;
        MtplTextView itemQuanti = holder.textViewItemQuanti;
        MtplButton removeItem = holder.orderSummeryRemoveButton;
        MtplTextView discount = holder.mtplTextViewDicount;
        ImageView imageView = holder.imageView;
        final ImageView imageView1 = holder.imageViewPlace;
        MtplTextView mtplTextViewProductCat = holder.mtplTextViewProductCat;
        LinearLayout addonsLayout = holder.addonsLayout;
        MtplTextView mtplTextViewAddOn = holder.txtViewAddOn;
        mtplTextViewAddOn.setSelected(true);
        final ProgressBar progressBar = holder.progressBar;
        MtplTextView mtplTextViewStrikeOf = holder.orderSummeryStrickItemPrice;
        LinearLayout linearLayoutDis = holder.dicLayout;
        LinearLayout linearLayoutDisAfter = holder.dicLayoutAfter;
        MtplTextView mtplTextViewDis = holder.mtplTextViewDisAmt;
        MtplTextView mtplTextViewDisAfter = holder.mtplTextViewDisAmtAfter;
        MtplTextView itemTotal = holder.itemTotal;
        MtplTextView totletxt = holder.totletxt;
        LinearLayout taxLay = holder.proTaxLay;
        MtplTextView txttax = holder.txtproTax;
        MtplTextView itemToal = holder.mtplTextViewTotal;
        itemName.setText(dataSet.get(listPosition).cProductName);
        Double perPrice = 0.00;
        try {
            if (dataSet.get(listPosition).nAddonTotalAmt != null) {
                perPrice = Double.parseDouble(dataSet.get(listPosition).nRate.toString()) + parseInt(dataSet.get(listPosition).nAddonTotalAmt.toString());
            } else {
                perPrice = Double.parseDouble(dataSet.get(listPosition).nRate.toString());
            }
        } catch (Exception e) {

        }
        Double tax = 0.0;
        if (dataSet.get(listPosition).getProductTaxAmt() != null) {
            if (dataSet.get(listPosition).getProductTaxAmt().toString().equals("0")) {
                taxLay.setVisibility(View.GONE);
                tax = 0.0;
            } else {
                txttax.setText(CM.getSp(context, "currency", "").toString() + "" + form.format(Double.parseDouble(dataSet.get(listPosition).getProductTaxAmt().toString())));
                tax = Double.parseDouble(dataSet.get(listPosition).getProductTaxAmt());
            }
        } else {
            taxLay.setVisibility(View.GONE);
        }
        Double dis = 0.0;
        if (!CM.getSp(context, "bIsDiscountAfterTax", "").toString().equals("")) {
            if (CM.getSp(context, "bIsDiscountAfterTax", "").toString().equals("true")) {
                linearLayoutDis.setVisibility(View.GONE);
                if (dataSet.get(listPosition).nDiscountAmt != null && !dataSet.get(listPosition).nDiscountAmt.toString().equals("null") && !dataSet.get(listPosition).nDiscountAmt.toString().equals("0")) {
                    dis = Double.parseDouble(dataSet.get(listPosition).nDiscountAmt);
                    mtplTextViewDisAfter.setText(CM.getSp(context, "currency", "").toString() + form.format(Double.parseDouble(dataSet.get(listPosition).nDiscountAmt)));
                } else {
                    dis = 0.0;
                    linearLayoutDisAfter.setVisibility(View.GONE);
                }
            } else {
                linearLayoutDisAfter.setVisibility(View.GONE);
                if (dataSet.get(listPosition).nDiscountAmt != null && !dataSet.get(listPosition).nDiscountAmt.toString().equals("null") && !dataSet.get(listPosition).nDiscountAmt.toString().equals("0")) {
                    dis = Double.parseDouble(dataSet.get(listPosition).nDiscountAmt);
                    mtplTextViewDis.setText(CM.getSp(context, "currency", "").toString() + form.format(Double.parseDouble(dataSet.get(listPosition).nDiscountAmt)));
                } else {
                    dis = 0.0;
                    linearLayoutDis.setVisibility(View.GONE);
                }

            }

        } else {

        }

        if (dataSet.get(listPosition).Qty != null) {
            totletxt.setText("Total for " + dataSet.get(listPosition).Qty + " Item");
        }

        try {
            itemPrice.setText(CM.getSp(context, "currency", "").toString() + form.format(perPrice));
        } catch (Exception e) {

        }


        try {
            itemQuanti.setText(dataSet.get(listPosition).Qty);
        } catch (Exception e) {

        }

        if (dataSet.get(listPosition).ProductTotalAmount != null) {
            itemToal.setText(CM.getSp(context, "currency", "").toString() + form.format(Double.parseDouble(dataSet.get(listPosition).ProductTotalAmount)));
        } else {
            itemToal.setText(CM.getSp(context, "currency", "").toString() + "0.00");
        }


        if (dataSet.get(listPosition).getProductCatName() != null) {
            mtplTextViewProductCat.setText(dataSet.get(listPosition).getProductCatName());
        }
        StringBuilder stringBuilder = new StringBuilder();

        if (dataSet.get(listPosition).getOrderSummeryPojoArraySubs() != null) {


            for (int i = 0; i < dataSet.get(listPosition).getOrderSummeryPojoArraySubs().size(); i++) {
                String header = dataSet.get(listPosition).getOrderSummeryPojoArraySubs().get(i).cAttributeLabel;
                for (int j = 0; j < dataSet.get(listPosition).getOrderSummeryPojoArraySubs().get(i).getOrderSummeryPojoArraySubSubs().size(); j++) {
                    if (dataSet.get(listPosition).getOrderSummeryPojoArraySubs().get(i).getOrderSummeryPojoArraySubSubs().get(j).getnPrice().toString() != null && dataSet.get(listPosition).getOrderSummeryPojoArraySubs().get(i).getOrderSummeryPojoArraySubSubs().get(j).getnPrice().toString().equals("0")) {
                    } else {
                        if (!dataSet.get(listPosition).getOrderSummeryPojoArraySubs().get(i).getOrderSummeryPojoArraySubSubs().get(j).getnPrice().toString().equals("null")) {
                            stringBuilder.append(dataSet.get(listPosition).getOrderSummeryPojoArraySubs().get(i).getOrderSummeryPojoArraySubSubs().get(j).getcAttributeValueLabel() + " ( " + header + " ) " + " ( " + CM.getSp(context, "currency", "").toString() + dataSet.get(listPosition).getOrderSummeryPojoArraySubs().get(i).getOrderSummeryPojoArraySubSubs().get(j).getnPrice() + " ) ");
                        } else {
                            stringBuilder.append(dataSet.get(listPosition).getOrderSummeryPojoArraySubs().get(i).getOrderSummeryPojoArraySubSubs().get(j).getcAttributeValueLabel());
                        }
                    }
                    stringBuilder.append(System.getProperty("line.separator"));
                }
            }
        } else {
            addonsLayout.setVisibility(View.GONE);
        }
        if (stringBuilder != null && !stringBuilder.toString().equals("")) {
            mtplTextViewAddOn.setText(stringBuilder);
        } else {
            addonsLayout.setVisibility(View.GONE);
        }

        Double tot = 0.0;
        try {
            if (!CM.getSp(context, "bIsDiscountAfterTax", "").toString().equals("")) {
                if (CM.getSp(context, "bIsDiscountAfterTax", "").toString().equals("true")) {
                    tot = Double.parseDouble(String.valueOf(perPrice)) - dis + tax;
                    itemTotal.setText(CM.getSp(context, "currency", "").toString() + String.valueOf(form.format(tot)));
                } else {
                    tot = Double.parseDouble(String.valueOf(perPrice)) + tax - dis;
                    itemTotal.setText(CM.getSp(context, "currency", "").toString() + String.valueOf(form.format(tot)));
                }
            } else {

            }
        } catch (Exception e) {

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
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}