package com.nfg.devlot.dehari.Helpers;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseInstance extends FirebaseInstanceIdService {


    public static String token = "";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();


        token = FirebaseInstanceId.getInstance().getToken();


        Log.d("[*] FCM Access Token:", token);
    }
}
