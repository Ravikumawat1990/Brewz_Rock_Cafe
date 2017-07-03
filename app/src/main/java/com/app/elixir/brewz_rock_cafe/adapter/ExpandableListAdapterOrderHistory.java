package com.app.elixir.brewz_rock_cafe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplTextView;
import com.app.elixir.brewz_rock_cafe.pojo.OrderSummeryPojo;
import com.app.elixir.brewz_rock_cafe.pojo.OrderSummeryPojoArray;
import com.app.elixir.brewz_rock_cafe.utils.CM;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by NetSupport on 23-11-2016.
 */
public class ExpandableListAdapterOrderHistory extends BaseExpandableListAdapter {

    private Context _context;
    private List<ArrayList<OrderSummeryPojo>> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<ArrayList<OrderSummeryPojo>, List<OrderSummeryPojoArray>> _listDataChild;

    public ExpandableListAdapterOrderHistory(Context context, ArrayList<ArrayList<OrderSummeryPojo>> listDataHeader, HashMap<ArrayList<OrderSummeryPojo>, List<OrderSummeryPojoArray>> listDataChild) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listDataChild;
    }


    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final OrderSummeryPojoArray childText = (OrderSummeryPojoArray) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtTitle = (TextView) convertView.findViewById(R.id.list_item_entry_title);
        TextView txtDesc = (TextView) convertView.findViewById(R.id.list_item_entry_summary);
        TextView txtQuantity = (TextView) convertView.findViewById(R.id.itemQuantity);
        final ImageView imaPlaceHolder = (ImageView) convertView.findViewById(R.id.imgPlaceHolder);
        MtplTextView mtplTextViewAddons = (MtplTextView) convertView.findViewById(R.id.addonstxt);
        MtplTextView mtplTextViewTot = (MtplTextView) convertView.findViewById(R.id.itemTotal);
        LinearLayout linearLayoutAddOns = (LinearLayout) convertView.findViewById(R.id.addOnsLayout);
        LinearLayout linearLayoutTot = (LinearLayout) convertView.findViewById(R.id.totLayout);


        ImageView imageView = (ImageView) convertView.findViewById(R.id.list_item_entry_drawable);

        Glide.with(_context)
                .load(childText.cProductImagePath)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        imaPlaceHolder.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        imaPlaceHolder.setVisibility(View.GONE);
                        return false;
                    }
                }).error(R.drawable.placeholder)
                .into(imageView);


        int perPrice = 0;
        try {
            if (childText.getnAddonTotalAmt() != null) {
                perPrice = (Integer.parseInt(childText.nRate) + Integer.parseInt(childText.getnAddonTotalAmt()));
            } else {
                perPrice = Integer.parseInt(childText.nRate);
            }
        } catch (Exception e) {

        }


        try {
            txtDesc.setText(CM.getSp(_context, "currency", "").toString() + "" + perPrice);
        } catch (Exception e) {

        }
        if (childText.getProductTotalAmount() != null && !childText.getProductTotalAmount().equals("")) {
            try {
                int tot = Integer.parseInt(childText.getProductTotalAmount()) * Integer.parseInt(childText.Qty);
                mtplTextViewTot.setText(CM.getSp(_context, "currency", "").toString() + String.valueOf(tot));
            } catch (Exception e) {

            }
        } else {
            linearLayoutTot.setVisibility(View.GONE);
            mtplTextViewTot.setVisibility(View.GONE);
        }
        txtQuantity.setText(childText.Qty);
        txtTitle.setText(childText.cProductName);
