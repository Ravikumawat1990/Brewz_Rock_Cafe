package com.app.elixir.brewz_rock_cafe.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.elixir.brewz_rock_cafe.Model.OrderListArray;
import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.interfac.OnItemClickListenerDeliverd;
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
public class adptDeliveryBox extends RecyclerView.Adapter<adptDeliveryBox.MyViewHolder> {


    private ArrayList<OrderListArray> dataSet;
    Context context;
    public OnItemClickListenerDeliverd listener;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public LinearLayout placeHolder;
        public TextView orderDetail, orderPersonNo, orderAmount, orderAddress, orderNo;
        MtplTextView orderTime, orderDate;
        public ImageView placeImage;
        MtplButton btnShowMap;
        CardView cardView;
        MtplButton mtplButtonDeliverd;


        public MyViewHolder(View itemView) {
            super(itemView);
            placeHolder = (LinearLayout) itemView.findViewById(R.id.mainHolder);
            orderDetail = (TextView) itemView.findViewById(R.id.orderDetail);
            orderPersonNo = (TextView) itemView.findViewById(R.id.orderPersonNo);
            orderAmount = (TextView) itemView.findViewById(R.id.orderAmount);
            orderAddress = (TextView) itemView.findViewById(R.id.orderAddress);
            cardView = (CardView) itemView.findViewById(R.id.placeCard);
            btnShowMap = (MtplButton) itemView.findViewById(R.id.btnShowMap);
            orderNo = (TextView) itemView.findViewById(R.id.orderNo);
            mtplButtonDeliverd = (MtplButton) itemView.findViewById(R.id.btnDeliverd);
            orderTime = (MtplTextView) itemView.findViewById(R.id.orderTime);
            orderDate = (MtplTextView) itemView.findViewById(R.id.orderDate);
            cardView.setOnClickListener(this);
            btnShowMap.setOnClickListener(this);
            mtplButtonDeliverd.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.placeCard) {
                listener.onItemClick(dataSet.get(getAdapterPosition()).nOrderID, "detail", "");
            } else if (view.getId() == R.id.btnShowMap) {
                listener.onItemClick(dataSet.get(getAdapterPosition()).nOrderID, "map", dataSet.get(getAdapterPosition()).cAddress);
            } else if (view.getId() == R.id.btnDeliverd) {
                listener.onItemClick(dataSet.get(getAdapterPosition()).nOrderID, "view", dataSet.get(getAdapterPosition()).cAddress);
            }
        }
    }


    public void SetOnItemClickListener(OnItemClickListenerDeliverd mItemClickListener) {

        this.listener = mItemClickListener;
    }

    public adptDeliveryBox(Context context, ArrayList<OrderListArray> data) {
        this.dataSet = data;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapterdeliverybox, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int listPosition) {

        TextView textViewOrderDetail = holder.orderDetail;
        TextView orderPersonNo = holder.orderPersonNo;
        TextView orderAmount = holder.orderAmount;
        TextView orderAddress = holder.orderAddress;
        TextView orderNo = holder.orderNo;
        MtplButton mtplButton = holder.mtplButtonDeliverd;
        CardView cardView = holder.cardView;
        MtplTextView mtplTextViewDate = holder.orderDate;
        MtplTextView mtplTextViewTime = holder.orderTime;
        textViewOrderDetail.setText(CM.getColoredSpanned(dataSet.get(listPosition).cCustomerName, "#000000"));


        //String text = "<font color='black'>Mobile No. : </font>." + dataSet.get(listPosition).cMobile + "";
        //Html.fromHtml("<font color='black'>Delivery Time : </font>." + dateFormat.format(readFormat.parse(dataSet.get(listPosition).dOrderTime)) + "")
        orderPersonNo.setText(dataSet.get(listPosition).cMobile);
        orderAmount.setText(CM.getSp(context, "currency", "").toString() + dataSet.get(listPosition).nAmount);

        // "<font color='black'>Pay Amount : </font>. <font color=#ffcc00> " + context.getString(R.string.cur_symbol) + dataSet.get(listPosition).nAmount + "") + "</font>"

        orderAddress.setText(dataSet.get(listPosition).cAddress);
        orderAddress.setSelected(true);
        orderNo.setText(dataSet.get(listPosition).cOrderNo);
        // 2016-10-18T00:00:00
        if (dataSet.get(listPosition).dOrderDate != null && !dataSet.get(listPosition).dOrderDate.equals(""))
            mtplTextViewTime.setText(CM.converDateFormate("yyyy-MM-dd'T'HH:mm:ss", "dd-MM-yyyy", dataSet.get(listPosition).dOrderDate));
        else {
            mtplTextViewTime.setText("Delivery Date : " + "--");
        }


        DateFormat readFormat = new SimpleDateFormat("hh:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("hh:mm aaa");

        if (dataSet.get(listPosition).dOrderTime != null && !dataSet.get(listPosition).dOrderTime.equals("")) {
            try {
                mtplTextViewDate.setText(dateFormat.format(readFormat.parse(dataSet.get(listPosition).dOrderTime)));
            } catch (ParseException e) {
                e.printStackTrace();
                mtplTextViewDate.setText("Delivery Time : " + "--");
            }
        } else {
            mtplTextViewDate.setText("Delivery Time : " + "--");
        }


    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}