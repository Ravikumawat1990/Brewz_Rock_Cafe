package com.app.elixir.brewz_rock_cafe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.activity.ViewCustomize;
import com.app.elixir.brewz_rock_cafe.database.ItemDetail;
import com.app.elixir.brewz_rock_cafe.database.Items;
import com.app.elixir.brewz_rock_cafe.interfac.OnItemClickListenerCust;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplTextView;
import com.app.elixir.brewz_rock_cafe.pojo.PojoArray;
import com.app.elixir.brewz_rock_cafe.pojo.PojoItemDetail;
import com.app.elixir.brewz_rock_cafe.pojo.PojoItems;
import com.app.elixir.brewz_rock_cafe.utils.CM;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapterNotCustomize extends BaseExpandableListAdapter implements View.OnClickListener {

    private Context _context;
    private List<String> _listDataHeader;
    private HashMap<String, ArrayList<PojoArray>> _listDataChild;

    public OnItemClickListenerCust listener;

    String checkedStatus;
    String nMapperDetailID;
    String nMapperID;
    String price;


    public ExpandableListAdapterNotCustomize(Context context, List<String> listDataHeader,
                                             HashMap<String, ArrayList<PojoArray>> listDataChild1) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listDataChild1;

    }

    public void SetOnItemClickListener(OnItemClickListenerCust mItemClickListener) {

        this.listener = mItemClickListener;
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
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ViewHolderChild viewHolderChild = new ViewHolderChild();
        final PojoArray childText = (PojoArray) getChild(groupPosition, childPosition);
        String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_footer, null);
            viewHolderChild.radioButton = (RadioButton) convertView.findViewById(R.id.btn_radioButton);
            viewHolderChild.checkBox = (CheckBox) convertView.findViewById(R.id.btn_checkBox);
            viewHolderChild.textViewFooter = (MtplTextView) convertView.findViewById(R.id.footer_name);
            viewHolderChild.checkBox.setOnClickListener(this);
            //  viewHolderChild.radioButton.setOnClickListener(this);
            convertView.setTag(viewHolderChild);
        } else {
            viewHolderChild = (ViewHolderChild) convertView.getTag();
        }
        if (childText.getIsMulti().equals("true")) {
            viewHolderChild.radioButton.setVisibility(View.GONE);
            viewHolderChild.checkBox.setVisibility(View.VISIBLE);

            if (childText.getIsChecked().equals("0")) {
                viewHolderChild.checkBox.setChecked(false);

            } else {
                viewHolderChild.checkBox.setChecked(true);
            }

        } else {
            viewHolderChild.checkBox.setVisibility(View.GONE);
            viewHolderChild.radioButton.setVisibility(View.VISIBLE);

            if (childText.getIsChecked().equals("0")) {
                viewHolderChild.radioButton.setChecked(false);
            } else {
                viewHolderChild.radioButton.setChecked(true);
            }

        }

        final ViewHolderChild finalViewHolderChild1 = viewHolderChild;
        viewHolderChild.radioButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (childText.getIsChecked().equals("0")) {
                    checkedStatus = "1";
                    nMapperDetailID = childText.getnMapperDetailID();
                    nMapperID = childText.getnMapperID();
                    price = childText.getnPrice();
                    CM.setSp(_context, "price", price);
                    finalViewHolderChild1.radioButton.setChecked(true);
                    ItemDetail.setUncheckedRadioButton(checkedStatus, nMapperDetailID, nMapperID);
                    ItemDetail.updateRadioButton(checkedStatus, nMapperDetailID, nMapperID);

                    String tot1 = ItemDetail.getAllCheckeditemRecord();
                    try {
                        double d = (double) Float.parseFloat(tot1);
                        Double aFloat;
                        if (!CM.getSp(_context, "quantity", "").toString().equals("")) {
                            aFloat = ((Double.parseDouble(CM.getSp(_context, "itemPrice", "").toString()) + d) * Double.parseDouble(CM.getSp(_context, "quantity", "").toString()));
                        } else {
                            aFloat = ((Double.parseDouble(CM.getSp(_context, "itemPrice", "").toString()) + d));
                        }
                        DecimalFormat form = new DecimalFormat("0.00");
                        ViewCustomize.item.setText(CM.getSp(_context, "currency", "").toString() + String.valueOf(form.format(aFloat)));
                    } catch (Exception e) {
                    }
                    _listDataChild.clear();
                    _listDataHeader.clear();
                    ArrayList<String> listDataHeaderStrings = new ArrayList<String>();
                    HashMap<String, ArrayList<PojoArray>> stringArrayListHashMap = new HashMap<>();
                    listDataHeaderStrings.clear();
                    stringArrayListHashMap.clear();
                    ArrayList<PojoItems> pojoItemses1 = Items.getAlldataNotReruird();
                    for (int j = 0; j < pojoItemses1.size(); j++) {
                        listDataHeaderStrings.add(pojoItemses1.get(j).getcAttributeLabel());
                        // String header = pojoItemses1.get(j).getcAttributeLabel();
                        ArrayList<PojoItemDetail> pojoItemDetails1 = ItemDetail.getSelectedIdRecord(pojoItemses1.get(j).getnMapperID());
                        ArrayList<PojoArray> pojoArrays = new ArrayList<>();
                        pojoArrays.clear();
                        for (int i = 0; i < pojoItemDetails1.size(); i++) {
                            PojoArray pojoArray = new PojoArray();
                            pojoArray.setnAttributeID(pojoItemDetails1.get(i).getnAttributeID());
                            pojoArray.setcAttributeLabel(pojoItemDetails1.get(i).getcAttributeLabel());
                            pojoArray.setnMapperDetailID(pojoItemDetails1.get(i).getnMapperDetailID());
                            pojoArray.setnMapperID(pojoItemDetails1.get(i).getnMapperID());
                            pojoArray.setcAttributeValueLabel(pojoItemDetails1.get(i).getcAttributeValueLabel());
                            pojoArray.setnAttributeValueMasterID(pojoItemDetails1.get(i).getnAttributeValueMasterID());
                            pojoArray.setnPrice(pojoItemDetails1.get(i).getnPrice());
                            pojoArray.setIsChecked(pojoItemDetails1.get(i).getIsChecked());
                            pojoArray.setIsMulti(pojoItemDetails1.get(i).getIsMulti());
                            pojoArray.setHeader(pojoItemDetails1.get(i).getHeader());
                            pojoArrays.add(pojoArray);
                        }
                        stringArrayListHashMap.put(pojoItemses1.get(j).getcAttributeLabel(), pojoArrays);
                    }
                    _listDataChild = stringArrayListHashMap;
                    _listDataHeader = listDataHeaderStrings;
                    ViewCustomize.expandableListAdapterNot.notifyDataSetChanged();
                    ViewCustomize.expListView1.invalidateViews();


                } else if (childText.getIsChecked().equals("1")) {
                    checkedStatus = "0";
                    nMapperDetailID = childText.getnMapperDetailID();
                    nMapperID = childText.getnMapperID();
                    price = childText.getnPrice();
                    CM.setSp(_context, "price", price);
                    finalViewHolderChild1.radioButton.setChecked(false);


                    ItemDetail.setUncheckedRadioButton(checkedStatus, nMapperDetailID, nMapperID);
                    ItemDetail.updateRadioButton(checkedStatus, nMapperDetailID, nMapperID);
                    String tot1 = ItemDetail.getAllCheckeditemRecord();
                    try {
                        double d = (double) Float.parseFloat(tot1);
                        Double aFloat;
                        if (!CM.getSp(_context, "quantity", "").toString().equals("")) {
                            aFloat = ((Double.parseDouble(CM.getSp(_context, "itemPrice", "").toString()) + d) * Double.parseDouble(CM.getSp(_context, "quantity", "").toString()));
                        } else {
                            aFloat = ((Double.parseDouble(CM.getSp(_context, "itemPrice", "").toString()) + d));
                        }
                        DecimalFormat form = new DecimalFormat("0.00");
                        ViewCustomize.item.setText(CM.getSp(_context, "currency", "").toString() + String.valueOf(form.format(aFloat)));
                    } catch (Exception e) {
                    }
                    _listDataChild.clear();
                    _listDataHeader.clear();
                    ArrayList<String> listDataHeaderStrings = new ArrayList<String>();
                    HashMap<String, ArrayList<PojoArray>> stringArrayListHashMap = new HashMap<>();
                    listDataHeaderStrings.clear();
                    stringArrayListHashMap.clear();
                    ArrayList<PojoItems> pojoItemses1 = Items.getAlldataNotReruird();
                    for (int j = 0; j < pojoItemses1.size(); j++) {
                        listDataHeaderStrings.add(pojoItemses1.get(j).getcAttributeLabel());
                        String header = pojoItemses1.get(j).getcAttributeLabel();
                        ArrayList<PojoItemDetail> pojoItemDetails1 = ItemDetail.getSelectedIdRecord(pojoItemses1.get(j).getnMapperID());
                        ArrayList<PojoArray> pojoArrays = new ArrayList<>();
                        pojoArrays.clear();
                        for (int i = 0; i < pojoItemDetails1.size(); i++) {
                            PojoArray pojoArray = new PojoArray();
                            pojoArray.setnAttributeID(pojoItemDetails1.get(i).getnAttributeID());
                            pojoArray.setcAttributeLabel(pojoItemDetails1.get(i).getcAttributeLabel());
                            pojoArray.setnMapperDetailID(pojoItemDetails1.get(i).getnMapperDetailID());
                            pojoArray.setnMapperID(pojoItemDetails1.get(i).getnMapperID());
                            pojoArray.setcAttributeValueLabel(pojoItemDetails1.get(i).getcAttributeValueLabel());
                            pojoArray.setnAttributeValueMasterID(pojoItemDetails1.get(i).getnAttributeValueMasterID());
                            pojoArray.setnPrice(pojoItemDetails1.get(i).getnPrice());
                            pojoArray.setIsChecked(pojoItemDetails1.get(i).getIsChecked());
                            pojoArray.setIsMulti(pojoItemDetails1.get(i).getIsMulti());
                            pojoArray.setHeader(pojoItemDetails1.get(i).getHeader());
                            pojoArrays.add(pojoArray);
                        }
                        stringArrayListHashMap.put(pojoItemses1.get(j).getcAttributeLabel(), pojoArrays);
                    }
                    _listDataChild = stringArrayListHashMap;
                    _listDataHeader = listDataHeaderStrings;
                    ViewCustomize.expandableListAdapterNot.notifyDataSetChanged();

                    ViewCustomize.expListView1.invalidateViews();
                }
            }
        });


        final ViewHolderChild finalViewHolderChild = viewHolderChild;
        viewHolderChild.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    finalViewHolderChild.checkBox.setChecked(true);
                    checkedStatus = "1";
                    nMapperDetailID = childText.getnMapperDetailID();
                    nMapperID = childText.getnMapperID();
                    price = childText.getnPrice();
                    CM.setSp(_context, "price", price);
                } else {
                    finalViewHolderChild.checkBox.setChecked(false);
                    checkedStatus = "0";
                    nMapperDetailID = childText.getnMapperDetailID();
                    nMapperID = childText.getnMapperID();
                    price = childText.getnPrice();
                    CM.setSp(_context, "price", price);
                }
            }
        });
        viewHolderChild.textViewFooter.setText(childText.getcAttributeValueLabel());
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

    // Header
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_header, null);
        }
        TextView lblListHeader = (TextView) convertView.findViewById(R.id.header_title);
        lblListHeader.setText(headerTitle);
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

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_checkBox:
                ItemDetail.updateCheckBox(checkedStatus, nMapperDetailID, nMapperID);
                String tot = ItemDetail.getAllCheckeditemRecord();
                try {
                    double d = (double) Float.parseFloat(tot);
                    Double aFloat;
                    if (!CM.getSp(_context, "quantity", "").toString().equals("")) {
                        aFloat = ((Double.parseDouble(CM.getSp(_context, "itemPrice", "").toString()) + d) * Double.parseDouble(CM.getSp(_context, "quantity", "").toString()));
                    } else {
                        aFloat = ((Double.parseDouble(CM.getSp(_context, "itemPrice", "").toString()) + d));
                    }
                    DecimalFormat form = new DecimalFormat("0.00");
                    ViewCustomize.item.setText(CM.getSp(_context, "currency", "").toString() + String.valueOf(form.format(aFloat)));
                } catch (Exception e) {
                }

                _listDataChild.clear();
                _listDataHeader.clear();
                ArrayList<String> listDataHeader = new ArrayList<String>();
                HashMap<String, ArrayList<PojoArray>> listDataChild1 = new HashMap<>();
                listDataHeader.clear();
                listDataChild1.clear();
                ArrayList<PojoItems> pojoItemses = Items.getAlldataNotReruird();
                for (int j = 0; j < pojoItemses.size(); j++) {
                    listDataHeader.add(pojoItemses.get(j).getcAttributeLabel());
                    ArrayList<PojoItemDetail> pojoItemDetails1 = ItemDetail.getSelectedIdRecord(pojoItemses.get(j).getnMapperID());
                    ArrayList<PojoArray> pojoArrays = new ArrayList<>();
                    pojoArrays.clear();
                    for (int i = 0; i < pojoItemDetails1.size(); i++) {
                        PojoArray pojoArray = new PojoArray();
                        pojoArray.setnAttributeID(pojoItemDetails1.get(i).getnAttributeID());
                        pojoArray.setcAttributeLabel(pojoItemDetails1.get(i).getcAttributeLabel());
                        pojoArray.setnMapperDetailID(pojoItemDetails1.get(i).getnMapperDetailID());
                        pojoArray.setnMapperID(pojoItemDetails1.get(i).getnMapperID());
                        pojoArray.setcAttributeValueLabel(pojoItemDetails1.get(i).getcAttributeValueLabel());
                        pojoArray.setnAttributeValueMasterID(pojoItemDetails1.get(i).getnAttributeValueMasterID());
                        pojoArray.setnPrice(pojoItemDetails1.get(i).getnPrice());
                        pojoArray.setIsChecked(pojoItemDetails1.get(i).getIsChecked());
                        pojoArray.setIsMulti(pojoItemDetails1.get(i).getIsMulti());
                        pojoArray.setHeader(pojoItemDetails1.get(i).getHeader());
                        pojoArrays.add(pojoArray);
                    }
                    listDataChild1.put(pojoItemses.get(j).getcAttributeLabel(), pojoArrays);
                }
                _listDataChild = listDataChild1;
                _listDataHeader = listDataHeader;
                ViewCustomize.expandableListAdapterNot.notifyDataSetChanged();
                ViewCustomize.expListView1.invalidateViews();
                break;
            case R.id.btn_radioButton:
                ItemDetail.setUncheckedRadioButton(checkedStatus, nMapperDetailID, nMapperID);
                ItemDetail.updateRadioButton(checkedStatus, nMapperDetailID, nMapperID);

                String tot1 = ItemDetail.getAllCheckeditemRecord();
                try {
                    double d = (double) Float.parseFloat(tot1);
                    Double aFloat;
                    if (!CM.getSp(_context, "quantity", "").toString().equals("")) {
                        aFloat = ((Double.parseDouble(CM.getSp(_context, "itemPrice", "").toString()) + d) * Double.parseDouble(CM.getSp(_context, "quantity", "").toString()));
                    } else {
                        aFloat = ((Double.parseDouble(CM.getSp(_context, "itemPrice", "").toString()) + d));
                    }
                    DecimalFormat form = new DecimalFormat("0.00");
                    ViewCustomize.item.setText(CM.getSp(_context, "currency", "").toString() + String.valueOf(form.format(aFloat)));
                } catch (Exception e) {
                }

                _listDataChild.clear();
                _listDataHeader.clear();
                ArrayList<String> listDataHeaderStrings = new ArrayList<String>();
                HashMap<String, ArrayList<PojoArray>> stringArrayListHashMap = new HashMap<>();
                listDataHeaderStrings.clear();
                stringArrayListHashMap.clear();
                ArrayList<PojoItems> pojoItemses1 = Items.getAlldataNotReruird();
                for (int j = 0; j < pojoItemses1.size(); j++) {
                    listDataHeaderStrings.add(pojoItemses1.get(j).getcAttributeLabel());
                    ArrayList<PojoItemDetail> pojoItemDetails1 = ItemDetail.getSelectedIdRecord(pojoItemses1.get(j).getnMapperID());
                    ArrayList<PojoArray> pojoArrays = new ArrayList<>();
                    pojoArrays.clear();
                    for (int i = 0; i < pojoItemDetails1.size(); i++) {
                        PojoArray pojoArray = new PojoArray();
                        pojoArray.setnAttributeID(pojoItemDetails1.get(i).getnAttributeID());
                        pojoArray.setcAttributeLabel(pojoItemDetails1.get(i).getcAttributeLabel());
                        pojoArray.setnMapperDetailID(pojoItemDetails1.get(i).getnMapperDetailID());
                        pojoArray.setnMapperID(pojoItemDetails1.get(i).getnMapperID());
                        pojoArray.setcAttributeValueLabel(pojoItemDetails1.get(i).getcAttributeValueLabel());
                        pojoArray.setnAttributeValueMasterID(pojoItemDetails1.get(i).getnAttributeValueMasterID());
                        pojoArray.setnPrice(pojoItemDetails1.get(i).getnPrice());
                        pojoArray.setIsChecked(pojoItemDetails1.get(i).getIsChecked());
                        pojoArray.setIsMulti(pojoItemDetails1.get(i).getIsMulti());
                        pojoArray.setHeader(pojoItemDetails1.get(i).getHeader());
                        pojoArrays.add(pojoArray);
                    }
                    stringArrayListHashMap.put(pojoItemses1.get(j).getcAttributeLabel(), pojoArrays);
                }
                _listDataChild = stringArrayListHashMap;
                _listDataHeader = listDataHeaderStrings;
                ViewCustomize.expandableListAdapterNot.notifyDataSetChanged();
                ViewCustomize.expListView1.invalidateViews();
                break;
        }
    }

    private class ViewHolderChild {
        public CheckBox checkBox;
        public RadioButton radioButton;
        public MtplTextView textViewFooter;
    }


}