//        childText.getOrderSummeryPojoArraySubs().size();

        StringBuilder stringBuilder = new StringBuilder();

        if (childText.getOrderSummeryPojoArraySubs() != null) {
            for (int i = 0; i < childText.getOrderSummeryPojoArraySubs().size(); i++) {
                String header = childText.getOrderSummeryPojoArraySubs().get(i).cAttributeLabel;
                for (int j = 0; j < childText.getOrderSummeryPojoArraySubs().get(i).getOrderSummeryPojoArraySubSubs().size(); j++) {
                    if (childText.getOrderSummeryPojoArraySubs().get(i).getOrderSummeryPojoArraySubSubs().get(j).getnPrice().toString() != null && childText.getOrderSummeryPojoArraySubs().get(i).getOrderSummeryPojoArraySubSubs().get(j).getnPrice().toString().equals("0")) {
                    } else {
                        if (!childText.getOrderSummeryPojoArraySubs().get(i).getOrderSummeryPojoArraySubSubs().get(j).getnPrice().toString().equals("null")) {
                            stringBuilder.append(childText.getOrderSummeryPojoArraySubs().get(i).getOrderSummeryPojoArraySubSubs().get(j).getcAttributeValueLabel() + " ( " + header + " ) " + " ( " + CM.getSp(_context, "currency", "").toString() + childText.getOrderSummeryPojoArraySubs().get(i).getOrderSummeryPojoArraySubSubs().get(j).getnPrice() + " ) ");
                        } else {
                            stringBuilder.append(childText.getOrderSummeryPojoArraySubs().get(i).getOrderSummeryPojoArraySubSubs().get(j).getcAttributeValueLabel());
                        }
                    }
                    stringBuilder.append(System.getProperty("line.separator"));
                }

            }
        } else {
            mtplTextViewAddons.setVisibility(View.GONE);
            linearLayoutAddOns.setVisibility(View.GONE);
        }

        if (!stringBuilder.toString().equals("")) {
            mtplTextViewAddons.setText(stringBuilder);
        } else {
            mtplTextViewAddons.setVisibility(View.GONE);
            linearLayoutAddOns.setVisibility(View.GONE);
        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        ArrayList<OrderSummeryPojo> headerTitle = (ArrayList<OrderSummeryPojo>) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView txtViewName = (TextView) convertView.findViewById(R.id.txtName);
        TextView txtViewEmail = (TextView) convertView.findViewById(R.id.txtemail);
        TextView txtViewMobile = (TextView) convertView.findViewById(R.id.txtmobile);
        TextView txtViewAddress = (TextView) convertView.findViewById(R.id.txt_adrress);
        TextView txtViewOrderNo = (TextView) convertView.findViewById(R.id.txtOrderNo);
        TextView txtViewOrderDate = (TextView) convertView.findViewById(R.id.txtOrderDateTime);
        TextView txtViewAmount = (TextView) convertView.findViewById(R.id.txt_Amount);
        TextView txtViewOrderStatus = (TextView) convertView.findViewById(R.id.txt_OrderStatus);
        TextView txtViewPayMode = (TextView) convertView.findViewById(R.id.txt_payMode);


        DateFormat readFormat = new SimpleDateFormat("hh:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("hh:mm aaa");
        try {
            //dd/MM/yyyy

            for (int i = 0; i < headerTitle.size(); i++) {

                StringBuilder stringBuilder = new StringBuilder();

                if (headerTitle.get(i).getAddressModels() != null) {
                    for (int p = 0; p < headerTitle.get(i).getAddressModels().size(); p++) {
                        if (!headerTitle.get(i).getAddressModels().get(p).getcAddressLine1().equals("null")) {
                            stringBuilder.append(headerTitle.get(i).getAddressModels().get(p).getcAddressLine1() + ", ");
                            stringBuilder.append(" ");

                        }
                        if (!headerTitle.get(i).getAddressModels().get(p).getcAddressLine2().equals("null")) {
                            stringBuilder.append(headerTitle.get(i).getAddressModels().get(p).getcAddressLine2() + ", ");
                            stringBuilder.append(" ");

                        }
                        if (!headerTitle.get(i).getAddressModels().get(p).getcCity().equals("null")) {
                            if (!headerTitle.get(i).getAddressModels().get(p).getcState().equals("null")) {
                                stringBuilder.append(headerTitle.get(i).getAddressModels().get(p).getcCity() + ", ");
                                stringBuilder.append(" ");
                            } else {
                                stringBuilder.append(headerTitle.get(i).getAddressModels().get(p).getcCity());
                                stringBuilder.append(" ");
                            }
                        }
                        if (!headerTitle.get(i).getAddressModels().get(p).getcState().equals("null")) {
                            if (!headerTitle.get(i).getAddressModels().get(p).getcCountry().equals("null")) {
                                stringBuilder.append(headerTitle.get(i).getAddressModels().get(p).getcState() + ", ");
                                stringBuilder.append(" ");
                            } else {
                                stringBuilder.append(headerTitle.get(i).getAddressModels().get(p).getcState());
                                stringBuilder.append(" ");
                            }
                        }
                        if (!headerTitle.get(i).getAddressModels().get(p).getcCountry().equals("null")) {
                            stringBuilder.append(headerTitle.get(i).getAddressModels().get(p).getcCountry());
                            stringBuilder.append(" ");
                        }
                        stringBuilder.append(System.getProperty("line.separator"));
                        txtViewAddress.setText(stringBuilder);
                        if (headerTitle.get(i).getAddressModels().get(p).getcCustomerName() != null) {
                            txtViewName.setText(headerTitle.get(i).getAddressModels().get(p).getcCustomerName());
                        }

                        if (headerTitle.get(i).getAddressModels().get(p).getcEmail() != null) {
                            txtViewEmail.setText(headerTitle.get(i).getAddressModels().get(p).getcEmail());
                        }

                        if (headerTitle.get(i).getAddressModels().get(p).getcMobile() != null) {
                            txtViewMobile.setText(headerTitle.get(i).getAddressModels().get(p).getcMobile());
                        }

                        if (headerTitle.get(i).getcOrderPaymentStatus() != null) {
                            txtViewOrderStatus.setText(headerTitle.get(i).getcOrderStatus());
                        }

                    }
                }


                if (headerTitle.get(i).cOrderNo != null) {
                    txtViewOrderNo.setText(headerTitle.get(i).cOrderNo);
                }

                if (headerTitle.get(i).dOrderDate != null) {
                    txtViewOrderDate.setText(CM.converDateFormate("yyyy-MM-dd'T'HH:mm:ss", "MMM dd, yyyy", headerTitle.get(i).dOrderDate) + " " + dateFormat.format(readFormat.parse(headerTitle.get(i).dOrderTime)));
                }

                if (headerTitle.get(i).getTotalAmount() != null && !headerTitle.get(i).getTotalAmount().equals("")) {
                    txtViewAmount.setText(CM.getSp(_context, "currency", "").toString() + "" + headerTitle.get(i).getTotalAmount());
                } else {
                    txtViewAmount.setText("0.00");
                }
                if (headerTitle.get(i).getcOrderPaymentMode() != null) {
                    txtViewPayMode.setText(headerTitle.get(i).getcOrderPaymentMode());
                }

            }


        } catch (Exception e) {
            e.getMessage();
        }


        // lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
