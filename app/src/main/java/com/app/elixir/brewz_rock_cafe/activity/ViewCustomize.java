package com.app.elixir.brewz_rock_cafe.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.adapter.ExpandableListAdapterCustomize;
import com.app.elixir.brewz_rock_cafe.adapter.ExpandableListAdapterNotCustomize;
import com.app.elixir.brewz_rock_cafe.database.ItemDetail;
import com.app.elixir.brewz_rock_cafe.database.Items;
import com.app.elixir.brewz_rock_cafe.mtplview.MtplTextView;
import com.app.elixir.brewz_rock_cafe.pojo.PojoArray;
import com.app.elixir.brewz_rock_cafe.pojo.PojoItemDetail;
import com.app.elixir.brewz_rock_cafe.pojo.PojoItems;
import com.app.elixir.brewz_rock_cafe.utils.CM;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewCustomize extends AppCompatActivity {


    private ArrayList<PojoItems> pojoCustomizes;
    private ArrayList<PojoItemDetail> pojoItemDetails;

    public ArrayList<String> listDataHeader;
    public HashMap<String, ArrayList<PojoArray>> listDataChild1;
    public static ExpandableListAdapterCustomize listAdapter;
    public ArrayList<PojoItems> pojoItemses;
    public ArrayList<PojoItemDetail> pojoItemDetails1;
    public ArrayList<PojoArray> pojoArrays;
    public static TextView item;
    public static MtplTextView mtplTextViewItems;
    public static ExpandableListView expListView, expListView1;
    public static ExpandableListAdapterNotCustomize expandableListAdapterNot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customize);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CM.finishActivity(ViewCustomize.this);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", "ok");
                setResult(Activity.RESULT_OK, returnIntent);
                finish();

            }
        });
        toolbar.setTitle(getString(R.string.customize));
        TextView titleTextView = null;
        try {
            Field f = (toolbar).getClass().getDeclaredField("mTitleTextView");
            f.setAccessible(true);
            titleTextView = (TextView) f.get(toolbar);
            Typeface font = Typeface.createFromAsset(getAssets(), getString(R.string.fontface_robotolight_0));
            titleTextView.setTypeface(font);
            titleTextView.setTextSize(20);
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }


        initView();

    }


    public void initView() {
        pojoCustomizes = new ArrayList<>();
        expListView = (ExpandableListView) findViewById(R.id.expListView);
        mtplTextViewItems = (MtplTextView) findViewById(R.id.items);
        item = (TextView) findViewById(R.id.textViewGrandTotal);

        String tot1 = ItemDetail.getAllCheckeditemRecord();
        try {
            double d = (double) Float.parseFloat(tot1);
            Double aFloat;
            if (!CM.getSp(ViewCustomize.this, "quantity", "").toString().equals("")) {
                aFloat = ((Double.parseDouble(CM.getSp(ViewCustomize.this, "itemPrice", "").toString()) + d) * Double.parseDouble(CM.getSp(ViewCustomize.this, "quantity", "").toString()));
            } else {
                aFloat = ((Double.parseDouble(CM.getSp(ViewCustomize.this, "itemPrice", "").toString()) + d));
            }
            DecimalFormat form = new DecimalFormat("0.00");
            item.setText(CM.getSp(ViewCustomize.this, "currency", "").toString() + String.valueOf(form.format(aFloat)));
        } catch (Exception e) {
        }


        addDate();
    }

    public void addDate() {
        prepareListDataForRequiredAddOn();
        prepareListDataForNotRequiredAddOn();
    }


