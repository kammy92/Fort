package com.karman.fort.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.karman.fort.R;
import com.karman.fort.utils.CustomImageSlider;
import com.karman.fort.utils.Utils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class TourDetailDialogFragment extends DialogFragment {
    CoordinatorLayout clMain;
    
    ImageView ivClose;
    TextView tvTitle;
    ImageView ivTourImage;
    TextView tvTourTitle;
    TextView tvTourDescription;
    SliderLayout slider;
    RelativeLayout rlSliderIndicator;
    TextView tvSliderPosition;
    SeekBar seekBar;
    ImageView ivPlayPause;
    TextView tvPlayTime;
    
    ArrayList<String> iconList = new ArrayList<> ();
    ArrayList<String> imageList = new ArrayList<> ();
    String title;
    String description;
    String audio_uri;
    
    MediaPlayer mediaPlayer;
    Handler seekHandler = new Handler ();
    private Runnable moveSeekBarThread = new Runnable () {
        public void run () {
            int mediaPos_new = mediaPlayer.getCurrentPosition ();
            int mediaMax_new = mediaPlayer.getDuration ();
//            if (mediaPlayer.getCurrentPosition () == mediaPlayer.getDuration ()){
//                    seekBar.setMax (mediaMax_new);
//                    seekBar.setProgress (0);
//                    seekHandler.postDelayed (this, 100);
//                    ivPlayPause.setImageResource (R.drawable.ic_play);
//                    tvPlayTime.setText ("00:00" +
//                            "/" +
//                            String.format (Locale.US, "%02d:%02d",
//                                    TimeUnit.MILLISECONDS.toMinutes (mediaMax_new) -
//                                            TimeUnit.HOURS.toMinutes (TimeUnit.MILLISECONDS.toHours (mediaMax_new)), // The change is in this line
//                                    TimeUnit.MILLISECONDS.toSeconds (mediaMax_new) -
//                                            TimeUnit.MINUTES.toSeconds (TimeUnit.MILLISECONDS.toMinutes (mediaMax_new))));
//            }
            
            if (mediaPlayer.isPlaying ()) {
                
                seekBar.setMax (mediaMax_new);
                seekBar.setProgress (mediaPos_new);
                seekHandler.postDelayed (this, 100);
                
                tvPlayTime.setText (String.format (Locale.US, "%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes (mediaPos_new) -
                                TimeUnit.HOURS.toMinutes (TimeUnit.MILLISECONDS.toHours (mediaPos_new)), // The change is in this line
                        TimeUnit.MILLISECONDS.toSeconds (mediaPos_new) -
                                TimeUnit.MINUTES.toSeconds (TimeUnit.MILLISECONDS.toMinutes (mediaPos_new))) +
                        "/" +
                        String.format (Locale.US, "%02d:%02d",
                                TimeUnit.MILLISECONDS.toMinutes (mediaMax_new) -
                                        TimeUnit.HOURS.toMinutes (TimeUnit.MILLISECONDS.toHours (mediaMax_new)), // The change is in this line
                                TimeUnit.MILLISECONDS.toSeconds (mediaMax_new) -
                                        TimeUnit.MINUTES.toSeconds (TimeUnit.MILLISECONDS.toMinutes (mediaMax_new))));
            }
        }
    };
    
    public TourDetailDialogFragment newInstance (ArrayList<String> iconList, String title, String description, String audio_uri, ArrayList<String> imageList) {
        TourDetailDialogFragment f = new TourDetailDialogFragment ();
        Bundle args = new Bundle ();
        args.putString ("title", title);
        args.putString ("description", description);
        args.putString ("audio_uri", audio_uri);
        args.putStringArrayList ("icon_list", iconList);
        args.putStringArrayList ("image_list", imageList);
        f.setArguments (args);
        return f;
    }
    
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setStyle (DialogFragment.STYLE_NORMAL, R.style.AppTheme);
    }
    
    @Override
    public void onActivityCreated (Bundle arg0) {
        super.onActivityCreated (arg0);
        Window window = getDialog ().getWindow ();
        window.getAttributes ().windowAnimations = R.style.DialogAnimation;
        window.addFlags (WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
    
    @Override
    public void onStart () {
        super.onStart ();
        Dialog d = getDialog ();
        if (d != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow ().setLayout (width, height);
        }
    }
    
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate (R.layout.fragment_dialog_tour_detail, container, false);
        initView (root);
        initBundle ();
        initData ();
        initListener ();
        return root;
    }
    
    private void initView (View root) {
        ivClose = root.findViewById (R.id.ivClose);
        tvTitle = root.findViewById (R.id.tvTitle);
        tvTourTitle = root.findViewById (R.id.tvTourTitle);
        tvTourDescription = root.findViewById (R.id.tvTourDescription);
        ivTourImage = root.findViewById (R.id.ivTourImage);
        slider = root.findViewById (R.id.slider);
        clMain = root.findViewById (R.id.clMain);
        rlSliderIndicator = root.findViewById (R.id.rlSliderIndicator);
        tvSliderPosition = root.findViewById (R.id.tvSliderPosition);
        seekBar = root.findViewById (R.id.seekBar);
        ivPlayPause = root.findViewById (R.id.ivPlayPause);
        tvPlayTime = root.findViewById (R.id.tvPlayTime);
    }
    
    private void initBundle () {
        Bundle bundle = this.getArguments ();
        imageList = bundle.getStringArrayList ("image_list");
        iconList = bundle.getStringArrayList ("icon_list");
        title = bundle.getString ("title");
        description = bundle.getString ("description");
        audio_uri = bundle.getString ("audio_uri");
    }
    
    private void initData () {
        Utils.setTypefaceToAllViews (getActivity (), tvTitle);
        tvTourTitle.setText (title);
        tvTourDescription.setText (description);
        
        
        mediaPlayer = new MediaPlayer ();
        try {
            AssetFileDescriptor afd = getActivity ().getAssets ().openFd (audio_uri);
            mediaPlayer.setDataSource (afd.getFileDescriptor (), afd.getStartOffset (), afd.getLength ());
            afd.close ();
            mediaPlayer.prepare ();
            seekBar.setMax (mediaPlayer.getDuration ());
        } catch (IOException e) {
            e.printStackTrace ();
        }
        
        seekBar.setMax (mediaPlayer.getDuration ()); // Set the Maximum range of the
        seekBar.setProgress (mediaPlayer.getCurrentPosition ());// set current progress to song's
        
        
        tvPlayTime.setText (String.format (Locale.US, "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes (mediaPlayer.getCurrentPosition ()) -
                        TimeUnit.HOURS.toMinutes (TimeUnit.MILLISECONDS.toHours (mediaPlayer.getCurrentPosition ())), // The change is in this line
                TimeUnit.MILLISECONDS.toSeconds (mediaPlayer.getCurrentPosition ()) -
                        TimeUnit.MINUTES.toSeconds (TimeUnit.MILLISECONDS.toMinutes (mediaPlayer.getCurrentPosition ()))) +
                "/" +
                String.format (Locale.US, "%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes (mediaPlayer.getDuration ()) -
                                TimeUnit.HOURS.toMinutes (TimeUnit.MILLISECONDS.toHours (mediaPlayer.getDuration ())), // The change is in this line
                        TimeUnit.MILLISECONDS.toSeconds (mediaPlayer.getDuration ()) -
                                TimeUnit.MINUTES.toSeconds (TimeUnit.MILLISECONDS.toMinutes (mediaPlayer.getDuration ()))));
        
        
        if (iconList.size () > 1 || imageList.size () > 1) {
            slider.removeAllSliders ();
            if (imageList.size () > 0) {
                tvSliderPosition.setText ("1 of " + imageList.size ());
                rlSliderIndicator.setVisibility (View.VISIBLE);
                for (int i = 0; i < imageList.size (); i++) {
                    String image = imageList.get (i);
                    CustomImageSlider slider2 = new CustomImageSlider (getActivity ());
                    slider2
                            .image (image)
                            .setScaleType (BaseSliderView.ScaleType.CenterCrop)
                            .setOnSliderClickListener (new BaseSliderView.OnSliderClickListener () {
                                @Override
                                public void onSliderClick (BaseSliderView slider) {
                                }
                            });
                    slider.addSlider (slider2);
                }
                slider.getPagerIndicator ().setVisibility (View.GONE);
                slider.setPresetTransformer (SliderLayout.Transformer.Default);
                slider.setCustomAnimation (new DescriptionAnimation ());
                slider.setDuration (15000);
                slider.addOnPageChangeListener (new ViewPagerEx.OnPageChangeListener () {
                    @Override
                    public void onPageScrolled (int position, float positionOffset, int positionOffsetPixels) {
                    }
                    
                    @Override
                    public void onPageSelected (int position) {
                        tvSliderPosition.setText ((position + 1) + " of " + imageList.size ());
                        tvSliderPosition.setVisibility (View.VISIBLE);
                    }
                    
                    @Override
                    public void onPageScrollStateChanged (int state) {
                    }
                });
                slider.setPresetIndicator (SliderLayout.PresetIndicators.Center_Bottom);
                
            } else {
                tvSliderPosition.setText ("1 of " + iconList.size ());
                rlSliderIndicator.setVisibility (View.VISIBLE);
                for (int i = 0; i < iconList.size (); i++) {
                    CustomImageSlider slider2 = new CustomImageSlider (getActivity ());
                    slider2
                            .image (createFileFromInputStream (iconList.get (i)))
                            .setScaleType (BaseSliderView.ScaleType.CenterCrop)
                            .setOnSliderClickListener (new BaseSliderView.OnSliderClickListener () {
                                @Override
                                public void onSliderClick (BaseSliderView slider) {
                                }
                            });
                    slider.addSlider (slider2);
                }
                slider.getPagerIndicator ().setVisibility (View.GONE);
                slider.setPresetTransformer (SliderLayout.Transformer.Default);
                slider.setCustomAnimation (new DescriptionAnimation ());
                slider.setDuration (15000);
                slider.addOnPageChangeListener (new ViewPagerEx.OnPageChangeListener () {
                    @Override
                    public void onPageScrolled (int position, float positionOffset, int positionOffsetPixels) {
                    }
                    
                    @Override
                    public void onPageSelected (int position) {
                        tvSliderPosition.setText ((position + 1) + " of " + iconList.size ());
                        rlSliderIndicator.setVisibility (View.VISIBLE);
                    }
                    
                    @Override
                    public void onPageScrollStateChanged (int state) {
                    }
                });
                slider.setPresetIndicator (SliderLayout.PresetIndicators.Center_Bottom);
            }
        } else {
            slider.setVisibility (View.GONE);
            ivTourImage.setVisibility (View.VISIBLE);
            rlSliderIndicator.setVisibility (View.GONE);
            if (iconList.size () > 0) {
                try {
                    ivTourImage.setImageDrawable (Drawable.createFromStream (getActivity ().getAssets ().open (iconList.get (0)), null));
                } catch (IOException ex) {
                    return;
                }
            } else {
                Picasso.with (getActivity ()).load (imageList.get (0)).into (ivTourImage);
            }
        }
    }
    
    private void initListener () {
        ivClose.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                getDialog ().dismiss ();
            }
        });
        ivTourImage.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                
            }
        });
        
        seekBar.setOnSeekBarChangeListener (new SeekBar.OnSeekBarChangeListener () {
            @Override
            public void onProgressChanged (SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == mediaPlayer.getDuration ()) {
                    mediaPlayer.seekTo (0);
                    seekBar.setMax (mediaPlayer.getDuration ());
                    seekBar.setProgress (0);
                    seekHandler.postDelayed (moveSeekBarThread, 100);
                    ivPlayPause.setImageResource (R.drawable.ic_play);
                    tvPlayTime.setText ("00:00" +
                            "/" +
                            String.format (Locale.US, "%02d:%02d",
                                    TimeUnit.MILLISECONDS.toMinutes (mediaPlayer.getDuration ()) -
                                            TimeUnit.HOURS.toMinutes (TimeUnit.MILLISECONDS.toHours (mediaPlayer.getDuration ())), // The change is in this line
                                    TimeUnit.MILLISECONDS.toSeconds (mediaPlayer.getDuration ()) -
                                            TimeUnit.MINUTES.toSeconds (TimeUnit.MILLISECONDS.toMinutes (mediaPlayer.getDuration ()))));
                    
                }
                if (mediaPlayer != null && fromUser) {
                    seekHandler.removeCallbacks (moveSeekBarThread);
                    seekHandler.postDelayed (moveSeekBarThread, 100);
                    mediaPlayer.seekTo (progress);
                    
                    if (mediaPlayer.isPlaying ()) {
                        mediaPlayer.start ();
                    } else {
                        mediaPlayer.pause ();
                    }
                    
                    tvPlayTime.setText (String.format (Locale.US, "%02d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes (progress) -
                                    TimeUnit.HOURS.toMinutes (TimeUnit.MILLISECONDS.toHours (progress)), // The change is in this line
                            TimeUnit.MILLISECONDS.toSeconds (progress) -
                                    TimeUnit.MINUTES.toSeconds (TimeUnit.MILLISECONDS.toMinutes (progress))) +
                            "/" +
                            String.format (Locale.US, "%02d:%02d",
                                    TimeUnit.MILLISECONDS.toMinutes (mediaPlayer.getDuration ()) -
                                            TimeUnit.HOURS.toMinutes (TimeUnit.MILLISECONDS.toHours (mediaPlayer.getDuration ())), // The change is in this line
                                    TimeUnit.MILLISECONDS.toSeconds (mediaPlayer.getDuration ()) -
                                            TimeUnit.MINUTES.toSeconds (TimeUnit.MILLISECONDS.toMinutes (mediaPlayer.getDuration ()))));
                    
                }
            }
            
            @Override
            public void onStartTrackingTouch (SeekBar seekBar) {
            }
            
            @Override
            public void onStopTrackingTouch (SeekBar seekBar) {
                
            }
        });

//        if (! mediaPlayer.isPlaying ()) {
//            mediaPlayer.start ();
//        } else {
//            mediaPlayer.pause ();
//        }
        
        
        ivPlayPause.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                seekHandler.removeCallbacks (moveSeekBarThread);
                seekHandler.postDelayed (moveSeekBarThread, 100);
                if (! mediaPlayer.isPlaying ()) {
                    ivPlayPause.setImageResource (R.drawable.ic_pause);
                    mediaPlayer.start ();
                } else {
                    ivPlayPause.setImageResource (R.drawable.ic_play);
                    mediaPlayer.pause ();
                }
            }
        });
    }
    
    @Override
    public void onPause () {
        super.onPause ();
        mediaPlayer.pause ();
    }
    
    @Override
    public void onDestroy () {
        super.onDestroy ();
        seekHandler.removeCallbacks (moveSeekBarThread);
        mediaPlayer.stop ();
        mediaPlayer.release ();
    }
    
    private File createFileFromInputStream (String file_name) {
        try {
            InputStream is = getActivity ().getAssets ().open (file_name);
            File f = new File (getActivity ().getCacheDir () + "test.jpg");
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
}