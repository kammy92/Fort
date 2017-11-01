package com.karman.fort.activity;

import android.content.Intent;
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
import com.karman.fort.dialog.TourDetailDialogFragment;
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
    
    String language = "";
    
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_begin_tour);
        initView ();
        getExtras ();
        initData ();
        initListener ();
        setData ();
    }
    
    private void getExtras () {
        Intent intent = getIntent ();
        language = intent.getStringExtra ("language");
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
        tourItemListAdapter.SetOnItemClickListener (new TourItemListAdapter.OnItemClickListener () {
            @Override
            public void onItemClick (View view, int position) {
                final TourItem tourItem = tourItemList.get (position);
                final android.app.FragmentTransaction ft = getFragmentManager ().beginTransaction ();
                TourDetailDialogFragment frag = new TourDetailDialogFragment ().newInstance (tourItem.getIconList (), tourItem.getTitle (), tourItem.getDescription (), tourItem.getAudio_uri (), tourItem.getImageList ());
                frag.show (ft, "");
            }
        });
    }
    
    
    @Override
    public void onBackPressed () {
        finish ();
        overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
    }
    
    private void setData () {
        if (getResources ().getStringArray (R.array.select_language)[0].equalsIgnoreCase (language)) {
            tourItemList.clear ();
            ArrayList<String> imagelist1 = new ArrayList<> ();
            ArrayList<Integer> iconList1a = new ArrayList<> ();
            iconList1a.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (1, iconList1a, ";zy/g sZE", "Description 1", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1b = new ArrayList<> ();
            iconList1b.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (2, iconList1b, "Innermost Area", "Description 2", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1c = new ArrayList<> ();
            iconList1c.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (3, iconList1c, "Outer Gate", "Description 3", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1d = new ArrayList<> ();
            iconList1d.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (4, iconList1d, "Nalwa Gate", "Description 4", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1e = new ArrayList<> ();
            iconList1e.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (5, iconList1e, "Inner Gate", "Description 5", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1f = new ArrayList<> ();
            iconList1f.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (6, iconList1f, "Moats & Ravelins", "Description 6", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1g = new ArrayList<> ();
            iconList1g.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (7, iconList1g, "Khas Mahal", "Description 7", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1h = new ArrayList<> ();
            iconList1h.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (8, iconList1h, "Anglo Sikh War Museum", "Description 8", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1i = new ArrayList<> ();
            iconList1i.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (9, iconList1i, "Toshakhana", "Description 9", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1j = new ArrayList<> ();
            iconList1j.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (10, iconList1j, "Bastions", "Description 10", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1k = new ArrayList<> ();
            iconList1k.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (11, iconList1k, "Darbar Hall", "Description 11", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1l = new ArrayList<> ();
            iconList1l.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (12, iconList1l, "Chloronome House or Phansi Ghar", "Description 12", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1m = new ArrayList<> ();
            iconList1m.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (13, iconList1m, "Keeler Gate", "Description 13", "0.mp3", imagelist1));
            tourItemListAdapter.notifyDataSetChanged ();
        }
    
        if (getResources ().getStringArray (R.array.select_language)[1].equalsIgnoreCase (language)) {
            tourItemList.clear ();
            ArrayList<String> imagelist1 = new ArrayList<> ();
            ArrayList<Integer> iconList1a = new ArrayList<> ();
            iconList1a.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (1, iconList1a, "General Intro", "Description 1", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1b = new ArrayList<> ();
            iconList1b.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (2, iconList1b, "Innermost Area", "Description 2", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1c = new ArrayList<> ();
            iconList1c.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (3, iconList1c, "Outer Gate", "Description 3", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1d = new ArrayList<> ();
            iconList1d.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (4, iconList1d, "Nalwa Gate", "Description 4", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1e = new ArrayList<> ();
            iconList1e.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (5, iconList1e, "Inner Gate", "Description 5", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1f = new ArrayList<> ();
            iconList1f.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (6, iconList1f, "Moats & Ravelins", "Description 6", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1g = new ArrayList<> ();
            iconList1g.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (7, iconList1g, "Khas Mahal", "Description 7", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1h = new ArrayList<> ();
            iconList1h.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (8, iconList1h, "Anglo Sikh War Museum", "Description 8", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1i = new ArrayList<> ();
            iconList1i.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (9, iconList1i, "Toshakhana", "Description 9", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1j = new ArrayList<> ();
            iconList1j.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (10, iconList1j, "Bastions", "Description 10", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1k = new ArrayList<> ();
            iconList1k.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (11, iconList1k, "Darbar Hall", "Description 11", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1l = new ArrayList<> ();
            iconList1l.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (12, iconList1l, "Chloronome House or Phansi Ghar", "Description 12", "0.mp3", imagelist1));
            ArrayList<Integer> iconList1m = new ArrayList<> ();
            iconList1m.add (R.drawable.about_us_image);
            tourItemList.add (new TourItem (13, iconList1m, "Keeler Gate", "Description 13", "0.mp3", imagelist1));
            tourItemListAdapter.notifyDataSetChanged ();
        }
    }
}