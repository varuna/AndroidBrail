package com.shahanp.invisibletouch.internal;

import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.view.View;

import com.shahanp.invisibletouch.utils.Log;

public class LicenceAgreementActivity extends SinglePackActivity {

    public final static String LICENCE_PREFERENCE = "com.shahanp.invisibletouch.internal.licenceagreement.LICENCE_PREFERENCE";
    public final static String LICENCE_PREF_KEY = "com.shahanp.invisibletouch.internal.licenceagreement.LICENCE_PREF_KEY";

    @Override
    protected void init() {
        super.init();
        setViewText("Licence","Register with appzone.lk. Registration charges Rs. 1 + tax.");
    }

    @Override
    protected void onAttachView(int id, View view) {

    }

    @Override
    public void onKeyOne() {

    }

    @Override
    public void onLongKeyOne() {

    }

    @Override
    public void onSwipeRight() {
        SharedPreferences init = getSharedPreferences(LICENCE_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor initEdit = init.edit();
        initEdit.putBoolean(LICENCE_PREF_KEY, true);
        initEdit.apply();
        initEdit.commit();
        finish();
        sendRegistrationSMS();
        Log.announce("Registered with Etisalat",false);
    }

    @Override
    public void onSwipeLeft() {
        finish();
    }

    @Override
    public void onDoubleSwipeRight() {

    }

    @Override
    public void onDoubleSwipeLeft() {

    }

    @Override
    public void onSwipeUp() {

    }

    @Override
    public void onDoubleSwipeUp() {

    }

    @Override
    public void onSwipeDown() {

    }

    @Override
    public void onDoubleSwipeDown() {

    }

    @Override
    public void onVolumeDownKeyShortPress() {

    }

    @Override
    public void onVolumeDownKeyLongPress() {

    }

    @Override
    public void onVolumeUpKeyShortPress() {

    }

    @Override
    public void onVolumeUpKeyLongPress() {

    }

    @Override
    public void onCameraKeyShortPress() {

    }

    @Override
    public void onCameraKeyLongPress() {

    }

    @Override
    public void onScreenLongPress() {

    }

    private void sendRegistrationSMS() {
        if (InvisibleTouchApplication.PRODUCTION) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("4499", null, "REG SS", null, null);
        }
    }
}
