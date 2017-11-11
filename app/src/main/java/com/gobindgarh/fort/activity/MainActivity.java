package com.gobindgarh.fort.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gobindgarh.fort.R;
import com.gobindgarh.fort.utils.AppConfigTags;
import com.gobindgarh.fort.utils.AppConfigURL;
import com.gobindgarh.fort.utils.NetworkConnection;
import com.gobindgarh.fort.utils.SetTypeFace;
import com.gobindgarh.fort.utils.UserDetailsPref;
import com.gobindgarh.fort.utils.Utils;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView tvTitle;
    TextView tvActivities;
    TextView tvAboutUs;
    TextView tvBeginTour;
    CoordinatorLayout clMain;
    ProgressDialog progressDialog;
    
    UserDetailsPref userDetailsPref;
    
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
        clMain = (CoordinatorLayout) findViewById (R.id.clMain);
    }
    
    private void initData () {
        progressDialog = new ProgressDialog (this);
        Utils.setTypefaceToAllViews (this, tvTitle);
        userDetailsPref = UserDetailsPref.getInstance ();
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
                Calendar c = Calendar.getInstance ();
                SimpleDateFormat df = new SimpleDateFormat ("yyyy-MM-dd", Locale.US);
                if (userDetailsPref.getBooleanPref (MainActivity.this, UserDetailsPref.OTP_VERIFIED) &&
                        userDetailsPref.getStringPref (MainActivity.this, UserDetailsPref.OTP_VERIFIED_DATE).equalsIgnoreCase (df.format (c.getTime ()))) {
                    new MaterialDialog.Builder (MainActivity.this)
                            .title ("Please select a Language")
                            .typeface (SetTypeFace.getTypeface (MainActivity.this), SetTypeFace.getTypeface (MainActivity.this))
                            .items (R.array.select_language)
                            .itemsCallback (new MaterialDialog.ListCallback () {
                                @Override
                                public void onSelection (MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                    Intent intent = new Intent (MainActivity.this, BeginTourActivity.class);
                                    intent.putExtra ("language", text);
                                    startActivity (intent);
                                    overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
                                }
                            })
                            .show ();
                } else {
//                    if (userDetailsPref.getBooleanPref (MainActivity.this, UserDetailsPref.OTP_VERIFIED)) {
//                    } else {
                    new MaterialDialog.Builder (MainActivity.this)
                            .content ("Enter OTP")
                            .inputType (InputType.TYPE_CLASS_NUMBER)
                            .typeface (SetTypeFace.getTypeface (MainActivity.this), SetTypeFace.getTypeface (MainActivity.this))
                            .positiveText ("SUBMIT")
                            .input ("", "", false, new MaterialDialog.InputCallback () {
                                @Override
                                public void onInput (@NonNull MaterialDialog dialog, CharSequence input) {
                                    if (input.toString ().length () == 6) {
                                        verifyOTP (input.toString ());
                                        dialog.dismiss ();
                                    } else {
                                        Utils.showToast (MainActivity.this, "OTP not mach", false);
                                    }
                                }
                            })
                            .show ();
//                    }
                }


//                Intent intent = new Intent (MainActivity.this, BeginTourActivity.class);
//                intent.putExtra ("language", getResources ().getStringArray (R.array.select_language)[0]);
//                startActivity (intent);
//                overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
    
            }
        });
    }
    
    
    private void verifyOTP (String otp) {
        if (NetworkConnection.isNetworkAvailable (this)) {
            Utils.showProgressDialog (progressDialog, getResources ().getString (R.string.progress_dialog_text_please_wait), true);
            Utils.showLog (Log.INFO, AppConfigTags.URL, AppConfigURL.URL_VERIFY_OTP + otp, true);
            StringRequest strRequest = new StringRequest (Request.Method.GET, AppConfigURL.URL_VERIFY_OTP + otp,
                    new Response.Listener<String> () {
                        @Override
                        public void onResponse (String response) {
                            Utils.showLog (Log.INFO, AppConfigTags.SERVER_RESPONSE, response, true);
                            progressDialog.dismiss ();
                            if (response != null) {
                                try {
                                    JSONObject jsonObj = new JSONObject (response);
                                    boolean is_error = jsonObj.getBoolean (AppConfigTags.ERROR);
                                    String message = jsonObj.getString (AppConfigTags.MESSAGE);
                                    if (! is_error) {
                                        Calendar c = Calendar.getInstance ();
                                        SimpleDateFormat df = new SimpleDateFormat ("yyyy-MM-dd", Locale.US);
                                        userDetailsPref.putStringPref (MainActivity.this, UserDetailsPref.OTP_VERIFIED_DATE, df.format (c.getTime ()));
                                        userDetailsPref.putBooleanPref (MainActivity.this, UserDetailsPref.OTP_VERIFIED, true);
                                        
                                        new MaterialDialog.Builder (MainActivity.this)
                                                .title ("Please select a Language")
                                                .typeface (SetTypeFace.getTypeface (MainActivity.this), SetTypeFace.getTypeface (MainActivity.this))
                                                .items (R.array.select_language)
                                                .itemsCallback (new MaterialDialog.ListCallback () {
                                                    @Override
                                                    public void onSelection (MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                                        Intent intent = new Intent (MainActivity.this, BeginTourActivity.class);
                                                        intent.putExtra ("language", text);
                                                        startActivity (intent);
                                                        overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
                                                    }
                                                })
                                                .show ();
                                    } else {
                                        userDetailsPref.putBooleanPref (MainActivity.this, UserDetailsPref.OTP_VERIFIED, false);
                                        Utils.showSnackBar (MainActivity.this, clMain, message, Snackbar.LENGTH_LONG, null, null);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace ();
                                    Utils.showSnackBar (MainActivity.this, clMain, getResources ().getString (R.string.snackbar_text_exception_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                                }
                            } else {
                                Utils.showSnackBar (MainActivity.this, clMain, getResources ().getString (R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                                Utils.showLog (Log.WARN, AppConfigTags.SERVER_RESPONSE, AppConfigTags.DIDNT_RECEIVE_ANY_DATA_FROM_SERVER, true);
                            }
                        }
                    },
                    new Response.ErrorListener () {
                        @Override
                        public void onErrorResponse (VolleyError error) {
                            progressDialog.dismiss ();
                            Utils.showLog (Log.ERROR, AppConfigTags.VOLLEY_ERROR, error.toString (), true);
                            NetworkResponse response = error.networkResponse;
                            if (response != null && response.data != null) {
                                Utils.showLog (Log.ERROR, AppConfigTags.ERROR, new String (response.data), true);
                            }
                            Utils.showSnackBar (MainActivity.this, clMain, getResources ().getString (R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                        }
                    }) {
                
                @Override
                public Map<String, String> getHeaders () throws AuthFailureError {
                    Map<String, String> params = new HashMap<> ();
                    Utils.showLog (Log.INFO, AppConfigTags.HEADERS_SENT_TO_THE_SERVER, "" + params, false);
                    return params;
                }
            };
            Utils.sendRequest (strRequest, 5);
        } else {
            Utils.showSnackBar (MainActivity.this, clMain, getResources ().getString (R.string.snackbar_text_no_internet_connection_available), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_go_to_settings), new View.OnClickListener () {
                @Override
                public void onClick (View v) {
                    Intent dialogIntent = new Intent (Settings.ACTION_SETTINGS);
                    dialogIntent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity (dialogIntent);
                }
            });
        }
    }
}
