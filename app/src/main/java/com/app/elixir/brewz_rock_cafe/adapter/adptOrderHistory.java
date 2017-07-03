package com.app.elixir.brewz_rock_cafe.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.interfac.OnItemClickListener;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplButton;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplTextView;
import com.app.elixir.brewz_rock_cafe.pojo.OrderSummeryPojo;

import java.util.ArrayList;

/**
 * Created by Elixir on 09-Aug-2016.
 */
public class adptOrderHistory extends RecyclerView.Adapter<adptOrderHistory.MyViewHolder> {


    private ArrayList<OrderSummeryPojo> dataSet;
    Context context;
    public OnItemClickListener listener;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public LinearLayout placeHolder;
        public LinearLayout placeNameHolder;
        public MtplTextView orderTileTextView, orderPriceTextView, orderAddressTextView, orderPhoneNoTextView;
        public ImageView placeImage;
        MtplButton mtplButtonSubmit;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            placeHolder = (LinearLayout) itemView.findViewById(R.id.mainHolder);
            orderTileTextView = (MtplTextView) itemView.findViewById(R.id.orderHistName);
            orderPriceTextView = (MtplTextView) itemView.findViewById(R.id.orderHistAmout);
            orderAddressTextView = (MtplTextView) itemView.findViewById(R.id.orderHistAddress);
            orderPhoneNoTextView = (MtplTextView) itemView.findViewById(R.id.orderHistPno);
            placeNameHolder = (LinearLayout) itemView.findViewById(R.id.placeNameHolder);
            placeImage = (ImageView) itemView.findViewById(R.id.placeImage);
            mtplButtonSubmit = (MtplButton) itemView.findViewById(R.id.orderHistbtnDtail);
            cardView = (CardView) itemView.findViewById(R.id.placeCard);
            //   cardView.setOnClickListener(this);
            mtplButtonSubmit.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(dataSet.get(getAdapterPosition()).getOrderSummeryPojoArrays().get(getAdapterPosition()).cProductName);
        }
    }


    public void SetOnItemClickListener(OnItemClickListener mItemClickListener) {

        this.listener = mItemClickListener;
    }

    public adptOrderHistory(Context context, ArrayList<OrderSummeryPojo> data) {
        this.dataSet = data;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapterorderhistory, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int listPosition) {

        MtplTextView textViewOrderName = holder.orderTileTextView;
        MtplTextView textViewPrice = holder.orderPriceTextView;
        MtplTextView textViewAddress = holder.orderAddressTextView;
        MtplTextView textViewPno = holder.orderPhoneNoTextView;
        //  textViewOrderName.setText(.get(listPosition).cProductName);
        if (dataSet.get(listPosition).dOrderDate != null) {
            textViewPrice.setText(dataSet.get(listPosition).dOrderDate);

        }
        if (dataSet.get(listPosition).dOrderTime != null) {
            textViewAddress.setText(dataSet.get(listPosition).dOrderTime);
        }

        if (dataSet.get(listPosition).TotalAmount != null) {
            textViewPno.setText(dataSet.get(listPosition).TotalAmount);
        }

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}