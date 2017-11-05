package com.karman.fort.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.karman.fort.model.Activities;
import com.karman.fort.utils.CustomImageSlider;
import com.karman.fort.utils.SetTypeFace;
import com.karman.fort.utils.Utils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class ActivityListAdapter extends RecyclerView.Adapter<ActivityListAdapter.ViewHolder> {
    OnItemClickListener mItemClickListener;
    private Activity activity;
    private List<Activities> activitiesList = new ArrayList<> ();
    
    public ActivityListAdapter (Activity activity, List<Activities> activitiesList) {
        this.activity = activity;
        this.activitiesList = activitiesList;
    }
    
    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from (parent.getContext ());
        final View sView = mInflater.inflate (R.layout.list_item_activity, parent, false);
        return new ViewHolder (sView);
    }
    
    @Override
    public void onBindViewHolder (final ViewHolder holder, int position) {//        runEnterAnimation (holder.itemView);
        final Activities activities = activitiesList.get (position);
        
        Utils.setTypefaceToAllViews (activity, holder.tvSliderPosition);
        
        
        if (activities.getDescription ().length () > 0) {
            holder.ivInfo.setVisibility (View.VISIBLE);
        } else {
            holder.ivInfo.setVisibility (View.GONE);
        }
        
        holder.tvTitle.setText (activities.getTitle ());
        
        holder.ivInfo.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                if (activities.getDescription ().contains ("youtube.com")) {
//                    Intent applicationIntent = new Intent (Intent.ACTION_VIEW, Uri.parse ("vnd.youtube:" + id));
                    Intent browserIntent = new Intent (Intent.ACTION_VIEW, Uri.parse (activities.getDescription ()));
//                    try {
//                        activity.startActivity (applicationIntent);
//                    } catch (ActivityNotFoundException ex) {
                    activity.startActivity (browserIntent);
//                    }
                } else {
                    MaterialDialog dialog = new MaterialDialog.Builder (activity)
                            .alwaysCallInputCallback ()
                            .content (activities.getDescription ())
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
        
        
        if (activities.getIconList ().size () > 1 || activities.getImageList ().size () > 1) {
            holder.slider.removeAllSliders ();
            if (activities.getImageList ().size () > 0) {
                holder.tvSliderPosition.setText ("1 of " + activities.getImageList ().size ());
                holder.rlSliderIndicator.setVisibility (View.VISIBLE);
                for (int i = 0; i < activities.getImageList ().size (); i++) {
                    String image = activities.getImageList ().get (i);
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
                        holder.tvSliderPosition.setText ((position + 1) + " of " + activities.getImageList ().size ());
                        holder.tvSliderPosition.setVisibility (View.VISIBLE);
                    }
                    
                    @Override
                    public void onPageScrollStateChanged (int state) {
                    }
                });
                holder.slider.setPresetIndicator (SliderLayout.PresetIndicators.Center_Bottom);
                
            } else {
                holder.tvSliderPosition.setText ("1 of " + activities.getIconList ().size ());
                holder.rlSliderIndicator.setVisibility (View.VISIBLE);
                for (int i = 0; i < activities.getIconList ().size (); i++) {
                    CustomImageSlider slider = new CustomImageSlider (activity);
                    slider
                            .image (createFileFromInputStream (activities.getIconList ().get (i)))
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
                        holder.tvSliderPosition.setText ((position + 1) + " of " + activities.getIconList ().size ());
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
            holder.ivActivityImage.setVisibility (View.VISIBLE);
            holder.rlSliderIndicator.setVisibility (View.GONE);
            if (activities.getIconList ().size () > 0) {
                try {
                    holder.ivActivityImage.setImageDrawable (Drawable.createFromStream (activity.getAssets ().open (activities.getIconList ().get (0)), null));
                } catch (IOException ex) {
                    return;
                }
            } else {
                Picasso.with (activity).load (activities.getImageList ().get (0)).into (holder.ivActivityImage);
            }
        }
        
        
    }
    
    @Override
    public int getItemCount () {
        return activitiesList.size ();
    }
    
    public void SetOnItemClickListener (final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    
    private File createFileFromInputStream (String file_name) {
        try {
            InputStream is = activity.getAssets ().open (file_name);
            File f = new File (activity.getCacheDir () + "test.jpg");
            OutputStream outputStream = new FileOutputStream (f);
            byte buffer[] = new byte[1024];
            int length = 0;
            
            while ((length = is.read (buffer)) > 0) {
                outputStream.write (buffer, 0, length);
            }
            
            outputStream.close ();
            is.close ();
            return f;
        } catch (IOException e) {
            //Logging exception
        }
        return null;
    }
    
    public interface OnItemClickListener {
        void onItemClick (View view, int position);
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        SliderLayout slider;
        ImageView ivActivityImage;
        TextView tvSliderPosition;
        TextView tvTitle;
        ImageView ivInfo;
        RelativeLayout rlSliderIndicator;
        
        public ViewHolder (View view) {
            super (view);
            tvSliderPosition = view.findViewById (R.id.tvSliderPosition);
            ivActivityImage = view.findViewById (R.id.ivActivityImage);
            slider = view.findViewById (R.id.slider);
            rlSliderIndicator = view.findViewById (R.id.rlSliderIndicator);
            tvTitle = view.findViewById (R.id.tvTitle);
            ivInfo = view.findViewById (R.id.ivInfo);
            view.setOnClickListener (this);
        }
        
        @Override
        public void onClick (View v) {
        }
    }
}
