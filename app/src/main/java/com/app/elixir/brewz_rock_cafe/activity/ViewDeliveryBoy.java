package com.app.elixir.brewz_rock_cafe.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.app.elixir.brewz_rock_cafe.Fragment.FragDeliveryboyHome;
import com.app.elixir.brewz_rock_cafe.Fragment.FragMyProfileDeliveryBox;
import com.app.elixir.brewz_rock_cafe.Fragment.FragOrderDetailView;
import com.app.elixir.brewz_rock_cafe.Fragment.FragPastDelivery;
import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.adapter.adptDeliveryBox;
import com.app.elixir.brewz_rock_cafe.database.tbl_notificationNew;
import com.app.elixir.brewz_rock_cafe.interfac.ActionBarTitleSetter;
import com.app.elixir.brewz_rock_cafe.interfac.OnFragmentInteractionListenerDelivery;
import com.app.elixir.brewz_rock_cafe.pojo.PojoDeliveryBoy;
import com.app.elixir.brewz_rock_cafe.utils.CM;
import com.app.elixir.brewz_rock_cafe.utils.CustomTypefaceSpan;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ViewDeliveryBoy extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ActionBarTitleSetter, OnFragmentInteractionListenerDelivery {

    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private adptDeliveryBox mAdapter;
    ArrayList<PojoDeliveryBoy> pojoDeliveryBoys;
    private ActionBarDrawerToggle toggle;
    private String mTitle;
    private Toolbar toolbar;
    private static final String TAG = "logout";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_delivery_boy);
        // setTitle("Delivery Boy");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("My title");
        setSupportActionBar(toolbar);
        initView();
        toolbar.setTitle("Delivery Boy");

        //   android.os.Debug.waitForDebugger();
        TextView titleTextView = null;
        try {
            Field f = toolbar.getClass().getDeclaredField("mTitleTextView");
            f.setAccessible(true);
            titleTextView = (TextView) f.get(toolbar);
            Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), getString(R.string.fontface_robotolight_0));
            titleTextView.setTypeface(font);
            titleTextView.setTextSize(20);
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem mi = menu.getItem(i);
            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }
            //the method we have create in activity
            applyFontToMenuItem(mi);
        }

        setFragment(0);
    }

    private void initView() {


    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcast_reciever, new IntentFilter("finish_activityDeliv"));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Fragment f = getSupportFragmentManager().findFragmentById(R.id.container);
            if (f instanceof FragPastDelivery) {
                FragmentManager fm = getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    setTitle(getString(R.string.app_name));
                    toggle.setDrawerIndicatorEnabled(true);
                    toggle.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_menu));
                    fm.popBackStack();
                } else {
                    showPopup(ViewDeliveryBoy.this);
                }
            } else if (f instanceof FragMyProfileDeliveryBox) {
                FragmentManager fm = getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    setTitle(getString(R.string.app_name));
                    toggle.setDrawerIndicatorEnabled(true);
                    toggle.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_menu));
                    fm.popBackStack();
                } else {
                    showPopup(ViewDeliveryBoy.this);
                }
            } else if (f instanceof FragOrderDetailView) {
                FragmentManager fm = getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    setTitle(getString(R.string.app_name));
                    toggle.setDrawerIndicatorEnabled(true);
                    toggle.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_menu));
                    fm.popBackStack();
                } else {
                    showPopup(ViewDeliveryBoy.this);
                }
            } else {
                FragmentManager fm = getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                } else {
                    showPopup(ViewDeliveryBoy.this);
                }
            }

            //  super.onBackPressed();
            //CM.finishActivity(ViewDeliveryBoy.this);


        }
        hideSoftKeyboard();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.view_delivery_boy, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            setFragment(0);
        } else if (id == R.id.nav_pastdelivery) {
            setFragment(1);
        } else if (id == R.id.nav_myprofile) {
            setFragment(2);
        } else if (id == R.id.nav_logout) {
            setFragment(3);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(int i) {

        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = new FragDeliveryboyHome();
                ft.replace(R.id.fragcontainer, fragment);
                ft.commit();
                break;
            case 1:
                fragment = new FragPastDelivery();
                ft.replace(R.id.fragcontainer, fragment).addToBackStack("FragDeliveryboyHome");
                ft.commit();
                break;
            case 2:
                fragment = new FragMyProfileDeliveryBox();
                ft.replace(R.id.fragcontainer, fragment).addToBackStack("FragDeliveryboyHome");
                ft.commit();
                break;
            case 3:
                showPopupForLogOut(ViewDeliveryBoy.this);
                break;

        }

    }

    @Override
    public void showDrawerToggle(boolean showDrawerToggle) {
        if (!showDrawerToggle) {
            toggle.setDrawerIndicatorEnabled(showDrawerToggle);
            toggle.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_white));
            toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    hideSoftKeyboard();
                    Fragment f = getSupportFragmentManager().findFragmentById(R.id.container);
                    if (f instanceof FragPastDelivery) {
                        showPopup(ViewDeliveryBoy.this);
                    } else if (f instanceof FragPastDelivery) {
                        FragmentManager fm = getSupportFragmentManager();
                        if (fm.getBackStackEntryCount() > 0) {
                            fm.popBackStack();
                        } else {
                            showPopup(ViewDeliveryBoy.this);
                        }
                    } else {
                        FragmentManager fm = getSupportFragmentManager();
                        if (fm.getBackStackEntryCount() > 0) {
                            fm.popBackStack();
                        } else {
                            showPopup(ViewDeliveryBoy.this);
                        }
                    }
                }
            });
        } else {
            toggle.setDrawerIndicatorEnabled(showDrawerToggle);
            toggle.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_menu));
        }

    }

    @Override
    public void setTitle(String title) {
        mTitle = title;
        toolbar.setTitle(mTitle);
    }

    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), getString(R.string.fontface_robotolight_0));
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

    public void showPopup(Context context) {
        new AlertDialog.Builder(context)
                .setTitle(getString(R.string.app_name))
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setIcon(R.drawable.applogo).show();
    }

    public void showPopupForLogOut(Context context) {
        new AlertDialog.Builder(context)
                .setTitle(getString(R.string.app_name))
                .setMessage("Are you sure you want to Logout?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            CM.setSp(ViewDeliveryBoy.this, TAG, "1");
                            CM.setSp(ViewDeliveryBoy.this, "custLog", "");
                            tbl_notificationNew.deleteAll();
                        } catch (Exception e) {
                            e.getMessage();
                        }

                        CM.startActivity(ViewDeliveryBoy.this, ViewLoginActivity.class);
                        finish();

                    }
                }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setIcon(R.drawable.applogo).show();
    }


    BroadcastReceiver broadcast_reciever = new BroadcastReceiver() {

        @Override
        public void onReceive(Context arg0, Intent intent) {
            String action = intent.getAction();
            if (action.equals("finish_activityDeliv")) {
                finish();
                // DO WHATEVER YOU WANT.
            }
        }
    };

}
