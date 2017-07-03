package com.app.elixir.brewz_rock_cafe.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.elixir.brewz_rock_cafe.Model.PastOrderModelArray;
import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.interfac.OnItemClickListener;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplButton;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplTextView;
import com.app.elixir.brewz_rock_cafe.utils.CM;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Elixir on 09-Aug-2016.
 */
public class adptPastDelivery extends RecyclerView.Adapter<adptPastDelivery.MyViewHolder> {


    private ArrayList<PastOrderModelArray> dataSet;
    Context context;
    public OnItemClickListener listener;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public LinearLayout placeHolder;
        public LinearLayout placeNameHolder;
        public TextView addressTextView, numberTextView, dateTextView, textViewOrderNo, textViewCustName, orderTime;
        MtplButton mtplButtonViewDetail;
        MtplTextView amount;
        MtplTextView mtplTextViewOrderStatus;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            placeHolder = (LinearLayout) itemView.findViewById(R.id.mainHolder);
            addressTextView = (TextView) itemView.findViewById(R.id.adptPastdeliveryaddress);
            numberTextView = (TextView) itemView.findViewById(R.id.adptpastdeliveryNumber);
            dateTextView = (TextView) itemView.findViewById(R.id.adptpastdeliveryDate);
            placeNameHolder = (LinearLayout) itemView.findViewById(R.id.placeNameHolder);
            textViewOrderNo = (MtplTextView) itemView.findViewById(R.id.adptpastdeliveryOrderNo);
            mtplButtonViewDetail = (MtplButton) itemView.findViewById(R.id.adptpastdeliveryViewDetail);
            textViewCustName = (MtplTextView) itemView.findViewById(R.id.adptpastdeliveryCustNumber);
            mtplTextViewOrderStatus = (MtplTextView) itemView.findViewById(R.id.adptpastdeliveryOrderStatus);
            amount = (MtplTextView) itemView.findViewById(R.id.adptpastdeliveryPayedAmount);
            cardView = (CardView) itemView.findViewById(R.id.placeCard);
            orderTime = (TextView) itemView.findViewById(R.id.adptpastdeliveryTime);
            mtplButtonViewDetail.setOnClickListener(this);
            mtplButtonViewDetail.setVisibility(View.GONE);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(dataSet.get(getAdapterPosition()).nOrderID);
        }
    }


    public void SetOnItemClickListener(OnItemClickListener mItemClickListener) {

        this.listener = mItemClickListener;
    }

    public adptPastDelivery(Context context, ArrayList<PastOrderModelArray> data) {
        this.dataSet = data;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapterpastdelixery, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        TextView address = holder.addressTextView;
        TextView number = holder.numberTextView;
        TextView date = holder.dateTextView;
        TextView orderNo = holder.textViewOrderNo;
        TextView custName = holder.textViewCustName;
        MtplTextView orderStatus = holder.mtplTextViewOrderStatus;
        MtplTextView payAmount = holder.amount;

        TextView orderTime = holder.orderTime;
        // "<font color='black'>Order Time : </font>. <font color=#ffcc00> " + dateFormat.format(readFormat.parse(dataSet.get(listPosition).dOrderTime)) + "</font>"

        if (dataSet.get(listPosition).cAddress != null) {
            address.setText(Html.fromHtml("<font color='black'>Address : </font>" + dataSet.get(listPosition).cAddress));
        } else {
            address.setText(Html.fromHtml("<font color='black'>Address : </font>" + "--"));
        }

        if (dataSet.get(listPosition).cMobile != null) {
            number.setText(Html.fromHtml("<font color='black'>Mobile No. : </font>" + dataSet.get(listPosition).cMobile));
        } else {
            number.setText(Html.fromHtml("<font color='black'>Mobile No. : </font>" + "--"));
        }


        if (dataSet.get(listPosition).dOrderDate != null && !dataSet.get(listPosition).dOrderDate.equals(""))
            date.setText(Html.fromHtml("<font color='black'>Order Date : </font>" + CM.converDateFormate("yyyy-MM-dd'T'HH:mm:ss", "dd-MM-yyyy", dataSet.get(listPosition).dOrderDate)));
        else {
            date.setText("Order Date : " + "--");
        }
        // date.setText("Date :" + dataSet.get(listPosition).dOrderDate);
        if (dataSet.get(listPosition).cOrderNo != null) {
            orderNo.setText(Html.fromHtml("<font color='black'>Order No. : </font>" + dataSet.get(listPosition).cOrderNo));
        } else {
            orderNo.setText(Html.fromHtml("<font color='black'>Order No. : </font>" + "--"));
        }

        if (dataSet.get(listPosition).cCustomerName != null) {
            custName.setText(CM.getColoredSpanned(dataSet.get(listPosition).cCustomerName, "#000000"));
        } else {
            custName.setText(CM.getColoredSpanned("--", "#000000"));
        }

        if (dataSet.get(listPosition).cOrderStatus != null) {
            if (!dataSet.get(listPosition).cOrderStatus.toString().equals("")) {
                orderStatus.setText(Html.fromHtml("<font color='black'>Order Status : </font>" + dataSet.get(listPosition).cOrderStatus));
            } else {
                orderStatus.setText(Html.fromHtml("<font color='black'>Order Status : </font>" + "--"));
            }

        } else {
            orderStatus.setText(Html.fromHtml("<font color='black'>Order Status : </font>" + "--"));
        }


        if (dataSet.get(listPosition).nAmount != null) {
            payAmount.setText(Html.fromHtml("<font color='black'>Payed Amount : </font> <font color=red>" + CM.getSp(context, "currency", "").toString() + dataSet.get(listPosition).nAmount + "</font>"));
        } else {
            payAmount.setText(Html.fromHtml("<font color='black'>Payed Amount : </font> <font color=red>" + CM.getSp(context, "currency", "").toString() + "--" + "</font>"));
        }


        DateFormat readFormat = new SimpleDateFormat("hh:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("hh:mm aaa");

        try {
            if (dataSet.get(listPosition).dOrderTime != null) {
                orderTime.setText(Html.fromHtml("<font color='black'>Order Time : </font>" + dateFormat.format(readFormat.parse(dataSet.get(listPosition).dOrderTime))));
            } else {
                orderTime.setText(Html.fromHtml("<font color='black'>Order Time : </font>" + "--"));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}