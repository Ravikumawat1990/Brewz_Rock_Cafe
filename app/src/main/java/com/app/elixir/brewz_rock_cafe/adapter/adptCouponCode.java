package com.app.elixir.brewz_rock_cafe.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.interfac.OnItemClickListener;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplTextView;

import java.util.ArrayList;

import com.app.elixir.brewz_rock_cafe.Model.CouponCodeModelArray;


/**
 * Created by Elixir on 09-Aug-2016.
 */
public class adptCouponCode extends RecyclerView.Adapter<adptCouponCode.MyViewHolder> {


    private ArrayList<CouponCodeModelArray> dataSet;
    Context context;
    public OnItemClickListener listener;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public LinearLayout placeHolder;
        public LinearLayout placeNameHolder;
        public MtplTextView offerDisTextView, offerCodeTextView, adptCouponCodetitle, adptCouponCodeApplicable, catType;
        MtplTextView mtplButtonCopytoClip;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            adptCouponCodetitle = (MtplTextView) itemView.findViewById(R.id.adptCouponCodetitle);
            offerDisTextView = (MtplTextView) itemView.findViewById(R.id.adptCouponCodeDis);
            offerCodeTextView = (MtplTextView) itemView.findViewById(R.id.adptCouponCode);
            adptCouponCodeApplicable = (MtplTextView) itemView.findViewById(R.id.adptCouponCodeApplicable);
            catType = (MtplTextView) itemView.findViewById(R.id.catType);
            cardView = (CardView) itemView.findViewById(R.id.placeCard);
            offerCodeTextView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(dataSet.get(getAdapterPosition()).cCouponCode);
        }
    }


    public void SetOnItemClickListener(OnItemClickListener mItemClickListener) {

        this.listener = mItemClickListener;
    }

    public adptCouponCode(Context context, ArrayList<CouponCodeModelArray> data) {
        this.dataSet = data;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adaptercouponcode, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        TextView textViewCodeDis = holder.offerDisTextView;
        TextView offerCodeTextView = holder.offerCodeTextView;
        MtplTextView adptCouponCodetitle = holder.adptCouponCodetitle;
        MtplTextView catType = holder.catType;
        MtplTextView adptCouponCodeApplicable = holder.adptCouponCodeApplicable;

        if (dataSet.get(listPosition).cCouponShortDesc == null) {
            adptCouponCodetitle.setVisibility(View.GONE);
            adptCouponCodetitle.setText("");
        } else {
            if (!dataSet.get(listPosition).cCouponShortDesc.equals("")) {
                adptCouponCodetitle.setVisibility(View.VISIBLE);
                adptCouponCodetitle.setText(dataSet.get(listPosition).cCouponShortDesc.toString().trim());
            } else {
                adptCouponCodetitle.setVisibility(View.GONE);
                adptCouponCodetitle.setText("");
            }


        }
        if (dataSet.get(listPosition).cCouponLongDesc == null) {
            textViewCodeDis.setText("");
            textViewCodeDis.setVisibility(View.GONE);
        } else {
            if (!dataSet.get(listPosition).cCouponLongDesc.equals("")) {
                textViewCodeDis.setText(dataSet.get(listPosition).cCouponLongDesc.toString().trim());
            } else {
                textViewCodeDis.setVisibility(View.GONE);
            }
        }
        if (dataSet.get(listPosition).cCouponCode == null) {
            offerCodeTextView.setText("");
            offerCodeTextView.setVisibility(View.GONE);
        } else {
            if (!dataSet.get(listPosition).cCouponCode.equals("")) {
                offerCodeTextView.setText(dataSet.get(listPosition).cCouponCode.toString().trim());
            } else {
                offerCodeTextView.setVisibility(View.GONE);
            }
        }

        if (dataSet.get(listPosition).cSuccessMsg == null) {
            catType.setText("");
            catType.setVisibility(View.GONE);
        } else {

            if (!dataSet.get(listPosition).cSuccessMsg.equals("")) {
                catType.setText(dataSet.get(listPosition).cSuccessMsg.toString().trim());
            } else {
                catType.setVisibility(View.GONE);
            }
        }
        if (dataSet.get(listPosition).cCategoryName == null) {
            adptCouponCodeApplicable.setText("");
            adptCouponCodeApplicable.setVisibility(View.GONE);
        } else {
            if (!dataSet.get(listPosition).cCategoryName.equals("")) {
                adptCouponCodeApplicable.setText("Applicable For " + dataSet.get(listPosition).cCategoryName.toString().trim());
            } else {
                adptCouponCodeApplicable.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}