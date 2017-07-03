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
import com.app.elixir.brewz_rock_cafe.interfac.OnItemClickListenerCustomize;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplTextView;
import com.app.elixir.brewz_rock_cafe.pojo.PojoCustomize;
import com.app.elixir.brewz_rock_cafe.pojo.PojoCustomizeSubPart;

import java.util.ArrayList;

/**
 * Created by Elixir on 09-Aug-2016.
 */
public class adptCustomize extends RecyclerView.Adapter<adptCustomize.MyViewHolder> {


    private ArrayList<PojoCustomize> dataSet;
    Context context;
    public OnItemClickListenerCustomize listener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public LinearLayout placeHolder;
        public LinearLayout placeNameHolder;
        MtplTextView textViewTitle;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            placeHolder = (LinearLayout) itemView.findViewById(R.id.mainHolder);
            placeNameHolder = (LinearLayout) itemView.findViewById(R.id.placeNameHolder);
            textViewTitle = (MtplTextView) itemView.findViewById(R.id.title);
            cardView = (CardView) itemView.findViewById(R.id.placeCard);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.placeNameHolder) {
            } else if (view.getId() == R.id.placeCard) {
                listener.onItemClick(dataSet.get(getAdapterPosition()).getIsMulti(), dataSet.get(getAdapterPosition()).getPojoCustomizeSubParts(), dataSet.get(getAdapterPosition()).getItemName());
            }
        }
    }


    public void SetOnItemClickListener(OnItemClickListenerCustomize mItemClickListener) {

        this.listener = mItemClickListener;
    }

    public adptCustomize(Context context, ArrayList<PojoCustomize> data) {
        this.dataSet = data;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adaptercustomize, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewTitle = holder.textViewTitle;
        ArrayList<PojoCustomizeSubPart> pojoCustomizeSubParts = dataSet.get(listPosition).getPojoCustomizeSubParts();

        if (dataSet.get(listPosition).getItemName() != null) {
            textViewTitle.setText(dataSet.get(listPosition).getItemName() + " " + "(" + pojoCustomizeSubParts.size() + ")" + "  Items");
        } else {
            textViewTitle.setText("0");
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}