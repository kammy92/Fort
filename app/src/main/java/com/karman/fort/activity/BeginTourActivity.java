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
            ArrayList<String> iconList1a = new ArrayList<> ();
            iconList1a.add ("begin_tour/general_intro/general_intro.jpg");
            tourItemList.add (new TourItem (1, iconList1a, "General Intro", getResources ().getString (R.string.general_intro_description_english), "begin_tour/general_intro/english/audio.mp3", imagelist1));
            ArrayList<String> iconList1b = new ArrayList<> ();
            iconList1b.add ("begin_tour/innermost_area/innnermost_area_of_fort.jpg");
            tourItemList.add (new TourItem (2, iconList1b, "Innermost Area", getResources ().getString (R.string.innermost_area_description_english), "begin_tour/innermost_area/english/audio.mp3", imagelist1));
            ArrayList<String> iconList1c = new ArrayList<> ();
            iconList1c.add ("begin_tour/outer_gate/outer_gate.jpg");
            tourItemList.add (new TourItem (3, iconList1c, "Outer Gate", getResources ().getString (R.string.outer_gate_description_english), "begin_tour/outer_gate/english/audio.mp3", imagelist1));
            ArrayList<String> iconList1d = new ArrayList<> ();
            iconList1d.add ("begin_tour/nalwa_gate/nalwa_gate.jpg");
            tourItemList.add (new TourItem (4, iconList1d, "Nalwa Gate", getResources ().getString (R.string.nalwa_gate_description_english), "begin_tour/nalwa_gate/english/audio.mp3", imagelist1));
            ArrayList<String> iconList1e = new ArrayList<> ();
            iconList1e.add ("begin_tour/inner_gate/inner_gate.jpg");
            tourItemList.add (new TourItem (5, iconList1e, "Inner Gate", getResources ().getString (R.string.inner_gate_description_english), "begin_tour/inner_gate/english/audio.mp3", imagelist1));
            ArrayList<String> iconList1f = new ArrayList<> ();
            iconList1f.add ("begin_tour/moats_ravelins/moats_ravelins.jpg");
            tourItemList.add (new TourItem (6, iconList1f, "Moats & Ravelins", getResources ().getString (R.string.moats_ravelins_description_english), "begin_tour/moats_ravelins/english/audio.mp3", imagelist1));
            ArrayList<String> iconList1g = new ArrayList<> ();
            iconList1g.add ("begin_tour/khas_mahal/khas_mahal.jpg");
            tourItemList.add (new TourItem (7, iconList1g, "Khas Mahal", getResources ().getString (R.string.khas_mahal_description_english), "begin_tour/khas_mahal/english/audio.mp3", imagelist1));
            ArrayList<String> iconList1h = new ArrayList<> ();
            iconList1h.add ("begin_tour/anglo_sikh_museum/anglo_sikh_museum.jpg");
            tourItemList.add (new TourItem (8, iconList1h, "Anglo Sikh War Museum", getResources ().getString (R.string.anglo_sikh_museum_description_english), "begin_tour/anglo_sikh_museum/english/audio.mp3", imagelist1));
            ArrayList<String> iconList1i = new ArrayList<> ();
            iconList1i.add ("begin_tour/toshakhana/toshakhana.jpg");
            tourItemList.add (new TourItem (9, iconList1i, "Toshakhana", getResources ().getString (R.string.toshakhana_description_english), "begin_tour/toshakhana/english/audio.mp3", imagelist1));
            ArrayList<String> iconList1j = new ArrayList<> ();
            iconList1j.add ("begin_tour/bastions/bastions.jpg");
            tourItemList.add (new TourItem (10, iconList1j, "Bastions", getResources ().getString (R.string.bastions_description_english), "begin_tour/bastions/english/audio.mp3", imagelist1));
            ArrayList<String> iconList1k = new ArrayList<> ();
            iconList1k.add ("begin_tour/darbar_hall/darbar_hall.jpg");
            tourItemList.add (new TourItem (11, iconList1k, "Darbar Hall", getResources ().getString (R.string.darbar_hall_description_english), "begin_tour/darbar_hall/english/audio.mp3", imagelist1));
            ArrayList<String> iconList1l = new ArrayList<> ();
            iconList1l.add ("begin_tour/chloronome/chloronome.jpg");
            tourItemList.add (new TourItem (12, iconList1l, "Chloronome House or Phansi Ghar", getResources ().getString (R.string.chloronome_description_english), "begin_tour/chloronome/english/audio.mp3", imagelist1));
            ArrayList<String> iconList1m = new ArrayList<> ();
            iconList1m.add ("begin_tour/keeler_gate/keeler_gate.jpg");
            tourItemList.add (new TourItem (13, iconList1m, "Keeler Gate", getResources ().getString (R.string.keeler_gate_description_english), "begin_tour/keeler_gate/english/audio.mp3", imagelist1));
            tourItemListAdapter.notifyDataSetChanged ();
        }
    
        if (getResources ().getStringArray (R.array.select_language)[1].equalsIgnoreCase (language)) {
            tourItemList.clear ();
            ArrayList<String> imagelist1 = new ArrayList<> ();
            ArrayList<String> iconList1a = new ArrayList<> ();
            iconList1a.add ("begin_tour/general_intro/general_intro.jpg");
            tourItemList.add (new TourItem (1, iconList1a, "ਆਮ ਭੂਮਿਕਾ", getResources ().getString (R.string.general_intro_description_gurmukhi), "begin_tour/general_intro/gurmukhi/audio.mp3", imagelist1));
            ArrayList<String> iconList1b = new ArrayList<> ();
            iconList1b.add ("begin_tour/innermost_area/innnermost_area_of_fort.jpg");
            tourItemList.add (new TourItem (2, iconList1b, "ਅੰਦਰੂਨੀ ਖੇਤਰ", getResources ().getString (R.string.innermost_area_description_gurmukhi), "begin_tour/innermost_area/gurmukhi/audio.mp3", imagelist1));
            ArrayList<String> iconList1c = new ArrayList<> ();
            iconList1c.add ("begin_tour/outer_gate/outer_gate.jpg");
            tourItemList.add (new TourItem (3, iconList1c, "ਆਊਟ ਗੇਟ", getResources ().getString (R.string.outer_gate_description_gurmukhi), "begin_tour/outer_gate/gurmukhi/audio.mp3", imagelist1));
            ArrayList<String> iconList1d = new ArrayList<> ();
            iconList1d.add ("begin_tour/nalwa_gate/nalwa_gate.jpg");
            tourItemList.add (new TourItem (4, iconList1d, "ਨਲਵਾ ਗੇਟ", getResources ().getString (R.string.nalwa_gate_description_gurmukhi), "begin_tour/nalwa_gate/gurmukhi/audio.mp3", imagelist1));
            ArrayList<String> iconList1e = new ArrayList<> ();
            iconList1e.add ("begin_tour/inner_gate/inner_gate.jpg");
            tourItemList.add (new TourItem (5, iconList1e, "ਅੰਦਰੂਨੀ ਗੇਟ", getResources ().getString (R.string.inner_gate_description_gurmukhi), "begin_tour/inner_gate/gurmukhi/audio.mp3", imagelist1));
            ArrayList<String> iconList1f = new ArrayList<> ();
            iconList1f.add ("begin_tour/moats_ravelins/moats_ravelins.jpg");
            tourItemList.add (new TourItem (6, iconList1f, "ਖਾਈ", getResources ().getString (R.string.moats_ravelins_description_gurmukhi), "begin_tour/moats_ravelins/gurmukhi/audio.mp3", imagelist1));
            ArrayList<String> iconList1g = new ArrayList<> ();
            iconList1g.add ("begin_tour/khas_mahal/khas_mahal.jpg");
            tourItemList.add (new TourItem (7, iconList1g, "ਖਾਸ ਮਹਿਲ", getResources ().getString (R.string.khas_mahal_description_gurmukhi), "begin_tour/khas_mahal/gurmukhi/audio.mp3", imagelist1));
            ArrayList<String> iconList1h = new ArrayList<> ();
            iconList1h.add ("begin_tour/anglo_sikh_museum/anglo_sikh_museum.jpg");
            tourItemList.add (new TourItem (8, iconList1h, "ਐਂਗਲੋ ਸਿੱਖ ਵਾਰ ਮਿਊਜ਼ੀਅਮ", getResources ().getString (R.string.anglo_sikh_museum_description_gurmukhi), "begin_tour/anglo_sikh_museum/gurmukhi/audio.mp3", imagelist1));
            ArrayList<String> iconList1i = new ArrayList<> ();
            iconList1i.add ("begin_tour/toshakhana/toshakhana.jpg");
            tourItemList.add (new TourItem (9, iconList1i, "ਤੋਸ਼ਾਖ਼ਾਨਾ", getResources ().getString (R.string.toshakhana_description_gurmukhi), "begin_tour/toshakhana/gurmukhi/audio.mp3", imagelist1));
            ArrayList<String> iconList1j = new ArrayList<> ();
            iconList1j.add ("begin_tour/bastions/bastions.jpg");
            tourItemList.add (new TourItem (10, iconList1j, "ਚਾਰ ਬੁਰਜ", getResources ().getString (R.string.bastions_description_gurmukhi), "begin_tour/bastions/gurmukhi/audio.mp3", imagelist1));
            ArrayList<String> iconList1k = new ArrayList<> ();
            iconList1k.add ("begin_tour/darbar_hall/darbar_hall.jpg");
            tourItemList.add (new TourItem (11, iconList1k, "ਦਰਬਾਰ ਹਾਲ", getResources ().getString (R.string.darbar_hall_description_gurmukhi), "begin_tour/darbar_hall/gurmukhi/audio.mp3", imagelist1));
            ArrayList<String> iconList1l = new ArrayList<> ();
            iconList1l.add ("begin_tour/chloronome/chloronome.jpg");
            tourItemList.add (new TourItem (12, iconList1l, "ਫਾਂਸੀ ਘਰ", getResources ().getString (R.string.chloronome_description_gurmukhi), "begin_tour/chloronome/gurmukhi/audio.mp3", imagelist1));
            ArrayList<String> iconList1m = new ArrayList<> ();
            iconList1m.add ("begin_tour/keeler_gate/keeler_gate.jpg");
            tourItemList.add (new TourItem (13, iconList1m, "ਕੇਐਲਰ ਗੇਟ", getResources ().getString (R.string.keeler_gate_description_gurmukhi), "begin_tour/keeler_gate/gurmukhi/audio.mp3", imagelist1));
            tourItemListAdapter.notifyDataSetChanged ();
        }
    }
}