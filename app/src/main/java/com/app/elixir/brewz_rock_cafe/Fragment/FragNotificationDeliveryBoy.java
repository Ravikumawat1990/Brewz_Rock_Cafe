package com.app.elixir.brewz_rock_cafe.Fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.elixir.brewz_rock_cafe.Model.NotificationPojo;
import com.app.elixir.brewz_rock_cafe.R;
import com.app.elixir.brewz_rock_cafe.activity.ViewNotification;
import com.app.elixir.brewz_rock_cafe.adapter.adptNotification;
import com.app.elixir.brewz_rock_cafe.database.tbl_notification;
import com.app.elixir.brewz_rock_cafe.interfac.ActionBarTitleSetter;
import com.app.elixir.brewz_rock_cafe.interfac.OnFragmentInteractionListenerDelivery;
import com.app.elixir.brewz_rock_cafe.interfac.OnItemClickListener;
import com.app.elixir.brewz_rock_cafe.utils.CM;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by NetSupport on 28-09-2016.
 */
public class FragNotificationDeliveryBoy extends Fragment {

    private Activity thisActivity;
    private OnFragmentInteractionListenerDelivery mListener;
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    ArrayList<NotificationPojo> notificationPojos;
    private LinearLayout noInternetLayout;
    private LinearLayout internetLayout;
    com.app.elixir.brewz_rock_cafe.adapter.adptNotification adptNotification;

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.mListener = (OnFragmentInteractionListenerDelivery) context;
            ((ActionBarTitleSetter) context).setTitle(getString(R.string.food_category));
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragnotificatin, container, false);
        setHasOptionsMenu(true);
        thisActivity = getActivity();
        ((ActionBarTitleSetter) thisActivity).setTitle(getString(R.string.notification));
        TextView titleTextView = null;
        try {
            Field f = ((ActionBarTitleSetter) thisActivity).getClass().getDeclaredField("mTitleTextView");
            f.setAccessible(true);
            titleTextView = (TextView) f.get(((ActionBarTitleSetter) thisActivity));
            Typeface font = Typeface.createFromAsset(thisActivity.getAssets(), getString(R.string.fontface_robotolight_0));
            titleTextView.setTypeface(font);
            titleTextView.setTextSize(20);
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }
        init(rootView);
        tbl_notification.updateAllTbl();
        return rootView;
    }

    public void init(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
        noInternetLayout = (LinearLayout) rootView.findViewById(R.id.noInternet);
        internetLayout = (LinearLayout) rootView.findViewById(R.id.Internet);

        notificationPojos = new ArrayList<>();

        if (CM.isInternetAvailable(getActivity())) {
            addDate();
            adptNotification = new adptNotification(thisActivity, notificationPojos);
            mRecyclerView.setAdapter(adptNotification);
            noInternetLayout.setVisibility(View.GONE);
            internetLayout.setVisibility(View.VISIBLE);
        } else {
            noInternetLayout.setVisibility(View.VISIBLE);
            internetLayout.setVisibility(View.GONE);
        }

        adptNotification.SetOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(String value) {
                CM.startActivity(thisActivity, ViewNotification.class);
            }
        });
    }


    void addDate() {
        for (int i = 0; i < 5; i++) {
            NotificationPojo notificationPojo = new NotificationPojo();
            notificationPojo.setTitle("10% Discount on all Category");
            notificationPojos.add(notificationPojo);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mListener.showDrawerToggle(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (menu.findItem(R.id.notiCount) != null) {
            menu.findItem(R.id.notiCount).setVisible(false);
        }
    }


}
