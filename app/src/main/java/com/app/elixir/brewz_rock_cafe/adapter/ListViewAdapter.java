package com.app.elixir.brewz_rock_cafe.adapter;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.app.elixir.brewz_rock_cafe.Model.CouponCodeModelArray;
import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.activity.ViewOrderSummary;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplTextView;
import com.app.elixir.brewz_rock_cafe.utils.CM;

import java.util.List;

/**
 * Created by NetSupport on 23-11-2016.
 */
public class ListViewAdapter extends ArrayAdapter<CouponCodeModelArray> {

    private List<CouponCodeModelArray> couponCodeModelArrays;
    private Activity activity;
    private int selectedPosition = -1;

    public ListViewAdapter(Activity context, int resource, List<CouponCodeModelArray> couponCodeModelArrays) {
        super(context, resource, couponCodeModelArrays);

        this.activity = context;
        this.couponCodeModelArrays = couponCodeModelArrays;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.item_listview, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        holder.checkBox.setTag(position); // This line is important.

        if (getItem(position).cCategoryName != null) {
            holder.adptCouponCodetitle.setText(getItem(position).cCategoryName);
        } else {
            holder.adptCouponCodetitle.setText("--");
        }

        if (position == selectedPosition) {
            holder.checkBox.setChecked(true);
        } else holder.checkBox.setChecked(false);

        holder.checkBox.setOnClickListener(onStateChangedListener(holder.checkBox, position));


        if (couponCodeModelArrays.get(position).cCouponShortDesc == null) {
            holder.adptCouponCodetitle.setVisibility(View.GONE);
            holder.adptCouponCodetitle.setText("");
        } else {
            if (!couponCodeModelArrays.get(position).cCouponShortDesc.equals("")) {
                holder.adptCouponCodetitle.setVisibility(View.VISIBLE);
                holder.adptCouponCodetitle.setText(couponCodeModelArrays.get(position).cCouponShortDesc.toString().trim());
            } else {
                holder.adptCouponCodetitle.setVisibility(View.GONE);
                holder.adptCouponCodetitle.setText("");
            }


        }
        if (couponCodeModelArrays.get(position).cCouponLongDesc == null) {
            holder.offerDisTextView.setText("");
            holder.offerDisTextView.setVisibility(View.GONE);
        } else {
            if (!couponCodeModelArrays.get(position).cCouponLongDesc.equals("")) {
                holder.offerDisTextView.setText(couponCodeModelArrays.get(position).cCouponLongDesc.toString().trim());
            } else {
                holder.offerDisTextView.setVisibility(View.GONE);
            }
        }
        if (couponCodeModelArrays.get(position).cCouponCode == null) {
            holder.offerCodeTextView.setText("");
            holder.offerCodeTextView.setVisibility(View.GONE);
        } else {
            if (!couponCodeModelArrays.get(position).cCouponCode.equals("")) {
                holder.offerCodeTextView.setText(couponCodeModelArrays.get(position).cCouponCode.toString().trim());
            } else {
                holder.offerCodeTextView.setVisibility(View.GONE);
            }
        }

        if (couponCodeModelArrays.get(position).cSuccessMsg == null) {
            holder.catType.setText("");
            holder.catType.setVisibility(View.GONE);
        } else {

            if (!couponCodeModelArrays.get(position).cSuccessMsg.equals("")) {
                holder.catType.setText(couponCodeModelArrays.get(position).cSuccessMsg.toString().trim());
            } else {
                holder.catType.setVisibility(View.GONE);
            }
        }
        if (couponCodeModelArrays.get(position).cCategoryName == null) {
            holder.adptCouponCodeApplicable.setText("");
            holder.adptCouponCodeApplicable.setVisibility(View.GONE);
        } else {
            if (!couponCodeModelArrays.get(position).cCategoryName.equals("")) {
                holder.adptCouponCodeApplicable.setText("Applicable For " + couponCodeModelArrays.get(position).cCategoryName.toString().trim());
            } else {
                holder.adptCouponCodeApplicable.setVisibility(View.GONE);
            }
        }

        return convertView;
    }

    private View.OnClickListener onStateChangedListener(final CheckBox checkBox, final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    selectedPosition = position;
                    ViewOrderSummary.editText.setEnabled(false);
                    ViewOrderSummary.editText.setText("");
                    CM.setSp(activity, "check", "1");
                    CM.setSp(activity, "checkDate", couponCodeModelArrays.get(position).cCouponCode.toString());
                    //  CM.showToast(activity, String.valueOf(selectedPosition) + "" + couponCodeModelArrays.get(position).cCouponCode);
                } else {
                    selectedPosition = -1;
                    CM.setSp(activity, "check", "0");
                    CM.setSp(activity, "checkDate", "");
                    ViewOrderSummary.editText.setEnabled(true);

                }
                notifyDataSetChanged();
            }
        };
    }

    private static class ViewHolder {

        private CheckBox checkBox;
        public LinearLayout placeHolder;
        public LinearLayout placeNameHolder;
        public MtplTextView offerDisTextView, offerCodeTextView, adptCouponCodetitle, adptCouponCodeApplicable, catType;
        CardView cardView;

        public ViewHolder(View view) {
            checkBox = (CheckBox) view.findViewById(R.id.check);
            adptCouponCodetitle = (MtplTextView) view.findViewById(R.id.adptCouponCodetitle);
            offerDisTextView = (MtplTextView) view.findViewById(R.id.adptCouponCodeDis);
            offerCodeTextView = (MtplTextView) view.findViewById(R.id.adptCouponCode);
            adptCouponCodeApplicable = (MtplTextView) view.findViewById(R.id.adptCouponCodeApplicable);
            catType = (MtplTextView) view.findViewById(R.id.catType);
        }
    }
}
