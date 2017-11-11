package com.gobindgarh.fort.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gobindgarh.fort.R;
import com.gobindgarh.fort.adapter.ActivityListAdapter;
import com.gobindgarh.fort.model.Activities;
import com.gobindgarh.fort.utils.AppConfigTags;
import com.gobindgarh.fort.utils.RecyclerViewMargin;
import com.gobindgarh.fort.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ActivityListFragment extends Fragment {
    RecyclerView rvActivities;
    List<Activities> activitiesList = new ArrayList<> ();
    ActivityListAdapter activityListAdapter;
    
    RelativeLayout rlBack;
    
    int activity_id;
    TextView tvTitle;
    
    public static ActivityListFragment newInstance (int activity_id) {
        ActivityListFragment fragment = new ActivityListFragment ();
        Bundle args = new Bundle ();
        args.putInt (AppConfigTags.ACTIVITY_ID, activity_id);
        fragment.setArguments (args);
        return fragment;
    }
    
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate (R.layout.fragment_activity_list, container, false);
        initView (rootView);
        initData ();
        initBundle ();
        initListener ();
        setData ();
        return rootView;
    }
    
    private void initBundle () {
        Bundle bundle = this.getArguments ();
        activity_id= bundle.getInt (AppConfigTags.ACTIVITY_ID);
    }
    
    private void initView (View rootView) {
        rvActivities = rootView.findViewById (R.id.rvActivities);
        tvTitle = getActivity ().findViewById (R.id.tvTitle);
        rlBack = getActivity ().findViewById (R.id.rlBack);
    }
    
    private void initData () {
        activityListAdapter = new ActivityListAdapter (getActivity (), activitiesList);
        rvActivities.setAdapter (activityListAdapter);
        rvActivities.setHasFixedSize (true);
        rvActivities.setLayoutManager (new LinearLayoutManager (getActivity (), LinearLayoutManager.VERTICAL, false));
        rvActivities.setItemAnimator (new DefaultItemAnimator ());
        rvActivities.addItemDecoration (new RecyclerViewMargin ((int) Utils.pxFromDp (getActivity (), 16), (int) Utils.pxFromDp (getActivity (), 16), (int) Utils.pxFromDp (getActivity (), 16), (int) Utils.pxFromDp (getActivity (), 16), 1, 0, RecyclerViewMargin.LAYOUT_MANAGER_LINEAR, RecyclerViewMargin.ORIENTATION_VERTICAL));
        Utils.setTypefaceToAllViews (getActivity (), tvTitle);
    }
    
    private void initListener () {
    }
    
    private void setData () {
        activitiesList.clear ();
        ArrayList<String> imagelist1 = new ArrayList<> ();
        switch (activity_id){
            case 1:
                ArrayList<String> iconList1a = new ArrayList<> ();
                iconList1a.add ("activities/ambarsari_zaika/amritsari_kulfi.jpg");
                activitiesList.add (new Activities (1, iconList1a, "Amritsari Kulfi", "", imagelist1));
                ArrayList<String> iconList1b = new ArrayList<> ();
                iconList1b.add ("activities/ambarsari_zaika/brothers_dhaba.jpg");
                activitiesList.add (new Activities (1, iconList1b, "Brothers Dhaba", "", imagelist1));
                ArrayList<String> iconList1c = new ArrayList<> ();
                iconList1c.add ("activities/ambarsari_zaika/gol_gape.jpg");
                activitiesList.add (new Activities (1, iconList1c, "GolGape", "", imagelist1));
                ArrayList<String> iconList1d = new ArrayList<> ();
                iconList1d.add ("activities/ambarsari_zaika/krishna_chaat.jpg");
                activitiesList.add (new Activities (1, iconList1d, "Krishna Chaat", "", imagelist1));
                ArrayList<String> iconList1e = new ArrayList<> ();
                iconList1e.add ("activities/ambarsari_zaika/lazeer.jpg");
                activitiesList.add (new Activities (1, iconList1e, "Lazeez", "", imagelist1));
                ArrayList<String> iconList1f = new ArrayList<> ();
                iconList1f.add ("activities/ambarsari_zaika/patola_drink.jpg");
                activitiesList.add (new Activities (1, iconList1f, "Patola Drink", "", imagelist1));
                ArrayList<String> iconList1g = new ArrayList<> ();
                iconList1g.add ("activities/ambarsari_zaika/tandoori_khazana.jpg");
                activitiesList.add (new Activities (1, iconList1g, "Tandoori Khazana", "", imagelist1));
                break;
            case 2:
                ArrayList<String> iconList2a = new ArrayList<> ();
                iconList2a.add ("activities/attraction/atv_ride.jpg");
                activitiesList.add (new Activities (1, iconList2a, "ATV Ride", "", imagelist1));
                ArrayList<String> iconList2b = new ArrayList<> ();
                iconList2b.add ("activities/attraction/bhagda_gidha.jpg");
                activitiesList.add (new Activities (1, iconList2b, "Bhangda Gidha", "", imagelist1));
                ArrayList<String> iconList2c = new ArrayList<> ();
                iconList2c.add ("activities/attraction/camel_ride.jpg");
                activitiesList.add (new Activities (1, iconList2c, "Camel Ride", "", imagelist1));
                ArrayList<String> iconList2d = new ArrayList<> ();
                iconList2d.add ("activities/attraction/couple_cycling.jpg");
                activitiesList.add (new Activities (1, iconList2d, "Couple Cycling", "", imagelist1));
                ArrayList<String> iconList2e = new ArrayList<> ();
                iconList2e.add ("activities/attraction/fun_bull_ride.jpg");
                activitiesList.add (new Activities (1, iconList2e, "Fun Bull Ride", "", imagelist1));
                ArrayList<String> iconList2f = new ArrayList<> ();
                iconList2f.add ("activities/attraction/fun_ride.jpg");
                activitiesList.add (new Activities (1, iconList2f, "Fun Ride", "", imagelist1));
                ArrayList<String> iconList2g = new ArrayList<> ();
                iconList2g.add ("activities/attraction/golf_cart.jpg");
                activitiesList.add (new Activities (1, iconList2g, "Golf Cart", "", imagelist1));
                ArrayList<String> iconList2h = new ArrayList<> ();
                iconList2h.add ("activities/attraction/horse_ride.jpg");
                activitiesList.add (new Activities (1, iconList2h, "Horse Ride", "", imagelist1));
                ArrayList<String> iconList2i = new ArrayList<> ();
                iconList2i.add ("activities/attraction/jwaakful_ride.jpg");
                activitiesList.add (new Activities (1, iconList2i, "Jwaakful Ride", "", imagelist1));
                ArrayList<String> iconList2j = new ArrayList<> ();
                iconList2j.add ("activities/attraction/kids_atv_ride.jpg");
                activitiesList.add (new Activities (1, iconList2j, "Kids ATV", "", imagelist1));
                ArrayList<String> iconList2k = new ArrayList<> ();
                iconList2k.add ("activities/attraction/kids_car_ride.jpg");
                activitiesList.add (new Activities (1, iconList2k, "Kids Car Ride", "", imagelist1));
                ArrayList<String> iconList2l = new ArrayList<> ();
                iconList2l.add ("activities/attraction/trampoline.jpg");
                activitiesList.add (new Activities (1, iconList2l, "Trampoline", "", imagelist1));
                ArrayList<String> iconList2m = new ArrayList<> ();
                iconList2m.add ("activities/attraction/virtual_reality.jpg");
                activitiesList.add (new Activities (1, iconList2m, "Virtual Reality", "", imagelist1));
                ArrayList<String> iconList2n = new ArrayList<> ();
                iconList2n.add ("activities/attraction/zorb_ball.jpg");
                activitiesList.add (new Activities (1, iconList2n, "Zorb Ball", "", imagelist1));
                break;
            case 3:
                ArrayList<String> iconList3a = new ArrayList<> ();
                iconList3a.add ("activities/haat_bazar/bapu_di_hatti.jpg");
                activitiesList.add (new Activities (1, iconList3a, "Bapu Di Hatti", "You can get Famous Punjabi Papad Wadia, Phulkari and Punjabi Suits etc.", imagelist1));
                ArrayList<String> iconList3b = new ArrayList<> ();
                iconList3b.add ("activities/haat_bazar/punjab_handlooms.jpg");
                activitiesList.add (new Activities (1, iconList3b, "Punjabi Handloom", "Towels, Bed sheets, Handmade Ladies Bags, Handkerchief, cushion and cushion covers etc.", imagelist1));
                ArrayList<String> iconList3c = new ArrayList<> ();
                iconList3c.add ("activities/haat_bazar/punjabi_virsa.jpg");
                activitiesList.add (new Activities (1, iconList3c, "Punjabi Virsa", "You can get all kind of Punjabi symbols like Kirpan, Khanda, Kanga, Golden Temple Picture, Punjabi Kada and Key Chain etc.", imagelist1));
                ArrayList<String> iconList3d = new ArrayList<> ();
                iconList3d.add ("activities/haat_bazar/shaguna_di_phulkari.jpg");
                activitiesList.add (new Activities (1, iconList3d, "Shaguna Di Phulkari", "You can get all kind of Punjabi Suits, Phulkari, Phulkari Jacket, Phulkari Shirt Piece, Pure Cotton Dupatta and Jaipuri Dupatta etc.", imagelist1));
                ArrayList<String> iconList3e = new ArrayList<> ();
                iconList3e.add ("activities/haat_bazar/sr_gifts_shop.jpg");
                activitiesList.add (new Activities (1, iconList3e, "SR Gifts Shop", "Deals in Artificial Jewellery and Incense Sticks (Agarbatti) etc.", imagelist1));
                ArrayList<String> iconList3f = new ArrayList<> ();
                iconList3f.add ("activities/haat_bazar/virasat_punjabi_zutti.jpg");
                activitiesList.add (new Activities (1, iconList3f, "Virasat Punjabi Zutti", "All kind of Punjabi Jutti specially designed.", imagelist1));
                break;
            case 4:
                ArrayList<String> iconList4a = new ArrayList<> ();
                iconList4a.add ("activities/musuems/toshakhana.jpg");
                activitiesList.add (new Activities (1, iconList4a, "Toshakhana", "The Royal treasury of Maharaja Ranjit Singh\nIt is believed that Maharaja Ranjit Singh kept a treasure worth Rs. 30 lakhs (A Kingly sum then) and precious jewels as well as gold and silver in the fort under a guard of 2000 soldiers. It is here that the Kohinoor was housed. They say no jeweller has so far been able to assess the price of the Koh-i-noor diamond. Coins of that period along with a replica of the Kohinoor can also be seen here.",imagelist1));
                ArrayList<String> iconList4b = new ArrayList<> ();
                iconList4b.add ("activities/musuems/war_musuem.jpg");
                activitiesList.add (new Activities (1, iconList4b, "War Museum", "See the rare instruments of war from the old times- including a replica of the Maharaja’s personal sword and his very own war attire. A specially made replica of the celebrated 14.3 ft. big Bhangian di Tope or The Zamzama- the largest canon of its time has been recreated specially for your viewing.", imagelist1));
                break;
            case 5:
                ArrayList<String> iconList5a = new ArrayList<> ();
                iconList5a.add ("activities/shows/kanda_boldiyan.jpg");
                activitiesList.add (new Activities (1, iconList5a, "Kanda Boldiyan - Whispering Walls", "https://www.youtube.com/watch?v=Tqblu_dhXow", imagelist1));
                ArrayList<String> iconList5b = new ArrayList<> ();
                iconList5b.add ("activities/shows/sher_e_punjab.jpg");
                activitiesList.add (new Activities (1, iconList5b, "Sher-e-Punjab", "https://www.youtube.com/watch?v=xuxV9FiciSk", imagelist1));
                break;
        }
    }
}