//    private void prepareListData() {
//        listDataHeader = new ArrayList<String>();
//        listDataChild1 = new HashMap<>();
//        listDataHeader.clear();
//        listDataChild1.clear();
//
//        if (pojoItemses != null && pojoItemses.size() > 0) {
//            pojoItemses.clear();
//        }
//        pojoItemses = Items.getAlldata();
//        if (pojoItemses != null) {
//            for (int j = 0; j < pojoItemses.size(); j++) {
//                listDataHeader.add(pojoItemses.get(j).getcAttributeLabel());
//                pojoItemDetails1 = ItemDetail.getSelectedIdRecord(pojoItemses.get(j).getnMapperID());
//                pojoArrays = new ArrayList<>();
//                pojoArrays.clear();
//                for (int i = 0; i < pojoItemDetails1.size(); i++) {
//                    PojoArray pojoArray = new PojoArray();
//                    pojoArray.setnAttributeID(pojoItemDetails1.get(i).getnAttributeID());
//                    pojoArray.setcAttributeLabel(pojoItemDetails1.get(i).getcAttributeLabel());
//                    pojoArray.setnMapperDetailID(pojoItemDetails1.get(i).getnMapperDetailID());
//                    pojoArray.setnMapperID(pojoItemDetails1.get(i).getnMapperID());
//                    pojoArray.setcAttributeValueLabel(pojoItemDetails1.get(i).getcAttributeValueLabel());
//                    pojoArray.setnAttributeValueMasterID(pojoItemDetails1.get(i).getnAttributeValueMasterID());
//                    pojoArray.setnPrice(pojoItemDetails1.get(i).getnPrice());
//                    pojoArray.setIsChecked(pojoItemDetails1.get(i).getIsChecked());
//                    pojoArray.setIsMulti(pojoItemDetails1.get(i).getIsMulti());
//                    pojoArray.setHeader(pojoItemDetails1.get(i).getHeader());
//                    pojoArrays.add(pojoArray);
//                }
//                listDataChild1.put(pojoItemses.get(j).getcAttributeLabel(), pojoArrays);
//            }
//            listAdapter = new ExpandableListAdapterCustomize(this, listDataHeader, listDataChild1);
//            expListView.setAdapter(listAdapter);
//
//
//            item = (TextView) findViewById(R.id.textViewGrandTotal);
//            String tot = ItemDetail.getAllCheckeditemRecord();
//            try {
//                double d = (Double.parseDouble(CM.getSp(ViewCustomize.this, "itemPrice", "").toString()) + (Double.parseDouble(tot)));
//                item.setText("Total " + CM.getSp(ViewCustomize.this, "currency", "").toString() + " " + String.valueOf(d));
//            } catch (Exception e) {
//
//            }
//        }
//    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.customize, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.custom:
                //CM.finishActivity(ViewCustomize.this);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", "ok");
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                break;

        }
        return true;
    }


    private void prepareListDataForNotRequiredAddOn() {
        listDataHeader = new ArrayList<String>();
        listDataChild1 = new HashMap<>();
        listDataHeader.clear();
        listDataChild1.clear();

        if (pojoItemses != null && pojoItemses.size() > 0) {
            pojoItemses.clear();
        }
        pojoItemses = Items.getAlldataNotReruird();
        if (pojoItemses != null) {
            for (int j = 0; j < pojoItemses.size(); j++) {
                listDataHeader.add(pojoItemses.get(j).getcAttributeLabel());
                pojoItemDetails1 = ItemDetail.getSelectedIdRecord(pojoItemses.get(j).getnMapperID());
                pojoArrays = new ArrayList<>();
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
            expandableListAdapterNot = new ExpandableListAdapterNotCustomize(this, listDataHeader, listDataChild1);
            expListView1.setAdapter(expandableListAdapterNot);
            expListView1.expandGroup(0);


            String tot = ItemDetail.getAllCheckeditemRecord();
            try {
                //   double d = (double) Float.parseFloat(tot);
                DecimalFormat form = new DecimalFormat("0.00");
                //  item.setText("Total " + CM.getSp(ViewCustomize.this, "currency", "").toString() + " " + String.valueOf(form.format(d)));


                double d = (double) Float.parseFloat(tot);
                Double aFloat;
                if (!CM.getSp(ViewCustomize.this, "quantity", "").toString().equals("")) {
                    aFloat = ((Double.parseDouble(CM.getSp(ViewCustomize.this, "itemPrice", "").toString()) + d) * Double.parseDouble(CM.getSp(ViewCustomize.this, "quantity", "").toString()));
                } else {
                    aFloat = ((Double.parseDouble(CM.getSp(ViewCustomize.this, "itemPrice", "").toString()) + d));
                }

                item.setText(CM.getSp(ViewCustomize.this, "currency", "").toString() + String.valueOf(form.format(aFloat)));


            } catch (Exception e) {

            }
        }
    }

    private void prepareListDataForRequiredAddOn() {
        listDataHeader = new ArrayList<String>();
        listDataChild1 = new HashMap<>();
        listDataHeader.clear();
        listDataChild1.clear();

        if (pojoItemses != null && pojoItemses.size() > 0) {
            pojoItemses.clear();
        }
        pojoItemses = Items.getAlldata();
        if (pojoItemses != null) {
            for (int j = 0; j < pojoItemses.size(); j++) {
                listDataHeader.add(pojoItemses.get(j).getcAttributeLabel());
                pojoItemDetails1 = ItemDetail.getSelectedIdRecord(pojoItemses.get(j).getnMapperID());
                pojoArrays = new ArrayList<>();
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
            if (listAdapter != null) {
                listAdapter = null;
            }

            listAdapter = new ExpandableListAdapterCustomize(this, listDataHeader, listDataChild1);
            expListView.setAdapter(listAdapter);
            expListView.expandGroup(0);
            expListView.invalidateViews();
            listAdapter.notifyDataSetChanged();
            item = (TextView) findViewById(R.id.textViewGrandTotal);
            String tot = ItemDetail.getAllCheckeditemRecord();
            try {
                //double d = (Double.parseDouble(CM.getSp(ViewCustomize.this, "itemPrice", "").toString()) + (Double.parseDouble(tot)));
                DecimalFormat form = new DecimalFormat("0.00");
                //item.setText("Total " + CM.getSp(ViewCustomize.this, "currency", "").toString() + " " + String.valueOf(form.format(d)));


                double d = (double) Float.parseFloat(tot);
                Double aFloat;
                if (!CM.getSp(ViewCustomize.this, "quantity", "").toString().equals("")) {
                    aFloat = ((Double.parseDouble(CM.getSp(ViewCustomize.this, "itemPrice", "").toString()) + d) * Double.parseDouble(CM.getSp(ViewCustomize.this, "quantity", "").toString()));
                } else {
                    aFloat = ((Double.parseDouble(CM.getSp(ViewCustomize.this, "itemPrice", "").toString()) + d));
                }

                item.setText(CM.getSp(ViewCustomize.this, "currency", "").toString() + String.valueOf(form.format(aFloat)));


            } catch (Exception e) {

            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //       CM.finishActivity(ViewCustomize.this);
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", "ok");
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
