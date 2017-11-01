package com.karman.fort.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.karman.fort.R;
import com.karman.fort.utils.Utils;

public class MainActivity extends AppCompatActivity {
    TextView tvTitle;
    TextView tvActivities;
    TextView tvAboutUs;
    TextView tvBeginTour;
    
    
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        initView ();
        initData ();
        initListener ();
    }
    
    private void initView () {
        tvTitle = (TextView) findViewById (R.id.tvTitle);
        tvActivities = (TextView) findViewById (R.id.tvActivities);
        tvAboutUs = (TextView) findViewById (R.id.tvAboutUs);
        tvBeginTour = (TextView) findViewById (R.id.tvBeginTour);
    }
    
    private void initData () {
        Utils.setTypefaceToAllViews (this, tvTitle);
    }
    
    private void initListener () {
        tvAboutUs.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent (MainActivity.this, AboutUsActivity.class);
                startActivity (intent);
                overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        tvActivities.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent (MainActivity.this, ActivitiesActivity.class);
                startActivity (intent);
                overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        tvBeginTour.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
//                new MaterialDialog.Builder (MainActivity.this)
//                        .content ("Enter OTP")
//                        .inputType (InputType.TYPE_CLASS_NUMBER)
//                        .typeface (SetTypeFace.getTypeface (MainActivity.this), SetTypeFace.getTypeface (MainActivity.this))
//                        .positiveText ("SUBMIT")
//                        .input ("", "", false, new MaterialDialog.InputCallback () {
//                            @Override
//                            public void onInput (@NonNull MaterialDialog dialog, CharSequence input) {
//                                if (input.toString ().length () == 6 && input.toString ().equalsIgnoreCase ("123456")) {
//                                    dialog.dismiss ();
//                                    new MaterialDialog.Builder (MainActivity.this)
//                                            .title ("Please select a Language")
//                                            .typeface (SetTypeFace.getTypeface (MainActivity.this), SetTypeFace.getTypeface (MainActivity.this))
//                                            .items (R.array.select_language)
//                                            .itemsCallback (new MaterialDialog.ListCallback () {
//                                                @Override
//                                                public void onSelection (MaterialDialog dialog, View itemView, int position, CharSequence text) {
//                                                    Intent intent = new Intent (MainActivity.this, BeginTourActivity.class);
//                                                    intent.putExtra ("language", text);
//                                                    startActivity (intent);
//                                                    overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
//                                                }
//                                            })
//                                            .show ();
//                                } else {
//                                    Utils.showToast (MainActivity.this, "OTP not mach", false);
//                                }
//                            }
//                        })
//                        .show ();
//
//
                Intent intent = new Intent (MainActivity.this, BeginTourActivity.class);
                intent.putExtra ("language", getResources ().getStringArray (R.array.select_language)[0]);
                startActivity (intent);
                overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
    
            }
        });
    }
    
    
}
