package com.karman.fort.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
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
    
    private void initData() {
        Utils.setTypefaceToAllViews (this, tvTitle);
    }
    
    private  void initListener(){
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
                new MaterialDialog.Builder (MainActivity.this)
                        .content ("Enter OTP")
                        .inputType (InputType.TYPE_CLASS_NUMBER)
                        .positiveText ("SUBMIT")
                        .input ("", "", false, new MaterialDialog.InputCallback () {
                            @Override
                            public void onInput (@NonNull MaterialDialog dialog, CharSequence input) {
                                if (input.toString ().length ()==6 && input.toString ().equalsIgnoreCase ("123456")){
                                    Intent intent = new Intent (MainActivity.this, BeginTourActivity.class);
                                    startActivity (intent);
                                    overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
                                }else{
                                    Utils.showToast (MainActivity.this, "OTP not mach", false);
                                }
                            }
                        })
                        .show ();
            }
        });
    }
    
    
}
