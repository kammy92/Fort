package com.karman.fort.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.karman.fort.R;
import com.karman.fort.adapter.TourItemListAdapter;
import com.karman.fort.model.TourItem;
import com.karman.fort.utils.RecyclerViewMargin;
import com.karman.fort.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class BeginTourActivity extends AppCompatActivity {
    CoordinatorLayout clMain;
    RelativeLayout rlBack;
    TextView tvTitle;
    
    RecyclerView rvTourItems;
    List<TourItem> tourItemList = new ArrayList<> ();
    TourItemListAdapter tourItemListAdapter;
    
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_begin_tour);
        initView ();
        initData ();
        initListener ();
        setData ();
    }
    
    private void initView () {
        rlBack = (RelativeLayout) findViewById (R.id.rlBack);
        clMain = (CoordinatorLayout) findViewById (R.id.clMain);
        tvTitle = (TextView) findViewById (R.id.tvTitle);
        rvTourItems = (RecyclerView) findViewById (R.id.rvTourItems);
    
    }
    
    private void initData () {
        tourItemListAdapter = new TourItemListAdapter (this, tourItemList);
        rvTourItems.setAdapter (tourItemListAdapter);
        rvTourItems.setHasFixedSize (true);
        rvTourItems.setLayoutManager (new LinearLayoutManager (this, LinearLayoutManager.VERTICAL, false));
        rvTourItems.setItemAnimator (new DefaultItemAnimator ());
        rvTourItems.addItemDecoration (new RecyclerViewMargin ((int) Utils.pxFromDp (this, 16), (int) Utils.pxFromDp (this, 16), (int) Utils.pxFromDp (this, 16), (int) Utils.pxFromDp (this, 16), 1, 0, RecyclerViewMargin.LAYOUT_MANAGER_LINEAR, RecyclerViewMargin.ORIENTATION_VERTICAL));
        Utils.setTypefaceToAllViews (this, rlBack);
    }
    
    private void initListener () {
        rlBack.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                finish ();
                overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }
    
    
    @Override
    public void onBackPressed () {
        finish ();
        overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
    }
    
    private void setData () {
        tourItemList.clear ();
        ArrayList<String> imagelist1 = new ArrayList<> ();
        ArrayList<Integer> iconList1a = new ArrayList<> ();
        iconList1a.add (R.drawable.about_us_image);
        tourItemList.add (new TourItem (1, iconList1a, "General Intro", "Description 1", imagelist1));
        ArrayList<Integer> iconList1b = new ArrayList<> ();
        iconList1b.add (R.drawable.about_us_image);
        tourItemList.add (new TourItem (1, iconList1b, "Innermost Area", "Description 1", imagelist1));
        ArrayList<Integer> iconList1c = new ArrayList<> ();
        iconList1c.add (R.drawable.about_us_image);
        tourItemList.add (new TourItem (1, iconList1c, "Outer Gate", "Description 1", imagelist1));
        ArrayList<Integer> iconList1d = new ArrayList<> ();
        iconList1d.add (R.drawable.about_us_image);
        tourItemList.add (new TourItem (1, iconList1d, "Nalwa Gate", "Description 1", imagelist1));
        ArrayList<Integer> iconList1e = new ArrayList<> ();
        iconList1e.add (R.drawable.about_us_image);
        tourItemList.add (new TourItem (1, iconList1e, "Inner Gate", "Description 1", imagelist1));
        ArrayList<Integer> iconList1f = new ArrayList<> ();
        iconList1f.add (R.drawable.about_us_image);
        tourItemList.add (new TourItem (1, iconList1f, "Moats & Ravelins", "Description 1", imagelist1));
        ArrayList<Integer> iconList1g = new ArrayList<> ();
        iconList1g.add (R.drawable.about_us_image);
        tourItemList.add (new TourItem (1, iconList1g, "Khas Mahal", "Description 1", imagelist1));
        ArrayList<Integer> iconList1h = new ArrayList<> ();
        iconList1h.add (R.drawable.about_us_image);
        tourItemList.add (new TourItem (1, iconList1h, "Anglo Sikh War Museum", "Description 1", imagelist1));
        ArrayList<Integer> iconList1i = new ArrayList<> ();
        iconList1i.add (R.drawable.about_us_image);
        tourItemList.add (new TourItem (1, iconList1i, "Toshakhana", "Description 1", imagelist1));
        ArrayList<Integer> iconList1j = new ArrayList<> ();
        iconList1j.add (R.drawable.about_us_image);
        tourItemList.add (new TourItem (1, iconList1j, "Bastions", "Description 1", imagelist1));
        ArrayList<Integer> iconList1k = new ArrayList<> ();
        iconList1k.add (R.drawable.about_us_image);
        tourItemList.add (new TourItem (1, iconList1k, "Darbar Hall", "Description 1", imagelist1));
        ArrayList<Integer> iconList1l = new ArrayList<> ();
        iconList1l.add (R.drawable.about_us_image);
        tourItemList.add (new TourItem (1, iconList1l, "Chloronome House or Phansi Ghar", "Description 1", imagelist1));
        ArrayList<Integer> iconList1m = new ArrayList<> ();
        iconList1m.add (R.drawable.about_us_image);
        tourItemList.add (new TourItem (1, iconList1m, "Keeler Gate", "Description 1", imagelist1));
    }
}