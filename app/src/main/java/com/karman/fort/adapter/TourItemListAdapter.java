package com.karman.fort.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.karman.fort.R;
import com.karman.fort.model.TourItem;
import com.karman.fort.utils.CustomImageSlider;
import com.karman.fort.utils.SetTypeFace;
import com.karman.fort.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class TourItemListAdapter extends RecyclerView.Adapter<TourItemListAdapter.ViewHolder> {
    OnItemClickListener mItemClickListener;
    private Activity activity;
    private List<TourItem> tourItemList = new ArrayList<> ();
    
    public TourItemListAdapter (Activity activity, List<TourItem> tourItemList) {
        this.activity = activity;
        this.tourItemList = tourItemList;
    }
    
    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from (parent.getContext ());
        final View sView = mInflater.inflate (R.layout.list_item_tour_item, parent, false);
        return new ViewHolder (sView);
    }
    
    @Override
    public void onBindViewHolder (final ViewHolder holder, int position) {//        runEnterAnimation (holder.itemView);
        final TourItem tourItem = tourItemList.get (position);
        
        Utils.setTypefaceToAllViews (activity, holder.tvSliderPosition);
        
        
        if (tourItem.getDescription ().length () > 0) {
            holder.ivInfo.setVisibility (View.VISIBLE);
        } else {
            holder.ivInfo.setVisibility (View.GONE);
        }
        
        holder.tvTitle.setText (tourItem.getTitle ());
        
        holder.ivInfo.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                if (tourItem.getDescription ().contains ("youtube.com")){
//                    Intent applicationIntent = new Intent (Intent.ACTION_VIEW, Uri.parse ("vnd.youtube:" + id));
                    Intent browserIntent = new Intent (Intent.ACTION_VIEW, Uri.parse (tourItem.getDescription ()));
//                    try {
//                        activity.startActivity (applicationIntent);
//                    } catch (ActivityNotFoundException ex) {
                        activity.startActivity (browserIntent);
//                    }
                } else {
                    MaterialDialog dialog = new MaterialDialog.Builder (activity)
                            .alwaysCallInputCallback ()
                            .content (tourItem.getDescription ())
                            .canceledOnTouchOutside (true)
                            .positiveText ("OK")
                            .typeface (SetTypeFace.getTypeface (activity), SetTypeFace.getTypeface (activity))
                            .onPositive (new MaterialDialog.SingleButtonCallback () {
                                @Override
                                public void onClick (@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    
                                    dialog.dismiss ();
                                }
                            }).build ();
                    dialog.show ();
                }
            }
        });
        
        
        if (tourItem.getIconList ().size () > 1 || tourItem.getImageList ().size () > 1) {
            holder.slider.removeAllSliders ();
            if (tourItem.getImageList ().size () > 0) {
                holder.tvSliderPosition.setText ("1 of " + tourItem.getImageList ().size ());
                holder.rlSliderIndicator.setVisibility (View.VISIBLE);
                for (int i = 0; i < tourItem.getImageList ().size (); i++) {
                    String image = tourItem.getImageList ().get (i);
                    CustomImageSlider slider = new CustomImageSlider (activity);
                    slider
                            .image (image)
                            .setScaleType (BaseSliderView.ScaleType.CenterCrop)
                            .setOnSliderClickListener (new BaseSliderView.OnSliderClickListener () {
                                @Override
                                public void onSliderClick (BaseSliderView slider) {
                                }
                            });
                    holder.slider.addSlider (slider);
                }
                holder.slider.getPagerIndicator ().setVisibility (View.GONE);
                holder.slider.setPresetTransformer (SliderLayout.Transformer.Default);
                holder.slider.setCustomAnimation (new DescriptionAnimation ());
                holder.slider.setDuration (15000);
                holder.slider.addOnPageChangeListener (new ViewPagerEx.OnPageChangeListener () {
                    @Override
                    public void onPageScrolled (int position, float positionOffset, int positionOffsetPixels) {
                    }
                    
                    @Override
                    public void onPageSelected (int position) {
                        holder.tvSliderPosition.setText ((position + 1) + " of " + tourItem.getImageList ().size ());
                        holder.tvSliderPosition.setVisibility (View.VISIBLE);
                    }
                    
                    @Override
                    public void onPageScrollStateChanged (int state) {
                    }
                });
                holder.slider.setPresetIndicator (SliderLayout.PresetIndicators.Center_Bottom);
                
            } else {
                holder.tvSliderPosition.setText ("1 of " + tourItem.getIconList ().size ());
                holder.rlSliderIndicator.setVisibility (View.VISIBLE);
                for (int i = 0; i < tourItem.getIconList ().size (); i++) {
                    int image = tourItem.getIconList ().get (i);
                    CustomImageSlider slider = new CustomImageSlider (activity);
                    slider
                            .image (image)
                            .setScaleType (BaseSliderView.ScaleType.CenterCrop)
                            .setOnSliderClickListener (new BaseSliderView.OnSliderClickListener () {
                                @Override
                                public void onSliderClick (BaseSliderView slider) {
                                }
                            });
                    holder.slider.addSlider (slider);
                }
                holder.slider.getPagerIndicator ().setVisibility (View.GONE);
                holder.slider.setPresetTransformer (SliderLayout.Transformer.Default);
                holder.slider.setCustomAnimation (new DescriptionAnimation ());
                holder.slider.setDuration (15000);
                holder.slider.addOnPageChangeListener (new ViewPagerEx.OnPageChangeListener () {
                    @Override
                    public void onPageScrolled (int position, float positionOffset, int positionOffsetPixels) {
                    }
                    
                    @Override
                    public void onPageSelected (int position) {
                        holder.tvSliderPosition.setText ((position + 1) + " of " + tourItem.getIconList ().size ());
                        holder.rlSliderIndicator.setVisibility (View.VISIBLE);
                    }
                    
                    @Override
                    public void onPageScrollStateChanged (int state) {
                    }
                });
                holder.slider.setPresetIndicator (SliderLayout.PresetIndicators.Center_Bottom);
            }
        } else {
            holder.slider.setVisibility (View.GONE);
            holder.ivTourItemImage.setVisibility (View.VISIBLE);
            holder.rlSliderIndicator.setVisibility (View.GONE);
            if (tourItem.getIconList ().size () > 0) {
                holder.ivTourItemImage.setImageResource (tourItem.getIconList ().get (0));
            } else {
                Picasso.with (activity).load (tourItem.getImageList ().get (0)).into (holder.ivTourItemImage);
            }
        }
        
        
    }
    
    @Override
    public int getItemCount () {
        return tourItemList.size ();
    }
    
    public void SetOnItemClickListener (final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    
    public interface OnItemClickListener {
        public void onItemClick (View view, int position);
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        SliderLayout slider;
        ImageView ivTourItemImage;
        TextView tvSliderPosition;
        TextView tvTitle;
        ImageView ivInfo;
        RelativeLayout rlSliderIndicator;
        
        public ViewHolder (View view) {
            super (view);
            tvSliderPosition = (TextView) view.findViewById (R.id.tvSliderPosition);
            ivTourItemImage = (ImageView) view.findViewById (R.id.ivTourItemImage);
            slider = (SliderLayout) view.findViewById (R.id.slider);
            rlSliderIndicator = (RelativeLayout) view.findViewById (R.id.rlSliderIndicator);
            tvTitle = (TextView) view.findViewById (R.id.tvTitle);
            ivInfo = (ImageView) view.findViewById (R.id.ivInfo);
            view.setOnClickListener (this);
        }
        
        @Override
        public void onClick (View v) {
        }
    }
}
