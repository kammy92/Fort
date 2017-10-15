package com.karman.fort.service;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.karman.fort.utils.UserDetailsPref;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName ();

    @Override
    public void onTokenRefresh () {
        super.onTokenRefresh ();
        UserDetailsPref userDetailsPref = UserDetailsPref.getInstance ();
        userDetailsPref.putStringPref (getApplicationContext (), UserDetailsPref.USER_FIREBASE_ID, FirebaseInstanceId.getInstance ().getToken ());
    }
}

